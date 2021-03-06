package com.gome.project.controler;

import com.gome.project.entity.User;
import com.gome.project.entity.YqmInfo;
import com.gome.project.service.UserService;
import com.gome.project.service.YqmInfoService;
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
@Controller
@RequestMapping(value="/user")
public class LoginControler {
    private final static Logger logger = LoggerFactory.getLogger(LoginControler.class);

    @Autowired
    private UserService userService;
    @Autowired
    private YqmInfoService yqmInfoService;

    @RequestMapping(value="/index")
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
    @RequestMapping(value="/toRegister")
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
    @RequestMapping(value="/register",method = {RequestMethod.POST,RequestMethod.GET})
    public ModelAndView register(HttpServletRequest request, HttpServletResponse response, ModelAndView model,
                                 User user){
        try{
            user.setSyzt("Y");
            int i = userService.registerUser(user);
            if(i==1){
                //注册成功，前往登录页面
                String phone = user.getPhone();
                model.setViewName("/login");
                model.addObject("phone",phone);
                return model;
            }else{
                //注册失败
                model.setViewName("/register");
                model.addObject("message","false");
                model.addObject("phone",user.getPhone());
                model.addObject("email",user.getEmail());

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
    @RequestMapping(value="/toLogin")
    public ModelAndView toLogin(HttpServletRequest request, HttpServletResponse response, ModelAndView model){
        model.setViewName("/login");
        return model;
    }

    /**
     * 用户登录
     * @param request
     * @param response
     * @param model
     * @param phone
     * @param password
     * @return
     */
    @RequestMapping(value="/login",method = {RequestMethod.POST,RequestMethod.GET})
    public ModelAndView login(HttpServletRequest request, HttpServletResponse response, ModelAndView model,
                      @RequestParam("phone")String phone,
                      @RequestParam("password")String password){
        logger.info("登录："+phone+" 密码："+password);
        try{
            User user = new User();
            user.setPassword(password);
            user.setPhone(phone);
            User loginUser = userService.login(user);
            if(loginUser != null){
                //用户登录成功
                model.setViewName("redirect:/user/index");
                //model.addObject("user",loginUser);
                request.getSession().setAttribute("user",loginUser);
            }else{
                //用户登录失败，重新登陆
                model.setViewName("/login");
                model.addObject("phone",phone);
                model.addObject("message","false");
            }

        }catch (Exception e){
            logger.error("系统出现异常",e);
        }

        return model;
    }

    /**
     * 根据用户填写的手机号查询数据库是否能用
     * @param request
     * @param response
     * @param model
     * @param phone
     * @return
     */
    @RequestMapping(value="/checkCondition",method = {RequestMethod.POST,RequestMethod.GET})
    public User chechPhone(HttpServletRequest request, HttpServletResponse response, ModelAndView model,
                           @RequestParam(value="phone",required = false) String phone,
                           @RequestParam(value="email",required = false) String email){
        User user = new User();
        user.setPhone(phone);
        user.setEmail(email);
        User checkUser = userService.checkUserWithConditions(user);
        return checkUser;
    }

    /**
     * 用户注册时检查邀请码是否可用
     * @param request
     * @param response
     * @param model
     * @return
     */
    @RequestMapping(value="/checkYqm",method = {RequestMethod.POST,RequestMethod.GET})
    public YqmInfo checkYqm(HttpServletRequest request, HttpServletResponse response, ModelAndView model,
                            @RequestParam(value="yaoqingma",required = false) String yaoqingma){
        YqmInfo yqmInfo = new YqmInfo();
        yqmInfo.setYaoqingma(yaoqingma);
        YqmInfo checkYqm = yqmInfoService.selectYqmInfoByCondition(yqmInfo);
        return checkYqm;
    }

    //用户修改密码
    @RequestMapping(value="/toChangePassword",method = {RequestMethod.POST,RequestMethod.GET})
    public ModelAndView tochange(HttpServletRequest request, HttpServletResponse response, ModelAndView model,
                                 @RequestParam(value="phone",required = true) String phone){

        User user = new User();
        user.setPhone(phone);

        return model;
    }

    //用户退出
    @RequestMapping(value="/logout")
    public ModelAndView logout(HttpServletRequest request, HttpServletResponse response, ModelAndView model){
        request.getSession().invalidate();
        model.setViewName("redirect:/user/index");
        return model;
    }

}
