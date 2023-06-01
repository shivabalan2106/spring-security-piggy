package com.piggybank.demo.model;

import java.util.Date;

import org.springframework.data.annotation.CreatedDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;

@Entity
@Table(name="piggy_tasks")
public class PiggyTasks {
	
	@Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "task_generator")
	@SequenceGenerator(name = "task_generator", sequenceName = "piggy_task_seq", allocationSize = 1)
    @Column(name = "task_id")
	private Long taskId;
	
	@Column(name="task_name")
	private String taskName;
	
	@Column(name="task_description")
	private String taskDescription;
	
	@CreatedDate
	@Column(name="created_date")
	private Date createdDate;
	
	

	public PiggyTasks(String taskName, String taskDescription, Date createdDate) {
		this.taskName = taskName;
		this.taskDescription = taskDescription;
		this.createdDate = createdDate;
	}

	public Long getTaskId() {
		return taskId;
	}

	public void setTaskId(Long taskId) {
		this.taskId = taskId;
	}

	public String getTaskName() {
		return taskName;
	}

	public void setTaskName(String taskName) {
		this.taskName = taskName;
	}

	public String getTaskDescription() {
		return taskDescription;
	}

	public void setTaskDescription(String taskDescription) {
		this.taskDescription = taskDescription;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}
	

}
