package com.project.springbootproject.model.dto.search;

import com.project.springbootproject.common.PageRequest;
import lombok.Data;

import java.io.Serializable;

/**
 * @author frank.xu
 * @createDate 2023-02-08 22:01:50
 */
@Data
@SuppressWarnings("All")
public class SearchRequest extends PageRequest implements Serializable {
    /**
     * 搜索词
     */
    private String searchText;
    /**
     * 类型
     */
    private String type;
    private static final long serialVersionUID = 3191241716373120793L;

}
