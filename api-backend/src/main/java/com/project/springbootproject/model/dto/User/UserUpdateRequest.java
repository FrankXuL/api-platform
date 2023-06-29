package com.project.springbootproject.model.dto.User;

import lombok.Data;

import java.io.Serializable;

/**
 * @author frank.xu
 * @createDate 2023-02-08 22:01:50
 */
@Data
public class UserUpdateRequest implements Serializable {
    /**
     * id
     */
    private Long id;

    /**
     * 用户昵称
     */
    private String userName;

    /**
     * 账号
     */
    private String userAccount;

    /**
     * 用户头像
     */
    private String userAvatar;

    /**
     * 性别
     */
    private Integer gender;

    /**
     * 用户角色: user, admin
     */
    private String userRole;

    /**
     * 密码
     */
    private String userPassword;

    private static final long serialVersionUID = 1L;
}
