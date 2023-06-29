package com.example.apiinterface.controller;

import cn.hutool.core.util.StrUtil;
import com.example.apiinterface.model.User;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

/**
 * @author frank.xu
 * @createDate 2023-02-08 22:01:50
 */
@RestController
@RequestMapping("/name")
public class NameController {
    @GetMapping("/get")
    public String getNameByGet(String name, HttpServletRequest request) {
//        String accessKey = request.getHeader("accessKey");
//        String secretKey = request.getHeader("secretKey");
//        if (StrUtil.isAllBlank(accessKey, secretKey)) {
//            throw new RuntimeException("参数错误");
//        }
//        if (!accessKey.equals("11") || !secretKey.equals("11")) {
//            throw new RuntimeException("无权限");
//        }
        return "GET:" + name;
    }

    @PostMapping("/post")
    public String getNameByPost(@RequestBody String name) {
        return "POST" + name;
    }

    @PostMapping("/user")
    public User getUserNameByPost(@RequestParam User user, HttpServletRequest request) {
//        String accessKey = request.getHeader("accessKey");
//        String secretKey = request.getHeader("secretKey");
//        if (StrUtil.isAllBlank(accessKey, secretKey)) {
//            throw new RuntimeException("参数错误");
//        }
//        if (!accessKey.equals("11") || !secretKey.equals("11")) {
//            throw new RuntimeException("无权限");
//        }
        return user;
    }
}
