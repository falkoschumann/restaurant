package de.muspellheim.restaurant;

import org.springframework.stereotype.Repository;

@Repository
public class NullRepository implements ReservationsRepository {
  @Override
  public void create(Reservation reservation) {
    // does nothing
  }
}
