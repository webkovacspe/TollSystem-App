package hu.kovacspeterzoltan.bootcamp.tollsystemapp.dto;

import hu.kovacspeterzoltan.bootcamp.tollsystemapp.entity.MotorwayVignetteEntity;
import hu.kovacspeterzoltan.bootcamp.tollsystemapp.entity.VehicleEntity;

import java.util.List;

public class MotorwayVignetteDTO {
    private VehicleEntity vehicle;
    private List<MotorwayVignetteEntity> motorwayVignettes;

    public MotorwayVignetteDTO(VehicleEntity vehicle, List<MotorwayVignetteEntity> motorwayVignettes) {
        this.vehicle = vehicle;
        this.motorwayVignettes = motorwayVignettes;
    }

    public VehicleEntity getVehicle() {
        return vehicle;
    }
    public void setVehicle(VehicleEntity vehicle) {
        this.vehicle = vehicle;
    }
    public List<MotorwayVignetteEntity> getMotorwayVignettes() {
        return motorwayVignettes;
    }
    public void setMotorwayVignettes(List<MotorwayVignetteEntity> motorwayVignettes) {
        this.motorwayVignettes = motorwayVignettes;
    }
}