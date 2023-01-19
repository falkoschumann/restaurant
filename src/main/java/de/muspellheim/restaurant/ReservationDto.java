package de.muspellheim.restaurant;

import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;
import java.util.Objects;
import java.util.Optional;
import org.springframework.lang.Nullable;

public record ReservationDto(
    @Nullable String at, @Nullable String email, @Nullable String name, int quantity) {
  Optional<Reservation> validate() {
    if (at == null) {
      return Optional.empty();
    }
    if (email == null) {
      return Optional.empty();
    }
    if (quantity < 1) {
      return Optional.empty();
    }

    try {
      var dateTime = LocalDateTime.parse(at);
      return Optional.of(
          new Reservation(dateTime, email, Objects.requireNonNullElse(name, ""), quantity));
    } catch (DateTimeParseException e) {
      return Optional.empty();
    }
  }
}
