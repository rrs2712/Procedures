package com.touchsurgery.procedures.data.model;

/**
 * Class to represent a Procedure object. Include getters, setters and toString method.
 *
 * @author Raul RS
 * @version 1.0
 */
public class Procedure {
    private String id;
    private String name;
    private String icon;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    @Override
    public String toString() {
        return "Procedure{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", icon='" + icon + '\'' +
                '}';
    }
}
