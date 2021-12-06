package com.revature.nova.helpers;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

public class RequestHeaders {
    @Getter @Setter
    public List<String> headers;

    public RequestHeaders(){
        headers = new ArrayList<>();
    }
}
