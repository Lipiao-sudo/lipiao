package com.gec.system.util;

import io.jsonwebtoken.*;
import org.springframework.util.StringUtils;

import java.util.Date;


/**
 * @author  gec
 * @since  2023/03/12
 *
 *
 *   生成JSON Web令牌的工具类
 */
public class JwtHelper {

    //token过期时间
    private static long tokenExpiration = 365 * 24 * 60 * 60 * 1000;
    //加密秘钥
    private static String tokenSignKey = "123456";

    //根据用户id和用户名称生成token字符串
    public static String createToken(String userId, String username) {
        String token = Jwts.builder()
            
            // subject  jwt所面向的用户(只是一个标识而已)
                .setSubject("DAFEI-USER")
            
            // 设置过期时间     jwt的过期时间，这个过期时间必须要大于签发时间
            .setExpiration(new Date(System.currentTimeMillis() + tokenExpiration))
            
            // 有效载荷 不要放敏感信息 比如密码
                .claim("userId", userId)
                .claim("username", username)
            
            // 使用的签名算法 以及 秘钥   Signature 部分是对前两部分的签名，防止数据篡改。
                .signWith(SignatureAlgorithm.HS512, tokenSignKey)
            // 压缩处理 一行显示
                .compressWith(CompressionCodecs.GZIP)
            // 拼接成一个 
                .compact();
        return token;
    }

    //从token字符串获取userid
    public static String getUserId(String token) {
        try {
            if (StringUtils.isEmpty(token)) return null;

            // 根据秘钥   去解析之前加密的token 字符串
            Jws<Claims> claimsJws = Jwts.parser().setSigningKey(tokenSignKey).parseClaimsJws(token);
            
            // 从token中获取body
            Claims claims = claimsJws.getBody();
            //  从body 中获取userId
            String userId = (String) claims.get("userId");
            return userId;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    //从token字符串获取username
    public static String getUsername(String token) {
        try {
            if (StringUtils.isEmpty(token)) return "";

             // 根据秘钥   去解析之前加密的token 字符串
            Jws<Claims> claimsJws = Jwts.parser().setSigningKey(tokenSignKey).parseClaimsJws(token);
            
             // 从token中获取body
            Claims claims = claimsJws.getBody();
            
             //  从body 中获取username
            return (String) claims.get("username");
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static void main(String[] args) {

        // a.生成 jwt token
        String token = JwtHelper.createToken("1001", "lisi");
        System.out.println(token);

        //b. 从 token 中解密获取 userId
        String userId = JwtHelper.getUserId(token);
        System.out.println(userId);



        //c.从 token 中解密获取 username
        String username = JwtHelper.getUsername(token);
        System.out.println(username);
    }
}