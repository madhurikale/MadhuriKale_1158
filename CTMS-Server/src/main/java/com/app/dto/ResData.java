package com.app.dto;

public class ResData {

	private String technology_name;
	private String trainer_name;
	public ResData() {
		super();
	}
	public ResData(String technology_name, String trainer_name) {
		super();
		this.technology_name = technology_name;
		this.trainer_name = trainer_name;
	}
	public String getTechnology_name() {
		return technology_name;
	}
	public void setTechnology_name(String technology_name) {
		this.technology_name = technology_name;
	}
	public String getTrainer_name() {
		return trainer_name;
	}
	public void setTrainer_name(String trainer_name) {
		this.trainer_name = trainer_name;
	}
	
}
