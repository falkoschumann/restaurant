package de.muspellheim.restaurant;

import java.time.LocalDateTime;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Reservation {
  private LocalDateTime date;
  private String email;
  private String name;
  private int quantity;
}
