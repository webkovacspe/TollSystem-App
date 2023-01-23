package hu.kovacspeterzoltan.bootcamp.tollsystem.interfaces;

import hu.kovacspeterzoltan.bootcamp.tollsystem.entity.MotorwayVignetteEntity;

import java.util.List;

public interface MotorwayVignetteStorageInterface {
    List<MotorwayVignetteEntity> findByRegistrationNumber(String registrationNumber);
}