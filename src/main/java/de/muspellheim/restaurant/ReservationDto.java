package de.muspellheim.restaurant;

import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;
import org.springframework.lang.Nullable;

public record ReservationDto(
    @Nullable String at, @Nullable String email, @Nullable String name, int quantity) {

  boolean isValid() {
    var ok = at != null && email != null && 0 < quantity;
    if (!ok) {
      return false;
    }

    try {
      LocalDateTime.parse(at);
      return true;
    } catch (DateTimeParseException e) {
      return false;
    }
  }
}
