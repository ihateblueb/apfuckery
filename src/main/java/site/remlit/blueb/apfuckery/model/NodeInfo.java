package site.remlit.blueb.apfuckery.model;

import java.util.ArrayList;
import java.util.Collections;

public class NodeInfo {
    public String version;
    public NodeInfoSoftware software = new NodeInfoSoftware();
    public ArrayList<String> protocols = new ArrayList<>(Collections.singleton("activitypub"));
    public boolean openRegistrations = false;
}
