package com.aadesh.Todolistapi.controller;

import com.aadesh.Todolistapi.entity.Todo;
import com.aadesh.Todolistapi.repo.TodoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class TodoController {
    @Autowired
    private TodoRepository todoRepository;
    @GetMapping("/getTodo/{id}")
    public Todo getTodo(@PathVariable long id) throws Exception {

        Optional<Todo> byId = todoRepository.findById(id);
        if(byId.isEmpty()){
            throw new Exception("Todo does not exists");
        }
        return byId.get();
    }

    @GetMapping("/getAllTodos")
    public List<Todo> getAllTodos(){
        return todoRepository.findAll();
    }

    @PostMapping("/addTodo")
    public void addTodo(@RequestBody Todo todo){
        todoRepository.save(todo);
    }
    @DeleteMapping("/deleteTodo/{id}")
    public void deleteTodo(@PathVariable long id) throws Exception {
        Optional<Todo> byId = todoRepository.findById(id);
        if(byId.isEmpty()){
            throw new Exception("todo with given id does not exists");
        }
        todoRepository.deleteById(id);
    }
    @PutMapping("/update/{id}")
    public void updateTodo(@RequestBody Todo todo , @PathVariable long id) throws Exception {
        Optional<Todo> byId = todoRepository.findById(id);
        if(byId.isEmpty()){
            throw new Exception("todo with given id does not exists");
        }
        todoRepository.deleteById(id);
        todoRepository.save(todo);
    }
}
