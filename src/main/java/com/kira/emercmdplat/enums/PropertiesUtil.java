package com.kira.emercmdplat.enums;

import java.io.IOException;
import java.util.Properties;

/**
 * @Author: kira
 * @Date: 2020/7/21 11:21
 * @Description:读取properties配置文件
 */
public enum PropertiesUtil {

    PROP_INSTANCE("/custom.properties") {
        @Override
        public void init(String filePath) {
            try {
                prop = new Properties();
                prop.load(PropertiesUtil.class.getResourceAsStream(filePath));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    };

    protected Properties prop;

    private String filePath;

    PropertiesUtil(String filePath) {
        this.filePath = filePath;
        this.init(this.filePath);
    }

    public abstract void init(String filePath);

    /**
     * 获取属性值
     * @param key
     * @return
     */
    public String getProp(String key) {
        return prop.getProperty(key);
    }
}
