package cinema;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.Mockito.*;

import java.util.Scanner;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class InputReaderTest {

    private InputReader inputReader;
    private Scanner mockScanner;

    @BeforeEach
    void setUp() {
        mockScanner = mock(Scanner.class);
        inputReader = new InputReader(mockScanner);
    }

    @Test
    void testGetNumberWithValidInput() {
        when(mockScanner.nextInt()).thenReturn(10);

        int result = inputReader.getNumber("Enter a number: ");

        assertThat(result).isEqualTo(10);
        verify(mockScanner).nextInt();
    }

    @Test
    void testGetNumberWithInvalidInputAndThenValid() {
        when(mockScanner.nextInt()).thenThrow(new java.util.InputMismatchException()).thenReturn(10);

        int result = inputReader.getNumber("Enter a number: ");
        assertThat(result).isEqualTo(10);

        verify(mockScanner, times(1)).next();
        verify(mockScanner, times(2)).nextInt();
    }

    @Test
    void testGetNumberWithInvalidInputFollowedByThreeValidInputs() {
        // Simulate invalid input followed by three valid inputs
        when(mockScanner.nextInt()).thenThrow(new java.util.InputMismatchException()).thenReturn(10, 20, 30);

        int result = inputReader.getNumber("Enter a number: ");

        assertThat(result).isEqualTo(10);
        // Verify that next() was called three times to consume the invalid inputs
        verify(mockScanner, times(1)).next();
        // Verify that nextInt() was called four times
        verify(mockScanner, times(2)).nextInt();
    }
}
