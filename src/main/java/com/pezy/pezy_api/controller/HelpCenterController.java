package com.pezy.pezy_api.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.pezy.pezy_api.entity.HelpCenter;
import com.pezy.pezy_api.pojo.UploadFileResponse;
import com.pezy.pezy_api.service.FileStorageService;
import com.pezy.pezy_api.service.HelpCenterService;
import com.pezy.pezy_api.utils.FileUploadUtils;

@RestController
@RequestMapping("/v1/help-center")
@CrossOrigin(origins = "*")
public class HelpCenterController {

	@Autowired
	private HelpCenterService service;
	
	@Autowired
	private FileStorageService fileService;
	
	@PostMapping
	public ResponseEntity<?> create(@RequestBody HelpCenter hc){
		return service.create(hc);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<?> update(@PathVariable("id")Long id, @RequestBody HelpCenter hc){
		return service.update(hc, id);
	}
	
	@GetMapping
	public ResponseEntity<?> findAll(){
		return service.findAll();
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<?> findById(@PathVariable("id")Long id){
		return service.findById(id);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<?> deleteById(@PathVariable("id")Long id){
		return service.deleteById(id);
	}
	
	@PostMapping("/info-graphic/upload")
    public UploadFileResponse uploadInfoGraphic(@RequestParam MultipartFile file) {
		FileUploadUtils fileUtils = new FileUploadUtils();
		return fileUtils.uploadFile(file, fileService, "/v1/help-center/info-graphic");
	}

    @GetMapping("/info-graphic/{fileName:.+}")
    public ResponseEntity<Resource> downloadInfoGraphic(@PathVariable String fileName, HttpServletRequest request) {
    	FileUploadUtils fileUtils = new FileUploadUtils();
    	return fileUtils.downloadFile(fileName, request, fileService);
    }
	
	@PostMapping("/banner/upload")
    public UploadFileResponse uploadBanner(@RequestParam MultipartFile file) {
		FileUploadUtils fileUtils = new FileUploadUtils();
		return fileUtils.uploadFile(file, fileService, "/v1/help-center/banner");
	}

    @GetMapping("/banner/{fileName:.+}")
    public ResponseEntity<Resource> uploadBanner(@PathVariable String fileName, HttpServletRequest request) {
    	FileUploadUtils fileUtils = new FileUploadUtils();
    	return fileUtils.downloadFile(fileName, request, fileService);
    }
    
}
