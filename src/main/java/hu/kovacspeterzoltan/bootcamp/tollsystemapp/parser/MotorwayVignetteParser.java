package hu.kovacspeterzoltan.bootcamp.tollsystemapp.parser;

import hu.kovacspeterzoltan.bootcamp.tollsystemapp.dto.MotorwayVignetteDTO;
import hu.kovacspeterzoltan.bootcamp.tollsystemapp.entity.MotorwayVignetteEntity;
import hu.kovacspeterzoltan.bootcamp.tollsystemapp.entity.VehicleEntity;
import hu.kovacspeterzoltan.bootcamp.tollsystemapp.validator.InvalidRegistrationNumberException;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

public class MotorwayVignetteParser {
    public JSONObject getRegistrationNumber(String registrationNumberJsonString) {
        try {
            JSONObject jsonObject = new JSONObject(registrationNumberJsonString);
            jsonObject.put("registrationNumber", jsonObject.getString("registrationNumber").toUpperCase());
            return jsonObject;
        } catch (JSONException e) {
            throw new InvalidRegistrationNumberException();
        }
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