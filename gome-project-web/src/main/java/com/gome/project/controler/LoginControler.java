package com.gome.project.controler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by qiaowentao on 2016/8/17.
 */
@Controller("/")
public class LoginControler {
    private final static Logger logger = LoggerFactory.getLogger(LoginControler.class);

    @RequestMapping("index")
    public ModelAndView index(ModelAndView modelAndView){
        modelAndView.setViewName("/login");
        return modelAndView;
    }

    @RequestMapping("login")
    public ModelAndView login(HttpServletRequest request, HttpServletResponse response, ModelAndView model,
                      @RequestParam("username")String username,
                      @RequestParam("password")String password){
        logger.info("登录："+username+" 密码："+password);
        //System.out.println("登录："+username+" 密码："+password);
        model.setViewName("/success");
        return model;
    }

}
