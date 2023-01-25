package hu.kovacspeterzoltan.bootcamp.tollsystemapp;

import hu.kovacspeterzoltan.bootcamp.tollsystemapp.api.VehicleRegisterPlugInInterface;
import hu.kovacspeterzoltan.bootcamp.tollsystemapp.dto.MotorwayVignetteDTO;
import hu.kovacspeterzoltan.bootcamp.tollsystemapp.dto.MotorwayVignetteRequestDTO;
import hu.kovacspeterzoltan.bootcamp.tollsystemapp.dto.VehicleRegisterRequestDTO;
import hu.kovacspeterzoltan.bootcamp.tollsystemapp.dto.VehicleRegisterResponseDTO;
import hu.kovacspeterzoltan.bootcamp.tollsystemapp.entity.MotorwayVignetteEntity;
import hu.kovacspeterzoltan.bootcamp.tollsystemapp.entity.VehicleEntity;
import hu.kovacspeterzoltan.bootcamp.tollsystemapp.api.MotorwayVignetteAPI;
import hu.kovacspeterzoltan.bootcamp.tollsystemapp.api.MotorwayVignettePresenterInterface;
import hu.kovacspeterzoltan.bootcamp.tollsystemapp.storage.MotorwayVignetteStorageInterface;
import hu.kovacspeterzoltan.bootcamp.tollsystemapp.parser.MotorwayVignetteParser;
import hu.kovacspeterzoltan.bootcamp.tollsystemapp.validator.MotorwayVignetteValidator;

import java.util.List;

public class MotorwayVignetteInteractor implements MotorwayVignetteAPI {
    private final MotorwayVignetteValidator validator;
    private final MotorwayVignetteParser parser;
    private MotorwayVignetteStorageInterface storage;
    private MotorwayVignettePresenterInterface presenter;
    private VehicleRegisterPlugInInterface vehicleRegisterPlugIn;

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

    public void setVehicleRegisterPlugInImp(VehicleRegisterPlugInInterface plugInImp) {
        vehicleRegisterPlugIn = plugInImp;
    }

    @Override
    public void findByRegistrationNumber(String requestJsonString) {
        validator.registrationNumberValidator(requestJsonString);
        MotorwayVignetteRequestDTO requestDTO = parser.requestJsonStringToDTO(requestJsonString);
        VehicleRegisterRequestDTO vehicleRegisterRequestDTO = parser.requestDTOToVehicleRegisterRequestDTO(requestDTO);

        VehicleRegisterResponseDTO vehicleRegisterResponseDTO = vehicleRegisterPlugIn.findVehicleByRegistrationNumber(vehicleRegisterRequestDTO);
        if (vehicleRegisterResponseDTO.isError()) {
            presenter.displayJsonResponse(parser.vehicleRegisterResponsDTOToErrorJsonString(vehicleRegisterResponseDTO));
        } else {
            VehicleEntity vehicle = parser.vehicleRegisterResponseToVehicleEntity(vehicleRegisterResponseDTO);
            List<MotorwayVignetteEntity> motorwayVignettes = storage.findByRegistrationNumber(requestDTO.registrationNumber);
            MotorwayVignetteDTO dto = parser.createDTO(vehicle, motorwayVignettes);
            presenter.displayJsonResponse(parser.dtoToJsonString(dto));
        }
    }
}