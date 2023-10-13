package cinema;

import java.util.Arrays;
import java.util.Scanner;

public class Cinema {

    public static int purchasedTickets = 0;
    public static int currentIncome = 0;
    public static double purchasedTicketsAsPercent = 0.00;

    /**
     * Friendly print of cinema room available/unavailable seats
     * @param arr 2D cinema room array
     */
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

    /**
     *
     * Calculate total possible profit based on total number of seats
     * @param numberOfRows number of cinema room rows
     * @param numberOfSeats number of cinema room seats
     * @return total profit
     */
    public static int calculateProfit(int numberOfRows, int numberOfSeats) {
        int totalNumberOfSeats = numberOfSeats * numberOfRows;
        int totalProfit;
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
        return totalProfit;
    }

    /**
     * Get price of ticket based on row selection
     * @param numberOfRows number of cinema room rows
     * @param numberOfSeats number of cinema room seats
     * @param selectRow selected row choice
     * @return ticket price
     */
    public static int getTicketPrice(int numberOfRows, int numberOfSeats, int selectRow) {
        int ticketPrice;
        if (numberOfRows * numberOfSeats < 60) {
            ticketPrice = 10;
        } else {
            int firstHalfRow = numberOfRows / 2;
            ticketPrice = (numberOfRows % 2 == 0 ? firstHalfRow >= selectRow : firstHalfRow > selectRow) ? 10: 8;
        }
        return ticketPrice;
    }

    /**
     * Get number of rows or seats based on message
     * @param message string message for row or seat
     * @return row/seat
     */
    public static int getNumber(String message) {
        Scanner input = new Scanner(System.in);
        System.out.println(message);

        return input.nextInt();
    }

    /**
     * Generate cinema room based of number of rows and seats
     * @param numberOfRows number of cinema room rows
     * @param numberOfSeats number of cinema room seats
     * @return 2D Array[row][seat]
     */
    public static char[][] generateCinema(int numberOfRows, int numberOfSeats) {
        char[][] cinema = new char[numberOfRows][numberOfSeats];

        for (char[] chars : cinema) {
            Arrays.fill(chars, 'S');
        }
        return cinema;
    }

    /**
     * Book seat in specific row
     * @param cinema cinema room array
     * @param row selected row choice
     * @param seat selected seat choice
     */
    public static void updateSeat(char[][] cinema, int row, int seat) {
        cinema[row][seat] = 'B';
    }

    /**
     * Check if seat is taken
     * @param cinema cinema room array
     * @param row selected row choice
     * @param seat selected seat choice
     * @return true if seat taken else false
     */
    public static boolean isSeatTaken(char[][] cinema, int row, int seat) {
        return cinema[row][seat] == 'B';
    }

    /**
     * Check if seat and row are valid selections based on current cinema room
     * @param row selected row choice
     * @param seat selected seat choice
     * @param numberOfRows number of cinema room rows
     * @param numberOfSeats number of cinema room seats
     * @return true if valid else false
     */
    public static boolean isSeatRowInvalid(int row, int seat, int numberOfRows, int numberOfSeats) {
        return (row >= 0 && seat >= 0) && (row <= numberOfRows - 1 && seat <= numberOfSeats - 1);
    }

    /**
     * Buy cinema ticket
     * @param cinema cinema room array
     * @param numberOfRows number of cinema room rows
     * @param numberOfSeats number of cinema room seats
     */
    public static void buyTicket(char[][] cinema, int numberOfRows, int numberOfSeats) {
        boolean valid = false;
        int row;
        int seat;

        do {
            row = getNumber("Enter a row number: ") - 1;
            seat = getNumber("Enter a seat number in that row: ") - 1;

            if (!isSeatRowInvalid(row, seat, numberOfRows, numberOfSeats)) {
                System.out.println("Wrong input!\n");
                continue;
            }

            if (isSeatTaken(cinema, row, seat)) {
                System.out.println("That ticket has already been purchased\n");
            } else {
                valid = true;
            }

        } while (!valid);

        int maxSeats = numberOfSeats * numberOfRows;

        int ticketPrice = getTicketPrice(numberOfRows, numberOfSeats, row);
        updateSeat(cinema, row, seat);

        System.out.printf("Ticket price: $%d%n", ticketPrice);
        purchasedTickets += 1;
        currentIncome += ticketPrice;
        purchasedTicketsAsPercent = ((double) purchasedTickets / maxSeats) * 100;
    }

    /**
     * Main menu choice selection
     * @param cinema cinema room array
     * @param choice selected menu choice number
     * @param numberOfRows number of cinema room rows
     * @param numberOfSeats number of cinema room seats
     */
    public static void menuSelect(char[][] cinema, int choice, int numberOfRows, int numberOfSeats) {
        switch (choice) {
            case 0: break;
            case 1: printCinema(cinema);
                break;
            case 2: buyTicket(cinema, numberOfRows, numberOfSeats);
                break;
            case 3: showStatistics(numberOfRows, numberOfSeats);
                break;
            default:
                System.out.println("Option not available.");
        }
    }

    /**
     * Print available menu
     */
    public static void printMenu() {
        System.out.println("""
                1. Show the seats
                2. Buy a ticket
                3. Statistics
                0. Exit
                """);
    }

    /**
     * Show current cinema statistics
     */
    public static void showStatistics(int numberOfRows, int numberOfSeats) {
        System.out.printf("Number of purchased tickets: %d%n", purchasedTickets);
        System.out.printf("Percentage: %.2f%%%n", purchasedTicketsAsPercent);
        System.out.printf("Current income: $%d%n", currentIncome);
        System.out.printf("Total income: $%d%n", calculateProfit(numberOfRows, numberOfSeats));
    }

    public static void main(String[] args) {
        // Write your code here
        Scanner scanner = new Scanner(System.in);

        int numberOfRows = getNumber("Enter the number of rows:");
        int numberOfSeats = getNumber("Enter the number of seats in each row:");

        final char[][] arr = generateCinema(numberOfRows, numberOfSeats);

        int choice = -1;
        while (choice != 0) {
            System.out.println();
            printMenu();
            choice = scanner.nextInt();
            menuSelect(arr, choice, numberOfRows, numberOfSeats);
        }

//        calculate_profit(numberOfRows, numberOfSeats);
    }
}