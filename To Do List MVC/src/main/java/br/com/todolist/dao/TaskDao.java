package br.com.todolist.dao;

import java.util.Date;

import br.com.todolist.models.Task;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class TaskDao {
	
	public TaskDao(Task task) {
		this.id = task.getId();
		this.name = task.getName();
		this.description = task.getDescription();
		this.date = task.getDate();
		this.complete = task.isComplete();
	}
	
	private long id;
	
	private String name;
	
	private String description;
	
	private Date date;
	
	private boolean complete;

}
