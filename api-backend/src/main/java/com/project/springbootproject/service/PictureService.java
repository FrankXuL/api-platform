package com.project.springbootproject.service;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.project.springbootproject.model.entity.Picture;

/**
 * 图片服务
 *
 * @author frank.xu
 * @createDate 2023-02-08 22:01:50
 */
@SuppressWarnings("all")
public interface PictureService {
    /**
     * 搜索图片
     *
     * @param searchText
     * @param pageNum
     * @param pageSize
     * @return
     */
    Page<Picture> searchPicture(String searchText, long pageNum, long pageSize);

}
