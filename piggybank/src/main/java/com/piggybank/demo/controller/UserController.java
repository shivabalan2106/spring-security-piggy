package com.piggybank.demo.controller;

import java.util.List;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.piggybank.demo.model.PiggyUser;
import com.piggybank.demo.payload.request.LoginRequest;
import com.piggybank.demo.payload.response.JwtResponse;
import com.piggybank.demo.payload.response.MessageResponse;
import com.piggybank.demo.repository.UserRepository;
import com.piggybank.demo.security.jwt.JwtUtils;
import com.piggybank.demo.security.service.UserDetailsImpl;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/v1/auth")
public class UserController {
	
	  @Autowired
	  UserRepository userRepo;
	
	  @Autowired
	  AuthenticationManager authenticationManager;

	  @Autowired
	  PasswordEncoder encoder;

	  @Autowired
	  JwtUtils jwtUtils;
	
	@PostMapping("/adduser")
	public PiggyUser addNewUser(@RequestBody LoginRequest loginRequest) {
		PiggyUser userdetails = new PiggyUser();
		userdetails.setFirstName(loginRequest.getFirstName());
		userdetails.setLastName(loginRequest.getLastName());
		userdetails.setMobileNumber(loginRequest.getMobileNumber());
		userdetails.setUserName(loginRequest.getUserName());
		userRepo.save(userdetails);
		return userdetails;
	}
	
	@GetMapping("/getalluser")
	public @ResponseBody List<PiggyUser> getAllUser() {
		return (List<PiggyUser>) userRepo.findAll();
	}
	
	@GetMapping("/getuser")
	public PiggyUser getUser(@RequestBody LoginRequest loginRequest) {
		System.out.println("number is...."+loginRequest.getMobileNumber());
		System.out.println("get repo....."+userRepo.findBymobileNumber(loginRequest.getMobileNumber()));
		return userRepo.findBymobileNumber(loginRequest.getMobileNumber());
	}
	
	@PostMapping("/signin")
	  public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {

			   	  Authentication authentication = authenticationManager.authenticate(new
				  UsernamePasswordAuthenticationToken(loginRequest.getMobileNumber(),loginRequest.getPassword()));
				  SecurityContextHolder.getContext().setAuthentication(authentication);
				  String jwt = jwtUtils.generateJwtToken(authentication);
				  UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();    
				  return ResponseEntity.ok(new JwtResponse(jwt, 
	                         userDetails.getUserId(), 
	                         userDetails.getUsername()));
	  }
	
	 @PostMapping("/signup")
	  public ResponseEntity<?> registerUser(@Valid @RequestBody LoginRequest loginRequest) {
			/*
			 * if (userRepository.existsByUsername(signUpRequest.getUsername())) { return
			 * ResponseEntity .badRequest() .body(new
			 * MessageResponse("Error: Username is already taken!")); }
			 * 
			 * if (userRepository.existsByEmail(signUpRequest.getEmail())) { return
			 * ResponseEntity .badRequest() .body(new
			 * MessageResponse("Error: Email is already in use!")); }
			 */
	    
	    String sendedOtp = generateUserOtp(loginRequest.getMobileNumber());

	    // Create new user's account
	    PiggyUser user = new PiggyUser(loginRequest.getUserName(), 
	    		loginRequest.getMobileNumber(),
	               sendedOtp);
	              // encoder.encode(signUpRequest.getPassword()));

	    userRepo.save(user);

	    return ResponseEntity.ok(new MessageResponse("User registered successfully and your otp for the login is"+sendedOtp));
	  }
	 
	 public String generateUserOtp(String mobileNumber) {
			String otpMsg="";
			
			String numbers = "0123456789";  
		    Random rndm_method = new Random();  
		    char[] otp = new char[4];  
		    for (int i = 0; i < 4; i++) {  
		        otp[i] = numbers.charAt(rndm_method.nextInt(numbers.length()));  
		    }  
			otpMsg = String.valueOf(otp);
			//piggyUser = checkIfExistingUser(number);
			
			/*
			 * if(piggyUser.getUserNumber()!=null) {
			 * System.out.println("enter userDetails is null"+piggyUser.getUserName()); }
			 */
			//sendUserOtp(number,otpMsg);
			System.out.println("message is...."+otpMsg);
			return otpMsg;
		}

}
