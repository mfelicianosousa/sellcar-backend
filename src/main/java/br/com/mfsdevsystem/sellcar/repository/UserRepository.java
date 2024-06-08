package br.com.mfsdevsystem.sellcar.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.mfsdevsystem.sellcar.entities.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long>{

}
