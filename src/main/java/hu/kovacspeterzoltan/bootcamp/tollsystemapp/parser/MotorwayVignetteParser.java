package hu.kovacspeterzoltan.bootcamp.tollsystemapp.parser;

import hu.kovacspeterzoltan.bootcamp.tollsystemapp.dto.MotorwayVignetteDTO;
import hu.kovacspeterzoltan.bootcamp.tollsystemapp.dto.MotorwayVignetteRequestDTO;
import hu.kovacspeterzoltan.bootcamp.tollsystemapp.dto.VehicleRegisterResponseDTO;
import hu.kovacspeterzoltan.bootcamp.tollsystemapp.entity.MotorwayVignetteEntity;
import hu.kovacspeterzoltan.bootcamp.tollsystemapp.entity.VehicleEntity;
import hu.kovacspeterzoltan.bootcamp.tollsystemapp.validator.InvalidRegistrationNumberException;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.List;

public class MotorwayVignetteParser {
    public MotorwayVignetteRequestDTO getRegistrationNumber(String registrationNumberJsonString) {
        try {
            JSONObject jsonObject = new JSONObject(registrationNumberJsonString);
            MotorwayVignetteRequestDTO r = new MotorwayVignetteRequestDTO();
            r.registrationNumber = jsonObject.getString("registrationNumber").toUpperCase();
            return r;
        } catch (JSONException e) {
            throw new InvalidRegistrationNumberException();
        }
    }

    public VehicleEntity vehicleRegisterResponseToVehicleEntity(VehicleRegisterResponseDTO responseDTO) {
        VehicleEntity v = new VehicleEntity();
        v.registrationNumber = responseDTO.registrationNumber;
        v.vehicleType = responseDTO.vehicleType;
        v.make = responseDTO.make;
        v.model = responseDTO.model;
        v.numberOfSeats = responseDTO.numberOfSeats;
        return v;
    }

    public MotorwayVignetteDTO createDTO(VehicleEntity vehicle, List<MotorwayVignetteEntity> motorwayVignettes) {
        return new MotorwayVignetteDTO(vehicle, motorwayVignettes);
    }

    public String dtoToJsonString(MotorwayVignetteDTO dto) {
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("vehicle", vehicleEntityToJsonObject(dto.getVehicle()));
            jsonObject.put("motorwayVignettes", motorwayVignettesToJsonArray(dto.getMotorwayVignettes()));
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return jsonObject.toString();
    }

    private JSONArray motorwayVignettesToJsonArray(List<MotorwayVignetteEntity> motorwayVignetteEntities) {
        JSONArray mv = new JSONArray();
        motorwayVignetteEntities.forEach(motorwayVignetteEntity -> {
            mv.put(motorwayVignetteEntityToJsonObject(motorwayVignetteEntity));
        });
        return mv;
    }

    private JSONObject vehicleEntityToJsonObject(VehicleEntity vehicle) throws JSONException {
        JSONObject v = new JSONObject();
        v.put("registrationNumber", vehicle.registrationNumber);
        v.put("vehicleType", vehicle.vehicleType);
        v.put("make", vehicle.make);
        v.put("model", vehicle.model);
        v.put("numberOfSeats", vehicle.numberOfSeats);
        return v;
    }

    private JSONObject motorwayVignetteEntityToJsonObject(MotorwayVignetteEntity motorwayVignetteEntity) {
        JSONObject mve = new JSONObject();
        try {
            mve.put("registrationNumber", motorwayVignetteEntity.getRegistrationNumber());
            mve.put("vehicleCategory", motorwayVignetteEntity.getVehicleCategory());
            mve.put("motorwayVignetteType", motorwayVignetteEntity.getMotorwayVignetteType());
            mve.put("price", motorwayVignetteEntity.getPrice());
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy.MM.dd hh:mm:ss");
            mve.put("validFrom", formatter.format(motorwayVignetteEntity.getValidFrom()));
            mve.put("validTo", formatter.format(motorwayVignetteEntity.getValidTo()));
            mve.put("dateOfPurchase", formatter.format(motorwayVignetteEntity.getDateOfPurchase()));
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return mve;
    }

    public VehicleRegisterResponseDTO vehicleJsonStringToVehicleDTO(String vehicleRegisterJsonResponse) {
        VehicleRegisterResponseDTO v = null;
        try {
            JSONObject jsonObject = new JSONObject(vehicleRegisterJsonResponse);
            v = new VehicleRegisterResponseDTO();
            String errorMessageKey = "errorMessage";
            if (jsonObject.has(errorMessageKey)) {
                v.errorMessage = jsonObject.getString(errorMessageKey);
            } else {
                v.registrationNumber = jsonObject.getString("registrationNumber").toUpperCase();
                v.vehicleType = jsonObject.getString("vehicleType");
                v.make = jsonObject.getString("make");
                v.model = jsonObject.getString("model");
                v.numberOfSeats = jsonObject.getInt("numberOfSeats");
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return v;
    }

    public String vehicleRegisterResponsDTOToErrorJsonString(VehicleRegisterResponseDTO responseDTO) {
        JSONObject j = new JSONObject();
        try {
            j.put("errorMessage", responseDTO.errorMessage);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return j.toString();
    }
}