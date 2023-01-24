package hu.kovacspeterzoltan.bootcamp.tollsystemapp;

import hu.kovacspeterzoltan.bootcamp.tollsystemapp.dto.MotorwayVignetteDTO;
import hu.kovacspeterzoltan.bootcamp.tollsystemapp.dto.VehicleRegisterPresenterImp;
import hu.kovacspeterzoltan.bootcamp.tollsystemapp.entity.MotorwayVignetteEntity;
import hu.kovacspeterzoltan.bootcamp.tollsystemapp.entity.VehicleEntity;
import hu.kovacspeterzoltan.bootcamp.tollsystemapp.api.MotorwayVignetteAPI;
import hu.kovacspeterzoltan.bootcamp.tollsystemapp.api.MotorwayVignettePresenterInterface;
import hu.kovacspeterzoltan.bootcamp.tollsystemapp.storage.MotorwayVignetteStorageInterface;
import hu.kovacspeterzoltan.bootcamp.tollsystemapp.parser.MotorwayVignetteParser;
import hu.kovacspeterzoltan.bootcamp.tollsystemapp.validator.MotorwayVignetteValidator;
import hu.kovacspeterzoltan.bootcamp.vehicleregister.api.VehicleRegisterAPI;
import org.json.JSONObject;

import java.util.List;

public class MotorwayVignetteInteractor implements MotorwayVignetteAPI {
    private MotorwayVignetteValidator validator;
    private MotorwayVignetteParser parser;

    private MotorwayVignetteStorageInterface storage;
    private MotorwayVignettePresenterInterface presenter;
    private VehicleRegisterAPI vehicleRegister;
    //private VehicleRegisterPresenterInterface vehicleRegisterPresenter;
    private VehicleRegisterPresenterImp vehicleRegisterPresenter;

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
    public void setVehicleRegisterPresenter(VehicleRegisterPresenterImp vehicleRegisterPresenter) {
        this.vehicleRegisterPresenter = vehicleRegisterPresenter;
    }
    @Override
    public void findByRegistrationNumber(String registrationNumberJsonString) {
        validator.registrationNumberValidator(registrationNumberJsonString);
        JSONObject registrationNumberJsonObject = parser.getRegistrationNumber(registrationNumberJsonString);

        // keresés a VehicleRegisterApp-ban
        //TODO ezt meg hogy kéne megcsinálni?
        vehicleRegister.findVehicleByRegistrationNumber(registrationNumberJsonObject.toString());
        JSONObject vehicleRegisterJsonResponse = vehicleRegisterPresenter.getResponseJsonObject();
        if (vehicleRegisterJsonResponse.has("errorMessage")) {
            presenter.displayJsonResponse(vehicleRegisterJsonResponse.toString());
        } else {
            VehicleEntity vehicle = parser.vehicleJsonStringToVehicleEntity(vehicleRegisterJsonResponse);
            List<MotorwayVignetteEntity> motorwayVignettes = storage.findByRegistrationNumber(vehicle.registrationNumber);
            MotorwayVignetteDTO dto = parser.createDTO(vehicle, motorwayVignettes);
            presenter.displayJsonResponse(parser.dtoToJsonString(dto));
        }
    }
}