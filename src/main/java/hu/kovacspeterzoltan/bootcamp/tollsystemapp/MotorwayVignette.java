package hu.kovacspeterzoltan.bootcamp.tollsystemapp;

import hu.kovacspeterzoltan.bootcamp.tollsystemapp.dto.MotorwayVignetteDTO;
import hu.kovacspeterzoltan.bootcamp.tollsystemapp.entity.MotorwayVignetteEntity;
import hu.kovacspeterzoltan.bootcamp.tollsystemapp.entity.VehicleEntity;
import hu.kovacspeterzoltan.bootcamp.tollsystemapp.api.MotorwayVignetteAPI;
import hu.kovacspeterzoltan.bootcamp.tollsystemapp.api.MotorwayVignettePresenter;
import hu.kovacspeterzoltan.bootcamp.tollsystemapp.storage.MotorwayVignetteStorageInterface;
import hu.kovacspeterzoltan.bootcamp.tollsystemapp.parser.MotorwayVignetteParser;
import hu.kovacspeterzoltan.bootcamp.tollsystemapp.validator.MotorwayVignetteValidator;

import java.util.List;

public class MotorwayVignette implements MotorwayVignetteAPI {
    MotorwayVignetteValidator validator;
    MotorwayVignetteParser parser;
    MotorwayVignetteStorageInterface storage;
    MotorwayVignettePresenter response;

    public MotorwayVignette() {
        validator = new MotorwayVignetteValidator();
        parser = new MotorwayVignetteParser();
    }
    @Override
    public void findByRegistrationNumber(String registrationNumberJsonString) {
        validator.registrationNumberValidator(registrationNumberJsonString);
        String registrationNumber = parser.registrationNumber(registrationNumberJsonString);

        // keresés a VehicleRegisterApp-ban
        String vehicleRegisterJsonResponse = "{}";
        // ha van találat, akkor fut tovább, ha nincs akkor hiba vissza
        validator.vehicleRegisterResponseValidator(vehicleRegisterJsonResponse);
        // validálás + parszolás
        VehicleEntity vehicle = parser.vehicleJsonStringToVehicleEntity(vehicleRegisterJsonResponse);

        List<MotorwayVignetteEntity> motorwayVignettes = storage.findByRegistrationNumber(registrationNumber);
        MotorwayVignetteDTO dto = parser.createDTO(vehicle, motorwayVignettes);
        response.displayJsonResponse(parser.DTOToJsonString(dto));
    }
}