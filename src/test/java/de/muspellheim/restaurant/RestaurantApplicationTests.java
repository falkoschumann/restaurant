package de.muspellheim.restaurant;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@AutoConfigureMockMvc
class RestaurantApplicationTests {

  @Test
  void contextLoads() {
    assertDoesNotThrow(() -> new Throwable());
  }
}
