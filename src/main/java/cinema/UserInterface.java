package cinema;

class UserInterface {
    private final InputReader inputReader;
    private final Cinema cinemaRoom;

    private boolean continueLoop = true;

    UserInterface(InputReader inputReader, Cinema cinemaRoom) {
        this.inputReader = inputReader;
        this.cinemaRoom = cinemaRoom;
    }

    public void start() {
        while (this.continueLoop) {
            printMenu();

            int choice = inputReader.getNumber("");

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

    private void buyTicket() {
        int row;
        int seat;

        do {
            row = inputReader.getNumber("Enter a row number: ") - 1;
            seat = inputReader.getNumber("Enter a seat number in that row: ") - 1;

        } while (!(cinemaRoom.validateSeatRow(row, seat)));

        cinemaRoom.makePurchase(row, seat);
    }

    private void menuSelect(int choice) {
        switch (choice) {
            case 0:
                continueLoop = false;
                break;
            case 1:
                System.out.println(cinemaRoom);
                System.out.println();
                break;
            case 2:
                buyTicket();
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
