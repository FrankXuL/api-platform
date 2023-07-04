package com.project.springbootproject.datasource;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.project.springbootproject.model.entity.Picture;
import com.project.springbootproject.service.PictureService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * 图片服务实现类
 *
 * @author frank.xu
 * @createDate 2023-02-08 22:01:50
 */
@Service
@SuppressWarnings("all")
public class PictureDataSource implements DataSource<Picture> {
    @Resource
    private PictureService pictureService;

    /**
     * 搜索
     *
     * @param searchText
     * @param pageNum
     * @param pageSize
     * @return
     */
    @Override
    public Page<Picture> doSearch(String searchText, long pageNum, long pageSize) {
        return pictureService.searchPicture(searchText, pageNum, pageSize);
    }
}
