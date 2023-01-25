package hu.kovacspeterzoltan.bootcamp.tollsystemapp.validator;

import org.json.JSONException;
import org.json.JSONObject;

public class MotorwayVignetteValidator {
    public void registrationNumberValidator(String registrationNumberJsonString) {
        if (registrationNumberJsonString == null || registrationNumberJsonString.equals("")) {
            throw new InvalidRegistrationNumberException();
        }
        try {
            JSONObject jsonObject = new JSONObject(registrationNumberJsonString);
            if (!jsonObject.has("registrationNumber")) {
                throw new InvalidRegistrationNumberException();
            }
        } catch (JSONException e) {
            throw new InvalidRegistrationNumberException();
        }
    }
}