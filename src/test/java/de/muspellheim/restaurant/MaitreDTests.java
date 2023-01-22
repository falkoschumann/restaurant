package de.muspellheim.restaurant;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.params.provider.Arguments.arguments;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Collection;
import java.util.List;
import java.util.stream.Stream;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

// CHECKSTYLE.SUPPRESS: AbbreviationAsWordInName
class MaitreDTests {
  @ParameterizedTest
  @MethodSource("acceptTestCases")
  void accept(MaitreD sut, LocalDateTime now, Collection<Reservation> reservations) {
    var r = Some.RESERVATION.withQuantity(11);

    var actual = sut.willAccept(now, reservations, r);

    assertTrue(actual);
  }

  static Stream<Arguments> acceptTestCases() {
    return Stream.of(
        arguments(
            new MaitreD(
                LocalTime.of(18, 0),
                LocalTime.of(21, 0),
                Duration.ofHours(6),
                List.of(Table.communalOf(12))),
            Some.NOW,
            List.of()),
        arguments(
            new MaitreD(
                LocalTime.of(18, 0),
                LocalTime.of(21, 0),
                Duration.ofHours(6),
                List.of(Table.communalOf(8), Table.communalOf(11))),
            Some.NOW,
            List.of()),
        arguments(
            new MaitreD(
                LocalTime.of(18, 0),
                LocalTime.of(21, 0),
                Duration.ofHours(6),
                List.of(Table.communalOf(2), Table.communalOf(11))),
            Some.NOW,
            List.of(Some.RESERVATION.withQuantity(2))),
        arguments(
            new MaitreD(
                LocalTime.of(18, 0),
                LocalTime.of(21, 0),
                Duration.ofHours(6),
                List.of(Table.communalOf(11))),
            Some.NOW,
            List.of(Reservations.theDayBefore(Some.RESERVATION.withQuantity(11)))),
        arguments(
            new MaitreD(
                LocalTime.of(18, 0),
                LocalTime.of(21, 0),
                Duration.ofHours(6),
                List.of(Table.communalOf(11))),
            Some.NOW,
            List.of(Reservations.theDayAfter(Some.RESERVATION.withQuantity(11)))),
        arguments(
            new MaitreD(
                LocalTime.of(18, 0),
                LocalTime.of(21, 0),
                Duration.ofMinutes(150),
                List.of(Table.standardOf(12))),
            Some.NOW,
            List.of(
                Reservations.addDate(Some.RESERVATION.withQuantity(11), Duration.ofMinutes(-150))),
            arguments(
                new MaitreD(
                    LocalTime.of(18, 0),
                    LocalTime.of(21, 0),
                    Duration.ofHours(1),
                    List.of(Table.standardOf(14))),
                Some.NOW,
                List.of(
                    Reservations.addDate(Some.RESERVATION.withQuantity(9), Duration.ofHours(1))))));
  }

  @ParameterizedTest
  @MethodSource("rejectTestCases")
  void reject(MaitreD sut, LocalDateTime now, Collection<Reservation> reservations) {
    var r = Some.RESERVATION.withQuantity(11);

    var actual = sut.willAccept(now, reservations, r);

    assertFalse(actual);
  }

  static Stream<Arguments> rejectTestCases() {
    return Stream.of(
        arguments(
            new MaitreD(
                LocalTime.of(18, 0),
                LocalTime.of(21, 0),
                Duration.ofHours(6),
                List.of(Table.communalOf(6), Table.communalOf(6))),
            Some.NOW,
            List.of()),
        arguments(
            new MaitreD(
                LocalTime.of(18, 0),
                LocalTime.of(21, 0),
                Duration.ofHours(6),
                List.of(Table.standardOf(12))),
            Some.NOW,
            List.of(Some.RESERVATION.withQuantity(1))),
        arguments(
            new MaitreD(
                LocalTime.of(18, 0),
                LocalTime.of(21, 0),
                Duration.ofHours(6),
                List.of(Table.standardOf(11))),
            Some.NOW,
            List.of(Reservations.oneHourBefore(Some.RESERVATION.withQuantity(1)))),
        arguments(
            new MaitreD(
                LocalTime.of(18, 0),
                LocalTime.of(21, 0),
                Duration.ofHours(6),
                List.of(Table.standardOf(12))),
            Some.NOW,
            List.of(Reservations.oneHourLater(Some.RESERVATION.withQuantity(2)))),
        /* Some.Reservation.At is the time of the 'hard-coded' reservation in the test below. Adding
         * 30 minutes to it means that the restaurant opens 30 minutes later than the desired
         * reservation time, and therefore must be rejected. */
        arguments(
            new MaitreD(
                Some.RESERVATION.at().plusMinutes(30).toLocalTime(),
                LocalTime.of(21, 0),
                Duration.ofHours(6),
                List.of(Table.standardOf(12))),
            Some.NOW,
            List.of()),
        /* Some.Reservation.At is the time of the 'hard-coded' reservation in the test below.
         * Subtracting 30 minutes from it means that the restaurant's last seating is 30 minutes
         * before the reservation time, and therefore the reservation must be rejected. */
        arguments(
            new MaitreD(
                LocalTime.of(18, 0),
                Some.RESERVATION.at().minusMinutes(30).toLocalTime(),
                Duration.ofHours(6),
                List.of(Table.standardOf(12))),
            Some.NOW,
            List.of()),
        arguments(
            new MaitreD(
                LocalTime.of(18, 0),
                LocalTime.of(21, 0),
                Duration.ofHours(6),
                List.of(Table.standardOf(12))),
            Some.NOW.plusDays(30),
            List.of()));
  }
}
