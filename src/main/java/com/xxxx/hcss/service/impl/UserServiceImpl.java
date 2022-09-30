package com.xxxx.hcss.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.xxxx.hcss.pojo.User;
import com.xxxx.hcss.mapper.UserMapper;
import com.xxxx.hcss.service.IUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xxxx.hcss.utils.MD5Util;
import com.xxxx.hcss.utils.ValidatorUtil;
import com.xxxx.hcss.vo.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.thymeleaf.util.StringUtils;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author zhumeng
 * @since 2022-09-29
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public RespBean doLogin(LoginVo loginVo, HttpServletRequest request, HttpServletResponse response) {
        String phone=loginVo.getMobile();
        String password=loginVo.getPassword();

        System.out.println(phone);
        System.out.println(password);

        if(StringUtils.isEmpty(phone)||StringUtils.isEmpty(password)){//账号或密码为空时报错
            return RespBean.error(RespBeanEnum.LOGIN_ERROR);
        }
        if(!ValidatorUtil.isMobile(phone)){//手机号不合规范报错
            return RespBean.error(RespBeanEnum.MOBILE_ERROR);
        }

        //QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        //queryWrapper.eq("phone", mobile);


        User user=userMapper.selectById(phone);//到数据库中去查询该用户
        if(null==user){//如果找不用户则提示登录错误
            return RespBean.error(RespBeanEnum.LOGIN_ERROR);
        }
        System.out.println(user.getSalt());
        System.out.println(user.getPassword());
        System.out.println(MD5Util.formPassToDBPass(password,user.getSalt()));
        if(!MD5Util.formPassToDBPass(password,user.getSalt()).equals(user.getPassword())){//如果密码错误也报登录错误
            return RespBean.error(RespBeanEnum.LOGIN_ERROR);
        }
        String ticket= UUIDUtil.uuid();//生成cookie
        request.getSession().setAttribute(ticket,user);
        CookieUtil.setCookie(request,response,"userTicket",ticket);
        return RespBean.success();
    }
}