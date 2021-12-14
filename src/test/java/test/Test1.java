package test;

import com.crm.utils.DateTimeUtil;
import com.crm.utils.MD5Util;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Test1 {
    public static void main(String[] args) {
        //验证失效时间:对比时间，小于返回1表示未失效，大于返回-1
        //失效时间
//        String expireTime = "2022-10-10 10:10:10";
//        String currentTime = DateTimeUtil.getSysTime();
//        int count = expireTime.compareTo(currentTime);
//        System.out.println(count);

        //密码加密
        String pwd = "123";
        pwd = MD5Util.getMD5(pwd);
        System.out.println(pwd);

    }
}
