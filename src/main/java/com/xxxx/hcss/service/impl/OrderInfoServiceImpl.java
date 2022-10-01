package com.xxxx.hcss.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.xxxx.hcss.pojo.OrderInfo;
import com.xxxx.hcss.mapper.OrderInfoMapper;
import com.xxxx.hcss.pojo.SeckillGoods;
import com.xxxx.hcss.pojo.SeckillOrder;
import com.xxxx.hcss.pojo.User;
import com.xxxx.hcss.service.IOrderInfoService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xxxx.hcss.service.ISeckillGoodsService;
import com.xxxx.hcss.service.ISeckillOrderService;
import com.xxxx.hcss.vo.GoodsVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author zhumeng
 * @since 2022-10-01
 */
@Service
public class OrderInfoServiceImpl extends ServiceImpl<OrderInfoMapper, OrderInfo> implements IOrderInfoService {
    @Autowired
    private ISeckillGoodsService seckillGoodsService;
    @Autowired
    private OrderInfoMapper orderInfoMapper;
    @Autowired
    private ISeckillOrderService seckillOrderService;
    @Override
    public OrderInfo seckill(User user, GoodsVo goods) {
        //秒杀商品表减库存
        SeckillGoods seckillGoods=seckillGoodsService.getOne(new QueryWrapper<SeckillGoods>().eq("goods_id",goods.getId()));
        seckillGoods.setStockCount(seckillGoods.getStockCount()-1);
        seckillGoodsService.updateById(seckillGoods);
        //生成订单
        OrderInfo orderInfo=new OrderInfo();
        orderInfo.setUserId(Long.valueOf(user.getId()));
        orderInfo.setGoodsId(goods.getId());
        orderInfo.setAddrId(0L);
        orderInfo.setGoodsName(goods.getGoodsName());
        orderInfo.setGoodsCount(1);
        orderInfo.setGoodsPrice(seckillGoods.getSeckillPrice());
        orderInfo.setOrderChannel(1);
        orderInfo.setStatus(0);
        orderInfo.setCreateDate(new Date());
        orderInfoMapper.insert(orderInfo);
        //生成秒杀订单
        SeckillOrder seckillOrder=new SeckillOrder();
        seckillOrder.setUserId(Long.valueOf(user.getId()));
        seckillOrder.setOrderId(orderInfo.getId());
        seckillOrder.setGoodsId(goods.getId());
        seckillOrder.setCreateDate(new Date());
        seckillOrderService.save(seckillOrder);
        return orderInfo;

    }
}
