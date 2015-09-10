package com.jyhon.servlet.foodpackage;

import com.oocl.jyhon.daoimple.FoodEntityDaoImple;
import com.oocl.jyhon.entiy.FoodEntity;
import com.oocl.jyhon.entiy.PackageEntity;
import com.oocl.jyhon.entiy.UserEntity;
import com.oocl.jyhon.util.FormDataUtil;
import org.apache.commons.fileupload.FileItem;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.List;

/**
 * Created by ZHANGJA4 on 8/7/2015.
 */
@WebServlet(name = "NewFoodServlet", urlPatterns = {"/NewPackageServlet"})

public class NewPackageServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("main/newFood.jsp").forward(request, response);
        return;
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }


}
