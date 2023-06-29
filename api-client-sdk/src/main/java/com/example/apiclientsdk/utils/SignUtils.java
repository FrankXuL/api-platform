package com.example.apiclientsdk.utils;

/**
 * 签名工具
 *
 * @author frank.xu
 * @createDate 2023-02-08 22:01:50
 */
public class SignUtils {
    /**
     * 生成签名
     *
     * @param body
     * @param secretKey
     * @return
     */
    public static String genSign(String body, String secretKey) {
        Digester md5 = new Digester(DigestAlgorithm.SHA256);
        String content = body + "." + secretKey;
        return md5.digestHex(content);
    }
}