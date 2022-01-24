package com.epam.ld.module2.testing;

import com.epam.ld.module2.testing.template.Template;
import com.epam.ld.module2.testing.template.TemplateEngine;

public class Main {
    public static void main(String[] args) {
        Messenger messenger = new Messenger(new MailServer(), new TemplateEngine());
        Client client = new Client();
        client.setAddresses("Lviv 16");
        messenger.sendMessage(client, new Template());
    }
}
