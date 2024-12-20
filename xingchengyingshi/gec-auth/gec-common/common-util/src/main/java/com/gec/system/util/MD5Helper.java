package com.gec.system.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
/**
 * @author  gec   2023/01/18
 *
 * 快速 实现 MD5 加密工具类
 *
 */
public final class MD5Helper {

    /**
     *  把明文按照MD5加密算法进行加密处理
     * @param strSrc 要加密的明文
     * @return  加密之后的密文
     */
    public static String encrypt(String strSrc) {
        try {
            char hexChars[] = { '0', '1', '2', '3', '4', '5', '6', '7', '8',
                    '9', 'a', 'b', 'c', 'd', 'e', 'f','g','h','j','k' };
            byte[] bytes = strSrc.getBytes();
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(bytes);
            bytes = md.digest();
            int j = bytes.length;
            char[] chars = new char[j * 2];
            int k = 0;
            for (int i = 0; i < bytes.length; i++) {
                byte b = bytes[i];
                chars[k++] = hexChars[b >>> 4 & 0xf];
                chars[k++] = hexChars[b & 0xf];
            }
            return new String(chars);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            throw new RuntimeException("MD5加密出错！！+" + e);
        }
    }


}