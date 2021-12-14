package com.crm.web.filter;

import com.crm.settings.domain.User;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class LoginFilter implements Filter {


    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws IOException, ServletException {

        System.out.println("进入到验证有没有登录过的过滤器");
        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) resp;

        //获取路径
        String path = request.getServletPath();
        //定义不应该被过滤器拦截的资源，自动放行请求
        if("/login.jsp".equals(path) || "/settings/user/login.do".equals(path)){
            chain.doFilter(req,resp);

        //其他资源必须验证有没有登录过
        }else {

            HttpSession session = request.getSession();
            User user = (User) session.getAttribute("user");

            //如果user不为null,说明登录过
            if(user!=null){
                chain.doFilter(req,resp);

                //没有登录过
            }else {
                //重定向到登录页
                response.sendRedirect(request.getContextPath() + "/login.jsp");
            }

        }


    }


    public void init(FilterConfig filterConfig) throws ServletException {

    }


    public void destroy() {

    }
}
