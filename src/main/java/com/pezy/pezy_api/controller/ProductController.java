package com.pezy.pezy_api.controller;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpStatus;
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

import com.pezy.pezy_api.entity.Product;
import com.pezy.pezy_api.pojo.ResponseMessage;
import com.pezy.pezy_api.pojo.UploadFileResponse;
import com.pezy.pezy_api.service.FileStorageService;
import com.pezy.pezy_api.service.ProductService;
import com.pezy.pezy_api.utils.FileUploadUtils;

@RestController
@RequestMapping("/v1/product")
@CrossOrigin(origins = "*")
public class ProductController {

	@Autowired
	private ProductService service;
	
	@Autowired
	private FileStorageService fileService;
	
	private ResponseMessage msg = new ResponseMessage();
	
	@PostMapping
	public ResponseEntity<?> create(@RequestBody Product prod){
		prod.setId(null);
		return ResponseEntity.status(HttpStatus.CREATED).body(service.save(prod));
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<?> updateById(@PathVariable("id") Long id, @RequestBody Product prod){
		prod.setId(id);
		return ResponseEntity.ok(service.save(prod));
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<?> deleteById(@PathVariable("id") Long id){
		if(service.deleteById(id)) {
			msg.setMessage("Delete product item success.");
			return ResponseEntity.ok(msg);
		}else {
			msg.setMessage("Delete product item not success.");
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(msg);
		}
	}
	
	@GetMapping
	public ResponseEntity<?> findAll(){
		return ResponseEntity.ok(service.findAll());
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<?> findById(@PathVariable("id") Long id){
		Optional<Product> prodOpt = service.findById(id);
		if(prodOpt.isPresent()) {
			return ResponseEntity.ok(prodOpt);
		} else {
			msg.setMessage("Product item not found.");
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(msg);
		}
	}
	
	@PostMapping("/cover/upload")
    public UploadFileResponse uploadFile(@RequestParam MultipartFile file) {
		FileUploadUtils fileUtils = new FileUploadUtils();
		return fileUtils.uploadFile(file, fileService, "/v1/product/cover");
	}

    @GetMapping("/cover/{fileName:.+}")
    public ResponseEntity<Resource> downloadFile(@PathVariable String fileName, HttpServletRequest request) {
    	FileUploadUtils fileUtils = new FileUploadUtils();
    	return fileUtils.downloadFile(fileName, request, fileService);
    }
	
	@PostMapping("/gallery/upload")
    public UploadFileResponse uploadFileGallery(@RequestParam MultipartFile file) {
		FileUploadUtils fileUtils = new FileUploadUtils();
		return fileUtils.uploadFile(file, fileService, "/v1/product/gallery");
	}
	
    @PostMapping("/gallery/uploads")
    public List<UploadFileResponse> uploadMultipleFiles(@RequestParam("files") MultipartFile[] files) {
        return Arrays.asList(files)
                .stream()
                .map(file -> uploadFileGallery(file))
                .collect(Collectors.toList());
    }

    @GetMapping("/gallery/{fileName:.+}")
    public ResponseEntity<Resource> downloadFileGallery(@PathVariable String fileName, HttpServletRequest request) {
    	FileUploadUtils fileUtils = new FileUploadUtils();
    	return fileUtils.downloadFile(fileName, request, fileService);
    }
}
