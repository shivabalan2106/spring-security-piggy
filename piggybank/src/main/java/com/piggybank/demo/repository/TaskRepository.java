package com.piggybank.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.piggybank.demo.model.PiggyTasks;

@Repository
public interface TaskRepository extends JpaRepository<PiggyTasks, Long> {
	

}
