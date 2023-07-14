package com.example.interfacecommon.service;


import com.example.interfacecommon.model.entity.User;

/**
 * 内部用户服务1
 * @author frank.xu
 * @createDate 2023-02-08 22:01:50
 */
public interface InnerUserService {

    /**
     * 数据库中查是否已分配给用户秘钥（accessKey）
     * @param accessKey
     * @return
     */
    User getInvokeUser(String accessKey);
}
