/**
 * Copyright (c) 2016 Enrico Candino
 * <p>
 * Distributed under the MIT License.
 */
package it.enricocandino.tagme4j.request;

import it.enricocandino.tagme4j.TagMeClient;
import it.enricocandino.tagme4j.response.RelResponse;
import okhttp3.FormBody;
import okhttp3.Request;
import okhttp3.RequestBody;

/**
 *
 * This service computes the relatedness between two entities by returning a value in the range [0,1],
 * which expresses how much the two entities are semantically related to each other,
 * where 0 = not related, 1 = related.
 * We point out that this service could be used to relate two texts by,
 * first, annotating them with TagMe and, then, estimating the pairwise relatedness among all pairs
 * of their annotated entities. All these values could be combined some way
 * (e.g. avg, max, etc. etc.) in order to derive some value which expresses
 * the relatedness between the two input texts.
 *
 * Such a measure can be very powerful, particularly when dealing with short
 * and poorly composed texts that do not share any syntactic term
 * (here, the classic Tf-Idf scheme would fail indeed!).
 *
 * A single request to this API can contain up to 100 entity pairs.
 * Entities are identified by means of a numeric value: namely,
 * the Wikipedia internal identifier of the page corresponding to an entity.
 *
 *
 * Created by Enrico Candino on 23/09/16.
 *
 */
public class RelRequest extends TagMeRequest<RelResponse> {

    public RelRequest(TagMeClient client) {
        super(client, "tagme/rel", RelResponse.class);
    }

    /**
     * optional
     *
     * The language of the text to be annotated.
     *
     * Accepted values are "de" for German, "en" for English and "it" for Italian. Default is "en".
     *
     * @param lang the language of the text to be annotated
     * @return
     */
    public RelRequest lang(String lang) {
        builder.setQueryParameter("lang", lang);
        return this;
    }

    /**
     * optional/required, repeated
     *
     * This parameter contains a pair of numeric identifiers for entities,
     * like the ones received using the Tagging service above.
     * The couple is encoded as a string where the two page IDs are separated by a space char.
     * Either this parameter or the parameter tt must be specified in the request.
     * To request multiple relatedness computations, repeat this parameter for all requested couples.
     * If one occurrence of tt parameter is found in the request,
     * any value provided using this parameter will be ignored.
     *
     * @param id1 the numeric identifier of the first entitiy
     * @param id1 the numeric identifier of the second entitiy
     * @return
     */
    public RelRequest id(long id1, long id2) {
        builder.addQueryParameter("id", id1+" "+id2);
        return this;
    }

    /**
     * optional/required, repeated
     *
     * This parameter contains a pair of entity titles,
     * like the ones received using the Tagging service above
     * (namely, the title of the corresponding Wikipedia page).
     * The pair is encoded as a string where space characters in titles
     * are replaced by "underscore" char and the two titles are separated by a space char.
     * Either this parameter or the parameter id must be specified in the request.
     * To request multiple relatedness computations, repeat this parameter for all requested couples.
     * If one occurrence of id parameter is found in the request,
     * any value provided using this parameter will be ignored.
     *
     * @param t1 the title of the first entitiy
     * @param t2 the title of the first entitiy
     * @return
     */
    public RelRequest tt(String t1, String t2) {
        builder.addQueryParameter("tt", t1+" "+t2);
        return this;
    }

    @Override
    protected Request getRequest() {
        RequestBody formBody = new FormBody.Builder().build();

        return new Request.Builder()
                .url(getUrl())
                .post(formBody)
                .build();
    }
}
