package com.piggybank.demo.controller;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.piggybank.demo.model.PiggyTasks;
import com.piggybank.demo.payload.request.TaskRequest;
import com.piggybank.demo.payload.response.MessageResponse;
import com.piggybank.demo.repository.TaskRepository;

 

@RestController
@RequestMapping("/api/v1/task")
public class TaskController {
	
	@Autowired
	TaskRepository taskRepository;

	@PostMapping("/addtask")
	public ResponseEntity<?> addTask(@RequestBody TaskRequest taskRequest) {
		PiggyTasks piggyTask = new PiggyTasks(taskRequest.getTaskName(),taskRequest.getTaskDescription(),new Date());
		taskRepository.save(piggyTask);
		
		return ResponseEntity.ok(new MessageResponse("Task has added successfully!!"));
	}
	
}
