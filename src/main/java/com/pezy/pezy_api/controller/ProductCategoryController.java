package com.pezy.pezy_api.controller;

import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpStatus;
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

import com.pezy.pezy_api.entity.ProductCategory;
import com.pezy.pezy_api.pojo.ResponseMessage;
import com.pezy.pezy_api.pojo.UploadFileResponse;
import com.pezy.pezy_api.service.FileStorageService;
import com.pezy.pezy_api.service.ProductCategoryService;
import com.pezy.pezy_api.utils.FileUploadUtils;

@RestController
@RequestMapping("/v1/category")
@CrossOrigin(origins = "*")
public class ProductCategoryController {

	@Autowired
	private ProductCategoryService service;
	
	@Autowired
	private FileStorageService fileService;
	
	private ResponseMessage msg = new ResponseMessage();
	
	@PostMapping
	public ResponseEntity<?> create(@RequestBody ProductCategory category){
		category.setId(null);
		return ResponseEntity.status(HttpStatus.CREATED).body(service.save(category));
	}
	
	@PutMapping
	public ResponseEntity<?> update(@RequestBody ProductCategory category){
		return ResponseEntity.ok(service.save(category));
	}
	
	@GetMapping
	public ResponseEntity<?> findAll(){
		List<ProductCategory> categories = service.findAll();
		if(categories.isEmpty()) {
			msg.setMessage("Data not found");
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(msg);
		}
		return ResponseEntity.ok(categories);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<?> findById(@PathVariable("id") Long id){
		Optional<ProductCategory> categoriesOptional = service.findById(id);
		if(categoriesOptional.isPresent()) {
			return ResponseEntity.ok(categoriesOptional);
		}
		msg.setMessage("Category item not found.");
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(msg);
	}
	
	@PostMapping("/tile/upload")
    public UploadFileResponse uploadFile(@RequestParam MultipartFile file) {
		FileUploadUtils fileUtils = new FileUploadUtils();
		return fileUtils.uploadFile(file, fileService, "/v1/category/tile");
	}

    @GetMapping("/tile/{fileName:.+}")
    public ResponseEntity<Resource> downloadFile(@PathVariable String fileName, HttpServletRequest request) {
    	FileUploadUtils fileUtils = new FileUploadUtils();
    	return fileUtils.downloadFile(fileName, request, fileService);
    }
}
