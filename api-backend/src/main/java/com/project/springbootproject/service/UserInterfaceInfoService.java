package com.project.springbootproject.service;

import com.project.springbootproject.model.entity.UserInterfaceInfo;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * @author frankXu
 * @description 针对表【user_interface_info(用户调用接口关系)】的数据库操作Service
 * @createDate 2023-06-17 21:13:09
 */
public interface UserInterfaceInfoService extends IService<UserInterfaceInfo> {
    /**
     * 参数校验
     *
     * @param userInterfaceInfo
     * @param add
     */
    public void validUserInterfaceInfo(UserInterfaceInfo userInterfaceInfo, boolean add);

    /**
     * 调用次数
     *
     * @param interfaceInfoId
     * @param userId
     * @return
     */
    public boolean invokeCount(long interfaceInfoId, long userId);
}
