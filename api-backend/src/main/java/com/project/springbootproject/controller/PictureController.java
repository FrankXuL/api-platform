package com.project.springbootproject.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.google.gson.Gson;
import com.project.springbootproject.common.BaseResponse;
import com.project.springbootproject.common.ErrorCode;
import com.project.springbootproject.common.ResultUtils;
import com.project.springbootproject.exception.ThrowUtils;
import com.project.springbootproject.model.dto.picture.PictureQueryRequest;
import com.project.springbootproject.model.entity.Picture;
import com.project.springbootproject.service.PictureService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 * 帖子接口
 *
 * @author frank123.xu
 */
@RestController
@RequestMapping("/picture")
@Slf4j
@SuppressWarnings("all")
public class PictureController {

    @Resource
    private PictureService pictureService;


    private final static Gson GSON = new Gson();

    /**
     * 分页获取列表（封装类）
     *
     * @param postQueryRequest
     * @param request
     * @return
     */
    @PostMapping("/list/page/vo")
    public BaseResponse<Page<Picture>> listPictureVOByPage(@RequestBody PictureQueryRequest pictureQueryRequest,
                                                           HttpServletRequest request) {
        long current = pictureQueryRequest.getCurrent();
        long size = pictureQueryRequest.getPageSize();
        ThrowUtils.throwIf(size > 20, ErrorCode.PARAMS_ERROR);
        String searchText = pictureQueryRequest.getSearchText();
        Page<Picture> picturePage = pictureService.searchPicture(searchText, current, size);
        return ResultUtils.success(picturePage);
    }

}
