package com.xxxx.hcss.controller;

import com.xxxx.hcss.service.IUserService;
import com.xxxx.hcss.vo.LoginVo;
import com.xxxx.hcss.vo.RespBean;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.xxxx.hcss.utils.BaseAction;

import static com.xxxx.hcss.utils.BaseAction.crossComain;


@Controller
@RequestMapping("/login")
@Slf4j
public class LoginController {
    @Autowired
    private IUserService userService;

    /**
     * 登录接口
     * @return
     */
    @RequestMapping("/toLogin")
    public String toLogin(){
        return "login";
    }

    /**
     * 登录跳转
     * @param loginVo
     * @return
     */
    @RequestMapping("/doLogin")
    @ResponseBody
    public RespBean doLogin(LoginVo loginVo, HttpServletRequest request,HttpServletResponse response){
        log.info("{}",loginVo);
        crossComain(request, response);
        return userService.doLogin(loginVo,request,response);
    }

}