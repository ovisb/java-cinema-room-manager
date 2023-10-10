package cinema;

import java.util.Scanner;

public class Cinema {

    public static void generate_seats(int rows) {
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
        for (int i=0; i < rows; i++) {
            System.out.print(i == 0 ? "  ": i + " ");
            for (int j=1; j <= rows; j++) {
                System.out.print(i == 0 ? j + " ": "S" + " ");
            }
            System.out.println();
        }
    }

    public static void calculate_profit(int numberOfRows, int numberOfSeats) {
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

    public static int get_number(String message) {
        Scanner input = new Scanner(System.in);
        System.out.println(message);

        int number = input.nextInt();
        return number;
    }

    public static void main(String[] args) {
        // Write your code here

        int numberOfRows = get_number("Enter the number of rows:");
        int numberOfSeats = get_number("Enter the number of seats in each row:");
        calculate_profit(numberOfRows, numberOfSeats);
    }
}