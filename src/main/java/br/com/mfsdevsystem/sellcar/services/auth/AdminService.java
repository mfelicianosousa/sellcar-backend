package br.com.mfsdevsystem.sellcar.services.auth;

import java.util.Optional;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import br.com.mfsdevsystem.sellcar.entities.User;
import br.com.mfsdevsystem.sellcar.entities.UserRole;
import br.com.mfsdevsystem.sellcar.repository.UserRepository;
import jakarta.annotation.PostConstruct;


@Service
public class AdminService implements AuthService {
	
	private final UserRepository userRepository;
	
	public AdminService(UserRepository userRepository) {
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

}
