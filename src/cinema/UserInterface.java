package cinema;

import java.util.Scanner;

public class UserInterface {
    private final Scanner scanner;
    private final Cinema cinemaRoom;

    private boolean continueLoop = true;

    public UserInterface(Scanner scanner, Cinema cinemaRoom) {
        this.scanner = scanner;
        this.cinemaRoom = cinemaRoom;
    }

    public void start() {
        while (this.continueLoop) {
            printMenu();

            int choice =scanner.nextInt();

            menuSelect(choice);
        }
    }

    private void printMenu() {
        System.out.println("""
        1. Show the seats
        2. Buy a ticket
        3. Statistics
        0. Exit
        """);
    }

    public void menuSelect(int choice) {
        switch (choice) {
            case 0:
                continueLoop = false;
                break;
            case 1:
                System.out.println(cinemaRoom);
                System.out.println();
                break;
            case 2:
                cinemaRoom.buyTicket();
                System.out.println();
                break;
            case 3:
                cinemaRoom.showStatistics();
                System.out.println();
                break;
            default:
                System.out.println("Option not available.");
        }
    }

}
