/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.scan.operacion.commons;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 *
 * @author joel
 */
@ConfigurationProperties(prefix = "file")
public class FileStorageProperties {
    private String uploadDir;
    private String uploadDgira;

    public String getUploadDir() {
        return uploadDir;
    }

    public void setUploadDir(String uploadDir) {
        this.uploadDir = uploadDir;
    }

    public String getUploadDgira() {
        return uploadDgira;
    }

    public void setUploadDgira(String uploadDgira) {
        this.uploadDgira = uploadDgira;
    }
    
    
}
