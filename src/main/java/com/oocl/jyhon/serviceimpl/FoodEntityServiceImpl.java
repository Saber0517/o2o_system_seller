package com.oocl.jyhon.serviceimpl;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.oocl.jyhon.daoimple.FoodEntityDaoImple;
import com.oocl.jyhon.entiy.FoodEntity;
import com.oocl.jyhon.jms.PtpProducter;
import com.oocl.jyhon.service.FoodEntityService;

import javax.jms.JMSException;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by ZHANGJA4 on 8/7/2015.
 */
public class FoodEntityServiceImpl implements FoodEntityService {

    FoodEntityDaoImple foodEntityDaoImple = new FoodEntityDaoImple();



    public Map<String, String> updateFoodEntity(Integer foodId, Double price) {
        Map<String, String> resultMap = new LinkedHashMap<String, String>();
        Integer result = foodEntityDaoImple.updateFoodEntityPrice(foodId, price);
        if (result > 0) {
            resultMap.put("successMessage", "update success");

        } else {
            resultMap.put("failMessage", "update fail");
        }
        return resultMap;
    }

    public Map<String, String> deleteFoodEntity(Integer foodId, Integer userId) {
        Map<String, String> resultMap = new LinkedHashMap<String, String>();
        Integer result = foodEntityDaoImple.deleteEntityByFoodId(foodId, userId);
        if (result > 0) {
            resultMap.put("SuccessMessage", "delete success");
        } else {
            resultMap.put("ErrorMessage", "fail to delete");
        }
        return resultMap;
    }

    public Map<String, String> addFoodEntity(FoodEntity foodEntity) {
        Map<String, String> resultMap = new LinkedHashMap<String, String>();
        foodEntity.setStatusID(3);
        Integer result = foodEntityDaoImple.addEntity(foodEntity);
        if (result > 0) {
            foodEntity.setFoodID(result);
            String resultMessage = "insert success";
            resultMap.put("succesMessage", resultMessage);
            sendMessageToJMS(foodEntity);
        } else {
            String resultMessage = "insert fail";
            resultMap.put("failMessage", resultMessage);
        }
        return resultMap;
    }

    private void sendMessageToJMS(FoodEntity foodEntity) {
        Gson gson = new Gson();
        PtpProducter ptpProducter = new PtpProducter();
        JsonObject jsonObject = new JsonObject();
        String appplyString = gson.toJson(foodEntity, FoodEntity.class);
        jsonObject.addProperty("FOOD",appplyString);
        try {
            ptpProducter.open();
            ptpProducter.sendMessage(gson.toJson(jsonObject));
        } catch (JMSException e) {
            e.printStackTrace();
        }
    }


    public List<FoodEntity> findAll() {
        List<FoodEntity> foodEntityList = Collections.emptyList();
        foodEntityList = foodEntityDaoImple.findAll();
        return foodEntityList;
    }

    public List<FoodEntity> groupByTypeId(Integer typeId) {
        List<FoodEntity> foodEntityList = Collections.emptyList();
        foodEntityList = foodEntityDaoImple.groupByTypeId(Integer.valueOf(typeId));
        return foodEntityList;
    }
}
