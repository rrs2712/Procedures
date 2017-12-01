package com.touchsurgery.procedures.data.model;

/**
 * Created by rrs27 on 2017-11-30.
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
