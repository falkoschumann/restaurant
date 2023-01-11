package de.muspellheim.restaurant.it;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.databind.ObjectMapper;
import de.muspellheim.restaurant.ReservationDto;
import lombok.SneakyThrows;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
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
                "2023-03-10 19:00", "katinka@example.com", "Katinka Ingabogovinanana", 2));

    var response = mvc.perform(request);

    response.andExpect(status().is2xxSuccessful());
  }

  @SneakyThrows
  private MockHttpServletRequestBuilder postReservation(ReservationDto reservation) {
    var json = objectMapper.writeValueAsString(reservation);
    return MockMvcRequestBuilders.post("/reservations")
        .contentType(MediaType.APPLICATION_JSON)
        .content(json);
  }
}
