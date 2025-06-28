package com.example.sping_boot_todo.controllers;

import com.example.sping_boot_todo.models.ToDoItem;
import com.example.sping_boot_todo.services.ToDoItemService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class ToDoFormController {
    @Autowired
    private ToDoItemService toDoItemService;

    @GetMapping("/create-toDo")
    public String showCreateForm(Model model) {
        model.addAttribute("toDoItem", new ToDoItem());
        return "new-toDo-item";
    }

    @PostMapping("/toDo")
    public String createToDoItem(@Valid ToDoItem toDoItem, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "new-toDo-item";
        }
        toDoItemService.save(toDoItem);
        return "redirect:/";
    }

    @GetMapping("/delete/{id}")
    public String deleteToDoItem(@PathVariable("id") Long id, Model model) {
        ToDoItem toDoItem = toDoItemService.getById(id).orElseThrow(() -> new IllegalArgumentException("ToDoItem id: " + id + " Not Found!"));

        toDoItemService.delete(toDoItem);
        return "redirect:/";
    }

    @GetMapping("/edit/{id}")
    public String showUpdateForm(@PathVariable("id") Long id, Model model) {
        ToDoItem toDoItem = toDoItemService
                .getById(id)
                .orElseThrow(() -> new IllegalArgumentException("ToDoItem id: " + id + " not found"));

        model.addAttribute("toDo", toDoItem);
        return "edit-toDo-item";
    }

    @PostMapping("/toDo/{id}")
    public String updateTodoItem(@PathVariable("id") Long id, @Valid ToDoItem toDoItem, BindingResult result, Model model) {

        ToDoItem item = toDoItemService
                .getById(id)
                .orElseThrow(() -> new IllegalArgumentException("TodoItem id: " + id + " not found"));

        item.setIsComplete(toDoItem.getIsComplete());
        item.setDescription(toDoItem.getDescription());

        toDoItemService.save(item);

        return "redirect:/";
    }
}