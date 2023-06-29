package com.project.springbootproject.model.dto.User;

import lombok.Data;

import java.io.Serializable;

/**
 * @author frank.xu
 * @createDate 2023-02-08 22:01:50
 */
@Data
public class UserRegisterRequest implements Serializable {
    private static final long serialVersionUID = 3191241716373120793L;

    private String userAccount;

    private String userPassword;

    private String checkPassword;

}
