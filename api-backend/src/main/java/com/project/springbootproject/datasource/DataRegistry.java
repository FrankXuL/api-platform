package com.project.springbootproject.datasource;

import com.project.springbootproject.model.enums.SearchTypeEnum;
import org.apache.poi.ss.formula.functions.T;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

/**
 * @author frank.xu
 * @createDate 2023-02-08 22:01:50
 */
@Component
public class DataRegistry {
    @Resource
    private PostDataSource postDataSource;
    @Resource
    private UserDataSource userDataSource;
    @Resource
    private PictureDataSource pictureDataSource;
    private Map<String, DataSource<T>> dataSourceMap;

    @PostConstruct
    public void doInit() {
        dataSourceMap = new HashMap() {{
            put(SearchTypeEnum.POST.getValue(), postDataSource);
            put(SearchTypeEnum.USER.getValue(), userDataSource);
            put(SearchTypeEnum.PICTURE.getValue(), pictureDataSource);
        }};
    }

    public DataSource<T> getDataSourceByType(String type) {
        if (dataSourceMap == null) {
            return null;
        }
        return dataSourceMap.get(type);
    }

}
