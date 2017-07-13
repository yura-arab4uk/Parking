package com.parking.model.entities;

/**
 * Created by Yurii on 17.01.2017.
 */
public class CarRider extends Item<CarRider> {

    private String name;
    private Integer phone_number;

    public CarRider() {
    }

    public CarRider(Long id,String name, Integer phone_number,Integer enabled) {
        setId(id);
        this.name = name;
        this.phone_number = phone_number;
        setEnabled(enabled);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getPhone_number() {
        return phone_number;
    }

    public void setPhone_number(Integer phone_number) {
        this.phone_number = phone_number;
    }

    @Override
    public String toString() {
        return "CarRider{id=" + getId() +
                ", name=" + name +
                ", phone_number=" + phone_number +
                ", enabled="+getEnabled()+'}';
    }
}
