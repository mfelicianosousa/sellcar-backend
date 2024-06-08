package br.com.mfsdevsystem.sellcar.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.mfsdevsystem.sellcar.entities.User;
import br.com.mfsdevsystem.sellcar.entities.UserRole;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
	
	Optional<User> findByEmail(String email);

	Optional<User> findByUserRole(UserRole admin);

}
