package com.project.springbootproject.datasource;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.project.springbootproject.model.dto.User.UserQueryRequest;
import com.project.springbootproject.model.vo.UserVO;
import com.project.springbootproject.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * 用户服务实现
 *
 * @author frank123.xu
 */
@Service
@Slf4j
@SuppressWarnings("all")
public class UserDataSource implements DataSource<UserVO> {

    @Resource
    private UserService userService;

    /**
     * 搜索
     *
     * @param searchText
     * @param pageNum
     * @param pageSize
     * @return
     */
    @Override
    public Page<UserVO> doSearch(String searchText, long pageNum, long pageSize) {
        UserQueryRequest userQueryRequest = new UserQueryRequest();
        userQueryRequest.setUserName(searchText);
        userQueryRequest.setCurrent(pageNum);
        userQueryRequest.setPageSize(pageSize);
        return userService.listUserVoByPage(userQueryRequest);
    }
}
