package com.epam.ld.module2.testing.template;

import com.epam.ld.module2.testing.Client;

import java.text.MessageFormat;

/**
 * The type Template engine.
 */
public class TemplateEngine {
    /**
     * Generate message string.
     *
     * @param template the template
     * @param client   the client
     * @return the string
     */
    public String generateMessage(Template template, Client client) {
        if (client.getAddresses() == null) {
            throw new RuntimeException("No values for massage");
        }
        return MessageFormat.format(template.getTemplate(), client.getAddresses());
    }
}
