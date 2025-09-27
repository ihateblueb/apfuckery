package site.remlit.blueb.apfuckery.model;

import java.util.ArrayList;

public class AsOrderedCollection extends AsContextedObject implements AsObject {

    public String id;
    public String type = "OrderedCollection";
    public ArrayList<String> orderedItems = new ArrayList<>();
    public int totalItems = 0;

}
