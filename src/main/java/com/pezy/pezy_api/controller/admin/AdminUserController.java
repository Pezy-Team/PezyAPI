package com.pezy.pezy_api.controller.admin;

import java.util.Optional;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.data.domain.PageRequest;
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

import com.pezy.pezy_api.entity.User;
import com.pezy.pezy_api.enumerate.GenderEnum;
import com.pezy.pezy_api.enumerate.RegisterByDeviceEnum;
import com.pezy.pezy_api.enumerate.RegisterByEnum;
import com.pezy.pezy_api.enumerate.UserTypeEnum;
import com.pezy.pezy_api.helper.AuthHelper;
import com.pezy.pezy_api.pojo.ResponseMessage;
import com.pezy.pezy_api.pojo.UploadFileResponse;
import com.pezy.pezy_api.properties.SecuritiesProperties;
import com.pezy.pezy_api.service.FileStorageService;
import com.pezy.pezy_api.service.UserService;
import com.pezy.pezy_api.utils.FileUploadUtils;
import com.pezy.pezy_api.utils.UserUtils;

@RestController
@RequestMapping("/v1/admin/user")
@CrossOrigin(origins = "*")
public class AdminUserController {

	@Autowired
	private UserService service;
	
	@GetMapping("/reg-device/{device}/limit/{limit}/of-page/{offset}")
	public ResponseEntity<?> findByRegisterDeviceIs(@PathVariable("device")RegisterByDeviceEnum device,
			@PathVariable("limit") Integer limit, @PathVariable("offset") Integer offset){
		User user = new User();
		user.setRegisterDevice(device);
		return service.findByRegisterDeviceIs(user, PageRequest.of(offset, limit));
	}
	
	@GetMapping("/reg-by/{by}/limit/{limit}/of-page/{offset}")
	public ResponseEntity<?> findByRegisterByIs(@PathVariable("by") RegisterByEnum by,
			@PathVariable("limit") Integer limit, @PathVariable("offset") Integer offset){
		User user = new User();
		user.setRegisterBy(by);
		return service.findByRegisterByIs(user, PageRequest.of(offset, limit));
	}
	
	@GetMapping("/gender-type/{gender}/limit/{limit}/of-page/{offset}")
	public ResponseEntity<?> findByGenderIs(@PathVariable("gender") GenderEnum gender,
			@PathVariable("limit") Integer limit, @PathVariable("offset") Integer offset){
		User user = new User();
		user.setGender(gender);
		return service.findByGenderIs(user, PageRequest.of(offset, limit));
	}
	
	@GetMapping("/type/{type}/limit/{limit}/of-page/{offset}")
	public ResponseEntity<?> findByUserTypeIs(@PathVariable("type") UserTypeEnum type, 
			@PathVariable("limit")Integer limit, @PathVariable("offset") Integer offset){
		User user = new User();
		user.setUserType(type);
		return service.findByUserTypeIs(user, PageRequest.of(offset, limit));
	}
	
}