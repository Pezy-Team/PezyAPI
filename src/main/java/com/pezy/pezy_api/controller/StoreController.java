package com.pezy.pezy_api.controller;

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

import com.pezy.pezy_api.entity.Store;
import com.pezy.pezy_api.pojo.ResponseMessage;
import com.pezy.pezy_api.pojo.UploadFileResponse;
import com.pezy.pezy_api.service.FileStorageService;
import com.pezy.pezy_api.service.StoreService;
import com.pezy.pezy_api.utils.FileUploadUtils;

@RestController
@RequestMapping("/v1/store")
@CrossOrigin(origins = "*")
public class StoreController {

	@Autowired
	private StoreService service;
	
	@Autowired
	private FileStorageService fileService;
	
	ResponseMessage msg = new ResponseMessage();
	
	@PostMapping
	public ResponseEntity<?> create(@RequestBody Store store){
		store.setId(null);
		return ResponseEntity.status(HttpStatus.CREATED).body(service.save(store));
	}
	
	@PutMapping
	public ResponseEntity<?> update(@RequestBody Store store){
		return ResponseEntity.ok(service.save(store));
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<?> findById(@PathVariable("id") Long id){
		Optional<Store> storeOptional = service.findById(id);
		if(storeOptional.isPresent()) {
			return ResponseEntity.ok(storeOptional);
		}
		msg.setMessage("Store not found!");
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(msg);
	}

	@PostMapping("/logo/upload")
    public UploadFileResponse uploadLogo(@RequestParam MultipartFile file) {
		FileUploadUtils fileUtils = new FileUploadUtils();
		return fileUtils.uploadFile(file, fileService, "/v1/store/logo");
	}

    @GetMapping("/logo/{fileName:.+}")
    public ResponseEntity<Resource> downloadLogo(@PathVariable String fileName, HttpServletRequest request) {
    	FileUploadUtils fileUtils = new FileUploadUtils();
    	return fileUtils.downloadFile(fileName, request, fileService);
    }

	@PostMapping("/id-card/upload")
    public UploadFileResponse uploadIdCard(@RequestParam MultipartFile file) {
		FileUploadUtils fileUtils = new FileUploadUtils();
		return fileUtils.uploadFile(file, fileService, "/v1/store/id-card");
	}

    @GetMapping("/id-card/{fileName:.+}")
    public ResponseEntity<Resource> downloadIdCard(@PathVariable String fileName, HttpServletRequest request) {
    	FileUploadUtils fileUtils = new FileUploadUtils();
    	return fileUtils.downloadFile(fileName, request, fileService);
    }

	@PostMapping("/id-card-with-face/upload")
    public UploadFileResponse uploadIdCardWithFace(@RequestParam MultipartFile file) {
		FileUploadUtils fileUtils = new FileUploadUtils();
		return fileUtils.uploadFile(file, fileService, "/v1/store/id-card-with-face");
	}

    @GetMapping("/id-card-with-face/{fileName:.+}")
    public ResponseEntity<Resource> downloadIdCardWithFace(@PathVariable String fileName, HttpServletRequest request) {
    	FileUploadUtils fileUtils = new FileUploadUtils();
    	return fileUtils.downloadFile(fileName, request, fileService);
    }

	@PostMapping("/bookbank/upload")
    public UploadFileResponse uploadBookbank(@RequestParam MultipartFile file) {
		FileUploadUtils fileUtils = new FileUploadUtils();
		return fileUtils.uploadFile(file, fileService, "/v1/store/bookbank");
	}

    @GetMapping("/bookbank/{fileName:.+}")
    public ResponseEntity<Resource> downloadBookbank(@PathVariable String fileName, HttpServletRequest request) {
    	FileUploadUtils fileUtils = new FileUploadUtils();
    	return fileUtils.downloadFile(fileName, request, fileService);
    }
	
	@GetMapping("/structure")
	public ResponseEntity<?> getStructure(){
		return ResponseEntity.ok(new Store());
	}
	
}
