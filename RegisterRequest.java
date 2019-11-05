package com.example.wpls;

import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

public class RegisterRequest extends StringRequest {

    //final static private String URL = "http://10.0.2.2/UserRegister.php";
    final static private String URL = "http://whitesnowtiger.com/android/UserRegister.php";
    private Map<String, String> parameters;

    public RegisterRequest(String userID, String userPassword, String userEmail, String userGender, String userGrade, Response.Listener<String> listener) {
        super(Method.POST, URL, listener, null);
        parameters = new HashMap<>();
        parameters.put("userID", userID);
        parameters.put("userPassword", userPassword);
        parameters.put("userEmail", userEmail);
        parameters.put("userGender", userGender);
        parameters.put("userGrade", userGrade);


    }

    @Override
    public Map<String, String> getParams() {
        return parameters;
    }
}
