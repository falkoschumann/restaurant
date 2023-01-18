package de.muspellheim.restaurant;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

@Repository
@Profile("test")
public class FakeDatabase extends ArrayList<Reservation> implements ReservationsRepository {

  @Override
  public void create(Reservation reservation) {
    add(reservation);
  }

  @Override
  public Collection<Reservation> readReservations(LocalDate date) {
    var min = date.atStartOfDay();
    var max = min.plusDays(1);
    return stream().filter(r -> !min.isAfter(r.at()) && !r.at().isAfter(max)).toList();
  }
}
