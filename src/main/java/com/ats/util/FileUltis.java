package com.ats.util;

import com.ats.model.FileModel;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class FileUltis {
    public static String saveFile(FileModel file, String path) {
        MultipartFile multipartFile = file.getFile();
            try {
                FileCopyUtils.copy(file.getFile().getBytes(), new File(path + file.getFile().getOriginalFilename()));
            } catch (IOException ex) {
                Logger.getLogger(FileUltis.class.getName()).log(Level.SEVERE, null, ex);
            }
            String fileName = multipartFile.getOriginalFilename();
            return fileName;
    }
}
