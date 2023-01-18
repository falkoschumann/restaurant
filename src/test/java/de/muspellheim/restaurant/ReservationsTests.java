package de.muspellheim.restaurant;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.time.LocalDateTime;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.lang.Nullable;

@SpringBootTest
@AutoConfigureMockMvc
class ReservationsTests {
  @Autowired private RestaurantApiFactory service;

  @Test
  void postValidReservation() throws Exception {
    var response =
        service.postReservation(
            new ReservationDto(
                "2023-03-10T19:00", "katinka@example.com", "Katinka Ingabogovinanana", 2));

    response.andExpect(status().is2xxSuccessful());
  }

  @ParameterizedTest
  @CsvSource({
    "2023-11-24T19:00,juliad@example.net,Julia Domna,5",
    "2024-02-13T18:15,x@example.com,Xenia Ng,9",
    "2023-08-23T16:55,kite@example.edu,,2",
  })
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

  @ParameterizedTest
  @CsvSource({
    ",j@example.net,Jay Xerxes,1",
    "Kein Datum,w@example.edu,Wk Hd,8",
    "2023-11-30T20:01,,Thora,19",
    "2022-01-02T12:10,3@example.org,3 Beard,0",
    "2045-12-31T11:45,git@example.com,Gil Tan,-1",
  })
  void postInvalidReservation(
      @Nullable String at, @Nullable String email, String name, int quantity) throws Exception {
    var response = service.postReservation(new ReservationDto(at, email, name, quantity));

    response.andExpect(status().isBadRequest());
  }
}
