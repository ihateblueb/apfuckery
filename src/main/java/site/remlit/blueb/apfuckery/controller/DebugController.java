package site.remlit.blueb.apfuckery.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
public class DebugController {

    @GetMapping("/debug/gen-uuid")
    public String get() {
        return UUID.randomUUID().toString();
    }

}
