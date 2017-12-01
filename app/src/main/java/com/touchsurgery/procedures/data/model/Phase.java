package com.touchsurgery.procedures.data.model;

/**
 * Class to represent a Phase object, variable of {@link Detail}. Include getters, setters and toString method.
 *
 * @author Raul RS
 * @version 1.0
 */
public class Phase {
    private String name;
    private String icon;

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
        return "Phase{" +
                "name='" + name + '\'' +
                ", icon='" + icon + '\'' +
                '}';
    }
}
