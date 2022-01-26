package com.epam.ld.module2.testing;

import java.util.Scanner;

public class ConsoleScanner {
    private final Scanner scanner;

    public ConsoleScanner(Scanner scanner) {
        this.scanner = scanner;
    }

    public String readNextConsoleLine() {
        return scanner.nextLine();
    }
}
