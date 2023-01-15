package de.muspellheim.restaurant;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasItem;

import java.time.LocalDateTime;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.springframework.lang.Nullable;

class ReservationsControllerTests {
  @ParameterizedTest
  @CsvSource(
      useHeadersInDisplayName = true,
      textBlock =
          """
          At,Email,Name,Quantity
          2023-11-24T19:00,juliad@example.net,Julia Domna,5
          2024-02-13T18:15,x@example.com,Xenia Ng,9
          2023-08-23T16:55,kite@example.edu,,2
          """)
  void postValidReservationWhenDatabaseIsEmpty(
      String at, String email, @Nullable String name, int quantity) {
    var db = new FakeDatabase();
    var sut = new ReservationsController(db);

    var dto = new ReservationDto(at, email, name, quantity);
    sut.post(dto);

    var expected =
        new Reservation(LocalDateTime.parse(at), email, name != null ? name : "", quantity);
    assertThat(db, hasItem(expected));
  }
}
