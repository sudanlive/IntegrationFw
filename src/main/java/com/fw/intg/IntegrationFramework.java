package com.fw.intg;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fw.intg.IntegrationConfig.Integration;

import jakarta.xml.ws.Service;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import javax.xml.namespace.QName;
import java.net.URL;
import java.util.Map;
import java.io.File;
import java.lang.reflect.Field;

public class IntegrationFramework {

    private static final String CONFIG_PATH = "/Users/sudan/Documents/GitHub/IntegrationFw/src/main/java/com/payment_app/intg/config.json";
    private IntegrationConfig config;

    public IntegrationFramework() {
        try {
            this.config = loadConfig(CONFIG_PATH);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    private IntegrationConfig loadConfig(String path) throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.readValue(new File(path), IntegrationConfig.class);
    }

    public Object execute(String integrationName, Object requestDto) throws Exception {
        Integration integration = config.getIntegrationByName(integrationName);
        if (integration == null) {
            throw new IllegalArgumentException("Integration not found: " + integrationName);
        }

        switch (integration.getType()) {
            case "SDK":
                return executeSdk(integration, requestDto);
            case "REST":
                return executeRestWithDto(integration, requestDto);
            case "SOAP":
                return executeSoapWithDto(integration, requestDto);
            default:
                throw new IllegalArgumentException("Unsupported integration type: " + integration.getType());
        }
    }

    private Object executeSdk(Integration integration, Object requestDto) throws Exception {
        // Create SDK request object
        Class<?> sdkRequestDtoClass = Class.forName(integration.getSdkRequestDto());
        Object sdkRequest = sdkRequestDtoClass.getDeclaredConstructor().newInstance();
    
        // Parse request field mapping from config.json
        Map<String, Map<String, String>> requestFieldMapping = integration.getRequestFieldMapping();
    
        // Map fields from app request DTO to SDK request DTO
        FieldMapper.mapFields(requestDto, sdkRequest, requestFieldMapping);
    
        // Load SDK class and method
        Class<?> sdkClass = Class.forName(integration.getSdkClass());
        Object sdkInstance = sdkClass.getDeclaredConstructor().newInstance();
    
        // Call SDK method
        Object sdkResponse = sdkClass.getMethod(integration.getMethod(), sdkRequestDtoClass).invoke(sdkInstance, sdkRequest);
    
        // Create app response object
        Class<?> responseDtoClass = Class.forName(integration.getResponseDto());
        Object appResponse = responseDtoClass.getDeclaredConstructor().newInstance();
    
        // Parse response field mapping from config.json
        Map<String, Map<String, String>> responseFieldMapping = integration.getResponseFieldMapping();
    
        // Map fields from SDK response DTO to app response DTO
        FieldMapper.mapFields(sdkResponse, appResponse, responseFieldMapping);
    
        return appResponse;
    }
    

    private Object executeRestWithDto(Integration integration, Object requestDto) throws Exception {
        RestTemplate restTemplate = new RestTemplate();
        ObjectMapper objectMapper = new ObjectMapper();
        
        // Convert request DTO to JSON
        String jsonRequest = objectMapper.writeValueAsString(requestDto);

        // Build headers
        HttpHeaders headers = new HttpHeaders();
        if (integration.getHeaders() != null) {
            integration.getHeaders().forEach(headers::set);
        }

        HttpEntity<String> entity = new HttpEntity<>(jsonRequest, headers);

        // Make the REST call
        ResponseEntity<String> response = restTemplate.exchange(
                integration.getSwaggerUrl() + integration.getEndpoint(),
                HttpMethod.valueOf(integration.getHttpMethod()),
                entity,
                String.class
        );

        // Parse the JSON response
        JsonNode rootNode = objectMapper.readTree(response.getBody());

        // Create and populate the response DTO
        Class<?> responseDtoClass = Class.forName(integration.getResponseDto());
        Object responseDto = responseDtoClass.getDeclaredConstructor().newInstance();

        // Use responseMapping from configuration
        Map<String, String> responseMapping = integration.getResponseMapping();
        for (Map.Entry<String, String> entry : responseMapping.entrySet()) {
            String dtoField = entry.getValue(); // Field in the DTO
            String jsonPath = entry.getKey(); // JSON path from the response

            // Extract value from JSON using the JSON path
            JsonNode valueNode = rootNode.at("/"+jsonPath.replace('.', '/'));
            Field field = responseDtoClass.getDeclaredField(dtoField);
            field.setAccessible(true);

            // Populate the DTO field based on its type
            if (field.getType().equals(int.class)) {
                field.set(responseDto, valueNode.asInt());
            } else {
                field.set(responseDto, valueNode.asText());
            }
        }

        return responseDto;
    }

    /*
    private Object executeRestWithDto(Integration integration, Object requestDto) throws Exception {
        RestTemplate restTemplate = new RestTemplate();
        ObjectMapper objectMapper = new ObjectMapper();

        // Convert request DTO to JSON
        String jsonRequest = objectMapper.writeValueAsString(requestDto);

        // Build headers
        HttpHeaders headers = new HttpHeaders();
        if (integration.getHeaders() != null) {
            integration.getHeaders().forEach(headers::set);
        }
        headers.setContentType(MediaType.APPLICATION_JSON);

        // Create the request entity
        HttpEntity<String> entity = new HttpEntity<>(jsonRequest, headers);

        // Convert the HTTP method string to HttpMethod enum
        HttpMethod httpMethod = HttpMethod.valueOf(integration.getHttpMethod().toUpperCase());

        // Make the REST call
        ResponseEntity<String> response = restTemplate.exchange(
                integration.getSwaggerUrl() + integration.getEndpoint(),
                httpMethod,  // Use HttpMethod enum here
                entity,
                String.class
        );

        // Deserialize the response JSON into the response DTO
        Class<?> responseDtoClass = Class.forName(integration.getResponseDto());
        return objectMapper.readValue(response.getBody(), responseDtoClass);
    } 
    */

    private Object executeSoapWithDto(Integration integration, Object requestDto) throws Exception {
        URL wsdlURL = new URL(integration.getWsdlUrl());
        QName serviceName = new QName(integration.getNamespace(), integration.getServiceName());
        QName portName = new QName(integration.getNamespace(), integration.getPortName());
        Service service = Service.create(wsdlURL, serviceName);
        Object port = service.getPort(Class.forName(integration.getPortInterface()));

        Object response = port.getClass()
                .getMethod(integration.getOperation(), Class.forName(integration.getRequestDto()))
                .invoke(port, requestDto);

        Class<?> responseDtoClass = Class.forName(integration.getResponseDto());
        if (!responseDtoClass.isInstance(response)) {
            throw new IllegalArgumentException("Invalid response type from SOAP method");
        }

        return response;
    }
}