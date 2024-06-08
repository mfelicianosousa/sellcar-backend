package br.com.mfsdevsystem.sellcar.services.jwt;

import org.springframework.security.core.userdetails.UserDetailsService;

public interface IUserService {

	UserDetailsService userDetailService();
	
}
