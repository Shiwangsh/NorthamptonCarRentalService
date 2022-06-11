package Vehicles;

import java.io.*;
public class Car implements Serializable {
    private int regNum;
    private String make;
    private String model;
    private String vehicleType;
    private int topSpeed;
    private String fuelType;
    private double hireRate;
    private int numOfDoors;
    private int hiredTo;
    public Car() {}

    public int getRegNum() {
        return regNum;
    }

    public void setRegNum(int regNum) {
        this.regNum = regNum;
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

    public String getVehicleType() {
        return vehicleType;
    }

    public void setVehicleType(String vehicleType) {
        this.vehicleType = vehicleType;
    }

    public int getTopSpeed() {
        return topSpeed;
    }

    public void setTopSpeed(int topSpeed) {
        this.topSpeed = topSpeed;
    }

    public String getFuelType() {
        return fuelType;
    }

    public void setFuelType(String fuelType) {
        this.fuelType = fuelType;
    }

    public double getHireRate() {
        return hireRate;
    }

    public void setHireRate(double hireRate) {
        this.hireRate = hireRate;
    }

    public int getNumOfDoors() {
        return numOfDoors;
    }

    public void setNumOfDoors(int numOfDoors) {
        this.numOfDoors = numOfDoors;
    }

    public int getHiredTo() {
        return hiredTo;
    }

    public void setHiredTo(int hiredTo) {
        this.hiredTo = hiredTo;
    }

    @Override
    public String toString() {
        return "Car{" +
                "regNum=" + regNum +
                ", make='" + make + '\'' +
                ", model='" + model + '\'' +
                ", vehicleType='" + vehicleType + '\'' +
                ", topSpeed=" + topSpeed +
                ", fuelType='" + fuelType + '\'' +
                ", hireRate=" + hireRate +
                ", numOfDoors=" + numOfDoors +
                ", hiredTo=" + hiredTo +
                '}';
    }

    }



