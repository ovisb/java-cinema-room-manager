package cinema;

class ValidateInput {

    private final Cinema cinema;

    public ValidateInput(Cinema cinema) {
        this.cinema = cinema;
    }

    public boolean validateSeatRow( int row, int seat) {
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
        return cinema.getCinema()[row][seat] == 'B';
    }

    private boolean isSeatRowInvalid(int row, int seat) {
        return (row >= 0 && seat >= 0) && (row <= cinema.getNumberOfRows() - 1 && seat <= cinema.getNumberOfSeats() - 1);
    }
}
