package com.app.pojos;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;


/*
 * Trainer Table(id,name,name,mobile,email,technology_id(foreign key->technology table))
 * used as foerign key in Training table
 * 
 * n for get mapping it should return json like
 * {
 * "id":1
    "name":"madhuri",
    "mobile":"9637666390",
    "email":"madhurikale50@gmail.com",
   "technology":{
       "id":1
   },
    "active":1
}
 */
@Entity
@Table(name = "trainer" ,uniqueConstraints={@UniqueConstraint(columnNames={"name","email"})})
public class Trainer {
	
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "trainer_id", insertable = false, updatable = false)
	private Integer trainer_id;
	@Column(name = "name",length = 50,unique = true)
	private String trainer_name;
	@Column(length = 50)
	private String mobile;
	@Column(length = 50,unique = true)
	private String email;

	@Column(columnDefinition = "integer default 1")
	private int active;
	//@JsonManagedReference
	int technology_id;
	public Trainer() {
		super();
	}

	public Trainer(Integer trainer_id, String trainer_name, String mobile, String email) {
		super();
		this.trainer_id = trainer_id;
		this.trainer_name = trainer_name;
		this.mobile = mobile;
		this.email = email;
	}

	


	public Integer getTrainer_id() {
		return trainer_id;
	}

	public void setTrainer_id(Integer trainer_id) {
		this.trainer_id = trainer_id;
	}

	

	public String getTrainer_name() {
		return trainer_name;
	}

	public void setTrainer_name(String trainer_name) {
		this.trainer_name = trainer_name;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	
	public int getTechnology_id() {
		return technology_id;
	}

	public void setTechnology_id(int technology_id) {
		this.technology_id = technology_id;
	}

	public int getActive() {
		return active;
	}

	public void setActive(int active) {
		this.active = active;
	}


	@Override
	public String toString() {
		return "Trainer [trainer_id=" + trainer_id + ", name=" + trainer_name + ", mobile=" + mobile + ", email=" + email + "]";
	}
	
	

}
