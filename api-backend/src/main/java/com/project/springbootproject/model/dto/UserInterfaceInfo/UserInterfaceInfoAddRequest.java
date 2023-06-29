package com.project.springbootproject.model.dto.UserInterfaceInfo;

import lombok.Data;

import java.io.Serializable;

/**
 * @author frank.xu
 * @createDate 2023-02-08 22:01:50
 */
@Data
public class UserInterfaceInfoAddRequest implements Serializable {

    /**
     * 调用用户 id
     */
    private Long userId;

    /**
     * 接口 id
     */
    private Long interfaceInfoId;

    /**
     * 总调用次数
     */
    private Integer totalNum;

    /**
     * 剩余调用次数
     */
    private Integer leftNum;

}
