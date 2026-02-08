package com.yourname.selenium.payloads;

import java.util.HashMap;
import java.util.Map;

public class PayloadCreateUser {
     public static Map<String, String> get(String userName, String password) {
        Map<String, String> map = new HashMap<>();
        map.put("userName", userName);
        map.put("password", password);
        return map;
    }   
}
