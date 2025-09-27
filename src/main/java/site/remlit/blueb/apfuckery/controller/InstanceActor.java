package site.remlit.blueb.apfuckery.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import site.remlit.blueb.apfuckery.model.AsActor;
import site.remlit.blueb.apfuckery.model.AsObject;
import site.remlit.blueb.apfuckery.model.AsPublicKey;
import site.remlit.blueb.apfuckery.service.StringStore;

import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.time.LocalDateTime;
import java.util.Base64;

import static site.remlit.blueb.apfuckery.StaticStorage.INSTANCE_ACTOR_ID;

@RestController
public class InstanceActor {

    private static final Logger logger = LoggerFactory.getLogger(InstanceActor.class);

    @Value("${apfuckery.url}")
    private String baseUrl;

    @Value("${instanceactor.username}")
    private String username;

    @Value("${instanceactor.displayName}")
    private String displayName;

    @Value("${instanceactor.bio}")
    private String bio;

    @Value("${instanceactor.avatarUrl}")
    private String avatarUrl;

    @Value("${instanceactor.isCat}")
    private boolean isCat;

    @Value("${instanceactor.speakAsCat}")
    private boolean speakAsCat;

    private String publicKey;

    /* initializer */
    {
        publicKey = StringStore.read("instanceactor_publickey");

        String privateKey = StringStore.read("instanceactor_privatekey");

        // stolen from aster-kt
        // https://github.com/ihateblueb/aster-kt/blob/main/src/main/kotlin/site/remlit/blueb/aster/service/KeypairService.kt
        if (publicKey == null || privateKey == null) {
            logger.info("Either actor's public key or private key is missing. Generating.");

            KeyPairGenerator generator;
            try {
                generator = KeyPairGenerator.getInstance("RSA");
            } catch (NoSuchAlgorithmException e) {
                throw new RuntimeException(e);
            }

            generator.initialize(2048);
            KeyPair keyPair = generator.generateKeyPair();

            Base64.Encoder encoder = Base64.getEncoder();

            if (publicKey == null) {
                String publicKeyEncoded = encoder.encodeToString(keyPair.getPublic().getEncoded());
                StringStore.write(
                        "instanceactor_publickey",
                        "-----BEGIN PUBLIC KEY-----\n" +
                                publicKeyEncoded +
                                "\n-----END PUBLIC KEY-----"
                );

                publicKey = StringStore.read("instanceactor_publickey");
            }

            if (privateKey == null) {
                String privateKeyEncoded = encoder.encodeToString(keyPair.getPrivate().getEncoded());
                StringStore.write(
                        "instanceactor_privatekey",
                        "-----BEGIN PRIVATE KEY-----\n" +
                                privateKeyEncoded +
                                "\n-----END PRIVATE KEY-----"
                );
            }
        }
    }

    @GetMapping("/actor/" + INSTANCE_ACTOR_ID)
    public ResponseEntity<AsObject> actor() {
        AsActor actor = new AsActor();

        actor.id = baseUrl + "actor/" + INSTANCE_ACTOR_ID;

        actor.name = displayName;
        actor.preferredUsername = username;

        actor.summary = bio;

        actor.isCat = isCat;
        actor.speakAsCat = speakAsCat;

        actor.url = actor.id;
        actor.inbox = actor.id + "/inbox";
        actor.sharedInbox = actor.id + "/inbox";
        actor.followers = actor.id + "/followers";
        actor.following = actor.id + "/following";

        actor.published = LocalDateTime.of(2025, 9, 27, 2, 4).toString();

        actor.publicKey = new AsPublicKey(
                actor.id,
                publicKey
        );

        HttpHeaders headers = new HttpHeaders();
        headers.set("Content-Type", "application/activity+json");

        return ResponseEntity.ok()
                .headers(headers)
                .body(actor);
    }

}
