package com.automationTest.config;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;

public class JsonDataProviderUtil {

    public static Object[][] getTestData(String fileName, String dataObject){
        try{
            String fileLocation = System.getProperty("user.dir") + File.separator + "src" + File.separator + "test" + File.separator + "resources" + File.separator + "testData" + File.separator + fileName;
            Reader reader = new FileReader(fileLocation);
            Gson gson = new Gson();
            JsonObject object = gson.fromJson(reader, JsonObject.class);
            JsonArray jsonArray = object.getAsJsonArray(dataObject);
            Object[][] data = new Object[jsonArray.size()][1];
            for (int i = 0; i < jsonArray.size(); i++) {
                data[i][0] = jsonArray.get(i).getAsJsonObject();
            }
            return data;
        }catch (IOException e){
            e.printStackTrace();
        }
        return null;
    }
}
