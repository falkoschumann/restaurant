package de.muspellheim.restaurant;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Data
@ConfigurationProperties("tables")
public class TableConfiguration {
  private TableType tableType;
  private int seats;

  Table toTable() {
    return switch (tableType) {
      case COMMUNAL -> Table.communalOf(seats);
      default -> Table.standardOf(seats);
    };
  }
}
