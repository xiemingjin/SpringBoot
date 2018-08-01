/*
package com.xiemj.common.Interceptor;

import com.xiemj.common.utils.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.PrintWriter;

@Component
public class InterceptorConfig implements HandlerInterceptor {
    private static final Logger log = LoggerFactory.getLogger(InterceptorConfig.class);

    */
/**
     * 进入controller层之前拦截请求
     * @param request
     * @param response
     * @param o
     * @return
     * @throws Exception
     *//*

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object o) throws Exception {

        String url  =request.getRequestURI();
        System.out.println(url);
        System.out.println("---------------------开始进入请求地址拦截----------------------------");
       */
/* log.info("---------------------开始进入请求地址拦截----------------------------");*//*

        HttpSession session = request.();
        Object obj =  session.getAttributgetSessione("userName");
        String userName = "";
       if(null!= obj )
       {
            userName = (String)obj;
       }
//拦截
        */
/*if(!StringUtils.isEmpty(userName))*//*

        if(StringUtils.isEmpty(userName))
        {
            return true;
        }
        else
        {
            PrintWriter printWriter = response.getWriter();
            printWriter.write("{code:0,message:\"session is invalid,please login again!\"}");
            return false;
        }

    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {
        log.info("--------------处理请求完成后视图渲染之前的处理操作---------------");
    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {
        log.info("---------------视图渲染之后的操作-------------------------0");
    }
}
*/
