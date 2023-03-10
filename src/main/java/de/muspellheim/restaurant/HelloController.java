package de.muspellheim.restaurant;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

  @GetMapping(path = "/", produces = MediaType.APPLICATION_JSON_VALUE)
  public String index() {
    return """
           { "message": "Hello World!" }
           """;
  }
}
