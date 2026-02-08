package com.yourname.selenium.payloads;

import java.util.HashMap;
import java.util.Map;
import java.util.List;

public class PayloadAddBook {

    public static Map<String, Object> payloadAddBook(String userId) {
        // Create the payload
        Map<String, Object> payload = new HashMap<>();
        
        // Add userId to the payload
        payload.put("userId", userId);
        
        // Add collectionOfIsbns to the payload (list of maps)
        payload.put("collectionOfIsbns", List.of(
            Map.of("isbn", "9781449325862")
        ));
        
        return payload;
    }
}
