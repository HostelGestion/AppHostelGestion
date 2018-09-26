package org.ifes.isis.tesis.applib.org.ifes.isis.tesis.applib.request;

/**
 * Created by router on 20/11/17.
 */

public class HttpHeaders {
    private String property;
    private String value;

    public HttpHeaders(String property, String value) {
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
