package de.muspellheim.restaurant;

import java.time.Clock;
import java.time.Instant;
import java.time.ZoneId;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

@Component
@Primary
public class FakeClockFactory implements ClockFactory {
  @Override
  public Clock get() {
    return Clock.fixed(Instant.parse("2020-05-18T17:13:17+02:00"), ZoneId.systemDefault());
  }
}
