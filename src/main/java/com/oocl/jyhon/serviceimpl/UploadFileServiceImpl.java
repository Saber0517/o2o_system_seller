package com.oocl.jyhon.serviceimpl;

import com.oocl.jyhon.dao.UploadFileEntityDao;
import com.oocl.jyhon.daoimple.UploadFileEntityDaoImple;
import com.oocl.jyhon.service.UploadFileService;

import java.io.InputStream;
import java.io.OutputStream;

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
