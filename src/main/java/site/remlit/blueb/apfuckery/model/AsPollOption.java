package site.remlit.blueb.apfuckery.model;

import org.jetbrains.annotations.NotNull;

public class AsPollOption implements AsObject {
    public String type = "Note";
    public String name;
    public AsPollReplies replies = new AsPollReplies();

    public AsPollOption(
            @NotNull String name
    ) {
        this.name = name;
    }
}
