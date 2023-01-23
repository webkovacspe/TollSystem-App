package hu.kovacspeterzoltan.bootcamp.tollsystem.parser;

import hu.kovacspeterzoltan.bootcamp.tollsystem.dto.MotorwayVignetteDTO;
import hu.kovacspeterzoltan.bootcamp.tollsystem.entity.MotorwayVignetteEntity;
import hu.kovacspeterzoltan.bootcamp.tollsystem.entity.VehicleEntity;

import java.util.List;

public class MotorwayVignetteParser {
    public String registrationNumber(String registrationNumber) {
        return registrationNumber.toUpperCase();
    }
    public VehicleEntity vehicleJsonStringToVehicleEntity(String vehicleJsonString) {
        return new VehicleEntity();
    }
    public MotorwayVignetteDTO createDTO(VehicleEntity vehicle, List<MotorwayVignetteEntity> motorwayVignettes) {
        return new MotorwayVignetteDTO(vehicle, motorwayVignettes);
    }
    public String jsonStringFromDTO(MotorwayVignetteDTO dto) {
        return "{}";
    }
}