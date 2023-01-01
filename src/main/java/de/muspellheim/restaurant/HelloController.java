package de.muspellheim.restaurant;

import org.springframework.web.bind.annotation.*;

@RestController
public class HelloController {

  @GetMapping("/")
  public String index() {
    return "Hello World!";
  }
}
