package com.xxxx.hcss.service;

import com.xxxx.hcss.pojo.User;
import com.baomidou.mybatisplus.extension.service.IService;
import com.xxxx.hcss.vo.LoginVo;
import com.xxxx.hcss.vo.RespBean;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author zhumeng
 * @since 2022-09-29
 */
public interface IUserService extends IService<User> {


    RespBean doLogin(LoginVo loginVo, HttpServletRequest request, HttpServletResponse response);
}
