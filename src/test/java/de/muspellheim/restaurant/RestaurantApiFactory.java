package de.muspellheim.restaurant;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

@Component
public class RestaurantApiFactory {
  @Autowired private MockMvc mvc;
  @Autowired private ObjectMapper objectMapper;

  public ResultActions postReservation(ReservationDto reservation) throws Exception {
    var json = objectMapper.writeValueAsString(reservation);
    var request =
        MockMvcRequestBuilders.post("/reservations")
            .contentType(MediaType.APPLICATION_JSON)
            .content(json);
    return mvc.perform(request);
  }
}
