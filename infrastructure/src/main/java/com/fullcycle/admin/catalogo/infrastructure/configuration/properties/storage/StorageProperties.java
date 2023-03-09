package com.fullcycle.admin.catalogo.infrastructure.configuration.properties.storage;

import com.fullcycle.admin.catalogo.infrastructure.services.StorageService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;

public class StorageProperties implements InitializingBean {

    private static final Logger log = LoggerFactory.getLogger(StorageProperties.class);
    private String locationPattern;

    private String filenamePatter;
    private StorageService storageService;

    public StorageProperties() {
    }

    public String getLocationPattern() {
        return locationPattern;
    }

    public void setLocationPattern(String locationPattern) {
        this.locationPattern = locationPattern;
    }

    public StorageService getStorageService() {
        return storageService;
    }

    public void setStorageService(StorageService storageService) {
        this.storageService = storageService;
    }

    public String getFilenamePatter() {
        return filenamePatter;
    }

    public void setFilenamePatter(String filenamePatter) {
        this.filenamePatter = filenamePatter;
    }

    @Override
    public void afterPropertiesSet() {
        log.debug(toString());
    }

    @Override
    public String toString() {
        return "StorageProperties{" +
                "locationPattern='" + locationPattern + '\'' +
                ", filenamePatter='" + filenamePatter + '\'' +
                ", storageService=" + storageService +
                '}';
    }
}
