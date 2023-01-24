package hu.kovacspeterzoltan.bootcamp.tollsystemapp;

import hu.kovacspeterzoltan.bootcamp.tollsystemapp.dto.MotorwayVignetteDTO;
import hu.kovacspeterzoltan.bootcamp.tollsystemapp.entity.MotorwayVignetteEntity;
import hu.kovacspeterzoltan.bootcamp.tollsystemapp.entity.VehicleEntity;
import hu.kovacspeterzoltan.bootcamp.tollsystemapp.api.MotorwayVignetteAPI;
import hu.kovacspeterzoltan.bootcamp.tollsystemapp.api.MotorwayVignettePresenterInterface;
import hu.kovacspeterzoltan.bootcamp.tollsystemapp.presenter.VehicleRegisterPresenterImp;
import hu.kovacspeterzoltan.bootcamp.tollsystemapp.storage.MotorwayVignetteStorageInterface;
import hu.kovacspeterzoltan.bootcamp.tollsystemapp.parser.MotorwayVignetteParser;
import hu.kovacspeterzoltan.bootcamp.tollsystemapp.validator.MotorwayVignetteValidator;
import hu.kovacspeterzoltan.bootcamp.vehicleregister.api.VehicleRegisterAPI;

import java.util.List;

public class MotorwayVignetteInteractor implements MotorwayVignetteAPI {
    private final MotorwayVignetteValidator validator;
    private final MotorwayVignetteParser parser;

    private MotorwayVignetteStorageInterface storage;
    private MotorwayVignettePresenterInterface presenter;
    private VehicleRegisterAPI vehicleRegister;
    private VehicleRegisterPresenterImp vehicleRegisterPresenter;

    public MotorwayVignetteInteractor() {
        validator = new MotorwayVignetteValidator();
        parser = new MotorwayVignetteParser();
    }

    public void setStorageImp(MotorwayVignetteStorageInterface storageImp) {
        storage = storageImp;
    }

    public void setPresenterImp(MotorwayVignettePresenterInterface responseImp) {
        presenter = responseImp;
    }

    public void setVehicleRegisterImp(VehicleRegisterAPI vehicleRegisterImp) {
        vehicleRegister = vehicleRegisterImp;
    }

    public void setVehicleRegisterPresenterImp(VehicleRegisterPresenterImp vehicleRegisterPresenterImp) {
        vehicleRegisterPresenter = vehicleRegisterPresenterImp;
    }

    @Override
    public void findByRegistrationNumber(String registrationNumberJsonString) {
        validator.registrationNumberValidator(registrationNumberJsonString);
        //TODO itt requestDTO-t kell csinálni
//        MotorwayVignetteRequestDTO requestDTO = parser.getRegistrationNumber(registrationNumberJsonString);
        vehicleRegister.findVehicleByRegistrationNumber(registrationNumberJsonString);
        if (vehicleRegisterPresenter.responseDTO.isError()) {
            presenter.displayJsonResponse(parser.vehicleRegisterResponsDTOToErrorJsonString(vehicleRegisterPresenter.responseDTO));
        } else {
            VehicleEntity vehicle = parser.vehicleRegisterResponseToVehicleEntity(vehicleRegisterPresenter.responseDTO);
            //TODO a requestDTO.rendszám-ot kell használni a kereséshez
            List<MotorwayVignetteEntity> motorwayVignettes = storage.findByRegistrationNumber(vehicle.registrationNumber);
            MotorwayVignetteDTO dto = parser.createDTO(vehicle, motorwayVignettes);
            presenter.displayJsonResponse(parser.dtoToJsonString(dto));
        }
    }
}