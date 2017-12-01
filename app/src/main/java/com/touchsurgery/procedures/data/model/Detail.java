package com.touchsurgery.procedures.data.model;

import java.util.List;

/**
 * Created by rrs27 on 2017-11-30.
 */

public class Detail {

    private String id;
    private String name;
    private List<Phase> phases;
    private String icon;
    private String card;

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

    public List<Phase> getPhases() {
        return phases;
    }

    public void setPhases(List<Phase> phases) {
        this.phases = phases;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getCard() {
        return card;
    }

    public void setCard(String card) {
        this.card = card;
    }

    @Override
    public String toString() {
        return "Detail{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", phases=" + phases +
                ", icon='" + icon + '\'' +
                ", card='" + card + '\'' +
                '}';
    }
}
