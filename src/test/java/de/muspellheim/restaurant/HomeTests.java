package de.muspellheim.restaurant;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

@SpringBootTest
@AutoConfigureMockMvc
class HomeTests {
  @Autowired private MockMvc mvc;

  @Test
  void homeReturnsJson() throws Exception {
    var request = MockMvcRequestBuilders.get("/").accept(MediaType.APPLICATION_JSON);

    var response = mvc.perform(request);

    response.andExpect(status().is2xxSuccessful());
    response.andExpect(content().contentType(MediaType.APPLICATION_JSON));
  }
}
