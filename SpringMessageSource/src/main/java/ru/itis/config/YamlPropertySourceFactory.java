package ru.itis.config;

import org.springframework.core.env.MapPropertySource;
import org.springframework.core.env.PropertySource;
import org.springframework.core.io.support.EncodedResource;
import org.springframework.core.io.support.PropertySourceFactory;
import org.yaml.snakeyaml.Yaml;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

public class YamlPropertySourceFactory implements PropertySourceFactory {

    @Override
    public PropertySource<?> createPropertySource(String name, EncodedResource resource) throws IOException {
        try (InputStream inputStream = resource.getInputStream()) {
            Yaml yaml = new Yaml();
            Map<String, Object> map = yaml.load(inputStream);
            Map<String, Object> flattenMap = new HashMap<>();
            flattenMap(map, "", flattenMap);
            return new MapPropertySource(resource.getResource().getFilename(), flattenMap);
        } catch (Exception e) {
            throw new RuntimeException("YAML reading error!" + e.getMessage());
        }
    }

    private void flattenMap(Map<String, Object> source, String parentKey, Map<String, Object> result) {
        for (Map.Entry<String, Object> entry : source.entrySet()) {
            String key = parentKey.isEmpty() ? entry.getKey() : parentKey + "." + entry.getKey();
            Object value = entry.getValue();

            if (value instanceof Map) {
                flattenMap((Map<String, Object>) value, key, result);
            } else {
                result.put(key, value);
            }
        }
    }
}
