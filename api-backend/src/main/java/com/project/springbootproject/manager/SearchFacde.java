package com.project.springbootproject.manager;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.project.springbootproject.common.ErrorCode;
import com.project.springbootproject.datasource.DataRegistry;
import com.project.springbootproject.datasource.PictureDataSource;
import com.project.springbootproject.datasource.PostDataSource;
import com.project.springbootproject.datasource.UserDataSource;
import com.project.springbootproject.exception.ThrowUtils;
import com.project.springbootproject.model.dto.search.SearchRequest;
import com.project.springbootproject.model.enums.SearchTypeEnum;
import com.project.springbootproject.model.vo.PostVO;
import com.project.springbootproject.model.vo.SearchVO;
import com.project.springbootproject.model.vo.UserVO;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.concurrent.CompletableFuture;

/**
 * 搜索门面模式
 *
 * @author frank.xu
 * @createDate 2023-02-08 22:01:50
 */
@Slf4j
@Component
@SuppressWarnings("all")
public class SearchFacde {
    @Resource
    private PostDataSource postDataSource;
    @Resource
    private UserDataSource userDataSource;
    @Resource
    private PictureDataSource pictureDataSource;
    @Resource
    private DataRegistry dataRegistry;

    public SearchVO searchAllByType(@RequestBody SearchRequest searchRequest, HttpServletRequest request) {
        String type = searchRequest.getType();
        SearchTypeEnum searchTypeEnum = SearchTypeEnum.getEnumByValue(type);
        String searchText = searchRequest.getSearchText();
        long pageNum = searchRequest.getCurrent();
        long pageSize = searchRequest.getPageSize();
        ThrowUtils.throwIf(StringUtils.isBlank(type), ErrorCode.PARAMS_ERROR);
        //返回值
        SearchVO searchVO = new SearchVO();
        //查询所有的
        if (searchTypeEnum == null) {
            CompletableFuture<Page<UserVO>> completableFutureUser = CompletableFuture.supplyAsync(() -> {
                //user
                return userDataSource.doSearch(searchText, pageNum, pageSize);
            });
            CompletableFuture<Page<PostVO>> completableFuturePost = CompletableFuture.supplyAsync(() -> {
                //post
                return postDataSource.doSearch(searchText, pageNum, pageSize);
            });
            CompletableFuture<Page<Picture>> completableFuturePicture = CompletableFuture.supplyAsync(() -> {
                //picture
                return pictureDataSource.doSearch(searchText, pageNum, pageSize);
            });
            CompletableFuture.allOf(completableFuturePicture, completableFutureUser, completableFuturePicture).join();
            try {
                Page<UserVO> userVOPage = completableFutureUser.get();
                Page<PostVO> postVOPage = completableFuturePost.get();
                Page<Picture> picturePage = completableFuturePicture.get();
                //设置返回值
                searchVO.setUserList(userVOPage.getRecords());
                searchVO.setPostList(postVOPage.getRecords());
                searchVO.setPictureList(picturePage.getRecords());
            } catch (Exception e) {
                log.error("查询异常", e);
                throw new BusinessException(ErrorCode.SYSTEM_ERROR, "查询错误");
            }
        } else {
            DataSource<?> dataSource = dataRegistry.getDataSourceByType(type);
            Page<?> page = dataSource.doSearch(searchText, pageNum, pageSize);
            searchVO.setDataList(page.getRecords());
        }
        return searchVO;
    }
}
