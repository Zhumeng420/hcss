package com.xxxx.hcss.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;
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
 * @since 2022-09-29
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="User对象", description="")
public class User implements Serializable {

    private static final long serialVersionUID = 1L;

    //@TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @TableField("user_name")
    private String userName;

    //@TableField("phone")
    @TableId("phone")
    private String phone;

    private String password;

    private String salt;

    private String head;

    @TableField("login_count")
    private Integer loginCount;

    @TableField("register_date")
    private LocalDateTime registerDate;

    @TableField("last_login_date")
    private LocalDateTime lastLoginDate;


}
