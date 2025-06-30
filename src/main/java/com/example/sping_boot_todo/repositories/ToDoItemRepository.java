package com.example.sping_boot_todo.repositories;

import com.example.sping_boot_todo.models.ToDoItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ToDoItemRepository extends JpaRepository<ToDoItem, Long> {

    List<ToDoItem> findByIsCompleteTrue();

    List<ToDoItem> findByIsCompleteFalse();
}
