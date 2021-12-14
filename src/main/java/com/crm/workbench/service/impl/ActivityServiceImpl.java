package com.crm.workbench.service.impl;

import com.crm.utils.SqlSessionUtil;
import com.crm.workbench.dao.ActivityDao;
import com.crm.workbench.domain.Activity;
import com.crm.workbench.service.ActivityService;

public class ActivityServiceImpl implements ActivityService {

    //反射
    private ActivityDao activityDao = SqlSessionUtil.getSqlSession().getMapper(ActivityDao.class);


    public boolean save(Activity a) {

        boolean flag = true;
        int count = activityDao.save(a);
        if(count != 1){
            flag = false;
        }

        return flag;
    }
}
