package de.muspellheim.restaurant;

import java.util.ArrayList;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;

@Primary
@Repository
public class FakeDatabase extends ArrayList<Reservation> implements ReservationsRepository {

  @Override
  public void create(Reservation reservation) {
    add(reservation);
  }
}
