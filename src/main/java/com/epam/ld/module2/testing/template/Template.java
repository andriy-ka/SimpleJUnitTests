package com.epam.ld.module2.testing.template;


/**
 * The type Template.
 */
public class Template {
    private final String template;

    public Template() {
        this.template = "Hi! #{name} this is your code: #{value} and your address: #{address}";
    }

    public String getTemplate() {
        return template;
    }

}
