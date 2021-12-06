package com.revature.nova.helpers;

import lombok.Getter;
import lombok.Setter;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class RequestHeaders {
    @Getter @Setter
    public JSONObject jsonObject;

    @Getter @Setter
    public List<String> headers;

    public RequestHeaders(){
        jsonObject = new JSONObject();
        headers = new ArrayList<>();
    }
}
