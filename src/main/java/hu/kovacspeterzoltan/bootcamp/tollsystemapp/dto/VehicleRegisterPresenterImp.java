package hu.kovacspeterzoltan.bootcamp.tollsystemapp.dto;

import hu.kovacspeterzoltan.bootcamp.tollsystemapp.validator.MotorwayVignetteValidator;
import hu.kovacspeterzoltan.bootcamp.vehicleregister.api.VehicleRegisterPresenterInterface;
import org.json.JSONException;
import org.json.JSONObject;

public class VehicleRegisterPresenterImp implements VehicleRegisterPresenterInterface {
    private JSONObject responseJsonObject;
    private MotorwayVignetteValidator validator;

    public VehicleRegisterPresenterImp() {
        validator = new MotorwayVignetteValidator();
    }

    @Override
    public void displayMessage(String s) {

    }
    @Override
    public void displayJsonResponse(String vehicleRegisterJsonResponse) {
        validator.vehicleRegisterResponseValidator(vehicleRegisterJsonResponse);
        try {
            responseJsonObject = new JSONObject(vehicleRegisterJsonResponse);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public JSONObject getResponseJsonObject() {
        return responseJsonObject;
    }
}
