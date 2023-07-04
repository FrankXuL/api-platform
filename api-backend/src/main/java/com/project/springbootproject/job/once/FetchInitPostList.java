package com.project.springbootproject.job.once;

import cn.hutool.http.HttpRequest;
import cn.hutool.json.JSONArray;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.project.springbootproject.esdao.PostEsDao;
import com.project.springbootproject.model.entity.Post;
import com.project.springbootproject.service.PostService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 全量同步帖子到 es
 *
 * @author frank123.xu
 */
// todo 取消注释开启任务
//@Component
@Slf4j
@SuppressWarnings("all")
public class FetchInitPostList implements CommandLineRunner {

    @Resource
    private PostService postService;

    @Resource
    private PostEsDao postEsDao;

    @Override
    public void run(String... args) {
        //获取数据
        String json = "{\n" +
                "  \"current\": 1,\n" +
                "  \"pageSize\": 8,\n" +
                "  \"sortField\": \"createTime\",\n" +
                "  \"sortOrder\": \"descend\",\n" +
                "  \"category\": \"文章\",\n" +
                "  \"reviewStatus\": 1\n" +
                "}";
        String url = "https://www.code-nav.cn/api/post/search/page/vo\n";
        String result = HttpRequest
                .post(url)
                .body(json)
                .execute().body();
//        System.out.println(result);
//        数据格式转换
        Map<String, Object> map = JSONUtil.toBean(result, Map.class);
        JSONObject data = (JSONObject) map.get("data");
        JSONArray records = data.getJSONArray("records");
        List<Post> postList = new ArrayList<>();
        for (Object record : records) {
            JSONObject record1 = (JSONObject) record;
            Post post = new Post();
            post.setTitle(record1.getStr("title"));
            post.setContent(record1.getStr("content"));
            JSONArray tags = record1.getJSONArray("tags");
            List<String> objects = tags.toList(String.class);
            post.setTags(JSONUtil.toJsonStr(objects));
            post.setThumbNum(1);
            post.setFavourNum(1);
            post.setUserId(1L);
            postList.add(post);
        }
        boolean b = postService.saveBatch(postList);
        if (b) {
            log.info("获取初始列表帖子数:" + postList.size());
        } else {
            log.info("获取失败");
        }
    }
}
