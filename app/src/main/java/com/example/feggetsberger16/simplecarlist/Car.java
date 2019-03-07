package com.example.feggetsberger16.simplecarlist;

/**
 * Created by feggetsberger16 on 07.03.2019.
 */

public class Car {
    private String first_name;
    private String last_name;
    private String make;
    private String model;

    public Car(String first_name, String last_name, String make, String model) {
        this.first_name = first_name;
        this.last_name = last_name;
        this.make = make;
        this.model = model;
    }

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public String getMake() {
        return make;
    }

    public void setMake(String make) {
        this.make = make;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }
}
