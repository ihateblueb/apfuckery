package site.remlit.blueb.apfuckery.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * ActivityStreams Object that has a @context property on it.
 * */
public abstract class AsContextedObject implements AsObject {

    @JsonProperty("@context")
    public ArrayList<Object> context = new ArrayList<>();

    {
        context.add("https://www.w3.org/ns/activitystreams");
        context.add("https://w3id.org/security/v1");

        Map<String, String> subcontext = new HashMap<>();

        subcontext.put("Key", "sec:Key");

        subcontext.put("sensitive", "as:sensitive");
        subcontext.put("manuallyApprovesFollowers", "as:manuallyApprovesFollowers");
        subcontext.put("quoteUrl", "as:quoteUrl");
        subcontext.put("Hashtag", "as:Hashtag");

        subcontext.put("vcard", "http://www.w3.org/2006/vcard/ns#");

        subcontext.put("schema", "http://schema.org#");
        subcontext.put("PropertyValue", "schema:PropertyValue");
        subcontext.put("value", "schema:value");

        subcontext.put("toot", "http://joinmastodon.org/ns#");
        subcontext.put("Emoji", "toot:Emoji");

        subcontext.put("fedibird", "http://fedibird.com/ns#");
        subcontext.put("quoteUri", "fedibird:quoteUri");

        subcontext.put("misskey", "https://misskey-hub.net/ns#");
        subcontext.put("_misskey_content", "misskey:_misskey_content");
        subcontext.put("_misskey_quote", "misskey:_misskey_quote");
        subcontext.put("_misskey_reaction", "misskey:_misskey_reaction");
        subcontext.put("_misskey_summary", "misskey:_misskey_summary");
        subcontext.put("isCat", "misskey:isCat");

        subcontext.put("firefish", "https://joinfirefish.org/ns#");
        subcontext.put("speakAsCat", "firefish:speakAsCat");

        subcontext.put("Bite", "https://ns.mia.jetzt/as#Bite");

        subcontext.put("aster", "https://blueb.pages.gay/ns#");
        subcontext.put("visibility", "aster:visibility");

        context.add(subcontext);
    }

}
