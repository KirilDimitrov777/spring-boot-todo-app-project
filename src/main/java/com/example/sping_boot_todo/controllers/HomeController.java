package com.example.sping_boot_todo.controllers;

import com.example.sping_boot_todo.models.ToDoItem;
import com.example.sping_boot_todo.services.ToDoItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class HomeController {

    @Autowired
    private ToDoItemService toDoItemService;

    @GetMapping("/")
    public ModelAndView index(@RequestParam(required = false, defaultValue = "all") String filter) {
        ModelAndView modelAndView = new ModelAndView("index");

        Iterable<ToDoItem> filteredItems;
        switch (filter.toLowerCase()) {
            case "completed":
                filteredItems = toDoItemService.getCompleted();
                break;
            case "pending":
                filteredItems = toDoItemService.getPending();
                break;
            default:
                filteredItems = toDoItemService.getAll();
        }

        modelAndView.addObject("toDoItems", filteredItems);
        modelAndView.addObject("filter", filter);
        return modelAndView;
    }
}
