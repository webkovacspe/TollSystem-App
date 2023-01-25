package hu.kovacspeterzoltan.bootcamp.tollsystemapp.api;

import hu.kovacspeterzoltan.bootcamp.tollsystemapp.dto.VehicleRegisterRequestDTO;
import hu.kovacspeterzoltan.bootcamp.tollsystemapp.dto.VehicleRegisterResponseDTO;

public interface VehicleRegisterPlugInInterface {
    VehicleRegisterResponseDTO findVehicleByRegistrationNumber(VehicleRegisterRequestDTO requestDTO);
}
