package com.xxxx.hcss.controller;

import com.xxxx.hcss.pojo.User;
//import net.sf.jsqlparser.Model;
import com.xxxx.hcss.service.IGoodsService;
import com.xxxx.hcss.service.IUserService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.ui.Model;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/goods")
public class GoodsController {
    @Autowired
    private IUserService userService;
    @Autowired
    private IGoodsService goodsService;
    /*@RequestMapping("/toList")
    public String toList(HttpServletRequest request, HttpServletResponse response, Model model, @CookieValue("userTicket") String ticket){
        if(StringUtils.isEmpty(ticket)){
            return "login";
        }
        //User user=(User) session.getAttribute(ticket);
        User user=userService.getUserByCookie(ticket,request,response);
        if(null==user){
            return "login";
        }
        model.addAttribute("user",user);
        return  "goodsList";
    }*/

    @RequestMapping("/toList")
    public String toList(Model model, User user){
        model.addAttribute("user",user);
        model.addAttribute("goodsList",goodsService.findGoodsVo());
        return  "../static/goodsList";
    }
}
