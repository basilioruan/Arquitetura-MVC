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

import br.com.todolist.models.Task;
import br.com.todolist.repositories.TaskRepository;
import br.com.todolist.viewModels.MTask;

@RestController
@RequestMapping("todolist")
public class TaskController {
	
	@Autowired(required=false)
	private TaskRepository repository;
	
	@GetMapping("/index")
	public ResponseEntity<List<MTask>> index() {
		try {
			List<Task> toDoList = repository.findAll();
			
			List<MTask> response = new ArrayList<MTask>();
			
			for(Task task : toDoList) {
				response.add(new MTask(task.getId(), task.getName(), task.getDescription(), task.getDate()));
			}
			
			return ResponseEntity.ok(response);
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
	
}
