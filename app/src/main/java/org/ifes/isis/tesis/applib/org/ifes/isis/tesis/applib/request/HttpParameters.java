package org.ifes.isis.tesis.applib.org.ifes.isis.tesis.applib.request;

/**
 * Created by router on 20/11/17.
 */

public class HttpParameters {
    private String property;
    private String value;

    public HttpParameters(String property, String value) {
        this.property = property;
        this.value = value;
    }

    public String getProperty() {
        return property;
    }

    public String getValue() {
        return value;
    }
}
