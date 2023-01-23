package hu.kovacspeterzoltan.bootcamp.tollsystemapp.storage;

import hu.kovacspeterzoltan.bootcamp.tollsystemapp.entity.MotorwayVignetteEntity;

import java.util.List;

public interface MotorwayVignetteStorageInterface {
    List<MotorwayVignetteEntity> findByRegistrationNumber(String registrationNumber);
}