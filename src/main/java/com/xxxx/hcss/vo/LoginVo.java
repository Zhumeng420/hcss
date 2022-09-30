package com.xxxx.hcss.vo;

import lombok.Data;
import lombok.NonNull;
import org.hibernate.validator.constraints.Length;
import org.jetbrains.annotations.NotNull;

@Data
public class LoginVo {
    @NonNull
    private String mobile;
    @NonNull
    @Length(min=32)
    private String password;

    @NotNull
    public String getMobile(){
        return this.mobile;
    }

    @NotNull
    public String getPassword(){
        return this.password;
    }
}
