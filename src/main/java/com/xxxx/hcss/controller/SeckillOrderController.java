package com.xxxx.hcss.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.xxxx.hcss.pojo.OrderInfo;
import com.xxxx.hcss.pojo.SeckillOrder;
import com.xxxx.hcss.pojo.User;
import com.xxxx.hcss.service.IGoodsService;
import com.xxxx.hcss.service.IOrderInfoService;
import com.xxxx.hcss.service.ISeckillGoodsService;
import com.xxxx.hcss.service.ISeckillOrderService;
import com.xxxx.hcss.vo.GoodsVo;
import com.xxxx.hcss.vo.RespBeanEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author zhumeng
 * @since 2022-10-01
 */
@Controller
@RequestMapping("/seckillOrder")
public class SeckillOrderController {
    @Autowired
    private IGoodsService goodsService;
    @Autowired
    private ISeckillOrderService iSeckillOrderService;
    @Autowired
    private IOrderInfoService iOrderInfoService;
    @RequestMapping("/doSeckill")
    public String doseckill(Model model, User user,Long goodsId){
        if(user==null){
            return "login";
        }
        model.addAttribute("user",user);
        GoodsVo goods=goodsService.findGoodsVoByGoodsId(goodsId);
        //判断库存
        if(goods.getStockCount()<1){
            model.addAttribute("errmsg", RespBeanEnum.EMPTY_STOCK.getMessage());
            return "seckillFail";
        }
        //判断是否重复抢购
        System.out.println("test begin");
        SeckillOrder seckillOrder=iSeckillOrderService.getOne(new QueryWrapper<SeckillOrder>()
                .eq("user_id",user.getId()).eq("goods_id",goodsId));
        if(seckillOrder!=null){
            model.addAttribute("errmsg",RespBeanEnum.REPEATE_ERROR.getMessage());
            return "secKillFail";
        }
        OrderInfo orderInfo=iOrderInfoService.seckill(user,goods);
        model.addAttribute("order",orderInfo);
        model.addAttribute("goods",goods);
        return "orderDetail";
    }
}
