package com.primeton.dailyreport.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Created by qinhao on 2018/4/27.
 */
public class MD5Util {

    /**
     * 生成32位md5码,加盐方式
     *
     * @param password
     * @return
     */
    public static String md5Password(String password) {
        try {
            // 得到一个信息摘要器
            MessageDigest digest = MessageDigest.getInstance("md5");
            byte[] result = digest.digest(password.getBytes());
            StringBuffer buffer = new StringBuffer();
            // 把每一个byte 做一个与运算 0xff;
            for (byte b : result) {
                // 与运算
                int number = b & 0xff;// 加盐
                String str = Integer.toHexString(number);
                if (str.length() == 1) {
                    buffer.append("0");
                }
                buffer.append(str);
            }

            // 标准的md5加密后的结果
            return buffer.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return "";
        }
    }

    public static void main(String[] args) {
        System.out.println(MD5Util.md5Password("1234"));
    }
}
