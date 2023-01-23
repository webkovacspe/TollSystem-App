package hu.kovacspeterzoltan.bootcamp.tollsystem.dto;

import hu.kovacspeterzoltan.bootcamp.tollsystem.entity.MotorwayVignetteEntity;
import hu.kovacspeterzoltan.bootcamp.tollsystem.entity.VehicleEntity;

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