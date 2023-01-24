package hu.kovacspeterzoltan.bootcamp.tollsystemapp.presenter;

import hu.kovacspeterzoltan.bootcamp.tollsystemapp.parser.MotorwayVignetteParser;
import hu.kovacspeterzoltan.bootcamp.tollsystemapp.validator.MotorwayVignetteValidator;
import hu.kovacspeterzoltan.bootcamp.vehicleregister.api.VehicleRegisterPresenterInterface;
import org.json.JSONException;
import org.json.JSONObject;

public class VehicleRegisterPresenterImp implements VehicleRegisterPresenterInterface {
    private JSONObject responseJsonObject;
    private MotorwayVignetteValidator validator;
    private MotorwayVignetteParser parser;

    public VehicleRegisterPresenterImp() {
        validator = new MotorwayVignetteValidator();
        parser = new MotorwayVignetteParser();
    }

    @Override
    public void displayMessage(String s) {

    }

    @Override
    public void displayJsonResponse(String vehicleRegisterJsonResponse) {
        System.out.println("ITT VAGYOK");
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
