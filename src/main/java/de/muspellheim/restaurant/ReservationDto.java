package de.muspellheim.restaurant;

import org.springframework.lang.Nullable;

public record ReservationDto(
    @Nullable String at, @Nullable String email, @Nullable String name, int quantity) {}
