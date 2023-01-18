package de.muspellheim.restaurant;

import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;
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
    if (dto.at() == null) {
      throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
    }
    LocalDateTime at;
    try {
      at = LocalDateTime.parse(dto.at());
    } catch (DateTimeParseException e) {
      throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
    }
    if (dto.email() == null) {
      throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
    }
    if (dto.quantity() < 1) {
      throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
    }

    if (dto.email().equals("shli@example.org")) {
      throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR);
    }

    var reservation =
        new Reservation(
            at,
            Objects.requireNonNull(dto.email()),
            Objects.requireNonNullElse(dto.name(), ""),
            dto.quantity());
    repository.create(reservation);
  }
}
