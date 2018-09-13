package com.xiemj.common.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.sql.SQLException;

@ControllerAdvice
@Slf4j
public class GlobalExceptionHandler {


    private final static String EXPTION_MSG_KEY = "message";

    @ExceptionHandler(BusinessException.class)
    @ResponseBody
    public void handleBizExp(HttpServletRequest request, Exception ex){
        log.info("Business exception handler  " + ex.getMessage() );
        request.getSession(true).setAttribute(EXPTION_MSG_KEY, ex.getMessage());
    }

    @ExceptionHandler(RuntimeException.class)
    public ModelAndView handSql(Exception ex){
        log.info("SQL Exception " + ex.getMessage());
        ModelAndView mv = new ModelAndView();
        mv.addObject("message", ex.getMessage());
        mv.setViewName("login");
        return mv;
    }
}
