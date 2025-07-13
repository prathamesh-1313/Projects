package com.smart.Models;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Entity
public class User {

	@Id
	public String uid;
	
	@NotBlank(message = "name field cannot be blank!!")
	@Size(min=4,max = 60,message = "Minimum 20 characters are allowed!!")
	public String uname;
	
	@Email(regexp = "^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$")
	public String uemail;
	@NotBlank(message = "Role field cannot be blank!!")
	public String urole;
	@NotBlank(message = "Password field cannot be blank!!")
	public String upassword;
	@Column(length = 500)
	public String uaddress;
	
	@OneToMany(mappedBy = "owner",cascade = CascadeType.ALL)
	@JsonIgnore
	private List<Store> store = new ArrayList<>();
	
	@OneToMany(mappedBy = "user",cascade = CascadeType.ALL)
	@JsonIgnore
	private List<Rating> ratings=new ArrayList<>();

	public String getUid() {
		return uid;
	}

	public void setUid(String uid) {
		this.uid = uid;
	}

	public String getUname() {
		return uname;
	}

	public void setUname(String uname) {
		this.uname = uname;
	}

	public String getUemail() {
		return uemail;
	}

	public void setUemail(String uemail) {
		this.uemail = uemail;
	}

	public String getUrole() {
		return urole;
	}

	public void setUrole(String urole) {
		this.urole = urole;
	}

	public String getUpassword() {
		return upassword;
	}

	public void setUpassword(String upassword) {
		this.upassword = upassword;
	}

	public String getUaddress() {
		return uaddress;
	}

	public void setUaddress(String uaddress) {
		this.uaddress = uaddress;
	}

	public List<Store> getStore() {
		return store;
	}

	public void setStore(List<Store> store) {
		this.store = store;
	}

	public List<Rating> getRatings() {
		return ratings;
	}

	public void setRatings(List<Rating> ratings) {
		this.ratings = ratings;
	}

	

	
	


	
	
	
	
	
}
