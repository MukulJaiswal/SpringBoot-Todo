package com.springboot.application.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.Size;

@Entity
public class Todo {
	
	@Id
	@GeneratedValue
	private int id;
	private String user;
	
    @Size(min=10, message="Enter at least 10 Characters...")
	private String desc;
	private Date date;
	private boolean done;
	
	public Todo() {
		super();
	}
	
	public Todo(int id, String user, String desc, Date date, boolean isDone) {
		super();
		this.id = id;
		this.user = user;
		this.desc = desc;
		this.date = date;
		this.done = isDone;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public boolean isDone() {
		return done;
	}

	public void setDone(boolean isDone) {
		this.done = isDone;
	}

	@Override
	public String toString() {
		return "Todo [id=" + id + ", user=" + user + ", desc=" + desc + ", date=" + date + ", isDone=" + done + "]";
	}
}
