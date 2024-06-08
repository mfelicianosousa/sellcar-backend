package br.com.mfsdevsystem.sellcar.services.jwt;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import br.com.mfsdevsystem.sellcar.repository.UserRepository;

@Service
public class UserService implements IUserService {
	
	private final UserRepository userRepository;
	
	public UserService(UserRepository userRepository) {
		this.userRepository = userRepository;
	}



	@Override
	public UserDetailsService userDetailService() {

		return new UserDetailsService() {

			@Override
			public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
			
				return userRepository.findByEmail(email).orElseThrow(()-> new UsernameNotFoundException("User Not Found"));
				
			}
			
			
		};
	}

}
