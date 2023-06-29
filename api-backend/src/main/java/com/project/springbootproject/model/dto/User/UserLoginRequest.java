package com.project.springbootproject.model.dto.User;

import lombok.Data;

import java.io.Serializable;

/**
 * 用户登录请求体
 * @author frank.xu
 * @createDate 2023-02-08 22:01:50
 */
@Data
public class UserLoginRequest implements Serializable {
    private static final long serialVersionUID = 3191241716373120793L;

    private String userAccount;

    private String userPassword;

}
