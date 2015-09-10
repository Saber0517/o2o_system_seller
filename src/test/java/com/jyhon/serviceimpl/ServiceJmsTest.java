package com.jyhon.serviceimpl;

import com.jyhon.service.UserEntityService;
import com.oocl.jyhon.entiy.FoodEntity;
import com.oocl.jyhon.entiy.UserEntity;
import com.jyhon.service.FoodEntityService;
import org.junit.Test;

import java.util.UUID;


/**
 * Created by ZHANGJA4 on 8/8/2015.
 */
public class ServiceJmsTest {
    @Test
    public void testRegiseter(){
        UserEntity userEntity = new UserEntity();
        userEntity.setUserName("test132");
        userEntity.setPassword("123456789");
        userEntity.setRole("tester");
        userEntity.setTel("18949381");
        userEntity.setLicense("QQ20150808-2@2x.png");
        userEntity.setIdCard(UUID.randomUUID().toString().substring(20));
        UserEntityService userEntityService= new UserEntityServiceImpl();
        //userEntityService.addEntity(userEntity);
    }

    @Test
    public void testAddFood(){
        FoodEntity foodEntity = new FoodEntity();
        foodEntity.setStatusID(3);
        foodEntity.setFoodName("海滩烧烤");
        foodEntity.setUserID(2);
        foodEntity.setTypeID(5);
        foodEntity.setPrice(20.0);
        foodEntity.setPictureURL("123.avi");
        FoodEntityService foodEntityService = new FoodEntityServiceImpl();
        //foodEntityService.addFoodEntity(foodEntity);
    }

}
