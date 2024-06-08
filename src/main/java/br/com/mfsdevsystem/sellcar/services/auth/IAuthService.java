package br.com.mfsdevsystem.sellcar.services.auth;

import br.com.mfsdevsystem.sellcar.dto.SignupRequest;
import br.com.mfsdevsystem.sellcar.dto.UserDTO;

public interface IAuthService {
	
	UserDTO signup(SignupRequest signupRequest);
	Boolean hasUserWithEmail(String email);

}
