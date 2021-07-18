package com.springboot.application.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.springframework.stereotype.Service;

import com.springboot.application.model.Todo;

@Service
public class TodoService {

	static List<Todo> list = new ArrayList<Todo>();
	static int index = 4;

	static {
		list.add(new Todo(1, "mukul", "learn SpringBoot", new Date(), false));
		list.add(new Todo(2, "mukul", "learn Hibernate", new Date(), false));
		list.add(new Todo(3, "mukul", "learn Angular", new Date(), false));
	}
	
	public void addTodo(String user, String desc, Date date, boolean isDone) {
		list.add(new Todo(index++, user, desc, date, isDone));
	}

	public List<Todo> retriveTodo(String user) {
		List<Todo> filteredList = new ArrayList<Todo>();
		for (Todo todo : list) {
			if (todo.getUser().equalsIgnoreCase(user)) {
				filteredList.add(todo);
			}
		}
		return filteredList;
	}
	
	public Todo retriveTodo(int id) {
		for (Todo todo : list) {
			if (todo.getId() == (id)) {
				return todo;
			}
		}
		return null;
	}

	public void deleteTodo(int id) {	
		Iterator<Todo> iterator = list.iterator();
		while(iterator.hasNext()) {
			Todo next = iterator.next();
			if(next.getId() == id) {
				iterator.remove();
			}
		}
	}
	
	public void updateTodo(int id, Todo todo) {
		Iterator<Todo> iterator = list.iterator();
		while(iterator.hasNext()) {
			Todo next = iterator.next();
			if(next.getId() == id) {
				next.setDesc(todo.getDesc());
				next.setDate(todo.getDate());
				next.setDone(todo.isDone());
			}
		}
	}
}
