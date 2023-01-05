package de.muspellheim.restaurant;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/reservations")
public class ReservationsController {
  @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
  public void post() {
    // TODO implement method
  }
}
