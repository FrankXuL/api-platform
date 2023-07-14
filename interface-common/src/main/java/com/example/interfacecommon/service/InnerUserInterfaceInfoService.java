package com.example.interfacecommon.service;

/**
 * 内部用户接口信息服务
 *
 * @author frank.xu
 * @createDate 2023-02-08 22:01:50
 */
public interface InnerUserInterfaceInfoService {

    /**
     * 调用接口统计
     *
     * @param interfaceInfoId
     * @param userId
     * @return
     */
    boolean invokeCount(long interfaceInfoId, long userId);
}
