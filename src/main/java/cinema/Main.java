package cinema;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter the number of rows: ");
        int rows = scanner.nextInt();
        System.out.println("Enter the number of seats in each row: ");
        int seats = scanner.nextInt();

        Cinema cinema = new Cinema(rows, seats);
        InputReader inputReader = new InputReader(scanner);

        UserInterface userInterface = new UserInterface(inputReader, cinema);

        userInterface.start();
    }
}
