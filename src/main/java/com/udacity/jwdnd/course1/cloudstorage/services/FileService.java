package com.udacity.jwdnd.course1.cloudstorage.services;

import com.udacity.jwdnd.course1.cloudstorage.mapper.FileMapper;
import com.udacity.jwdnd.course1.cloudstorage.model.File;
import com.udacity.jwdnd.course1.cloudstorage.util.ErrorMessage;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MaxUploadSizeExceededException;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@Service
public class FileService {

    private final FileMapper fileMapper;
    @Value("${spring.servlet.multipart.max-file-size}")
    private String maxFileSize;

    public FileService(FileMapper fileMapper) {
        this.fileMapper = fileMapper;
    }

    public String getMaxFileSize() {
        return maxFileSize;
    }

    private File convertMultipartFileToFile(Integer userId, MultipartFile multipartFile) throws IOException, MaxUploadSizeExceededException {
        File file = null;
        try {
            String name = multipartFile.getOriginalFilename();
            String contentType = multipartFile.getContentType();
            byte[] data = multipartFile.getBytes();
            long size = multipartFile.getSize();
            String sizeStr = String.valueOf(size);
            if (size <= 0 || size > Integer.parseInt(getMaxFileSize().replace("MB", ""))*1024*1024) {
                throw new MaxUploadSizeExceededException(0);
            }
            file = new File(name, contentType, sizeStr, data, userId);
        }
         catch (MaxUploadSizeExceededException e) {

         }
        return file;
    }

    public Map<String, Object> uploadFile(Integer userId, MultipartFile multipartFile) throws IOException, MaxUploadSizeExceededException {
        Map<String, Object> result = new HashMap<>();
        Integer uploadedId = null;
        File file = convertMultipartFileToFile(userId, multipartFile);
        if (Objects.nonNull(file)) {
            File foundFile = fileMapper.selectOneByUserIdAndName(userId, file.getName());
            if (Objects.nonNull(foundFile)) {
                uploadedId = 0;
                String message = ErrorMessage.FILE_NAME_EXIST_MESSAGE;
                result.put("message", message);
            } else {
                uploadedId = fileMapper.insertOne(file);
                result.put("message", "File uploaded successfully!");
            }
        } else {
            uploadedId = 0;
            String message = ErrorMessage.FILE_SIZE_LIMIT.concat(getMaxFileSize());
            result.put("message", message);
        }
        result.put("uploadedId", uploadedId);
        return result;
    }

    public List<File> findAllByUserId(Integer userId) {
        return fileMapper.selectAllByUserId(userId);
    }

    public List<File> findAllByUserIdOrderByIdDesc(Integer userId) {
        return fileMapper.selectAllByUserIdOrderById(userId);
    }

    public File findOneByUserIdAndId(Integer userId, Integer id) {
        return fileMapper.selectOneByUserIdAndId(userId, id);
    }

    public void deleteOneByUserIdAndId(Integer userId, Integer id) {
        fileMapper.deleteOneByUserIdAndId(userId, id);
    }

    public List<File> findAllByUsernameOrderByIdDesc(String username) {
        return fileMapper.selectAllByUsernameOrderById(username);
    }
}
