package de.muspellheim.restaurant;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.Objects;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
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

  @Override
  public Collection<Reservation> readReservations(LocalDate date) {
    Objects.requireNonNull(date);

    var sql =
        """
        SELECT at, name, email, qQuantity
          FROM reservations
         WHERE date_trunc('day', at) = ?;
        """;
    return jdbc.query(sql, this::mapReservations, date);
  }

  private Reservation mapReservations(ResultSet rs, int rowNum) throws SQLException {
    return new Reservation(
        rs.getObject("at", LocalDateTime.class),
        rs.getString("email"),
        rs.getString("name"),
        rs.getInt("quantity"));
  }
}
