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

import com.pezy.pezy_api.entity.Order;
import com.pezy.pezy_api.pojo.UploadFileResponse;
import com.pezy.pezy_api.service.FileStorageService;
import com.pezy.pezy_api.service.OrderService;
import com.pezy.pezy_api.utils.FileUploadUtils;

@RestController
@RequestMapping("/v1/order")
@CrossOrigin(origins = "*")
public class OrderController {

	@Autowired
	private OrderService service;
	
	@Autowired
	private FileStorageService fileService;
	
	@PostMapping
	public ResponseEntity<?> create(@RequestBody Order o){
		return service.create(o);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<?> updateById(@PathVariable("id")Long id, @RequestBody Order o){
		return service.updateById(o, id);
	}
	
	@GetMapping
	public ResponseEntity<?> findAll(){
		return service.findAll();
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<?> findById(@PathVariable("id")Long id){
		return service.findById(id);
	}
	
	/**
	 * Uploading
	 */
	
	@PostMapping("/payment-slip/upload")
    public UploadFileResponse slipUpload(@RequestParam MultipartFile file) {
		FileUploadUtils fileUtils = new FileUploadUtils();
		return fileUtils.uploadFile(file, fileService, "/v1/order/payment-slip");
	}

    @GetMapping("/payment-slip/{fileName:.+}")
    public ResponseEntity<Resource> slipDownload(@PathVariable String fileName, HttpServletRequest request) {
    	FileUploadUtils fileUtils = new FileUploadUtils();
    	return fileUtils.downloadFile(fileName, request, fileService);
    }
	
	@PostMapping("/packing-picture/upload")
    public UploadFileResponse packingUpload(@RequestParam MultipartFile file) {
		FileUploadUtils fileUtils = new FileUploadUtils();
		return fileUtils.uploadFile(file, fileService, "/v1/order/packing-picture");
	}

    @GetMapping("/packing-picture/{fileName:.+}")
    public ResponseEntity<Resource> packingDownload(@PathVariable String fileName, HttpServletRequest request) {
    	FileUploadUtils fileUtils = new FileUploadUtils();
    	return fileUtils.downloadFile(fileName, request, fileService);
    }
	
	@PostMapping("/transport-bill/upload")
    public UploadFileResponse transportBillUpload(@RequestParam MultipartFile file) {
		FileUploadUtils fileUtils = new FileUploadUtils();
		return fileUtils.uploadFile(file, fileService, "/v1/order/transport-bill");
	}

    @GetMapping("/transport-bill/{fileName:.+}")
    public ResponseEntity<Resource> transportBillDownload(@PathVariable String fileName, HttpServletRequest request) {
    	FileUploadUtils fileUtils = new FileUploadUtils();
    	return fileUtils.downloadFile(fileName, request, fileService);
    }
    
}
