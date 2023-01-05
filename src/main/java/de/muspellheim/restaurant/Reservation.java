package de.muspellheim.restaurant;

import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Reservation {
  private LocalDateTime date;
  private String email;
  private String name;
  private int quantity;
}
