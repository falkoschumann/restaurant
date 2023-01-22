package de.muspellheim.restaurant;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

class Some {
  public static final LocalDateTime NOW = LocalDateTime.of(2022, 4, 1, 20, 15);

  public static final Reservation RESERVATION = new Reservation(NOW, "x@example.net", "", 1);

  public static final MaitreD MAITRE_D =
      new MaitreD(
          LocalTime.of(16, 0),
          LocalTime.of(21, 0),
          Duration.ofMinutes(12),
          List.of(Table.communalOf(10)));
}
