package hu.kovacspeterzoltan.bootcamp.tollsystemapp.parser;

import hu.kovacspeterzoltan.bootcamp.tollsystemapp.dto.MotorwayVignetteDTO;
import hu.kovacspeterzoltan.bootcamp.tollsystemapp.entity.MotorwayVignetteEntity;
import hu.kovacspeterzoltan.bootcamp.tollsystemapp.entity.VehicleEntity;
import hu.kovacspeterzoltan.bootcamp.tollsystemapp.validator.InvalidRegistrationNumberException;
import hu.kovacspeterzoltan.bootcamp.tollsystemapp.validator.InvalidVehicleJsonFormatException;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
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
    public VehicleEntity vehicleJsonStringToVehicleEntity(JSONObject vehicleJson) {
        try {
            VehicleEntity v = new VehicleEntity();
            v.registrationNumber = vehicleJson.getString("registrationNumber");
            v.vehicleType = vehicleJson.getString("vehicleType");
            v.make = vehicleJson.getString("make");
            v.model = vehicleJson.getString("model");
            v.numberOfSeats = vehicleJson.getInt("numberOfSeats");
            return v;
        } catch (JSONException e) {
            throw new InvalidVehicleJsonFormatException();
        }
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
}