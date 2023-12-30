package cinema;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class CinemaTest {

    private Cinema cinema;

    @BeforeEach
    void setUp() {
        cinema = new Cinema(3, 3);
    }

    @Test
    void shouldInitializeWithCorrectRowsAndSeats() {
        cinema = new Cinema(3, 3);
        assertThat(cinema.getNumberOfRows()).isEqualTo(3);
        assertThat(cinema.getNumberOfSeats()).isEqualTo(3);
    }

    @Test
    void shouldPurchaseSingleTicket() {
        cinema = new Cinema(3, 3);

        cinema.makePurchase(1, 1);

        assertThat(cinema.getPurchasedTickets()).isEqualTo(1);
        assertThat(cinema.getPurchasedTickets()).isGreaterThan(0);
    }

    @Test
    void shouldPurchaseMultipleTickets() {
        cinema = new Cinema(3, 3);

        cinema.makePurchase(0, 0);
        cinema.makePurchase(1, 1);
        cinema.makePurchase(2, 2);
        cinema.makePurchase(0, 1);
        cinema.makePurchase(0, 2);

        assertThat(cinema.getPurchasedTickets()).isEqualTo(5);
    }

    @Test
    void shouldPurchaseMultipleTicketsWithMoreRowsSeats() {
        cinema = new Cinema(9, 9);

        cinema.makePurchase(0, 0);
        cinema.makePurchase(1, 1);
        cinema.makePurchase(2, 2);
        cinema.makePurchase(3, 1);
        cinema.makePurchase(4, 2);
        cinema.makePurchase(5, 3);
        cinema.makePurchase(5, 8);
        cinema.makePurchase(6, 8);
        cinema.makePurchase(7, 6);
        cinema.makePurchase(8, 7);

        assertThat(cinema.getPurchasedTickets()).isEqualTo(10);
    }

    @Test
    void inputShouldBeValid() {
        assertThat(cinema.validateSeatRow(0, 0)).isTrue();
        assertThat(cinema.validateSeatRow(1, 0)).isTrue();
        assertThat(cinema.validateSeatRow(2, 0)).isTrue();
        assertThat(cinema.validateSeatRow(2, 2)).isTrue();
    }

    @Test
    void inputShouldNotBeValid() {
        assertThat(cinema.validateSeatRow(3, 3)).isFalse();
        assertThat(cinema.validateSeatRow(-1, -5)).isFalse();
        assertThat(cinema.validateSeatRow(2, 3)).isFalse();
        assertThat(cinema.validateSeatRow(3, 1)).isFalse();
    }
}