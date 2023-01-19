package de.muspellheim.restaurant;

import java.util.Objects;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping(path = "/reservations", produces = MediaType.APPLICATION_JSON_VALUE)
public class ReservationsController {
  private final ReservationsRepository repository;

  public ReservationsController(ReservationsRepository repository) {
    this.repository = repository;
  }

  @PostMapping()
  @ResponseStatus(HttpStatus.NO_CONTENT)
  public void post(@RequestBody ReservationDto dto) {
    Objects.requireNonNull(dto, "dto");
    var reservation =
        dto.validate().orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST));

    var reservations = repository.readReservations(reservation.at().toLocalDate());
    var reservedSeats = reservations.stream().mapToInt(Reservation::quantity).sum();
    if (10 < reservedSeats + dto.quantity()) {
      throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR);
    }

    repository.create(reservation);
  }
}
