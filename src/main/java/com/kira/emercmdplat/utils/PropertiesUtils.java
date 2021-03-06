package com.kira.emercmdplat.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Properties;

/**
 * @Author: kira
 * @Date: 2020/4/8 01:15
 * @Description:读取properties文件
 */
public class PropertiesUtils {

    private static volatile Properties properties;
    private static PropertiesUtils propertiesUtils = new PropertiesUtils();

    /**
     * 私有构造，禁止直接创建
     */
    private PropertiesUtils() {
        properties = new Properties();
        InputStream in = PropertiesUtils.class.getClassLoader()
                .getResourceAsStream("custom.properties");
        BufferedReader bf = new BufferedReader(new InputStreamReader(in));
        try {
            properties.load(bf);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 获取单例
     *double check + lock 防止被多个进程创建多个单例对象
     * @return PropertiesUtils
     */
    public static PropertiesUtils getInstance() {
        if (propertiesUtils == null) {
            synchronized (PropertiesUtils.class) {
                if (propertiesUtils == null) {
                    propertiesUtils = new PropertiesUtils();
                }
            }
        }
        return propertiesUtils;
    }

    /**
     * 根据属性名读取值
     *
     * @param name 名称
     */
    public String getProperty(String name) {
        return properties.getProperty(name);
    }

    public String getAttachmentGainPath() {
        return properties.getProperty("attachmentGainPath");
    }
}
