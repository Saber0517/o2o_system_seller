package com.jyhon.serviceimpl;

import com.jyhon.service.UploadFileService;
import com.oocl.jyhon.dao.UploadFileEntityDao;
import com.oocl.jyhon.daoimple.UploadFileEntityDaoImple;

import java.io.InputStream;

/**
 * Created by WhiteSaber on 15/8/9.
 */
public class UploadFileServiceImpl implements UploadFileService {
    UploadFileEntityDao uploadFileEntityDao = new UploadFileEntityDaoImple();

    public int insert(InputStream inputStream, String filename) {
        return uploadFileEntityDao.insert(inputStream, filename);
    }

    public InputStream getFile(String fileName) {
        return uploadFileEntityDao.getFile(fileName);
    }
}
