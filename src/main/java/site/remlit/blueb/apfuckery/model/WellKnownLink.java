package site.remlit.blueb.apfuckery.model;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class WellKnownLink {
    public String rel;
    public String href;
    public String type;

    public WellKnownLink(
            @NotNull String rel,
            @NotNull String href,
            @Nullable String type
    ) {
        this.rel = rel;
        this.href = href;
        this.type = type;
    }
}
