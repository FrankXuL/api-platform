package com.project.springbootproject.service.impl;


import java.util.List;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.interfacecommon.model.entity.InterfaceInfo;
import com.project.springbootproject.common.ErrorCode;
import com.project.springbootproject.common.PageRequest;
import com.project.springbootproject.exception.BusinessException;
import com.project.springbootproject.mapper.InterfaceInfoMapper;
import com.example.interfacecommon.model.entity.User;
import com.project.springbootproject.model.enums.InterfaceInfoStatusEnum;
import com.project.springbootproject.service.InterfaceInfoService;
import com.project.springbootproject.service.UserService;
import org.apache.commons.lang3.StringUtils;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
/**
 * @author frankXu
 * @description 针对表【interface_info(接口信息)】的数据库操作Service实现
 * @createDate 2023-06-17 21:10:01
 */
@Service
public class InterfaceInfoServiceImpl extends ServiceImpl<InterfaceInfoMapper, InterfaceInfo> implements InterfaceInfoService {

    private static final Integer MAX_PAGE_SIZE = 50;
    @Resource
    UserService userService;

    @Override
    public void validInterfaceInfo(InterfaceInfo interfaceInfo, boolean add) {
        if (interfaceInfo == null) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        String name = interfaceInfo.getName();
        String description = interfaceInfo.getDescription();
        String url = interfaceInfo.getUrl();
        String requestParams = interfaceInfo.getRequestParams();
        String requestHeader = interfaceInfo.getRequestHeader();
        String responseHeader = interfaceInfo.getResponseHeader();
        String method = interfaceInfo.getMethod();
        // 创建和更新时，所有参数必须非空
        if (add) {
            if (StringUtils.isAnyBlank(name, description, url, requestParams, requestHeader, responseHeader, method)) {
                throw new BusinessException(ErrorCode.PARAMS_ERROR);
            }
        }
        if (StringUtils.isNotBlank(name) && name.length() > MAX_PAGE_SIZE) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR, "名称过长");
        }
    }

    @Override
    public Long addInterfaceInfo(InterfaceInfo interfaceInfo, HttpServletRequest request) {
        //获取登录信息
        User loginUser = userService.getLoginUser(request);
        interfaceInfo.setUserId(loginUser.getId());
        // 参数校验
        validInterfaceInfo(interfaceInfo, true);
        //插入数据
        boolean save = this.save(interfaceInfo);
        if (!save) {
            throw new BusinessException(ErrorCode.SYSTEM_ERROR);
        }
        return interfaceInfo.getId();
    }

    @Override
    public Boolean deleteInterfaceInfo(Long id, HttpServletRequest request) {
        // 判断是否存在
        InterfaceInfo oldInterfaceInfo = getInterfaceInfo(id);
        // 仅本人或管理员可删除
        permissionVerification(request, oldInterfaceInfo);
        return this.removeById(id);
    }

    @Override
    public Boolean updateInterdaceInfo(InterfaceInfo interfaceInfo, HttpServletRequest request) {
        // 参数校验
        validInterfaceInfo(interfaceInfo, false);
        // 判断是否存在
        InterfaceInfo oldInterfaceInfo = getInterfaceInfo(interfaceInfo.getId());
        //仅本人或管理员可删除
        permissionVerification(request, oldInterfaceInfo);

        return this.updateById(interfaceInfo);
    }


    @Override
    public List<InterfaceInfo> getInterfaceInfoList(InterfaceInfo interfaceInfo) {
        LambdaQueryWrapper<InterfaceInfo> lambdaQueryWrapper = this.conditionalQueryList(interfaceInfo);
        return this.list(lambdaQueryWrapper);
    }

    @Override
    public Page<InterfaceInfo> getInterfaceInfoPage(InterfaceInfo interfaceInfo, PageRequest pageRequest) {
        long current = pageRequest.getCurrent();
        long size = pageRequest.getPageSize();
        LambdaQueryWrapper<InterfaceInfo> lambdaQueryWrapper = conditionalQueryList(interfaceInfo);
        //todo 通过字符串判断排序字段进行排序
        return this.page(new Page<>(current, size), lambdaQueryWrapper);
    }

    @Override
    public Boolean onlineInterface(Long id, HttpServletRequest request) {
        // 判断是否存在
        InterfaceInfo oldInterfaceInfo = getInterfaceInfo(id);
        // todo 判断该接口是否可以调用
        // 仅本人或管理员可修改
        permissionVerification(request, oldInterfaceInfo);
        InterfaceInfo interfaceInfo = new InterfaceInfo();
        interfaceInfo.setId(id);
        interfaceInfo.setStatus(InterfaceInfoStatusEnum.ONLINE.getValue());
        return updateById(interfaceInfo);
    }

    @Override
    public Boolean offlineInterface(Long id, HttpServletRequest request) {
        // 判断是否存在
        InterfaceInfo oldInterfaceInfo = getInterfaceInfo(id);
        // todo 判断该接口是否可以调用
        // 仅本人或管理员可修改
        permissionVerification(request, oldInterfaceInfo);
        InterfaceInfo interfaceInfo = new InterfaceInfo();
        interfaceInfo.setId(id);
        interfaceInfo.setStatus(InterfaceInfoStatusEnum.OFFLINE.getValue());
        return updateById(interfaceInfo);
    }

    /**
     * 判断是否存在
     *
     * @param id
     * @return
     */
    @NotNull
    private InterfaceInfo getInterfaceInfo(Long id) {
        InterfaceInfo oldInterfaceInfo = this.getById(id);
        if (oldInterfaceInfo == null) {
            throw new BusinessException(ErrorCode.NOT_FOUND_ERROR);
        }
        return oldInterfaceInfo;
    }

    /**
     * 通用条件语句
     *
     * @param interfaceInfo
     * @return
     */
    private LambdaQueryWrapper<InterfaceInfo> conditionalQueryList(InterfaceInfo interfaceInfo) {
        LambdaQueryWrapper<InterfaceInfo> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper
                .eq(interfaceInfo.getId() != null, InterfaceInfo::getId, interfaceInfo.getId())
                .eq(StringUtils.isNotBlank(interfaceInfo.getName()), InterfaceInfo::getName, interfaceInfo.getName())
                .like(StringUtils.isNotBlank(interfaceInfo.getDescription()), InterfaceInfo::getDescription, interfaceInfo.getName())
                .eq(StringUtils.isNotBlank(interfaceInfo.getUrl()), InterfaceInfo::getUrl, interfaceInfo.getUrl())
                .eq(StringUtils.isNotBlank(interfaceInfo.getMethod()), InterfaceInfo::getMethod, interfaceInfo.getMethod())
                .eq(interfaceInfo.getStatus() != null, InterfaceInfo::getStatus, interfaceInfo.getStatus());
        return lambdaQueryWrapper;
    }

    /**
     * 修改权限
     *
     * @param request
     * @param oldInterfaceInfo
     */
    private void permissionVerification(HttpServletRequest request, InterfaceInfo oldInterfaceInfo) {
        User user = userService.getLoginUser(request);
        // 仅本人或管理员可修改
        if (!oldInterfaceInfo.getUserId().equals(user.getId()) && !userService.isAdmin(request)) {
            throw new BusinessException(ErrorCode.NO_AUTH_ERROR);
        }
    }
}




