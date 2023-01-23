package hu.kovacspeterzoltan.bootcamp.tollsystemapp.dto;

import hu.kovacspeterzoltan.bootcamp.vehicleregister.api.VehicleRegisterPresenterInterface;

public class VehicleRegisterPresenterImp implements VehicleRegisterPresenterInterface {
    @Override
    public void displayMessage(String s) {

    }
    @Override
    public void displayJsonResponse(String s) {
        System.out.println(s);
    }
}
