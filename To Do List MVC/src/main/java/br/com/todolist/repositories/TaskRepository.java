package br.com.todolist.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.todolist.models.Task;

public interface TaskRepository extends JpaRepository<Task, Long>{
	
	public Task findById(long id);
	
}
