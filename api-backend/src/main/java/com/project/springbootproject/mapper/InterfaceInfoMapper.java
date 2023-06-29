package com.project.springbootproject.mapper;

import com.project.springbootproject.model.entity.InterfaceInfo;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author frankXu
 * @description 针对表【interface_info(接口信息)】的数据库操作Mapper
 * @createDate 2023-06-17 21:10:01
 * @Entity com.project.springbootproject.model.entity.InterfaceInfo
 */
@Mapper
public interface InterfaceInfoMapper extends BaseMapper<InterfaceInfo> {

}




