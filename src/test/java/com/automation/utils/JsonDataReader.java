package com.automation.utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.automation.pojo.LoginData;

import java.io.File;
import java.io.IOException;

public class JsonDataReader {

    public static LoginData[] getLoginData(String filePath) {
        ObjectMapper mapper = new ObjectMapper();
        try {
            return mapper.readValue(new File(filePath), LoginData[].class);
        } catch (IOException e) {
            throw new RuntimeException("Failed to read JSON test data from: " + filePath, e);
        }
    }
}
