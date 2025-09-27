package site.remlit.blueb.apfuckery.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import site.remlit.blueb.apfuckery.model.*;

import java.time.LocalDateTime;
import java.util.ArrayList;

@RestController
public class MaximumPollController {

    private static final Logger logger = LoggerFactory.getLogger(MaximumPollController.class);

    @Value("${apfuckery.url}")
    private String baseUrl;

    final String POLL_ID = "b76d82af-7cfd-4714-80f6-01f9a02be461";
    final String REPLY_ID = "b86d82af-7cfd-4714-80f6-01f9a02be461";
    final String ACTOR_ID = "dc70a132-97fc-43af-92e4-14707821c51c";

    @RequestMapping("/note/" + POLL_ID)
    public ResponseEntity<AsObject> get(
            @RequestHeader("User-Agent") String userAgent
    ) {
        logger.info("Max int poll hit from {}", userAgent);

        AsQuestion question = new AsQuestion();

        question.id = baseUrl + "note/" + POLL_ID;
        question.attributedTo = baseUrl + "actor/" + ACTOR_ID;
        question.replies = baseUrl + "note/" + POLL_ID + "/replies";

        question.content = "whats your favorite integer?";

        question.published = LocalDateTime.of(2025, 9, 27, 2, 21).toString();

        question.oneOf = new ArrayList<>();

        for (int i  = 0; i < (1024 * 5); i++) {
            question.oneOf.add(new AsPollOption(
                    String.valueOf(i)
            ));
        }

        question.to.add("https://www.w3.org/ns/activitystreams#Public");

        HttpHeaders headers = new HttpHeaders();
        headers.set("Content-Type", "application/activity+json");

        return ResponseEntity.ok()
                .headers(headers)
                .body(question);
    }

    @RequestMapping("/note/" + POLL_ID + "/replies")
    public ResponseEntity<AsObject> getReplies(
            @RequestHeader("User-Agent") String userAgent
    ) {
        AsOrderedCollection collection = new AsOrderedCollection();

        collection.id = baseUrl + "note/" + POLL_ID + "/replies";

        ArrayList<String> items = new ArrayList<>();

        items.add(baseUrl + "note/" + REPLY_ID);

        collection.orderedItems = items;
        collection.totalItems = items.size();

        HttpHeaders headers = new HttpHeaders();
        headers.set("Content-Type", "application/activity+json");

        return ResponseEntity.ok()
                .headers(headers)
                .body(collection);
    }

    @RequestMapping("/note/" + REPLY_ID)
    public ResponseEntity<AsObject> getReply(
            @RequestHeader("User-Agent") String userAgent
    ) {
        logger.info("Max int poll reply hit from {}", userAgent);

        AsNote note = new AsNote();

        note.id = baseUrl + "note/" + REPLY_ID;
        note.attributedTo = baseUrl + "actor/" + ACTOR_ID;

        note.content = "this poll isn't actually from 0 to max int, realistically the json would have been too big and every instance would reject it. and that would be a lot of wasted bandwidth and resource usage. so this is instead just 10240 options, and almost every fedi software in existence will chop it down to about 5 to 25. but still a fun little joke post.";

        note.published = LocalDateTime.of(2025, 9, 27, 2, 24).toString();

        note.to.add("https://www.w3.org/ns/activitystreams#Public");

        HttpHeaders headers = new HttpHeaders();
        headers.set("Content-Type", "application/activity+json");

        return ResponseEntity.ok()
                .headers(headers)
                .body(note);
    }
}
