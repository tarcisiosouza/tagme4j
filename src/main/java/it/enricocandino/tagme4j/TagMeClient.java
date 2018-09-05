/**
 * Copyright (c) 2016 Enrico Candino
 * <p>
 * Distributed under the MIT License.
 */
package it.enricocandino.tagme4j;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import it.enricocandino.tagme4j.json.RelatednessDeserializer;
import it.enricocandino.tagme4j.model.Relatedness;
import it.enricocandino.tagme4j.request.RelRequest;
import it.enricocandino.tagme4j.request.SpotRequest;
import it.enricocandino.tagme4j.request.TagRequest;
import okhttp3.*;

public class TagMeClient {

    private final static String scheme = "https";
    private final static String host   = "tagme.d4science.org";

    private String apikey;
    private OkHttpClient client;
    private Gson gson;

    /**
     *
     * @param apikey the D4Science Service Authorization Token
     */
    public TagMeClient(String apikey) {
        this.apikey = apikey;
        this.client = new OkHttpClient();
        this.gson = new GsonBuilder()
                .registerTypeAdapter(Relatedness.class, new RelatednessDeserializer())
                .create();
    }

    public TagRequest tag() {
        return new TagRequest(this);
    }

    public SpotRequest spot() {
        return new SpotRequest(this);
    }

    public RelRequest rel() {
        return new RelRequest(this);
    }

    public static String getScheme() {
        return scheme;
    }

    public static String getHost() {
        return host;
    }

    public String getApikey() {
        return apikey;
    }

    public OkHttpClient getClient() {
        return client;
    }

    public Gson getGson() {
        return gson;
    }
}
