package com.jyhon.serviceimpl;


import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.jyhon.service.UserEntityService;
import com.oocl.jyhon.dao.EntityDao;
import com.oocl.jyhon.daoimple.UserEntityDaoImple;
import com.oocl.jyhon.entiy.FoodEntity;
import com.oocl.jyhon.entiy.UserEntity;
import com.jyhon.jms.PtpProducter;

import javax.jms.JMSException;

/**
 * Created by ZHANGJA4 on 8/8/2015.
 */
public class UserEntityServiceImpl implements UserEntityService {
    EntityDao userEntityDaoImple = new UserEntityDaoImple();

    public int addEntity(UserEntity userEntity) {
        userEntity.setRole("seller");
        userEntity.setStatusId(3);
        Integer result = userEntityDaoImple.addEntity(userEntity);
        if (result > 0) {
            userEntity.setUserID(result);
            sendMessageToJMS(userEntity);
        }
        return result;
    }

    private void sendMessageToJMS(UserEntity userEntity) {
        Gson gson = new Gson();
        PtpProducter ptpProducter = new PtpProducter();
        JsonObject jsonObject = new JsonObject();
        String appplyString = gson.toJson(userEntity, UserEntity.class);
        jsonObject.addProperty("USER",appplyString);
        try {
            ptpProducter.open();
            ptpProducter.sendMessage(gson.toJson(jsonObject));
        } catch (JMSException e) {
            e.printStackTrace();
        }
    }
    public int updateEntity(UserEntity userEntity) {
        return userEntityDaoImple.updateEntity(userEntity);
    }

    public UserEntity verify(UserEntity userEntity) {

        return (UserEntity) userEntityDaoImple.verify(userEntity);
    }
}
