package hu.kovacspeterzoltan.bootcamp.tollsystemapp.dto;

public class VehicleRegisterResponseDTO {
    public String errorMessage;
    public String registrationNumber;
    public String vehicleType;
    public String make;
    public String model;
    public int numberOfSeats;

    public boolean isError() {
        return (errorMessage != null);
    }
}