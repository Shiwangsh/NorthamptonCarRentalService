package Vehicles;

import java.io.Serializable;

public class HireVehicle implements Serializable {
    private int customerID;
    private  int vehicleID;
    private String vehicleType;
    public HireVehicle(){}

    public int getCustomerID() {
        return customerID;
    }

    public void setCustomerID(int customerID) {
        this.customerID = customerID;
    }

    public int getVehicleID() {
        return vehicleID;
    }

    public void setVehicleID(int vehicleID) {
        this.vehicleID = vehicleID;
    }

    public String getVehicleType() {
        return vehicleType;
    }

    public void setVehicleType(String vehicleType) {
        this.vehicleType = vehicleType;
    }

    @Override
    public String toString() {
        return "HireVehicle{" +
                "customerID=" + customerID +
                ", vehicleID=" + vehicleID +
                ", vehicleType='" + vehicleType + '\'' +
                '}';
    }
}
