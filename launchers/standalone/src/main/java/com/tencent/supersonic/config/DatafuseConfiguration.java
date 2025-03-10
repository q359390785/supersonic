package com.tencent.supersonic.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;

@Configuration
public class DatafuseConfiguration {

    /**
     * datafuse驱动位置
     */
    @Value("${datafuse.plugin_dir}")
    private String pluginDir;

    @PostConstruct
    public void setPluginDirAsSystemProperty() {
        // 将读取到的变量设置为系统属性
        System.setProperty("plugin_dir", pluginDir);
    }
}
