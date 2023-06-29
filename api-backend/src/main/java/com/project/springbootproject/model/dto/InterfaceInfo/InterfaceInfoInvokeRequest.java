package com.project.springbootproject.model.dto.InterfaceInfo;

import lombok.Data;

import java.io.Serializable;

/**
 * @author frank.xu
 * @createDate 2023-02-08 22:01:50
 */
@Data
public class InterfaceInfoInvokeRequest implements Serializable {

    /**
     * 主键
     */
    private Long id;

    /**
     * 用户请求参数
     */
    private String userRequestParams;

    private static final long serialVersionUID = 1L;
}

