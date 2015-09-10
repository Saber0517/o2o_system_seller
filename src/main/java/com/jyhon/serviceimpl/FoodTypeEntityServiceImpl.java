package com.jyhon.serviceimpl;

import com.oocl.jyhon.dao.EntityDao;
import com.oocl.jyhon.daoimple.FoodTypeEntityDaoImple;
import com.oocl.jyhon.entiy.FoodTypeEntity;
import com.jyhon.service.FoodTypeEntityService;

import java.util.List;

/**
 * Created by ZHANGJA4 on 8/8/2015.
 */
public class FoodTypeEntityServiceImpl implements FoodTypeEntityService {
    EntityDao foodTypeEntityDaoImple = new FoodTypeEntityDaoImple();

    public List<FoodTypeEntity> findAll() {
        List<FoodTypeEntity> foodTypeEntityList = foodTypeEntityDaoImple.findAll();
        return foodTypeEntityList;
    }
}
