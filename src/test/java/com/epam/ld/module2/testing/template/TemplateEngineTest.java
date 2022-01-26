package com.epam.ld.module2.testing.template;

import com.epam.ld.module2.testing.Client;
import org.jetbrains.annotations.NotNull;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DynamicTest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestFactory;
import org.junit.jupiter.api.condition.EnabledForJreRange;
import org.junit.jupiter.api.condition.JRE;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;

import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;

@EnabledForJreRange(min = JRE.JAVA_8, max = JRE.JAVA_11)
public class TemplateEngineTest {

    private TemplateEngine templateEngine;
    private Template template;
    private Client client;
    private Map<String, String> map;

    @BeforeEach
    public void init() {
        this.template = new Template();
        this.templateEngine = Mockito.spy(TemplateEngine.class);
        this.client = Mockito.mock(Client.class);
        this.map = getDefaultMap();
    }

    // Dynamic tests
    // Meta annotation "Fast"
    @Fast
    @TestFactory
    Collection<DynamicTest> dynamicTests() {
        return Arrays.asList(
                DynamicTest.dynamicTest("Add test",
                        () -> assertEquals(2, Math.addExact(1, 1))),
                DynamicTest.dynamicTest("Multiply Test",
                        () -> assertEquals(4, Math.multiplyExact(2, 2))));
    }

    @Test
    public void shouldGenerateMessage() {
        templateEngine = Mockito.mock(TemplateEngine.class);

        // Partial Mock
        doReturn(map).when(templateEngine).mapValues(template);
        when(templateEngine.replaceKeysToValues(template, map)).thenCallRealMethod();
        when(templateEngine.generateMessage(template, client)).thenCallRealMethod();

        assertEquals("Hi! Andriy this is your code: 33 and your address: Lviv", templateEngine.generateMessage(template, client));

    }

    // Custom extension
    @ExtendWith(CustomExtension.class)
    @Test
    public void shouldThrowExceptionIfAnyValueEmpty() {
        map.put("name", "");

        doThrow(RuntimeException.class).when(templateEngine).validateValue(map, "name", "");
        // ExpectedException
        assertThrows(RuntimeException.class, () -> templateEngine.generateMessage(template, client));
    }

    @Test
    public void shouldReturnValidValue() {
        map.put("name", "#{tag}");

        doReturn(map).when(templateEngine).mapValues(template);

        assertEquals("Hi! #{tag} this is your code: 33 and your address: Lviv", templateEngine.generateMessage(template, client));
    }

    @Test
    public void shouldSupportLatin1Character() {
        map.put("name", "Æ");

        doReturn(map).when(templateEngine).mapValues(template);

        assertEquals("Hi! Æ this is your code: 33 and your address: Lviv", templateEngine.generateMessage(template, client));
    }

    @NotNull
    private Map<String, String> getDefaultMap() {
        Map<String, String> mapForMapValues = new HashMap<>();
        mapForMapValues.put("name", "Andriy");
        mapForMapValues.put("value", "33");
        mapForMapValues.put("address", "Lviv");
        return mapForMapValues;
    }
}
