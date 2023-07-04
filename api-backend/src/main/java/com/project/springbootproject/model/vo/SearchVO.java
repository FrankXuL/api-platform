package com.project.springbootproject.model.vo;

import com.project.springbootproject.model.entity.Picture;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * 搜索VO类
 */
@Data
@SuppressWarnings("all")
public class SearchVO implements Serializable {


    private List<UserVO> userList;
    private List<PostVO> postList;
    private List<Picture> pictureList;
    private List<?> dataList;
    private static final long serialVersionUID = 1L;

}
