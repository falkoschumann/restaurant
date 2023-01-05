package de.muspellheim.restaurant;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/** Stub controller. */
@RestController
public class HelloController {

  @GetMapping(path = "/", produces = MediaType.APPLICATION_JSON_VALUE)
  public String index() {
    return """
      { "message": "Hello World!" }
    """.trim();
  }
}
