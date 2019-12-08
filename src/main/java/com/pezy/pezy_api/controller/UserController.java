package com.pezy.pezy_api.controller;

import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.HeaderParam;

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

import com.pezy.pezy_api.entity.User;
import com.pezy.pezy_api.helper.AuthHelper;
import com.pezy.pezy_api.pojo.AuthRequest;
import com.pezy.pezy_api.pojo.ResponseMessage;
import com.pezy.pezy_api.pojo.TokenMessage;
import com.pezy.pezy_api.pojo.UploadFileResponse;
import com.pezy.pezy_api.properties.SecuritiesProperties;
import com.pezy.pezy_api.service.FileStorageService;
import com.pezy.pezy_api.service.UserService;
import com.pezy.pezy_api.utils.FileUploadUtils;
import com.pezy.pezy_api.utils.UserUtils;

@RestController
@RequestMapping("/v1/user")
@CrossOrigin(origins = "*")
public class UserController {

	@Autowired
	private UserService userServ;
	
	@Autowired
	private SecuritiesProperties secProp;
	
	@Autowired
	private FileStorageService fileService;
	
	private UserUtils userUtils = new UserUtils();
	private ResponseMessage fMsg = new ResponseMessage();

	@PostMapping
	public ResponseEntity<?> save(@RequestBody User user) {
		/**
		 * Check user exist.
		 */
		if(userUtils.isEmailExist(user.getEmail(), userServ)) {
			fMsg.setMessage("This email is exists.");
			return ResponseEntity.badRequest().body(fMsg);
		} else if(userUtils.isUsernameExist(user.getUsername(), userServ)) {
			fMsg.setMessage("This username is exists.");
			return ResponseEntity.badRequest().body(fMsg);
		}
		
		String raw = user.getPassword() + secProp.getSALT();
		String pwd = AuthHelper.bcrypt(secProp.getSTRENGTH(), raw);
		user.setPassword(pwd);
		User userResult = userServ.save(user);
		userResult.setPassword("***");
		return ResponseEntity.status(HttpStatus.CREATED).body(userResult);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<?> findById(@PathVariable("id") Long id) {
		Optional<User> userOpt = userServ.findById(id);
		if(userOpt.isPresent()) {
			userOpt.get().setPassword("***");
			return ResponseEntity.ok(userOpt);
		}
		
		return ResponseEntity.notFound().build();
	}
	
	@PutMapping
	public ResponseEntity<?> update(@RequestBody User user){
		Optional<User> userOpt = userServ.findById(user.getId());
		user.setPassword(userOpt.get().getPassword());
		return ResponseEntity.ok(userServ.save(user));
	}
	
	@GetMapping("/getprofile")
	public ResponseEntity<?> getProfile(@RequestParam("token") String token){
		return userServ.getProfileByTokenAndTokenExpireAfter(token);
	}
	
	@GetMapping("/is-token-expire")
	public ResponseEntity<?> isTokenExpire(@RequestParam("token") String token){
		return userServ.isTokenExpire(token);
	}

	@PostMapping("/auth-by-email")
	public ResponseEntity<?> authByEmail(@RequestBody AuthRequest user) {
		List<User> users = userServ.findUserByEmail(user.getEmail());
		ResponseMessage resMessage = new ResponseMessage();
		if (users.isEmpty()) {
			resMessage.setMessage("Email or username was invalid.");
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(resMessage);
		}
		
		/**
		 * Verify token.
		 */
		String pwd = user.getPassword() + secProp.getSALT();
		User userResult = userUtils.verifyPassword(users, pwd);
		if(userResult == null) {
			resMessage.setMessage("Username or password was invalid.");
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(resMessage);
		}
		
		/**
		 * Store token.
		 */
		TokenMessage tokenMsg = new TokenMessage();
		String token = userUtils.storeToken(userServ, userResult, secProp);
		tokenMsg.setUser(userServ.findUserByToken(token));
		tokenMsg.setToken(token);
		tokenMsg.setMessage("Authenticate successful.");
		return ResponseEntity.ok(tokenMsg);
	}
	
	@PostMapping("/auth-by-username")
	public ResponseEntity<?> authByUsername(@RequestBody AuthRequest user) {
		List<User> users = userServ.findUserByUsername(user.getUsername());
		ResponseMessage resMessage = new ResponseMessage();
		if (users.isEmpty()) {
			resMessage.setMessage("Email or username was invalid.");
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(resMessage);
		}
		
		/**
		 * Verify token.
		 */
		User userResult = userUtils.verifyPassword(users, user.getPassword() + secProp.getSALT());
		if(userResult == null) {
			resMessage.setMessage("Username or password was invalid.");
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(resMessage);
		}
		
		/**
		 * Store token.
		 */
		TokenMessage tokenMsg = new TokenMessage();
		String token = userUtils.storeToken(userServ, userResult, secProp);
		tokenMsg.setUser(userServ.findUserByToken(token));
		tokenMsg.setToken(token);
		tokenMsg.setMessage("Authenticate successful.");
		return ResponseEntity.ok(tokenMsg);
	}
	
	@GetMapping("/structure")
	public ResponseEntity<?> getStructure(){
		User body = new User();
		return ResponseEntity.ok(body);
	}
	
	@PostMapping("/profile/upload")
    public UploadFileResponse uploadFile(@RequestParam MultipartFile file) {
		FileUploadUtils fileUtils = new FileUploadUtils();
		return fileUtils.uploadFile(file, fileService, "/v1/user/profile");
	}

    @GetMapping("/profile/{fileName:.+}")
    public ResponseEntity<Resource> downloadFile(@PathVariable String fileName, HttpServletRequest request) {
    	FileUploadUtils fileUtils = new FileUploadUtils();
    	return fileUtils.downloadFile(fileName, request, fileService);
    }

}
