package site.remlit.blueb.apfuckery.model;

import java.util.ArrayList;

public class AsQuestion extends AsNote {
    public String type = "Question";

    public String endTime;

    public ArrayList<AsPollOption> oneOf;
    public ArrayList<AsPollOption> manyOf;
}
