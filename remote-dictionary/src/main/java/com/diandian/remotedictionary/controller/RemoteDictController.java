package com.diandian.remotedictionary.controller;

import com.diandian.remotedictionary.config.FilePathConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;

/**
 * Description of RemoteDictController
 * Copyright 2017 diandian technology, Inc. All rights reserved.
 *
 * @author yuancheng.li
 * @version $Id: $
 * @created on 2017年12月18日
 */
@RequestMapping("/remoteDict")
@Controller
public class RemoteDictController {

    private static Logger logger = LoggerFactory.getLogger(RemoteDictController.class);

    @Autowired
    private FilePathConfig filePathConfig;

    private static Long fileLastModify;

    private static final int LEN = System.lineSeparator().length();

    @RequestMapping(value = "/getRemoteDictValue", method = {RequestMethod.GET, RequestMethod.HEAD})
    @ResponseBody
    public String getRemoteDictValue(HttpServletRequest request, HttpServletResponse response) {

        String eTag = null;
        String methodType = request.getMethod();
        logger.debug("request method==>" + methodType);
        File file = new File(filePathConfig.getFilePath());
        if (!file.exists()) {
            return null;
        }
        long lastModified = file.lastModified();

        if (RequestMethod.HEAD.name().equalsIgnoreCase(methodType)) {
            //只要告诉他文件有没有变化
            if (fileLastModify != null && fileLastModify == lastModified) {
                response.setStatus(304);
            } else {
                fileLastModify = lastModified;
                response.setHeader("Last-Modified", String.valueOf(lastModified));
                response.setHeader("ETag", "ETag");
            }

            return null;

        } else if (RequestMethod.GET.name().equalsIgnoreCase(methodType)) {
            // 获取数据
            BufferedReader reader = null;
            // 获取文件内容
            try {
                reader = new BufferedReader(new InputStreamReader(new FileInputStream(file)));
                StringBuilder lineBuilder = new StringBuilder();
                String lineContent = null;
                while ((lineContent = reader.readLine()) != null) {
                    lineBuilder.append(lineContent);
                    lineBuilder.append(System.lineSeparator());
                }

                eTag = lineBuilder.toString();
                int len = eTag.length();
                eTag = eTag.substring(0, len - LEN);
            } catch (FileNotFoundException e) {
                logger.error("file not found", e);
                eTag = null;
            } catch (IOException e) {
                logger.error("read file error", e);
                eTag = null;
            } finally {
                if (reader != null) {
                    try {
                        reader.close();
                    } catch (IOException e) {
                        logger.error("close file error", e);
                    }
                }
            }

        }

        return eTag;
    }

}
