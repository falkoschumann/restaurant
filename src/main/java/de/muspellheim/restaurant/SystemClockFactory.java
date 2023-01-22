package de.muspellheim.restaurant;

import java.time.Clock;
import org.springframework.stereotype.Component;

@Component
public class SystemClockFactory implements ClockFactory {
  @Override
  public Clock get() {
    return Clock.systemDefaultZone();
  }
}
