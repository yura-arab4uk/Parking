package com.parking.model.entities;

import java.sql.Timestamp;

/**
 * Created by Yurii on 17.01.2017.
 */
public class Car extends Item<Car> implements Comparable<Car> {
    private String car_number;
    private Timestamp checkin_datetime;
    private Timestamp checkout_datetime;
    private Long parking_time;
    private Long brand_id;
    private Long color_id;

    public Car() {
    }

    public Car(Long id,String car_number, Timestamp checkin_datetime, Timestamp checkout_datetime, Long parking_time, Long brand_id, Long color_id,Integer enabled) {
        setId(id);
        this.car_number = car_number;
        this.checkin_datetime = checkin_datetime;
        this.checkout_datetime = checkout_datetime;
        this.parking_time = parking_time;
        this.brand_id = brand_id;
        this.color_id = color_id;
        setEnabled(enabled);
    }

    public String getCar_number() {
        return car_number;
    }

    public void setCar_number(String car_number) {
        this.car_number = car_number;
    }

    public Timestamp getCheckin_datetime() {
        return checkin_datetime;
    }

    public void setCheckin_datetime(Timestamp checkin_datetime) {
        this.checkin_datetime = checkin_datetime;
    }

    public Timestamp getCheckout_datetime() {
        return checkout_datetime;
    }

    public void setCheckout_datetime(Timestamp checkout_datetime) {
        this.checkout_datetime = checkout_datetime;
    }

    public Long getParking_time() {
        return parking_time;
    }

    public void setParking_time(Long parking_time) {
        this.parking_time = parking_time;
    }

    public Long getBrand_id() {
        return brand_id;
    }

    public void setBrand_id(Long brand_id) {
        this.brand_id = brand_id;
    }

    public Long getColor_id() {
        return color_id;
    }

    public void setColor_id(Long color_id) {
        this.color_id = color_id;
    }

    @Override
    public String toString() {
        return "Car{id=" + getId() +
                ", car_number=" + car_number +
                ", checkin_datetime=" + checkin_datetime +
                ", checkout_datetime=" + checkout_datetime +
                ", parking_time=" + parking_time +
                ", brand_id=" + brand_id +
                ", color_id=" + color_id +
                ", enabled="+getEnabled()+'}';
    }



    @Override
    public int compareTo(Car o) {
        return (this.getId() < o.getId()) ? -1 : (this.getId() == o.getId()) ? 0 : 1;
    }

    @Override
    public int hashCode() {
        int result = car_number != null ? car_number.hashCode() : 0;
        result = 31 * result + (checkin_datetime != null ? checkin_datetime.hashCode() : 0);
        return result;
    }
}
