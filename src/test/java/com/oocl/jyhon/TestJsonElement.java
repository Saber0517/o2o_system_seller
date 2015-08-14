package com.oocl.jyhon;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.oocl.jyhon.entiy.FoodEntity;
import com.oocl.jyhon.jms.PtpProducter;
import org.junit.Test;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

/**
 * Created by ZHANGJA4 on 8/14/2015.
 */
public class TestJsonElement {
    @Test
    public void test() throws UnsupportedEncodingException {
        String m = "{\"action\":\"update\",\"content\":\"{\\\"id\\\":3,\\\"mname\\\":\\\"\\\\u0026#32418;\\\\u0026#28903;\\\\u0026#32905;\\\",\\\"price\\\":123.0,\\\"graphId\\\":58,\\\"shopId\\\":3}\",\"mark\":\"3\",\"time\":\"1439517027819\"}";
        Gson gson = new Gson();
        JsonObject jsonObject = gson.fromJson(m, JsonObject.class);
        JsonElement content = jsonObject.get("content");
//        System.out.println(jsonElement.getAsString());

        System.out.println(URLDecoder.decode(content.getAsString(), "utf-8"));
//        String u =
    }
    @Test
    public void test1() throws UnsupportedEncodingException {
        Gson gson = new Gson();
        FoodEntity foodEntity = new FoodEntity();
        foodEntity.setFoodID(1);
        foodEntity.setFoodName("???");

        JsonObject jsonObject = new JsonObject();
        String appplyString = gson.toJson(foodEntity, FoodEntity.class);
        jsonObject.addProperty("FOOD", appplyString);

//        System.out.println(jsonObject.getAsString());
        System.out.println(URLDecoder.decode(jsonObject.toString(),"utf-8"));
    }
}
