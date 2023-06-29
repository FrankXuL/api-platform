package com.project.springbootproject.mapper;

import com.project.springbootproject.model.entity.User;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.context.annotation.Configuration;

/**
* @author frankXu
* @description 针对表【user(用户)】的数据库操作Mapper
* @createDate 2023-06-17 21:12:21
* @Entity com.project.springbootproject.model.entity.User
*/

public interface UserMapper extends BaseMapper<User> {

}




