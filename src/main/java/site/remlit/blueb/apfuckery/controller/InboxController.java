package site.remlit.blueb.apfuckery.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class InboxController {

    private static final Logger logger = LoggerFactory.getLogger(InboxController.class);

    @PostMapping("/inbox")
    public static void post() {
        logger.info("Voided inbox message");
    }

}
