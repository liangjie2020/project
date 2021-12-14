package com.crm.workbench.web.controller;

import com.crm.settings.domain.User;
import com.crm.settings.service.UserService;
import com.crm.settings.service.impl.UserServiceImpl;
import com.crm.utils.*;
import com.crm.workbench.domain.Activity;
import com.crm.workbench.service.ActivityService;
import com.crm.workbench.service.impl.ActivityServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

public class ActivityController extends HttpServlet {

    //设计模板
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        System.out.println("进入市场活动控制器");
        //获取地址
        String path = request.getServletPath();
        if("/workbench/activity/getUserList.do".equals(path)){

            getUserList(request,response);

        }else if("/workbench/activity/save.do".equals(path)){
            save(request,response);
        }


    }

    private void save(HttpServletRequest request, HttpServletResponse response) {

       System.out.println("执行市场活动的添加操作");

       String id  = UUIDUtil.getUUID();
       String owner = request.getParameter("owner");
       String name = request.getParameter("name");
       String startDate = request.getParameter("startDate");
       String endDate = request.getParameter("endDate");
       String cost = request.getParameter("cost");
       String description = request.getParameter("description");
       //创建时间，当前系统时间
       String createTime = DateTimeUtil.getSysTime();
       //创建人：当前登录用户
       String createBy = ((User)request.getSession().getAttribute("user")).getName();

       Activity a = new Activity();
       a.setId(id);
       a.setCost(cost);
       a.setStartDate(startDate);
       a.setOwner(owner);
       a.setName(name);
       a.setEndDate(endDate);
       a.setDescription(description);
       a.setCreateTime(createTime);
       a.setCreateBy(createBy);

       //动态代理创建对象
       ActivityService as = (ActivityService) ServiceFactory.getService(new ActivityServiceImpl());
       boolean flag = as.save(a);

        //将boolean值解析为json串
        PrintJson.printJsonFlag(response,flag);


    }

    private void getUserList(HttpServletRequest request, HttpServletResponse response) {
        /*这段代码需要牢记*/

        System.out.println("取得用户信息列表");
        //代理创建对象
        UserService us = (UserService) ServiceFactory.getService(new UserServiceImpl());
        List<User> uList = us.getUserList();
        //转换集合成json类型
        PrintJson.printJsonObj(response,uList);
    }


}
