package com.app.pojos;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
@Table(name = "training_info")
public class Training {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "training_id", insertable = false, updatable = false)
	private Integer id;
	
	private int empid;
	private int technology_id;
	private int trainer_id;
	
	@Column(name = "start_date",length = 50)
	@JsonFormat(pattern = "yyyy-MM-dd")
	private LocalDate startDate;
	
	@Column(name = "end_date")
	@JsonFormat(pattern = "yyyy-MM-dd")
	private LocalDate endDate;
	

	
	private int no_of_trainee;
	private String project_mgr_status;
	private String traning_cor_status;
	
	@Column(length=1000)
	private String comment;
	public Training() {
		super();
	}
	
	

	public Training(Integer id, LocalDate startDate, LocalDate endDate, int no_of_trainee,
			String project_mgr_status, String traning_cor_status, String comment) {
		super();
		this.id = id;
		this.startDate = startDate;
		this.endDate = endDate;
		
		this.no_of_trainee = no_of_trainee;
		this.project_mgr_status = project_mgr_status;
		this.traning_cor_status = traning_cor_status;
		this.comment = comment;
	}



	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	

	public int getEmpid() {
		return empid;
	}



	public void setEmpid(int empid) {
		this.empid = empid;
	}



	public int getTechnology_id() {
		return technology_id;
	}



	public void setTechnology_id(int technology_id) {
		this.technology_id = technology_id;
	}



	public int getTrainer_id() {
		return trainer_id;
	}



	public void setTrainer_id(int trainer_id) {
		this.trainer_id = trainer_id;
	}



	public LocalDate getStartDate() {
		return startDate;
	}
	public void setStartDate(LocalDate startDate) {
		this.startDate = startDate;
	}
	public LocalDate getEndDate() {
		return endDate;
	}
	public void setEndDate(LocalDate endDate) {
		this.endDate = endDate;
	}

	
	public int getNo_of_trainee() {
		return no_of_trainee;
	}
	public void setNo_of_trainee(int no_of_trainee) {
		this.no_of_trainee = no_of_trainee;
	}
	
	public String getProject_mgr_status() {
		return project_mgr_status;
	}



	public void setProject_mgr_status(String project_mgr_status) {
		this.project_mgr_status = project_mgr_status;
	}



	public String getTraning_cor_status() {
		return traning_cor_status;
	}



	public void setTraning_cor_status(String traning_cor_status) {
		this.traning_cor_status = traning_cor_status;
	}



	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}
	@Override
	public String toString() {
		return "Training [id=" + id + ", startDate=" + startDate + ", endDate=" + endDate 
				+ ", no_of_trainee=" + no_of_trainee + ", comment=" + comment + "]";
	}
	
	

}
