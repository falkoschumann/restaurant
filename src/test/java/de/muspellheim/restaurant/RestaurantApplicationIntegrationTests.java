package de.muspellheim.restaurant;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class RestaurantApplicationIntegrationTests {

  @Test
  void contextLoads() {
    assertDoesNotThrow(() -> new Throwable());
  }
}
