package hu.kovacspeterzoltan.bootcamp.tollsystemapp.presenter;

import hu.kovacspeterzoltan.bootcamp.tollsystemapp.dto.VehicleRegisterResponseDTO;
import hu.kovacspeterzoltan.bootcamp.tollsystemapp.parser.MotorwayVignetteParser;
import hu.kovacspeterzoltan.bootcamp.tollsystemapp.validator.MotorwayVignetteValidator;
import hu.kovacspeterzoltan.bootcamp.vehicleregister.api.VehicleRegisterPresenterInterface;

public class VehicleRegisterPresenterImp implements VehicleRegisterPresenterInterface {
    private final MotorwayVignetteValidator validator;
    private final MotorwayVignetteParser parser;
    public VehicleRegisterResponseDTO responseDTO;

    public VehicleRegisterPresenterImp() {
        validator = new MotorwayVignetteValidator();
        parser = new MotorwayVignetteParser();
    }

    @Override
    public void displayMessage(String s) {

    }

    @Override
    public void displayJsonResponse(String vehicleRegisterJsonResponse) {
        validator.vehicleRegisterResponseValidator(vehicleRegisterJsonResponse);
        responseDTO = parser.vehicleJsonStringToVehicleDTO(vehicleRegisterJsonResponse);
    }
}