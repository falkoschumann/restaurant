package de.muspellheim.restaurant;

import java.time.Duration;
import java.time.LocalDateTime;

record Seating(Duration seatingDuration, Reservation reservation) {
  LocalDateTime start() {
    return reservation.at();
  }

  LocalDateTime end() {
    return start().plus(seatingDuration);
  }

  boolean overlaps(Reservation other) {
    var otherSeating = new Seating(seatingDuration, other);
    return start().isBefore(otherSeating.end()) && otherSeating.start().isBefore(end());
  }
}
