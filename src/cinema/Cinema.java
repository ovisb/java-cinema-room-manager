package cinema;

import java.util.Arrays;
import java.util.Scanner;

public class Cinema {

    public static void printCinema(char[][] arr) {
        /*
            Cinema:
              1 2 3 4 5 6 7 8
            1 S S S S S S S S
            2 S S S S S S S S
            3 S S S S S S S S
            4 S S S S S S S S
            5 S S S S S S S S
            6 S S S S S S S S
            7 S S S S S S S S
         */
        System.out.println("Cinema:");
        for (int k = 0; k <= arr[0].length; k++) {
            System.out.print(k == 0 ? "  ": k + " ");
        }
        System.out.println();
        for (int i = 0; i < arr.length; i++) {
            System.out.print(i + 1 + " ");
            for (int j = 0; j < arr[i].length; j++) {
                System.out.print(arr[i][j] + " ");
            }
            System.out.println();
        }
    }

    public static void calculateProfit(int numberOfRows, int numberOfSeats) {
        int totalNumberOfSeats = numberOfSeats * numberOfRows;
        int totalProfit = 0;
        if (totalNumberOfSeats < 60) {
            totalProfit = totalNumberOfSeats * 10;
        }
        else {
            // First half row seat 10$
            // Second half row seat 8$
            int first_half_row = numberOfRows / 2;
            int first_half_profit = first_half_row * numberOfSeats * 10;
            int second_half_profit = (numberOfRows - first_half_row) * numberOfSeats * 8;
            totalProfit = first_half_profit + second_half_profit;
        }
        System.out.println("Total income: ");
        System.out.printf("$%d%n", totalProfit);
    }

    public static int getTicketPrice(int numberOfRows, int numberOfSeats, int selectRow, int selectSeat) {
        int ticketPrice;
        if (numberOfRows * numberOfSeats < 60) {
            ticketPrice = 10;
        } else {
            int first_half_row = numberOfRows / 2;
            ticketPrice = selectRow <= first_half_row ? 10: 8;
        }
        return ticketPrice;
    }

    public static int getNumber(String message) {
        Scanner input = new Scanner(System.in);
        System.out.println(message);

        int number = input.nextInt();
        return number;
    }

    public static char[][] generateCinema(int numberOfRows, int numberOfSeats) {
        char[][] cinema = new char[numberOfRows][numberOfSeats];

        for (int i = 0; i < cinema.length; i++) {
            for (int j = 0; j < cinema[i].length; j++) {
                cinema[i][j] = 'S';
            }
        }

        return cinema;
    }

    public static void updateSeat(char[][] cinema, int row_seat, int seat_number) {
        cinema[row_seat-1][seat_number-1] = 'B';
    }

    public static void buyTicket(char[][] cinema, int numberOfRows, int numberOfSeats) {
        int selectRow = getNumber("Enter a row number: ");
        int selectSeat = getNumber("Enter a seat number in that row: ");

        int ticketPrice = getTicketPrice(numberOfRows, numberOfSeats, selectRow, selectSeat);
        System.out.printf("Ticket price: $%d%n" + "\n", ticketPrice);
        updateSeat(cinema, selectRow, selectSeat);
    }

    public static void mainLoop(char[][] cinema, int choice, int numberOfRows, int numberOfSeats) {
        switch (choice) {
            case 0: break;
            case 1: printCinema(cinema);
                break;
            case 2: buyTicket(cinema, numberOfRows, numberOfSeats);
                break;
            default:
                System.out.println("Option not available. Please select from the available.");
        }
    }

    public static void printMenu() {
        System.out.println("""
                1. Show the seats
                2. Buy a ticket
                0. Exit
                """);
    }

    public static void main(String[] args) {
        // Write your code here
        Scanner scanner = new Scanner(System.in);

        int numberOfRows = getNumber("Enter the number of rows:");
        int numberOfSeats = getNumber("Enter the number of seats in each row:");

        char[][] arr = generateCinema(numberOfRows, numberOfSeats);

        int choice = -1;
        while (choice != 0) {
            printMenu();
            choice = scanner.nextInt();
            mainLoop(arr, choice, numberOfRows, numberOfSeats);
        }

//        calculate_profit(numberOfRows, numberOfSeats);
    }
}