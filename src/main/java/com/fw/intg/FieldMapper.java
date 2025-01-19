package com.fw.intg;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Map;

public class FieldMapper {
    public static Object mapFields(Object source, Object target, Map<String, Map<String, String>> fieldMapping) throws Exception {
        Class<?> sourceClass = source.getClass();
        Class<?> targetClass = target.getClass();

        for (Map.Entry<String, Map<String, String>> entry : fieldMapping.entrySet()) {
            String sourceFieldName = entry.getKey();
            Map<String, String> mappingDetails = entry.getValue();
            String targetFieldName = mappingDetails.get("field");
            String mapperMethod = mappingDetails.get("mapperMethod");

            Field sourceField = sourceClass.getDeclaredField(sourceFieldName);
            Field targetField = targetClass.getDeclaredField(targetFieldName);

            sourceField.setAccessible(true);
            targetField.setAccessible(true);

            Object value = sourceField.get(source);

            // Apply custom mapper method if specified
            if (mapperMethod != null && !mapperMethod.isEmpty()) {
                value = invokeCustomMapper(mapperMethod, value);
            }

            targetField.set(target, value);
        }

        return target;
    }

    private static Object invokeCustomMapper(String mapperMethod, Object input) throws Exception {
        // Split the mapper configuration into class and method
        String[] parts = mapperMethod.split("::");
        String className = parts[0];
        String methodName = parts[1];

        // Load the mapper class and method
        Class<?> mapperClass = Class.forName(className);
        Method method = mapperClass.getMethod(methodName, input.getClass());

        // Invoke the mapper method
        return method.invoke(null, input); // Assumes static method
    }
}

