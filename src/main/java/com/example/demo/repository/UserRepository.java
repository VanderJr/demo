package com.example.demo.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.demo.config.security.UserAuth;

public interface UserRepository extends JpaRepository<UserAuth,Long>{
	
	@Query(value="SELECT NEW com.example.demo.config.security.UserAuth(e.id, e.email, e.password, e.name, e.forceResetPassword, e.enabled, e.role.name) "
			+ "FROM Employee e "
			+ "WHERE email = ?1 ")
	Optional<UserAuth> findByEmail(String email);
	
	@Query(value="SELECT NEW com.example.demo.config.security.UserAuth(e.id, e.email, e.password, e.name, e.forceResetPassword, e.enabled, e.role.name) "
			+ "FROM Employee e "
			+ "WHERE id = ?1 ")
	Optional<UserAuth> findById(Long id);
}
