package com.app.dto;

import com.app.pojos.Technology;
import com.app.pojos.Trainer;
import com.app.pojos.Training;
import com.app.pojos.User;

public class TrainingDetails {
	
	private Training training;
	private Technology technology;
	private Trainer trainer;
	private User user;
	
	public TrainingDetails() {
		super();
	}
	
	public TrainingDetails(Training training, Technology technology, Trainer trainer, User user) {
		super();
		this.training = training;
		this.technology = technology;
		this.trainer = trainer;
		this.user = user;
	}

	public Technology getTechnology() {
		return technology;
	}
	public void setTechnology(Technology technology) {
		this.technology = technology;
	}
	public Trainer getTrainer() {
		return trainer;
	}
	public void setTrainer(Trainer trainer) {
		this.trainer = trainer;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public Training getTraining() {
		return training;
	}
	public void setTraining(Training training) {
		this.training = training;
	}
	
	

}
