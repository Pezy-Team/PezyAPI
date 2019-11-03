package com.pezy.pezy_api.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.pezy.pezy_api.entity.StoreAds;
import com.pezy.pezy_api.pojo.UploadFileResponse;
import com.pezy.pezy_api.service.FileStorageService;
import com.pezy.pezy_api.service.StoreAdsService;
import com.pezy.pezy_api.utils.FileUploadUtils;

@RestController
@RequestMapping("/v1/store-ads")
@CrossOrigin(origins = "*")
public class StoreAdsController {
	
	@Autowired
	private StoreAdsService service;
	
	@Autowired
	private FileStorageService fileService;
	
	@PostMapping
	public ResponseEntity<?> create(@RequestBody StoreAds ads){
		return service.create(ads);
	}
	
	@PutMapping
	public ResponseEntity<?> update(@RequestBody StoreAds ads){
		return service.update(ads);
	}
	
	@GetMapping
	public ResponseEntity<?> findAll(){
		return service.findAll();
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<?> findById(@PathVariable("id") Long id){
		return service.findById(id);
	}
	
	@GetMapping("/by-store-id/{id}")
	public ResponseEntity<?> findByStoreId(@PathVariable("id")Long id){
		return service.findAdsByStoreId(id);
	}
	
	
	/**
	 * Upload
	 */

	
	@PostMapping("/ads-banner/upload")
    public UploadFileResponse uploadFile(@RequestParam MultipartFile file) {
		FileUploadUtils fileUtils = new FileUploadUtils();
		return fileUtils.uploadFile(file, fileService, "/v1/store-ads/ads-banner");
	}

    @GetMapping("/ads-banner/{fileName:.+}")
    public ResponseEntity<Resource> downloadFile(@PathVariable String fileName, HttpServletRequest request) {
    	FileUploadUtils fileUtils = new FileUploadUtils();
    	return fileUtils.downloadFile(fileName, request, fileService);
    }

}
