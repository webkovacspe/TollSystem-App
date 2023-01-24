package hu.kovacspeterzoltan.bootcamp.tollsystemapp;

import hu.kovacspeterzoltan.bootcamp.tollsystemapp.dto.MotorwayVignetteDTO;
import hu.kovacspeterzoltan.bootcamp.tollsystemapp.dto.MotorwayVignetteRequestDTO;
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
    public void findByRegistrationNumber(String requestJsonString) {
        validator.registrationNumberValidator(requestJsonString);
        MotorwayVignetteRequestDTO requestDTO = parser.requestJsonStringToDTO(requestJsonString);
        vehicleRegister.findVehicleByRegistrationNumber(parser.requestDTOToVehicleRequestJsonString(requestDTO));
        if (vehicleRegisterPresenter.responseDTO.isError()) {
            presenter.displayJsonResponse(parser.vehicleRegisterResponsDTOToErrorJsonString(vehicleRegisterPresenter.responseDTO));
        } else {
            VehicleEntity vehicle = parser.vehicleRegisterResponseToVehicleEntity(vehicleRegisterPresenter.responseDTO);
            List<MotorwayVignetteEntity> motorwayVignettes = storage.findByRegistrationNumber(requestDTO.registrationNumber);
            MotorwayVignetteDTO dto = parser.createDTO(vehicle, motorwayVignettes);
            presenter.displayJsonResponse(parser.dtoToJsonString(dto));
        }
    }
}