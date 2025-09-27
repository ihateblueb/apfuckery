package site.remlit.blueb.apfuckery.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import site.remlit.blueb.apfuckery.model.NodeInfo;
import site.remlit.blueb.apfuckery.model.NodeInfoSoftware;
import site.remlit.blueb.apfuckery.model.WellKnown;
import site.remlit.blueb.apfuckery.model.WellKnownLink;

import java.net.URI;
import java.util.ArrayList;

import static site.remlit.blueb.apfuckery.StaticStorage.INSTANCE_ACTOR_ID;

@RestController
public class FederationEssentials {

    @Value("${apfuckery.url}")
    private String baseUrl;

    private final ArrayList<String> usernames = new ArrayList<>();

    @Value("${instanceactor.username}")
    private String instanceactorUsername;

    {
        usernames.add(instanceactorUsername);
    }

    @GetMapping("/.well-known/webfinger")
    public ResponseEntity<WellKnown> getWellKnownLinks(
            @RequestParam("resource") String resource
    ) {
        WellKnown wellKnown = new WellKnown();

        String res = resource
                .replace("acct:@", "")
                .replace("acct:", "")
                .replace("@" + URI.create(baseUrl).getHost(), "")
                .replace("@", "")
                .replace(baseUrl + "actor/", "");

        if (!usernames.contains(res) && !res.equals(INSTANCE_ACTOR_ID)) {
            throw new RuntimeException("User not found");
        }

        String apId = baseUrl + "actor/" + INSTANCE_ACTOR_ID;

        wellKnown.subject = resource;

        wellKnown.aliases = new ArrayList<>();
        wellKnown.aliases.add(apId);

        wellKnown.links = new ArrayList<>();
        wellKnown.links.add(new WellKnownLink(
                "self",
                "application/activity+json",
                apId
        ));

        HttpHeaders headers = new HttpHeaders();
        headers.set("Content-Type", "application/activity+json");

        return ResponseEntity.ok()
                .headers(headers)
                .body(wellKnown);
    }

    @GetMapping("/.well-known/nodeinfo")
    public ResponseEntity<WellKnown> getWellKnownNodeInfo() {
        WellKnown wellKnown = new WellKnown();

        wellKnown.links = new ArrayList<>();
        wellKnown.links.add(new WellKnownLink(
                "http://nodeinfo.diaspora.software/ns/schema/2.1",
                baseUrl + "nodeinfo/2.1",
                null
        ));
        wellKnown.links.add(new WellKnownLink(
                "http://nodeinfo.diaspora.software/ns/schema/2.0",
                baseUrl + "nodeinfo/2.0",
                null
        ));

        HttpHeaders headers = new HttpHeaders();
        headers.set("Content-Type", "application/jrd+json");

        return ResponseEntity.ok()
                .headers(headers)
                .body(wellKnown);
    }

    @GetMapping("/nodeinfo/2.1")
    public ResponseEntity<NodeInfo> getNodeInfoTwoOne() {
        NodeInfo nodeInfo = new NodeInfo();

        nodeInfo.version = "2.1";

        HttpHeaders headers = new HttpHeaders();
        headers.set("Content-Type", "application/activity+json");

        return ResponseEntity.ok()
                .headers(headers)
                .body(nodeInfo);
    }

    @GetMapping("/nodeinfo/2.0")
    public ResponseEntity<NodeInfo> getNodeInfoTwo() {
        NodeInfo nodeInfo = new NodeInfo();

        nodeInfo.version = "2.0";

        HttpHeaders headers = new HttpHeaders();
        headers.set("Content-Type", "application/activity+json");

        return ResponseEntity.ok()
                .headers(headers)
                .body(nodeInfo);
    }

}
