package de.muspellheim.restaurant;

import java.time.Clock;
import java.time.LocalDateTime;
import java.util.Objects;
import org.springframework.beans.factory.annotation.Autowired;
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
  private final MaitreD maitreD;
  private final Clock clock;

  @Autowired
  public ReservationsController(
      ReservationsRepository repository, MaitreD maitreD, ClockFactory clockFactory) {
    this.repository = repository;
    this.maitreD = maitreD;
    this.clock = clockFactory.get();
  }

  @PostMapping()
  @ResponseStatus(HttpStatus.NO_CONTENT)
  public void post(@RequestBody ReservationDto dto) {
    Objects.requireNonNull(dto, "dto");

    var reservation =
        dto.validate().orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST));

    var reservations = repository.readReservations(reservation.at().toLocalDate());
    if (!maitreD.willAccept(LocalDateTime.now(clock), reservations, reservation)) {
      throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR);
    }

    repository.create(reservation);
  }
}
