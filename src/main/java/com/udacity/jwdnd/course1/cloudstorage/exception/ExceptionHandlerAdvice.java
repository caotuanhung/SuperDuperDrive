package com.udacity.jwdnd.course1.cloudstorage.exception;

import com.udacity.jwdnd.course1.cloudstorage.services.FileService;
import com.udacity.jwdnd.course1.cloudstorage.util.ErrorMessage;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.multipart.MaxUploadSizeExceededException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@ControllerAdvice
public class ExceptionHandlerAdvice {
    private FileService fileService;
    public ExceptionHandlerAdvice(FileService fileService) {
        this.fileService = fileService;
    }

    @ExceptionHandler(MaxUploadSizeExceededException.class)
    public String handleMaxSizeException(
            MaxUploadSizeExceededException e,
            HttpServletRequest request,
            HttpServletResponse response) throws IOException, MaxUploadSizeExceededException {
        String username = request.getUserPrincipal().getName();
        String message = "Upload file fail! \nFile's size must more than 0, and less than " + fileService.getMaxFileSize();
        return "redirect:/home/users/".concat(username).concat("/upload-file-fail/").concat(message);
    }
}
