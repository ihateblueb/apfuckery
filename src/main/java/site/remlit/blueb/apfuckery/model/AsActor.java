package site.remlit.blueb.apfuckery.model;

import java.util.ArrayList;

public class AsActor extends AsContextedObject implements AsObject {

    public String id;

    public String type = "Person";

    public String name;
    public String preferredUsername;

    public String content;

    public boolean discoverable = true;
    public boolean manuallyApprovesFollowers = false;

    public boolean isCat = true;
    public boolean speakAsCat = false;

    public String url;
    public String inbox;
    public String sharedInbox;
    public String followers;
    public String following;

    public String published;

    public ArrayList<AsTag> tag = new ArrayList<>();

    public String publicKey;

}
