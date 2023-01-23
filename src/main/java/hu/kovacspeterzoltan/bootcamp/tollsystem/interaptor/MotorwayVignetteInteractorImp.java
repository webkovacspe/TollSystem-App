package hu.kovacspeterzoltan.bootcamp.tollsystem.interaptor;

import hu.kovacspeterzoltan.bootcamp.tollsystem.dto.MotorwayVignetteDTO;
import hu.kovacspeterzoltan.bootcamp.tollsystem.entity.MotorwayVignetteEntity;
import hu.kovacspeterzoltan.bootcamp.tollsystem.entity.VehicleEntity;
import hu.kovacspeterzoltan.bootcamp.tollsystem.interfaces.MotorwayVignetteRequestInterface;
import hu.kovacspeterzoltan.bootcamp.tollsystem.interfaces.MotorwayVignetteResponseInterface;
import hu.kovacspeterzoltan.bootcamp.tollsystem.interfaces.MotorwayVignetteStorageInterface;
import hu.kovacspeterzoltan.bootcamp.tollsystem.parser.MotorwayVignetteParser;
import hu.kovacspeterzoltan.bootcamp.tollsystem.validator.MotorwayVignetteValidator;

import java.util.List;

public class MotorwayVignetteInteractorImp implements MotorwayVignetteRequestInterface {
    MotorwayVignetteValidator validator;
    MotorwayVignetteParser parser;
    MotorwayVignetteStorageInterface storage;
    MotorwayVignetteResponseInterface response;

    public MotorwayVignetteInteractorImp() {
        validator = new MotorwayVignetteValidator();
        parser = new MotorwayVignetteParser();
    }
    @Override
    public void findByRegistrationNumber(String registrationNumber) {
        validator.registrationNumberValidator(registrationNumber);
        registrationNumber = parser.registrationNumber(registrationNumber);
        // keresés a VehicleRegisterApp-ban
        String vehicleRegisterJsonResponse = "{}";
        // ha van találat, akkor fut tovább, ha nincs akkor hiba vissza

        VehicleEntity vehicle = parser.vehicleJsonStringToVehicleEntity(vehicleRegisterJsonResponse);
        List<MotorwayVignetteEntity> motorwayVignettes = storage.findByRegistrationNumber(registrationNumber);
        MotorwayVignetteDTO dto = parser.createDTO(vehicle, motorwayVignettes);
        response.displayJsonResponse(parser.jsonStringFromDTO(dto));
    }
}