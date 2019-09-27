package com.pezy.pezy_api.utils;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.pezy.pezy_api.pojo.UploadFileResponse;
import com.pezy.pezy_api.service.FileStorageService;

public class FileUploadUtils {
	
	private static Logger logger = LoggerFactory.getLogger("PezyLog");

    
    public UploadFileResponse uploadFile(MultipartFile file, FileStorageService fileStorageService, String pathFile) {
        String fileName = fileStorageService.storeFile(file);

        String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath()
//        		.path("/v1/upload")
//                .path("/downloadFile/")
        		.path(pathFile)
        		.path("/")
                .path(fileName)
                .toUriString();

        return new UploadFileResponse(fileName, fileDownloadUri,
                file.getContentType(), file.getSize());
    }

    public List<UploadFileResponse> uploadMultipleFiles(MultipartFile[] files, FileStorageService fileStorageService, String pathFile) {
        return Arrays.asList(files)
                .stream()
                .map(file -> uploadFile(file, fileStorageService, pathFile))
                .collect(Collectors.toList());
    }

    public ResponseEntity<Resource> downloadFile(String fileName, HttpServletRequest request, FileStorageService fileStorageService) {
        // Load file as Resource
        Resource resource = fileStorageService.loadFileAsResource(fileName);

        // Try to determine file's content type
        String contentType = null;
        try {
            contentType = request.getServletContext().getMimeType(resource.getFile().getAbsolutePath());
        } catch (IOException ex) {
            logger.info("Could not determine file type.");
        }

        // Fallback to the default content type if type could not be determined
        if(contentType == null) {
            contentType = "application/octet-stream";
        }

        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(contentType))
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + resource.getFilename() + "\"")
                .body(resource);
    }
}
