package com.example.interfacecommon.service;


import com.example.interfacecommon.model.entity.InterfaceInfo;

/**
 * 内部接口信息服务
 *
 * @author frank.xu
 * @createDate 2023-02-08 22:01:50
 */
public interface InnerInterfaceInfoService {
    InterfaceInfo getInterfaceInfo(String path, String method);
}
