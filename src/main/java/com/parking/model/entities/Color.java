package com.parking.model.entities;

/**
 * Created by Yurii on 17.01.2017.
 */
public class Color extends Item<Color> {
    private String name;

    public Color() {
    }

    public Color(Long id,String name,Integer enabled) {
        setId(id);
        this.name = name;
        setEnabled(enabled);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Color{id=" + getId() +
                ", name=" + name  +
                ", enabled="+getEnabled()+'}';
    }
}
