package hu.kovacspeterzoltan.bootcamp.tollsystemapp.entity;

import java.util.Date;

public class MotorwayVignetteEntity {
    public String registrationNumber;
    public String vehicleCategory;
    public String motorwayVignetteType;
    public float price;
    public Date validFrom;
    public Date validTo;
    public Date dateOfPurchase;

    public String getRegistrationNumber() {
        return registrationNumber;
    }

    public void setRegistrationNumber(String registrationNumber) {
        this.registrationNumber = registrationNumber;
    }

    public String getVehicleCategory() {
        return vehicleCategory;
    }

    public void setVehicleCategory(String vehicleCategory) {
        this.vehicleCategory = vehicleCategory;
    }

    public String getMotorwayVignetteType() {
        return motorwayVignetteType;
    }

    public void setMotorwayVignetteType(String motorwayVignetteType) {
        this.motorwayVignetteType = motorwayVignetteType;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public Date getValidFrom() {
        return validFrom;
    }

    public void setValidFrom(Date validFrom) {
        this.validFrom = validFrom;
    }

    public Date getValidTo() {
        return validTo;
    }

    public void setValidTo(Date validTo) {
        this.validTo = validTo;
    }

    public Date getDateOfPurchase() {
        return dateOfPurchase;
    }

    public void setDateOfPurchase(Date dateOfPurchase) {
        this.dateOfPurchase = dateOfPurchase;
    }

    public boolean isValid() {
        Date currentDate = new Date();
        return (currentDate.compareTo(validFrom) >= 0 && currentDate.compareTo(validTo) <= 0);
    }
}