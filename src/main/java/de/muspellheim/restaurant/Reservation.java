package de.muspellheim.restaurant;

import java.time.LocalDateTime;

public record Reservation(LocalDateTime at, String email, String name, int quantity) {
  public Reservation {
    if (quantity < 1) {
      throw new IllegalArgumentException("Der Wert quantity muss eine positive Ganzzahl > 0 sein.");
    }
  }
}
