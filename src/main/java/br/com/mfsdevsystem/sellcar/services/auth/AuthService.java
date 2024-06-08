package br.com.mfsdevsystem.sellcar.services.auth;

import java.util.Optional;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import br.com.mfsdevsystem.sellcar.dto.SignupRequest;
import br.com.mfsdevsystem.sellcar.dto.UserDTO;
import br.com.mfsdevsystem.sellcar.entities.User;
import br.com.mfsdevsystem.sellcar.entities.UserRole;
import br.com.mfsdevsystem.sellcar.repository.UserRepository;
import jakarta.annotation.PostConstruct;


@Service
public class AuthService implements IAuthService {
	
	private final UserRepository userRepository;
	
	public AuthService(UserRepository userRepository) {
		this.userRepository = userRepository;
	}


    @PostConstruct
	public void createAnAdminAccount() {
		Optional<User> optAdmin = userRepository.findByUserRole(UserRole.ADMIN);
		if ( optAdmin.isEmpty()){
			User admin = new User();
			admin.setName( "Admin" );
			admin.setEmail( "admin@test.com" );
			admin.setUserRole( UserRole.ADMIN);
			admin.setPassword(new BCryptPasswordEncoder().encode("admin"));
			userRepository.save( admin );
			System.out.println("Admin account created succefully");
		} else {
			System.out.println("Admin account already exist!");
		}
	}


	@Override
	public Boolean hasUserWithEmail(String email) {
		
		return userRepository.findByEmail(email).isPresent();
		
	}


	@Override
	public UserDTO signup(SignupRequest signupRequest) {
		User user = new User();
		user.setEmail(signupRequest.getEmail());
		user.setName(signupRequest.getName());
		user.setPassword(new BCryptPasswordEncoder().encode(signupRequest.getPassword()));
		user.setUserRole(UserRole.CUSTOMER);
		
		return userRepository.save(user).getUserDTO();
	}

}
