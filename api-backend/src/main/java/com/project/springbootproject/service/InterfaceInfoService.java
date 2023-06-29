package com.project.springbootproject.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.project.springbootproject.common.PageRequest;
import com.project.springbootproject.model.entity.InterfaceInfo;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @author frankXu
 * @description 针对表【interface_info(接口信息)】的数据库操作Service
 * @createDate 2023-06-17 21:10:01
 */
@SuppressWarnings("All")
public interface InterfaceInfoService extends IService<InterfaceInfo> {
    /**
     * 参数校验
     *
     * @param interfaceInfo
     * @param add
     */
    void validInterfaceInfo(InterfaceInfo interfaceInfo, boolean add);

    /**
     * 添加
     *
     * @param interfaceInfo
     * @return
     */
    Long addInterfaceInfo(InterfaceInfo interfaceInfo, HttpServletRequest request);

    /**
     * 删除
     *
     * @param id
     * @param request
     * @return
     */
    Boolean deleteInterfaceInfo(Long id, HttpServletRequest request);

    /**
     * 更新
     *
     * @param interfaceInfo
     * @param request
     * @return
     */
    Boolean updateInterdaceInfo(InterfaceInfo interfaceInfo, HttpServletRequest request);

    /**
     * 获取列表
     *
     * @param interfaceInfo
     * @return
     */
    List<InterfaceInfo> getInterfaceInfoList(InterfaceInfo interfaceInfo);

    /**
     * 分页查询
     *
     * @param interfaceInfo
     * @return
     */
    Page<InterfaceInfo> getInterfaceInfoPage(InterfaceInfo interfaceInfo, PageRequest pageRequest);

    /**
     * 发布
     *
     * @param id
     * @param request
     * @return
     */
    Boolean onlineInterface(Long id, HttpServletRequest request);

    /**
     * 下线
     *
     * @param id
     * @param request
     * @return
     */
    Boolean offlineInterface(Long id, HttpServletRequest request);
}
