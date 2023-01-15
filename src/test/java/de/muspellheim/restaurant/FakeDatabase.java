package de.muspellheim.restaurant;

import java.util.ArrayList;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

@Repository
@Profile("test")
public class FakeDatabase extends ArrayList<Reservation> implements ReservationsRepository {

  @Override
  public void create(Reservation reservation) {
    add(reservation);
  }
}
