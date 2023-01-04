package de.muspellheim.restaurant;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

@SpringBootTest
@AutoConfigureMockMvc
class HelloControllerTests {
  @Autowired private MockMvc mvc;

  @Test
  void homeIsOk() throws Exception {
    mvc.perform(MockMvcRequestBuilders.get("/")) //
        .andExpect(status().is2xxSuccessful());
  }
}
