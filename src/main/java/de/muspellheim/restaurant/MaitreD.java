package de.muspellheim.restaurant;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Objects;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public record MaitreD(
    LocalTime opensAt, LocalTime lastSeating, Duration seatingDuration, Collection<Table> tables) {

  @Autowired
  public MaitreD(RestaurantConfiguration configuration) {
    this(
        configuration.getOpensAt(),
        configuration.getLastSeating(),
        configuration.getSeatingDuration(),
        configuration.getTables().stream().map(TableConfiguration::toTable).toList());
  }

  public boolean willAccept(
      LocalDateTime now, Collection<Reservation> existingReservations, Reservation candidate) {
    Objects.requireNonNull(existingReservations, "existingReservations");
    Objects.requireNonNull(candidate, "candidate");
    if (candidate.at().isBefore(now)) {
      return false;
    }
    if (isOutsideOfOpeningHours(candidate)) {
      return false;
    }

    var seating = new Seating(seatingDuration, candidate);
    var relevantReservations = existingReservations.stream().filter(seating::overlaps).toList();
    var availableTables = allocate(relevantReservations);
    return availableTables.stream().anyMatch(t -> t.fits(candidate.quantity()));
  }

  private boolean isOutsideOfOpeningHours(Reservation reservation) {
    return reservation.at().toLocalTime().isBefore(opensAt)
        || lastSeating.isBefore(reservation.at().toLocalTime());
  }

  private Collection<Table> allocate(Collection<Reservation> reservations) {
    var availableTables = new ArrayList<>(tables);
    for (var r : reservations) {
      var table = availableTables.stream().filter(t -> t.fits(r.quantity())).findAny();
      if (table.isPresent()) {
        availableTables.remove(table.get());
        if (table.get().isCommunal()) {
          availableTables.add(table.get().reserve(r.quantity()));
        }
      }
    }
    return List.copyOf(availableTables);
  }
}
