package com.fw.intg;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.http.HttpMethod;

import java.util.List;
import java.util.Map;
import java.util.Objects;

public class IntegrationConfig {
    @JsonProperty("integrations")
    private List<Integration> integrations;

    public List<Integration> getIntegrations() {
        return integrations;
    }

    public Integration getIntegrationByName(String name) {
        return integrations.stream()
                .filter(i -> i.getName().equals(name))
                .findFirst()
                .orElse(null);  
    }

    public static class Integration {
        private String name;
        private String type; // SDK, REST, SOAP
        private String sdkClass;
        private String method;
        private Map<String, Object> sdkConfig;
        private String swaggerUrl;
        private String endpoint;
        private String httpMethod;
        private Map<String, String> headers;
        private String requestDto;
        private String responseDto;
        private String wsdlUrl;
        private String namespace;
        private String serviceName;
        private String portName;
        private String portInterface;
        private String operation;
        private String sdkRequestDto;
        private String sdkResponseDto;
        private Map<String, Map<String, String>> requestFieldMapping;
        private Map<String, Map<String, String>> responseFieldMapping;
        private Map<String, String> responseMapping;

        public String getName() {
            return this.name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getType() {
            return this.type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getSdkClass() {
            return this.sdkClass;
        }

        public void setSdkClass(String sdkClass) {
            this.sdkClass = sdkClass;
        }

        public String getMethod() {
            return this.method;
        }

        public void setMethod(String method) {
            this.method = method;
        }

        public Map<String,Object> getSdkConfig() {
            return this.sdkConfig;
        }

        public void setSdkConfig(Map<String,Object> sdkConfig) {
            this.sdkConfig = sdkConfig;
        }

        public String getSwaggerUrl() {
            return this.swaggerUrl;
        }

        public void setSwaggerUrl(String swaggerUrl) {
            this.swaggerUrl = swaggerUrl;
        }

        public String getEndpoint() {
            return this.endpoint;
        }

        public void setEndpoint(String endpoint) {
            this.endpoint = endpoint;
        }

        public String getHttpMethod() {
            return this.httpMethod;
        }

        public void setHttpMethod(String httpMethod) {
            this.httpMethod = httpMethod;
        }

        public Map<String,String> getHeaders() {
            return this.headers;
        }

        public void setHeaders(Map<String,String> headers) {
            this.headers = headers;
        }

        public String getRequestDto() {
            return this.requestDto;
        }

        public void setRequestDto(String requestDto) {
            this.requestDto = requestDto;
        }

        public String getResponseDto() {
            return this.responseDto;
        }

        public void setResponseDto(String responseDto) {
            this.responseDto = responseDto;
        }

        public String getWsdlUrl() {
            return this.wsdlUrl;
        }

        public void setWsdlUrl(String wsdlUrl) {
            this.wsdlUrl = wsdlUrl;
        }

        public String getNamespace() {
            return this.namespace;
        }

        public void setNamespace(String namespace) {
            this.namespace = namespace;
        }

        public String getServiceName() {
            return this.serviceName;
        }

        public void setServiceName(String serviceName) {
            this.serviceName = serviceName;
        }

        public String getPortName() {
            return this.portName;
        }

        public void setPortName(String portName) {
            this.portName = portName;
        }

        public String getPortInterface() {
            return this.portInterface;
        }

        public void setPortInterface(String portInterface) {
            this.portInterface = portInterface;
        }

        public String getOperation() {
            return this.operation;
        }

        public void setOperation(String operation) {
            this.operation = operation;
        }  
        
        public void setRequestFieldMapping(Map<String, Map<String, String>> requestFieldMapping) {
            this.requestFieldMapping = requestFieldMapping;
        }

        public Map<String, Map<String, String>> getRequestFieldMapping() {
            return requestFieldMapping;
        }

        public void setResponseFieldMapping(Map<String, Map<String, String>> responseFieldMapping) {
            this.responseFieldMapping = responseFieldMapping;
        }

        public Map<String, Map<String, String>> getResponseFieldMapping() {
            return responseFieldMapping;
        }

        public void setResponseMapping(Map<String, String> responseMapping) {
            this.responseMapping = responseMapping;
        }

        public Map<String, String> getResponseMapping() {
            return responseMapping;
        }

        public void setSdkRequestDto(String sdkRequestDto) {
            this.sdkRequestDto = sdkRequestDto;
        }

        public String getSdkRequestDto() {
            return this.sdkRequestDto;
        }

        public void setSdkResponseDto(String sdkResponseDto) {
            this.sdkResponseDto = sdkResponseDto;
        }

        public String getSdkResponseDto() {
            return this.sdkResponseDto;
        }
    }

}

