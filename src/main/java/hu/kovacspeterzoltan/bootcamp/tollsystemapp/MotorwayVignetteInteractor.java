package hu.kovacspeterzoltan.bootcamp.tollsystemapp;

import hu.kovacspeterzoltan.bootcamp.tollsystemapp.dto.MotorwayVignetteDTO;
import hu.kovacspeterzoltan.bootcamp.tollsystemapp.dto.MotorwayVignetteRequestDTO;
import hu.kovacspeterzoltan.bootcamp.tollsystemapp.dto.VehicleRegisterResponseDTO;
import hu.kovacspeterzoltan.bootcamp.tollsystemapp.entity.MotorwayVignetteEntity;
import hu.kovacspeterzoltan.bootcamp.tollsystemapp.entity.VehicleEntity;
import hu.kovacspeterzoltan.bootcamp.tollsystemapp.api.MotorwayVignetteAPI;
import hu.kovacspeterzoltan.bootcamp.tollsystemapp.api.MotorwayVignettePresenterInterface;
import hu.kovacspeterzoltan.bootcamp.tollsystemapp.storage.MotorwayVignetteStorageInterface;
import hu.kovacspeterzoltan.bootcamp.tollsystemapp.parser.MotorwayVignetteParser;
import hu.kovacspeterzoltan.bootcamp.tollsystemapp.validator.MotorwayVignetteValidator;
import hu.kovacspeterzoltan.bootcamp.vehicleregister.api.VehicleRegisterAPI;
import hu.kovacspeterzoltan.bootcamp.vehicleregister.api.VehicleRegisterPresenterInterface;

import java.util.List;

public class MotorwayVignetteInteractor implements MotorwayVignetteAPI, VehicleRegisterPresenterInterface {
    private MotorwayVignetteValidator validator;
    private MotorwayVignetteParser parser;

    private MotorwayVignetteStorageInterface storage;
    private MotorwayVignettePresenterInterface presenter;
    private VehicleRegisterAPI vehicleRegister;
    private VehicleRegisterResponseDTO vehicleRegisterRequest;

    public MotorwayVignetteInteractor() {
        validator = new MotorwayVignetteValidator();
        parser = new MotorwayVignetteParser();
    }

    public void setStorageImp(MotorwayVignetteStorageInterface storageImp) {
        this.storage = storageImp;
    }

    public void setPresenterImp(MotorwayVignettePresenterInterface responseImp) {
        this.presenter = responseImp;
    }

    public void setVehicleRegisterImp(VehicleRegisterAPI vehicleRegisterImp) {
        this.vehicleRegister = vehicleRegisterImp;
    }

    @Override
    public void findByRegistrationNumber(String registrationNumberJsonString) {
        validator.registrationNumberValidator(registrationNumberJsonString);
        MotorwayVignetteRequestDTO requestDTO = parser.getRegistrationNumber(registrationNumberJsonString);
        vehicleRegister.findVehicleByRegistrationNumber(registrationNumberJsonString);
    }

    @Override
    public void displayMessage(String s) {

    }

    @Override
    public void displayJsonResponse(String vehicleRegisterJsonResponse) {
        validator.vehicleRegisterResponseValidator(vehicleRegisterJsonResponse);
        vehicleRegisterRequest = parser.vehicleJsonStringToVehicleDTO(vehicleRegisterJsonResponse);
        if (vehicleRegisterRequest.isError()) {
            presenter.displayJsonResponse(vehicleRegisterJsonResponse);
        } else {
            VehicleEntity vehicle = parser.vehicleRegisterResponseToVehicleEntity(vehicleRegisterRequest);
            List<MotorwayVignetteEntity> motorwayVignettes = storage.findByRegistrationNumber(vehicleRegisterRequest.registrationNumber);
            MotorwayVignetteDTO dto = parser.createDTO(vehicle, motorwayVignettes);
            presenter.displayJsonResponse(parser.dtoToJsonString(dto));
        }
    }
}