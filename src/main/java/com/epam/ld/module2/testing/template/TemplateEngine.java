package com.epam.ld.module2.testing.template;

import com.epam.ld.module2.testing.Client;
import com.epam.ld.module2.testing.ConsoleScanner;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.stream.Collectors;

/**
 * The type Template engine.
 */
public class TemplateEngine {

    private final ConsoleScanner consoleScanner;

    public TemplateEngine() {
        this.consoleScanner = new ConsoleScanner(new Scanner(System.in));
    }

    /**
     * Generate message string.
     *
     * @param template the template
     * @param client   the client
     * @return the string
     */
    public String generateMessage(Template template, Client client) {
        Map<String, String> mapValues = mapValues(template);
        return replaceKeysToValues(template, mapValues);
    }

    public Map<String, String> mapValues(Template template) {
        List<String> keys = Arrays.stream(template.getTemplate().split(" ")).filter(s -> s.contains("#{")).map(s -> s.replace("#{", "").replace("}", "")).collect(Collectors.toList());
        Map<String, String> result = new HashMap<>(keys.size());
        keys.forEach(e -> {
            System.out.println(e + " = ");
            String currentValue = consoleScanner.readNextConsoleLine();
            validateValue(result, e, currentValue);
        });
        return result;
    }

    public void validateValue(Map<String, String> result, String e, String currentValue) {
        if (!currentValue.isEmpty()) {
            result.put(e, currentValue);
        } else {
            throw new RuntimeException("No values for massage");
        }
    }

    public String replaceKeysToValues(Template template, Map<String, String> map) {
        List<String> list = Arrays.asList(template.getTemplate().split(" "));
        map.forEach((key, value) -> {
            if (list.contains("#{" + key + "}")) {
                Collections.replaceAll(list, "#{" + key + "}", value);
            }
        });
        return String.join(" ", list);
    }
}
