package de.muspellheim.restaurant;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/** Stub controller. */
@RestController
public class HelloController {

  @GetMapping("/")
  public String index() {
    return "Hello World!";
  }
}
