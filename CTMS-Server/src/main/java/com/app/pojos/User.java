package com.app.pojos;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

/*
 User table(empid(pk),name,mobile,email,password,role,active)--->used as foreign key in Training table
 for getmapping it should return json like this
 {
 	"empid":1
    "name":"Madhuri",
    "mobile":"9637666390",
    "email":"madhurikale50@gmail.com",
    "password":"madhuri",
    "role":"admin",
    "active":1
}

 
 */
@Entity
@Table(name = "users",uniqueConstraints={@UniqueConstraint(columnNames={"name","email"})})
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "empid", insertable = false, updatable = false)
	private Integer empid;
	@Column(name = "name",length = 50,unique = true)
	private String user_name;
	@Column(length = 50)
	private String mobile;
	@Column(length = 50,unique = true)
	private String email;
	@Column(length = 50)
	@JsonProperty(access = Access.WRITE_ONLY)
	private String password;
	@Column(length = 50)
	private String role;
	@Column(columnDefinition = "integer default 1")
	private int active;
	
	

	//@OneToOne(mappedBy = "user",cascade = CascadeType.ALL)
	//private Training traning;
	public User() {
		
	}

	public User(Integer empid, String user_name, String mobile, String email, String password, String role) {
		super();
		this.empid = empid;
		this.user_name = user_name;
		this.mobile = mobile;
		this.email = email;
		this.password = password;
		this.role = role;
	}

	
	public Integer getEmpid() {
		return empid;
	}

	public void setEmpid(Integer empid) {
		this.empid = empid;
	}

	
	public String getUser_name() {
		return user_name;
	}

	public void setUser_name(String user_name) {
		this.user_name = user_name;
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

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public int getActive() {
		return active;
	}

	public void setActive(int active) {
		this.active = active;
	}

	//public Training getTraning() {
	//	return traning;
	//}

	//public void setTraning(Training traning) {
	//	this.traning = traning;
	//}

	@Override
	public String toString() {
		return "User [empid=" + empid + ", name=" + user_name + ", mobile=" + mobile + ", email=" + email + ", password="
				+ password + ", role=" + role + "]";
	}
	
	
	
}
