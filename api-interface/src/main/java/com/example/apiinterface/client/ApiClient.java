package com.example.apiinterface.client;

import cn.hutool.Hutool;
import cn.hutool.crypto.digest.DigestAlgorithm;
import cn.hutool.crypto.digest.Digester;
import cn.hutool.crypto.digest.MD5;
import cn.hutool.http.HttpRequest;
import cn.hutool.http.HttpResponse;
import cn.hutool.http.HttpUtil;
import cn.hutool.json.JSONUtil;
import com.example.apiinterface.model.User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.HashMap;
import java.util.Map;

/**
 * 调用第三方接口客户端
 *
 * @author frank.xu
 * @createDate 2023-02-08 22:01:50
 */
public class ApiClient {
    private String accessKey;
    private String secretKey;

    public String getNameByGet(String name) {
        HashMap<String, Object> paramMap = new HashMap<>();
        paramMap.put("name", name);
        String result = HttpUtil.get("http://127.0.0.1:8123/api/name/", paramMap);
        System.out.println(result);
        return result;
    }

    public String getNameByPost(@RequestBody String name) {
        HashMap<String, Object> paramMap = new HashMap<>();
        paramMap.put("name", name);
        String result = HttpUtil.post("http://127.0.0.1:8123/api/name/", paramMap);
        System.out.println(result);
        return result;
    }

    private Map<String, String> getHeaderMap() {
        HashMap<String, String> headerMap = new HashMap<>();
        headerMap.put("accessKey", accessKey);
        //headerMap.put("secretKey", secretKey);
        headerMap.put("nonce", "");
        headerMap.put("body", "");
        headerMap.put("timestamp", String.valueOf(System.currentTimeMillis() / 1000));
        headerMap.put("sign", getSign(headerMap));
        return headerMap;
    }

    private String getSign(Map<String, String> map) {
        Digester md5 = new Digester(DigestAlgorithm.SHA256);
        String content = map.toString() + "." + secretKey;
        return md5.digestHex(content);
    }

    public String getUserNameByPost(@RequestParam User user) {
        String json = JSONUtil.toJsonStr(user);
        HttpResponse execute = HttpRequest.post("http://127.0.0.1:8123/api/name/")
                .body(json)
                .execute();
        System.out.println(execute.getStatus());
        System.out.println(execute.body());
        return execute.body();
    }

    public static void main(String[] args) {
        ApiClient apiClient = new ApiClient();
        System.out.println(apiClient.getNameByGet("aa"));
        System.out.println(apiClient.getNameByPost("aa1"));
        User user = new User();
        user.setUserName("123");
        System.out.println(apiClient.getUserNameByPost(user));
    }
}
