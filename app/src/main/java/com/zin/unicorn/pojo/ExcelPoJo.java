package com.zin.unicorn.pojo;

import java.io.Serializable;

/**
 * Created by ZhuJinMing on 2017/7/24.
 */
public class ExcelPoJo implements Serializable {

    private String fileName;
    private String createTime;
    private String fileSize;

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getFileSize() {
        return fileSize;
    }

    public void setFileSize(String fileSize) {
        this.fileSize = fileSize;
    }
}
