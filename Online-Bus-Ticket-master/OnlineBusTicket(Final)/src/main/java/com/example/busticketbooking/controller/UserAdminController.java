package com.example.busticketbooking.controller;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.busticketbooking.Response.CustomDetailsImpl;
import com.example.busticketbooking.Response.UserAdminLogin;
import com.example.busticketbooking.Response.UserAdminResponse;
import com.example.busticketbooking.configuration.JwtProvider;
import com.example.busticketbooking.entity.UserAdmin;
import com.example.busticketbooking.repository.UserAdminRepository;

@Controller
@RequestMapping("/auth")
public class UserAdminController {

	@Autowired
	private UserAdminRepository userAdminRepo;

	@Autowired
	private JwtProvider jwtProvider;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Autowired(required = true)
	private AuthenticationManager authenticationManager;

	@Autowired
	private CustomDetailsImpl customDetailsImpl;

	private static final String PASSWORD_PATTERN = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=!])(?=\\S+$).{8,}$";

	
	private boolean isValidPassword(String password) {

		return password.matches(PASSWORD_PATTERN);
	}
	

	@PostMapping("/userRegister")
	public ResponseEntity<UserAdminResponse> registerUser(@RequestBody UserAdmin user) {

		String email = user.getEmail();
		String password = user.getPassword();
		String Name = user.getName();
		Date DOB = user.getDob();
		String phoneNumber = user.getPhone();
		String Gender = user.getGender();
		int Age = user.getAge();
		//String addRole = "ROLE_USER";
		
		UserAdminResponse UserResponse = new UserAdminResponse();
		
		
		if (!isValidPassword(password)) {
            UserResponse.setMessage("Password must be at least 8 characters long and contain at least one uppercase letter, one lowercase letter, one digit, and one special character.");
            UserResponse.setSuccess(false);
            return new ResponseEntity<>(UserResponse, HttpStatus.BAD_REQUEST);
        }

		UserAdmin isEmailExist = userAdminRepo.findByEmail(email);


		if (isEmailExist != null) {
			UserResponse.setMessage("Email is Already Used with another account");
			UserResponse.setSuccess(false);
			return new ResponseEntity<>(UserResponse, HttpStatus.BAD_REQUEST);
		}
		UserAdmin addUser = new UserAdmin();
		addUser.setEmail(email);
		addUser.setPassword(passwordEncoder.encode(password));
		addUser.setName(Name);
		addUser.setPhone(phoneNumber);
		addUser.setGender(Gender);
		addUser.setAge(Age);
		//addUser.setRole(addRole);
		addUser.setDob(DOB);

		UserAdmin savedUser = userAdminRepo.save(addUser);

		Authentication authentication = new UsernamePasswordAuthenticationToken(savedUser.getEmail(),
				savedUser.getPassword());

		SecurityContextHolder.getContext().setAuthentication(authentication);

		String token = jwtProvider.generateToken(authentication);

		// AuthResponse authResponse = new AuthResponse();
		UserResponse.setJwt(token);
		UserResponse.setMessage("Account Created Successfully");
		UserResponse.setSuccess(true);

		return new ResponseEntity<UserAdminResponse>(UserResponse, HttpStatus.CREATED);
	}

	@PostMapping("/registerAdmin")
	public ResponseEntity<UserAdminResponse> ResgisteAdmin(@RequestBody UserAdmin user) {

		String email = user.getEmail();
		String password = user.getPassword();
		String Name = user.getName();
		Date DOB = user.getDob();
		String phoneNumber = user.getPhone();
		String Gender = user.getGender();
		int Age = user.getAge();
		String addRole = "ROLE_ADMIN";
		
		UserAdminResponse UserResponse = new UserAdminResponse();
		
		if (!isValidPassword(password)) {
            UserResponse.setMessage("Password must be at least 8 characters long and contain at least one uppercase letter, one lowercase letter, one digit, and one special character.");
            UserResponse.setSuccess(false);
            return new ResponseEntity<>(UserResponse, HttpStatus.BAD_REQUEST);
        }

		UserAdmin isEmailExist = userAdminRepo.findByEmail(email);


		if (isEmailExist != null) {
			UserResponse.setMessage("Email is Already Used with another account");
			UserResponse.setSuccess(false);
			return new ResponseEntity<>(UserResponse, HttpStatus.BAD_REQUEST);
		}
		UserAdmin addUser = new UserAdmin();
		addUser.setEmail(email);
		addUser.setPassword(passwordEncoder.encode(password));
		addUser.setName(Name);
		addUser.setPhone(phoneNumber);
		addUser.setGender(Gender);
		addUser.setAge(Age);
		//addUser.setRole(addRole);
		addUser.setDob(DOB);

		UserAdmin savedUser = userAdminRepo.save(addUser);

		Authentication authentication = new UsernamePasswordAuthenticationToken(savedUser.getEmail(),
				savedUser.getPassword());

		SecurityContextHolder.getContext().setAuthentication(authentication);

		String token = jwtProvider.generateToken(authentication);

		// AuthResponse authResponse = new AuthResponse();
		UserResponse.setJwt(token);
		UserResponse.setMessage("Account Created Successfully");
		UserResponse.setSuccess(true);

		return new ResponseEntity<UserAdminResponse>(UserResponse, HttpStatus.CREATED);
	}

	@PostMapping("/Login")
	public ResponseEntity<UserAdminResponse> loginUserHandler(@RequestBody UserAdminLogin loginRequest) {

		String username = loginRequest.getEmail();

		String password = loginRequest.getPassword();

		//Authentication authentication = authenticationManager
				//.authenticate(new UsernamePasswordAuthenticationToken(username, password));

		 Authentication authentication = authenticate(username, password);

		SecurityContextHolder.getContext().setAuthentication(authentication);

		String token = jwtProvider.generateToken(authentication);

		UserAdmin userAdmin = userAdminRepo.findByEmail(username);

		UserAdminResponse userAdminResponse = new UserAdminResponse();
		userAdminResponse.setJwt(token);
		userAdminResponse.setMessage("Login Successfullyy");
		userAdminResponse.setUserAdmin(userAdmin);
		userAdminResponse.setSuccess(true);

		return new ResponseEntity<UserAdminResponse>(userAdminResponse, HttpStatus.FOUND);
	}

	private Authentication authenticate(String username, String password) {
		UserDetails userAgentDetails = customDetailsImpl.loadUserByUsername(username);

		if (userAgentDetails == null) {
			throw new BadCredentialsException("Invalid Email Id...");
		}

		if (!passwordEncoder.matches(password, userAgentDetails.getPassword())) {
			throw new BadCredentialsException("Invalid Password for email id" + userAgentDetails.getUsername());
		}

		System.out.println(userAgentDetails);
		return new UsernamePasswordAuthenticationToken(userAgentDetails, null, userAgentDetails.getAuthorities());
	}

}
