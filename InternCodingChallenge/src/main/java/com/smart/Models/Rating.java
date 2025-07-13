package com.smart.Models;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

@Entity
public class Rating {

	@Id
	private String id;
	private String ratingVal;
	
	@ManyToOne
	private  User user;
	
	@ManyToOne
	private Store store;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getRatingVal() {
		return ratingVal;
	}

	public void setRatingVal(String ratingVal) {
		this.ratingVal = ratingVal;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Store getStore() {
		return store;
	}

	public void setStore(Store store) {
		this.store = store;
	}

	

	@Override
	public String toString() {
		return "Rating [id=" + id + ", ratingVal=" + ratingVal + ", user=" + user + ", store=" + store + "]";
	}
	
	

	
	
}
