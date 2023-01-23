package hu.kovacspeterzoltan.bootcamp.tollsystemapp.parser;

import hu.kovacspeterzoltan.bootcamp.tollsystemapp.dto.MotorwayVignetteDTO;
import hu.kovacspeterzoltan.bootcamp.tollsystemapp.entity.MotorwayVignetteEntity;
import hu.kovacspeterzoltan.bootcamp.tollsystemapp.entity.VehicleEntity;

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
    public String DTOToJsonString(MotorwayVignetteDTO dto) {
        return "{}";
    }
}