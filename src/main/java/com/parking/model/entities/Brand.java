package com.parking.model.entities;

/**
 * Created by Yurii on 17.01.2017.
 */
public class Brand extends Item<Brand> {
    private String name;

    public Brand() {
    }

    public Brand(Long id,String name,Integer enabled) {
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
        return "Brand{id=" + getId() +
                ", name=" + name +
                ", enabled="+getEnabled()+'}';
    }
}
