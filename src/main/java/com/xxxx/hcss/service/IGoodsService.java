package com.xxxx.hcss.service;

import com.xxxx.hcss.pojo.Goods;
import com.baomidou.mybatisplus.extension.service.IService;
import com.xxxx.hcss.vo.GoodsVo;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author zhumeng
 * @since 2022-10-01
 */
public interface IGoodsService extends IService<Goods> {

    List<GoodsVo> findGoodsVo();

    GoodsVo findGoodsVoByGoodsId(Long goodsId);
}
