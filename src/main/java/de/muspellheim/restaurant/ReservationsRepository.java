package de.muspellheim.restaurant;

import java.time.LocalDate;
import java.util.Collection;

public interface ReservationsRepository {
  void create(Reservation reservation);

  Collection<Reservation> readReservations(LocalDate date);
}
