package de.muspellheim.restaurant;

import java.time.LocalDateTime;

public record Reservation(LocalDateTime date, String email, String name, int quantity) {}
