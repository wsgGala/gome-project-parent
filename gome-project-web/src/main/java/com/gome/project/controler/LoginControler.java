package com.gome.project.controler;

import org.apache.log4j.Logger;
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
    private Logger logger = Logger.getLogger(LoginControler.class);

    @RequestMapping("index")
    public ModelAndView index(ModelAndView modelAndView){
        modelAndView.setViewName("/login");
        return modelAndView;
    }

    @RequestMapping("login")
    public ModelAndView login(HttpServletRequest request, HttpServletResponse response, ModelAndView model,
                      @RequestParam("username")String username,
                      @RequestParam("password")String password){
        logger.debug("登录："+username+" 密码："+password);
        model.setViewName("/success");
        return model;
    }

}
