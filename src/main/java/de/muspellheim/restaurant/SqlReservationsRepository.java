package de.muspellheim.restaurant;

import java.util.Objects;
import org.springframework.context.annotation.Profile;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
@Profile("production")
public class SqlReservationsRepository implements ReservationsRepository {
  private final JdbcTemplate jdbc;

  public SqlReservationsRepository(JdbcTemplate jdbc) {
    this.jdbc = jdbc;
  }

  @Override
  public void create(Reservation reservation) {
    Objects.requireNonNull(reservation, "reservation");

    var sql =
        """
        INSERT INTO reservations (at, name, email, quantity)
        VALUES (?, ?, ?, ?);
        """;
    jdbc.update(
        sql, reservation.at(), reservation.name(), reservation.email(), reservation.quantity());
  }
}
