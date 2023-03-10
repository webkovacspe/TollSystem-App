package hu.kovacspeterzoltan.bootcamp.tollsystemapp.parser;

import hu.kovacspeterzoltan.bootcamp.tollsystemapp.dto.MotorwayVignetteDTO;
import hu.kovacspeterzoltan.bootcamp.tollsystemapp.dto.MotorwayVignetteRequestDTO;
import hu.kovacspeterzoltan.bootcamp.tollsystemapp.dto.VehicleRegisterRequestDTO;
import hu.kovacspeterzoltan.bootcamp.tollsystemapp.dto.VehicleRegisterResponseDTO;
import hu.kovacspeterzoltan.bootcamp.tollsystemapp.entity.MotorwayVignetteEntity;
import hu.kovacspeterzoltan.bootcamp.tollsystemapp.entity.VehicleEntity;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class MotorwayVignetteParser {
    SimpleDateFormat formatter;

    public MotorwayVignetteParser() {
        formatter = new SimpleDateFormat("yyyy.MM.dd hh:mm:ss");
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
            mve.put("validFrom", dateToDateString(motorwayVignetteEntity.getValidFrom()));
            mve.put("validTo", dateToDateString(motorwayVignetteEntity.getValidTo()));
            mve.put("dateOfPurchase", dateToDateString(motorwayVignetteEntity.getDateOfPurchase()));
            mve.put("isValid", motorwayVignetteEntity.isValid());
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return mve;
    }

    private String dateToDateString(Date date) {
        return formatter.format(date);
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

    public MotorwayVignetteRequestDTO requestJsonStringToDTO(String requestJsonString) {
        MotorwayVignetteRequestDTO dto = new MotorwayVignetteRequestDTO();
        try {
            JSONObject j = new JSONObject(requestJsonString);
            dto.registrationNumber = j.getString("registrationNumber").toUpperCase();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return dto;
    }


    public VehicleRegisterRequestDTO requestDTOToVehicleRegisterRequestDTO(MotorwayVignetteRequestDTO requestDTO) {
        VehicleRegisterRequestDTO dto = new VehicleRegisterRequestDTO();
        dto.registrationNumber = requestDTO.registrationNumber;
        return dto;
    }
}