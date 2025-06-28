package com.example.sping_boot_todo.services;

import com.example.sping_boot_todo.models.ToDoItem;
import com.example.sping_boot_todo.repositories.ToDoItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.Optional;

@Service
public class ToDoItemService {

    @Autowired
    private ToDoItemRepository toDoItemRepository;

    public Iterable<ToDoItem> getAll() {
        return toDoItemRepository.findAll();
    }

    public Optional<ToDoItem> getById(Long id) {
        return  toDoItemRepository.findById(id);
    }

    public ToDoItem save(ToDoItem toDoItem) {
        if(toDoItem.getId()==null) {
            toDoItem.setCreatedAt(Instant.now());
        }
        toDoItem.setUpdatedAt(Instant.now());
        return toDoItemRepository.save(toDoItem);
    }

    public void delete(ToDoItem toDoItem) {
        toDoItemRepository.delete(toDoItem);
    }
}