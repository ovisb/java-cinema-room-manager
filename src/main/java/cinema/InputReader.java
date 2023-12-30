package cinema;

import java.util.Scanner;

public class InputReader {
    private final Scanner scanner;

    InputReader(Scanner scanner) {
        this.scanner = scanner;
    }

    public int getNumber(String message) {
        while (true) {
            try {
                System.out.println(message);
                return scanner.nextInt();
            } catch (Exception e) {
                System.out.println("Please input a number.");
                scanner.next();
            }
        }
    }
}
