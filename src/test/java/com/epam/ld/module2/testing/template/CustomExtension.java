package com.epam.ld.module2.testing.template;

import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.api.extension.TestInstancePostProcessor;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.util.Date;

public class CustomExtension implements TestInstancePostProcessor {
    @Override
    public void postProcessTestInstance(Object testInstance, ExtensionContext context) throws Exception {
        File myFile = new File("information.txt");
        BufferedWriter writer = new BufferedWriter(new FileWriter(myFile));
        writer.write(String.valueOf(testInstance.getClass()) + new Date());

        writer.close();
    }
}
