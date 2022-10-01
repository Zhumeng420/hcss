package com.xxxx.hcss.vo;


import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.xxxx.hcss.pojo.Goods;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Date;

/**
 * 商品返回对象
 *
 * @author: LC
 * @date 2022/3/3 5:43 下午
 * @ClassName: GoodsVo
 */

@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel("商品返回对象")
public class GoodsVo  {
        private Long id;
        private String goodsName;
        private String goodsTitle;
        private String goodsImg;
        private String goodsDetail;
        private BigDecimal goodsPrice;
        private Integer goodsStock;
        private Integer seckillPrice;
        private Integer stockCount;
        private Date startDate;
        private Date endDate;
}
