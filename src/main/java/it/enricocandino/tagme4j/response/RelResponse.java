/**
 * Copyright (c) 2016 Enrico Candino
 * <p>
 * Distributed under the MIT License.
 */
package it.enricocandino.tagme4j.response;

import it.enricocandino.tagme4j.model.Relatedness;
import java.util.List;

public class RelResponse extends TagMeResponse {

    private List<Relatedness> result;

    public List<Relatedness> getResult() {
        return result;
    }

    public void setResult(List<Relatedness> result) {
        this.result = result;
    }
}
