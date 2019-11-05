package com.example.wpls;

import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

public class AddRequest extends StringRequest {

    final private static String URL = "http://whitesnowtiger.com/android/ClassAdd.php";
    private Map<String, String> parameters;

    public AddRequest(String userID, String classID, Response.Listener<String> listener) {
        super(Method.POST, URL, listener, null);

        parameters = new HashMap<String, String>();
        parameters.put("userID", userID);
        parameters.put("classID", classID);

    }

    public Map<String, String> getParams() { return parameters;}
}
