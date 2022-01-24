package com.epam.ld.module2.testing.template;

import com.epam.ld.module2.testing.Client;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.doReturn;

public class TemplateEngineTest {

    private TemplateEngine templateEngine;
    private Client client;

    @BeforeEach
    public void init() {
        this.templateEngine = new TemplateEngine();
        this.client = Mockito.mock(Client.class);
    }

    @Test
    public void shouldGenerateMessage() {
        Client clientSpy = Mockito.spy(Client.class);
        doReturn("Lviv 15").when(clientSpy).getAddresses();
        assertEquals("Hi! Lviv 15", templateEngine.generateMessage(new Template(), clientSpy));

    }

    @Test
    public void shouldThrowExceptionWhenGenerateMessage() {
        Client clientException = Mockito.mock(Client.class);
        assertThrows(RuntimeException.class, () -> templateEngine.generateMessage(new Template(), clientException));
    }

    @Test
    public void shouldReturnValidValue() {
        Mockito.when(client.getAddresses()).thenReturn("{0}");
        assertEquals("Hi! {0}", templateEngine.generateMessage(new Template(), client));
    }

    @Test
    public void shouldSupportLatin1Character() {
        Mockito.when(client.getAddresses()).thenReturn("Æ");
        assertEquals("Hi! Æ", templateEngine.generateMessage(new Template(), client));
    }
}
