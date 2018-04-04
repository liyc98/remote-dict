package com.diandian.remotedictionary.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * Description of FilePathConfig
 * Copyright 2017 diandian technology, Inc. All rights reserved.
 *
 * @author yuancheng.li
 * @version $Id: $
 * @created on 2017年12月18日
 */
@Component
@ConfigurationProperties("file-path-config")
public class FilePathConfig {

    private String filePath;

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }
}
