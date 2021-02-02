package com.app.pojos;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
/*
 * Technology Table(id(pk),name,active)
 * N foreign key in Training table
 * 
 * for get mapping it should return json 
 * {
 * "id":1
    "name":"java",
    
    "active":1,
	
}
 */
@Entity
@Table(name = "technology",uniqueConstraints={@UniqueConstraint(columnNames={"name"})})
public class Technology {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "technology_id", insertable = false, updatable = false)
	private Integer technology_id;
	@Column(name="name",length = 50,unique = true)
	private String technology_name;
	
	@Column(columnDefinition = "integer default 1")
	private int active;
	
	
	
	

	public Technology() {
		super();
	}





	public Technology(Integer technology_id, String technology_name, int active) {
		super();
		this.technology_id = technology_id;
		this.technology_name = technology_name;
		this.active = active;
	}





	public Integer getTechnology_id() {
		return technology_id;
	}





	public void setTechnology_id(Integer technology_id) {
		this.technology_id = technology_id;
	}










	public String getTechnology_name() {
		return technology_name;
	}





	public void setTechnology_name(String technology_name) {
		this.technology_name = technology_name;
	}





	public int getActive() {
		return active;
	}





	public void setActive(int active) {
		this.active = active;
	}





	@Override
	public String toString() {
		return "Technology [technology_id=" + technology_id + ", name=" + technology_name + ", active=" + active + "]";
	}

	

	
}
