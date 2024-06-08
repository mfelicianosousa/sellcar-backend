package br.com.mfsdevsystem.sellcar.controller;

import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.mfsdevsystem.sellcar.dto.AuthenticationRequest;
import br.com.mfsdevsystem.sellcar.dto.AuthenticationResponse;
import br.com.mfsdevsystem.sellcar.dto.SignupRequest;
import br.com.mfsdevsystem.sellcar.dto.UserDTO;
import br.com.mfsdevsystem.sellcar.entities.User;
import br.com.mfsdevsystem.sellcar.repository.UserRepository;
import br.com.mfsdevsystem.sellcar.services.auth.AuthService;
import br.com.mfsdevsystem.sellcar.services.jwt.UserService;
import br.com.mfsdevsystem.sellcar.utils.JWTUtil;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

	private final AuthService authService;
	
	private final JWTUtil jwtUtil;
	
	private final UserService userService;
	
	private final UserRepository userRepository;
	
	private final AuthenticationManager authManager;

	public AuthController(AuthService authService, JWTUtil jwtUtil, UserService userService,
			UserRepository userRepository, AuthenticationManager authManager) {
		this.authService = authService;
		this.jwtUtil = jwtUtil;
		this.userService = userService;
		this.userRepository = userRepository;
		this.authManager = authManager;
	}



	@PostMapping("/signup")
	public ResponseEntity<?> signup(@RequestBody SignupRequest signupRequest){
		if (authService.hasUserWithEmail(signupRequest.getEmail()))
			return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body("User already exist");
		UserDTO userDTO = authService.signup(signupRequest);
		if (userDTO == null)
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
		return ResponseEntity.status(HttpStatus.CREATED).body(userDTO);
		
	}
	
	@PostMapping("/login")
	public AuthenticationResponse login( @RequestBody AuthenticationRequest authenticationRequest) {
		try {
			authManager.authenticate(new UsernamePasswordAuthenticationToken(
					authenticationRequest.getEmail(),
					authenticationRequest.getPassword()));
		} catch (BadCredentialsException e) {
			throw new BadCredentialsException("Incorrect username or password");
		}
		
		final UserDetails userDetails = userService.userDetailService().loadUserByUsername(authenticationRequest.getEmail());
		Optional<User> optionalUser = userRepository.findByEmail(authenticationRequest.getEmail());
	    final String jwt = jwtUtil.generateToken(userDetails);
	    AuthenticationResponse response = new AuthenticationResponse();
	    if (optionalUser.isPresent()) {
	    	response.setJwt(jwt);
	    	response.setUserRole(optionalUser.get().getUserRole());
	    	response.setUserId(optionalUser.get().getId());
	    	
	    }
	
	    return response;
	}
	
}
