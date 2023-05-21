package com.udacity.jwdnd.course1.cloudstorage.controller;

import com.udacity.jwdnd.course1.cloudstorage.model.File;
import com.udacity.jwdnd.course1.cloudstorage.model.User;
import com.udacity.jwdnd.course1.cloudstorage.services.FileService;
import com.udacity.jwdnd.course1.cloudstorage.services.LoginService;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MaxUploadSizeExceededException;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Map;
import java.util.Objects;

@Controller
@RequestMapping(value = "users/{username}/files")
@PreAuthorize("#username == authentication.name")
public class FileController {

    private final FileService fileService;
    private final LoginService loginService;

    public FileController(FileService fileService, LoginService loginService) {
        this.fileService = fileService;
        this.loginService = loginService;
    }

    @PostMapping()
    public String uploadOne(@PathVariable String username, @RequestParam(name = "fileUpload") MultipartFile multipartFile, Model model) throws IOException, MaxUploadSizeExceededException {
        User foundUser = loginService.findOneByUsername(username);
        Map<String, Object> resultDetails = fileService.uploadFile(foundUser.getId(), multipartFile);
        Integer uploadedId = (Integer) resultDetails.get("uploadedId");
        String message = (String) resultDetails.get("message");
        String controllerName;
        if (Objects.nonNull(uploadedId) && uploadedId > 0) {
            controllerName = "redirect:/home/users/".concat(username).concat("/upload-file-successfully/").concat(message);
        } else {
            controllerName = "redirect:/home/users/".concat(username).concat("/upload-file-fail/").concat(message);
        }
        return controllerName;
    }

    @GetMapping(value = "download/{fileId}")
    @ResponseBody
    public ResponseEntity<Resource> downloadOne(@PathVariable String username, @PathVariable Integer fileId) {
        User foundUser = loginService.findOneByUsername(username);
        File file = fileService.findOneByUserIdAndId(foundUser.getId(), fileId);
        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(file.getContentType()))
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + file.getName() + "\"")
                .body(new ByteArrayResource(file.getData()));
    }

    @GetMapping(value = "/delete/{fileId}")
    public String deleteOne(@PathVariable String username, @PathVariable(name = "fileId") Integer fileId, Model model) {
        User foundUser = loginService.findOneByUsername(username);
        fileService.deleteOneByUserIdAndId(foundUser.getId(), fileId);
        return "redirect:/home/users/".concat(username).concat("/delete-file-successfully");
    }
}
