package de.muspellheim.restaurant;

import static org.junit.jupiter.api.Assertions.assertThrows;

import java.time.LocalDateTime;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class ReservationTests {
  @ParameterizedTest
  @ValueSource(ints = {0, -1})
  void quantityMustBePositive(int invalidQuantity) {
    LocalDateTime at = LocalDateTime.of(2024, 8, 19, 11, 30);

    assertThrows(
        IllegalArgumentException.class,
        () -> new Reservation(at, "mail@example.com", "Marie Ilsøe", invalidQuantity));
  }
}
