package de.muspellheim.restaurant;

import java.time.Duration;
import java.util.Objects;

class Reservations {
  private Reservations() {}

  static Reservation addDate(Reservation reservation, Duration duration) {
    Objects.requireNonNull(reservation, "reservation");
    return reservation.withAt(reservation.at().plus(duration));
  }

  static Reservation oneHourBefore(Reservation reservation) {
    return reservation.withAt(reservation.at().minusHours(1));
  }

  static Reservation theDayBefore(Reservation reservation) {
    return reservation.withAt(reservation.at().minusDays(1));
  }

  static Reservation oneHourLater(Reservation reservation) {
    return reservation.withAt(reservation.at().plusHours(1));
  }

  static Reservation theDayAfter(Reservation reservation) {
    return reservation.withAt(reservation.at().plusDays(1));
  }
}
