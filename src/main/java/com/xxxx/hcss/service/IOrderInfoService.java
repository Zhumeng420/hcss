package com.xxxx.hcss.service;

import com.xxxx.hcss.pojo.OrderInfo;
import com.baomidou.mybatisplus.extension.service.IService;
import com.xxxx.hcss.pojo.User;
import com.xxxx.hcss.vo.GoodsVo;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author zhumeng
 * @since 2022-10-01
 */
public interface IOrderInfoService extends IService<OrderInfo> {

    OrderInfo seckill(User user, GoodsVo goods);
}
