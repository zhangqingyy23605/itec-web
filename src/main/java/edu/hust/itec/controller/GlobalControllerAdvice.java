package edu.hust.itec.controller;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created by xsh on 2015/5/12.
 */
@ControllerAdvice
public class GlobalControllerAdvice {

    //处理全局异常
    @ExceptionHandler({Exception.class})
    public ModelAndView handleGlobalException(Exception ex) {
        ModelAndView mav = new ModelAndView();
        mav.setViewName("/common/error");
        mav.addObject("exception", ex);
        return mav;
    }

}
