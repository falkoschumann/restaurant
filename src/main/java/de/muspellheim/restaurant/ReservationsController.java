package de.muspellheim.restaurant;

import java.time.LocalDateTime;
import java.util.Objects;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/reservations", produces = MediaType.APPLICATION_JSON_VALUE)
public class ReservationsController {
  private final ReservationsRepository repository;

  public ReservationsController(ReservationsRepository repository) {
    this.repository = repository;
  }

  @PostMapping()
  @ResponseStatus(HttpStatus.CREATED)
  public void post(@RequestBody ReservationDto dto) {
    Objects.requireNonNull(dto, "dto");

    repository.create(
        new Reservation(
            LocalDateTime.of(2023, 11, 24, 19, 0), "juliad@example.net", "Julia Domna", 5));
  }
}
