package hu.kovacspeterzoltan.bootcamp.tollsystemapp.dto;

import hu.kovacspeterzoltan.bootcamp.vehicleregister.api.VehicleRegisterPresenterInterface;

public class VehicleRegisterPresenterImp implements VehicleRegisterPresenterInterface {
    public String response;
    @Override
    public void displayMessage(String s) {

    }

    @Override
    public void displayJsonRequest(String s) {
        response = s;
    }

    @Override
    public String toString() {
        return response;
    }
}
