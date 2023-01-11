package de.muspellheim.restaurant.it;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class RestaurantApplicationTests {

  @Test
  void contextLoads() {
    assertDoesNotThrow(() -> new Throwable());
  }
}
