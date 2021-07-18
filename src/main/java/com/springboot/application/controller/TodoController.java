package com.springboot.application.controller;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.springboot.application.model.Todo;
import com.springboot.application.service.TodoRepository;
import com.springboot.application.service.TodoService;

@Controller
public class TodoController {

	@Autowired
	private TodoService todoService;
	
	@Autowired
	private TodoRepository todoRepository;

	@RequestMapping(value = "/list-todos", method = RequestMethod.GET)
	public String showTodos(ModelMap model) {
		String name = getLoggedInUserName(model);
		model.put("todos", todoService.retriveTodo(name));
		return "list-todos";
	}

	@RequestMapping(value = "/add-todos", method = RequestMethod.GET)
	public String addTodos(ModelMap model) {
		model.addAttribute("todo", new Todo(0, getLoggedInUserName(model), "", new Date(), false));
		return "add-todo";
	}

	@RequestMapping(value = "/add-todos", method = RequestMethod.POST)
	public String addTodos(@Valid Todo todo, BindingResult result, ModelMap model) {
		if (result.hasErrors()) {
			return "add-todo";
		}
		String user = getLoggedInUserName(model);
		todoService.addTodo(user, todo.getDesc(), todo.getDate(), false);
		return "redirect:/list-todos";
	}

	// http://localhost:8080/delete-todo?id=2 --ID passed via Query parameters
	// This @RequestParam annotation takes the id mentioned above in url.

	/**
	 * @see https://www.youtube.com/watch?v=OROTCvW1lZE&ab_channel=AlfaeLearning
	 *      Difference between PathVariable Vs RequestParam
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/delete-todo", method = RequestMethod.GET)
	public String deleteTodo(@RequestParam int id) {
		if (id == 1) {
			throw new ArithmeticException("Intentional error occured");
		}
		todoService.deleteTodo(id);
		return "redirect:/list-todos";
	}

	@RequestMapping(value = "/update-todo", method = RequestMethod.GET)
	public String showUpdateTodoPage(@RequestParam int id, ModelMap model) {
		model.put("todo", todoService.retriveTodo(id));
		return "add-todo";
	}

	@RequestMapping(value = "/update-todo", method = RequestMethod.POST)
	public String updateTodo(@Valid Todo todo, BindingResult result, ModelMap model) {
		if (result.hasErrors()) {
			return "add-todo";
		}
		todoService.updateTodo(todo.getId(), todo);
		return "redirect:/list-todos";
	}

	@InitBinder
	protected void initBinder(WebDataBinder binder) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, false));
	}

	private String getLoggedInUserName(ModelMap model) {
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		if (principal instanceof UserDetails) {
			return ((UserDetails) principal).getUsername();
		}
		return principal.toString();
	}
}
