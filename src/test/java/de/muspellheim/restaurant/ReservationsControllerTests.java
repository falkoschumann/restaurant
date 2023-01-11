package de.muspellheim.restaurant;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasItem;

import java.time.LocalDateTime;
import org.junit.jupiter.api.Test;

class ReservationsControllerTests {
  @Test
  void postValidReservationWhenDatabaseIsEmpty() {
    var db = new FakeDatabase();
    var sut = new ReservationsController(db);

    var dto = new ReservationDto("2023-11-24 19:00", "juliad@example.net", "Julia Domna", 5);
    sut.post(dto);

    var expected =
        new Reservation(
            LocalDateTime.of(2023, 11, 24, 19, 0), "juliad@example.net", "Julia Domna", 5);
    assertThat(db, hasItem(expected));
  }
}
