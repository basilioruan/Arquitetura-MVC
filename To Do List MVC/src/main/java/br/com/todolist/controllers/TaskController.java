package br.com.todolist.controllers;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.todolist.dao.TaskDao;
import br.com.todolist.models.Task;
import br.com.todolist.repositories.TaskRepository;

@RestController
@RequestMapping("todolist")
public class TaskController {
	
	@Autowired(required=false)
	private TaskRepository repository;
	
	@GetMapping("/index")
	public ResponseEntity<List<TaskDao>> index() {
		try {
			List<Task> toDoList = repository.findAll();
			
			List<TaskDao> response = new ArrayList<TaskDao>();
			
			for(Task task : toDoList) {
				response.add(new TaskDao(task));
			}
			
			return ResponseEntity.ok(response);
		}
		catch(Exception ex) {
			throw ex;
		}
	}
	
	@GetMapping("/indexof/{id}")
	public ResponseEntity<TaskDao> indexOf(@PathVariable(value="id") long id) {
		try {
			Task task = repository.findById(id);
			
			TaskDao taskDao = new TaskDao(task);
			
			return ResponseEntity.ok(taskDao);
		}
		catch(Exception ex) {
			throw ex;
		}
	}
	
	@PostMapping("/insert")
	public ResponseEntity<String> insert(@RequestBody Task task) {
		try {
			repository.save(task);
			
			return new ResponseEntity<String>("Sucessfully saved", HttpStatus.CREATED);
		}
		catch(Exception ex) {
			throw ex;
		}
	}
	
	@PutMapping("/update")
	public ResponseEntity<String> update(@RequestBody Task task) {
		try {
			repository.saveAndFlush(task);
			
			return new ResponseEntity<String>("Sucessfully updated", HttpStatus.ACCEPTED);
		}
		catch(Exception ex) {
			throw ex;
		}
	}
	
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<String> delete(@PathVariable(value="id") long id) {
		try {
			repository.deleteById(id);
			
			return ResponseEntity.ok("Sucessfully deleted");
		}
		catch(Exception ex) {
			throw ex;
		}
	}
	
	@GetMapping("/complete/{id}/{value}")
	public ResponseEntity<String> complete(@PathVariable(value="id") long id, @PathVariable(value="value") boolean value){
		try {
			Task task = repository.findById(id);
			task.setComplete(value);
			
			repository.saveAndFlush(task);
			
			return ResponseEntity.ok("Sucessfully change of complete");
			
		}
		catch(Exception ex) {
			throw ex;
		}
	}
	
}
