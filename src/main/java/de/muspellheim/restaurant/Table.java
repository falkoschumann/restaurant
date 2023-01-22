package de.muspellheim.restaurant;

public record Table(int seats, boolean isStandard) {
  public static Table standardOf(int seats) {
    return new Table(seats, true);
  }

  public static Table communalOf(int seats) {
    return new Table(seats, false);
  }

  public boolean isCommunal() {
    return !isStandard;
  }

  public Table withSeats(int newSeats) {
    return new Table(newSeats, isStandard);
  }

  boolean fits(int quantity) {
    return quantity <= seats;
  }

  Table reserve(int seatsToReserve) {
    return withSeats(seats - seatsToReserve);
  }
}
