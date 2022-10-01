package com.xxxx.hcss.pojo;

import java.math.BigDecimal;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;
import java.util.Date;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 
 * </p>
 *
 * @author zhumeng
 * @since 2022-10-01
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("order_info")
@ApiModel(value="OrderInfo对象", description="")
public class OrderInfo implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @ApiModelProperty(value = "用户id")
    @TableField("user_id")
    private Long userId;

    @ApiModelProperty(value = "商品id")
    @TableField("goods_id")
    private Long goodsId;

    @ApiModelProperty(value = "收货地址id")
    @TableField("addr_id")
    private Long addrId;

    @ApiModelProperty(value = "冗余过来的商品名称")
    @TableField("goods_name")
    private String goodsName;

    @ApiModelProperty(value = "商品数量")
    @TableField("goods_count")
    private Integer goodsCount;

    @ApiModelProperty(value = "商品价格")
    @TableField("goods_price")
    private BigDecimal goodsPrice;

    @ApiModelProperty(value = "支付通道：1 PC、2 Android、3 ios")
    @TableField("order_channel")
    private Integer orderChannel;

    @ApiModelProperty(value = "订单状态：0 未支付，1已支付，2 已发货，3 已收货，4 已退款，5 已完成")
    private Integer status;

    @TableField("create_date")
    private Date createDate;

    @ApiModelProperty(value = "支付时间")
    @TableField("pay_date")
    private Date payDate;


}
