package com.smart.helper;

public class Message {

	private String message;
	private String mtype;
	
	
	
	public Message(String message, String mtype) {
		super();
		this.message = message;
		this.mtype = mtype;
	}



	public String getMessage() {
		return message;
	}



	public void setMessage(String message) {
		this.message = message;
	}



	public String getMtype() {
		return mtype;
	}



	public void setMtype(String mtype) {
		this.mtype = mtype;
	}
	
	
	
	
}
