package org.example.datn.impl;

import org.example.datn.service.UploadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletContext;
import java.io.File;
@Service
public class UploadImpl implements UploadService {
    @Autowired
    ServletContext app;

    @Override
    public File save(MultipartFile file, String folder) {
        File dir = new File("D:/FPT Polytechnic/DUANTOTNGHIEP/DATN_SD45/src/main/resources/static/" + folder);
        if (!dir.exists()) {
            dir.mkdirs();
        }
        String s = System.currentTimeMillis()+file.getOriginalFilename();
        String name=Integer.toHexString(s.hashCode())+s.substring(s.lastIndexOf("."));
        try {
            File savedFile = new File(dir,name);
            file.transferTo(savedFile);
            System.out.println(savedFile.getAbsolutePath());
            return savedFile;
        }catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
