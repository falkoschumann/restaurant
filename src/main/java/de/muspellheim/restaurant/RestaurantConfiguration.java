package de.muspellheim.restaurant;

import java.time.Duration;
import java.time.LocalTime;
import java.util.List;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.format.annotation.DateTimeFormat;

@Data
@ConfigurationProperties("restaurant")
public class RestaurantConfiguration {
  @DateTimeFormat(iso = DateTimeFormat.ISO.TIME)
  private LocalTime opensAt;

  @DateTimeFormat(iso = DateTimeFormat.ISO.TIME)
  private LocalTime lastSeating;

  private Duration seatingDuration;
  private List<TableConfiguration> tables;

  @Autowired
  public RestaurantConfiguration(List<TableConfiguration> tables) {
    this.tables = tables;
  }
}
