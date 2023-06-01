package com.piggybank.demo.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.piggybank.demo.model.PiggyUser;

@Repository
public interface UserRepository extends JpaRepository<PiggyUser, Long>{
	
	Optional<PiggyUser> findByUserName(String username);

	Boolean existsByUserName(String username);
	  
	PiggyUser findBymobileNumber(String number);
	
	// Boolean existsByEmail(String email);
}
