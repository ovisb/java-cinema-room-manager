package cinema;

import java.util.Arrays;
import java.util.Scanner;

public class Cinema {

    private final int numberOfRows;
    private final int numberOfSeats;

    private final char[][] cinema;
    private int purchasedTickets = 0;
    private int currentIncome = 0;
    private double purchasedTicketsAsPercent = 0.00;

    public Cinema(int numberOfRows, int numberOfSeats) {
        this.numberOfRows = numberOfRows;
        this.numberOfSeats = numberOfSeats;
        this.cinema = new char[numberOfRows][numberOfSeats];
        generateCinema();
    }

    private void generateCinema() {
        for (char[] chars : this.cinema) {
            Arrays.fill(chars, 'S');
        }
    }

    private int getNumber(String message) {
        Scanner input = new Scanner(System.in);
        System.out.println(message);

        return input.nextInt();
    }

    public void buyTicket() {
        int row;
        int seat;

        do {
            row = getNumber("Enter a row number: ") - 1;
            seat = getNumber("Enter a seat number in that row: ") - 1;

        } while (!validateSeatRow(row, seat));

        makePurchase(row, seat);
    }

    private boolean validateSeatRow(int row, int seat) {
        if (!isSeatRowInvalid(row, seat)) {
            System.out.println("Wrong input!\n");
            return false;
        }

        if (isSeatTaken(row, seat)) {
            System.out.println("That ticket has already been purchased\n");
            return false;
        }

        return true;
    }

    private boolean isSeatTaken(int row, int seat) {
        return this.cinema[row][seat] == 'B';
    }

    private boolean isSeatRowInvalid(int row, int seat) {
        return (row >= 0 && seat >= 0) && (row <= this.numberOfRows - 1 && seat <= this.numberOfSeats - 1);
    }

    private void makePurchase(int row, int seat) {

        int ticketPrice = calculateTicketPrice(row);
        updateSeat(row, seat);

        System.out.printf("Ticket price: $%d%n", ticketPrice);
        purchasedTickets += 1;
        currentIncome += ticketPrice;
        purchasedTicketsAsPercent = ((double) purchasedTickets / totalNumberOfSeats()) * 100;
    }

    private int calculateTicketPrice(int selectRow) {
        int ticketPrice = 10;
        if (totalNumberOfSeats() < 60) {
            return ticketPrice;
        }

        return ticketPriceAfterSeatLimitReached(selectRow);
    }

    private int ticketPriceAfterSeatLimitReached(int selectRow) {
        int firstHalfRow = this.numberOfRows / 2;

        return (this.numberOfRows % 2 == 0 ? firstHalfRow == selectRow : firstHalfRow > selectRow) ? 10: 8;
    }

    private void updateSeat(int row, int seat) {
        this.cinema[row][seat] = 'B';
    }

    public void showStatistics() {
        System.out.printf("Number of purchased tickets: %d%n", this.purchasedTickets);
        System.out.printf("Percentage: %.2f%%%n", this.purchasedTicketsAsPercent);
        System.out.printf("Current income: $%d%n", this.currentIncome);
        System.out.printf("Total income: $%d%n", calculateProfit());
    }

    private int calculateProfit() {

        if (totalNumberOfSeats() < 60) {
            return totalNumberOfSeats() * 10;
        }

        // First half row seat 10$
        // Second half row seat 8$
        return calculateProfitAfterSeatLimit();
    }

    private int totalNumberOfSeats() {
        return this.numberOfSeats * this.numberOfRows;
    }

    private int calculateProfitAfterSeatLimit() {
        int first_half_row = this.numberOfRows / 2;
        int first_half_profit = first_half_row * this.numberOfSeats * 10;
        int second_half_profit = (this.numberOfRows - first_half_row) * this.numberOfSeats * 8;

        return first_half_profit + second_half_profit;
    }

    @Override
    public String toString() {
        StringBuilder out = new StringBuilder();
        System.out.println("Cinema:");
        for (int k = 0; k <= this.cinema[0].length; k++) {
            out.append(k == 0 ? "  ": k + " ");
        }
        out.append("\n");
        for (int i = 0; i < this.cinema.length; i++) {
            out.append(i + 1).append(" ");
            for (int j = 0; j < this.cinema[i].length; j++) {
                out.append(this.cinema[i][j]).append(" ");
            }
            out.append("\n");
        }
        return out.toString();
    }
}