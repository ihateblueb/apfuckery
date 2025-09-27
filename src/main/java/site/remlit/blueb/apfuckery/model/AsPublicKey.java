package site.remlit.blueb.apfuckery.model;

import org.jetbrains.annotations.NotNull;

public class AsPublicKey implements AsObject {
    public String id;
    public String owner;
    public String publicKeyPem;

    public AsPublicKey(
            @NotNull String owner,
            @NotNull String publicKeyPem
    ) {
        this.id = owner + "#main-key";
        this.owner = owner;
        this.publicKeyPem = publicKeyPem;
    }
}
