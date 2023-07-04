package com.project.springbootproject.service.impl;

import cn.hutool.json.JSONUtil;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.project.springbootproject.common.ErrorCode;
import com.project.springbootproject.exception.BusinessException;
import com.project.springbootproject.model.entity.Picture;
import com.project.springbootproject.service.PictureService;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 图片服务实现类
 *
 * @author frank.xu
 * @createDate 2023-02-08 22:01:50
 */
@Service
@SuppressWarnings("all")
public class PictureServiceImpl implements PictureService {
    /**
     * 搜索图片
     *
     * @param searchText
     * @param pageNum
     * @param pageSize
     * @return
     */
    @Override
    public Page<Picture> searchPicture(String searchText, long pageNum, long pageSize) {
        long current = (pageNum - 1) * pageSize;
        String url = String.format("https://cn.bing.com/images/search?q=%s&first=%s", searchText, current);
        //String url = "https://cn.bing.com/images/search?q=%E9%A3%8E%E6%99%AF&qs=n&form=QBILPG&sp=-1&lq=0&pq=feng%27j&sc=10-6&cvid=7456A79E64FA485381ECE75CECFB2C0E&ghsh=0&ghacc=0&first=" + current;
        Document doc = null;
        try {
            doc = Jsoup.connect(url).get();
        } catch (IOException e) {
            throw new BusinessException(ErrorCode.SYSTEM_ERROR, "数据获取异常");
        }
        Elements select = doc.select(".iuscp.isv");
        List<Picture> list = new ArrayList<>();

        for (Element element : select) {
            //取图片地址
            String m = element.select(".iusc").get(0).attr("m");
            Map<String, Object> map = JSONUtil.toBean(m, Map.class);
            String murl = (String) map.get("murl");
            String title = element.select(".inflnk").get(0).attr("aria-label");
            //创建图片对象
            Picture picture = new Picture();
            picture.setUrl(murl);
            picture.setTitle(title);
            list.add(picture);
            if (list.size() >= pageSize) {
                break;
            }
        }
        Page<Picture> picturePage = new Page<>(pageNum, pageSize);
        picturePage.setRecords(list);
        return picturePage;
    }
}
