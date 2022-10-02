package com.xxxx.hcss.controller;


import com.xxxx.hcss.pojo.User;
import com.xxxx.hcss.vo.RespBean;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author zhumeng
 * @since 2022-09-29
 */

@RestController
@RequestMapping("/user")
public class UserController {
    /******用于测试的接口******/
    @RequestMapping("/info")
    @ResponseBody
    public RespBean info(User user){
        return  RespBean.success(user);
    }

}
