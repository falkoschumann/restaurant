package de.muspellheim.restaurant.it;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.databind.ObjectMapper;
import de.muspellheim.restaurant.ReservationDto;
import lombok.SneakyThrows;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.lang.Nullable;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

@SpringBootTest
@AutoConfigureMockMvc
class ReservationsControllerTests {
  @Autowired private MockMvc mvc;
  @Autowired private ObjectMapper objectMapper;

  @Test
  void postValidReservation() throws Exception {
    var request =
        postReservation(
            new ReservationDto(
                "2023-03-10T19:00", "katinka@example.com", "Katinka Ingabogovinanana", 2));

    var response = mvc.perform(request);

    response.andExpect(status().is2xxSuccessful());
  }

  @ParameterizedTest
  @CsvSource(
      useHeadersInDisplayName = true,
      textBlock =
          """
          At,Email,Name,Quantity
          ,j@example.net,Jay Xerxes,1
          Kein Datum,w@example.edu,Wk Hd,8
          2023-11-30T20:01,,Thora,19
          2022-01-02T12:10,3@example.org,3 Beard,0
          2045-12-31T11:45,git@example.com,Gil Tan,-1
          """)
  void postInvalidReservation(
      @Nullable String at, @Nullable String email, String name, int quantity) throws Exception {
    var request = postReservation(new ReservationDto(at, email, name, quantity));

    var response = mvc.perform(request);

    response.andExpect(status().isBadRequest());
  }

  @SneakyThrows
  private MockHttpServletRequestBuilder postReservation(ReservationDto reservation) {
    var json = objectMapper.writeValueAsString(reservation);
    return MockMvcRequestBuilders.post("/reservations")
        .contentType(MediaType.APPLICATION_JSON)
        .content(json);
  }
}
