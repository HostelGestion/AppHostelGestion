package org.ifes.isis.tesis.applib.generic;

/**
 * Created by router on 31/12/17.
 */

public class GenericItem {
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public GenericItem(String name) {
        this.name = name;
    }
}
