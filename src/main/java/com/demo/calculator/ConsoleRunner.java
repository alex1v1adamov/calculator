package com.demo.calculator;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.text.MessageFormat;
import java.util.Scanner;

@Component
@RequiredArgsConstructor
@Profile("!test")
public class ConsoleRunner implements CommandLineRunner {

    private final CalcService calcService;

    private static String ABORT = "abort";


    @Override
    public void run(String... args) {
        communicateConsole();
    }

    private void communicateConsole() {
        String line = null;
        System.out.println("Enter expression:");
        while (!ABORT.equalsIgnoreCase(line)) {
            Scanner scanner = new Scanner(System.in);
            line = scanner.nextLine();

            String result = calcService.evaluate(line)
                .orElse(MessageFormat.format("Синтаксическая ошибка в выражении: {0}", line));
            System.out.println(result);
        }
    }
}
