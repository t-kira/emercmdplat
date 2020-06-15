package com.kira.emercmdplat.utils;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;

/**
 * @Author: kira
 * @Date: 2020/6/11 12:27
 * @Description:
 */
public class SecuritySHA1Utils {

    /**
     * @return
     * @Comment SHA1实现
     * @Author kira
     * @Date 2020/6/11 12:27
     */
    public static String shaEncode(String inStr){
        MessageDigest sha = null;
        try {
            sha = MessageDigest.getInstance("SHA");
        } catch (Exception e) {
            System.out.println(e.toString());
            e.printStackTrace();
            return "";
        }
        try {
            byte[] byteArray = inStr.getBytes("UTF-8");
            byte[] md5Bytes = sha.digest(byteArray);
            StringBuffer hexValue = new StringBuffer();
            for (int i = 0; i < md5Bytes.length; i++) {
                int val = ((int) md5Bytes[i]) & 0xff;
                if (val < 16) {
                    hexValue.append("0");
                }
                hexValue.append(Integer.toHexString(val));
            }
            return hexValue.toString();
        } catch (UnsupportedEncodingException e) {
            System.out.println(e.toString());
            e.printStackTrace();
            return "";
        }
    }
}
