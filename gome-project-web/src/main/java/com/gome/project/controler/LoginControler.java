package com.gome.project.controler;

import com.gome.project.entity.User;
import com.gome.project.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
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

    @Autowired
    private UserService userService;

    @RequestMapping("index")
    public ModelAndView index(ModelAndView modelAndView){
        modelAndView.setViewName("/index");
        return modelAndView;
    }

    /**
     * 去往注册页面
     * @param request
     * @param response
     * @param model
     * @param user
     * @return
     */
    @RequestMapping(value="toRegister")
    public ModelAndView toRegister(HttpServletRequest request, HttpServletResponse response, ModelAndView model,
                                   User user){

        model.addObject("user",user);
        model.setViewName("/register");
        return model;
    }

    /**
     * 用户注册
     * @param request
     * @param response
     * @param model
     * @return
     */
    @RequestMapping(value="register",method = {RequestMethod.POST,RequestMethod.GET})
    public ModelAndView register(HttpServletRequest request, HttpServletResponse response, ModelAndView model,
                                 User user){
        try{
            user.setSyzt("Y");
            int i = userService.registerUser(user);
            if(i==1){
                //注册成功，前往登录页面
                model.setViewName("/login");
                return model;
            }else{

            }
        }catch(Exception e){
            logger.error("系统出现异常",e);
        }
        return model;
    }

    /**
     * 去往登录页面
     * @param request
     * @param response
     * @param model
     * @return
     */
    @RequestMapping("toLogin")
    public ModelAndView toLogin(HttpServletRequest request, HttpServletResponse response, ModelAndView model){
        model.setViewName("/login");
        return model;
    }

    /**
     * 用户登录
     * @param request
     * @param response
     * @param model
     * @param username
     * @param password
     * @return
     */
    @RequestMapping(value="login",method = {RequestMethod.POST,RequestMethod.GET})
    public ModelAndView login(HttpServletRequest request, HttpServletResponse response, ModelAndView model,
                      @RequestParam("username")String username,
                      @RequestParam("password")String password){
        logger.info("登录："+username+" 密码："+password);
        try{
            User user = new User();
            user.setPassword(password);
            user.setUsername(username);
            User loginUser = userService.login(user);
            System.out.println(loginUser);
            if(loginUser != null){
                //用户登录成功
                model.setViewName("/success");
                model.addObject("user",loginUser);
            }else{
                //用户登录失败，重新登陆
                model.setViewName("/login");
            }

        }catch (Exception e){
            logger.error("系统出现异常",e);
        }

        return model;
    }

}
