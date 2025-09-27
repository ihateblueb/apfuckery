package site.remlit.blueb.apfuckery.model;

import java.util.ArrayList;

public class AsNote extends AsContextedObject implements AsObject {
    public String id;

    public String type = "Note";
    public String attributedTo;
    public String replies;

    public String content;

    public String published;

    public ArrayList<AsTag> tag = new ArrayList<>();

    public ArrayList<String> to = new ArrayList<>();
    public ArrayList<String> cc = new ArrayList<>();
}
