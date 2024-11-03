package com.BackEnd.Controller;

import com.BackEnd.DTO.CreateTodoRequestDTO;
import com.BackEnd.DTO.TodoDetailsDTO;
import com.BackEnd.Entity.Todolist;
import com.BackEnd.Service.TodoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;


@RestController
@RequestMapping("/api")

public class TodoItemsController {

    @Autowired
    private TodoService todoService;



    @PostMapping("/createTodo")
    public ResponseEntity<CreateTodoRequestDTO> createTodo(@RequestBody CreateTodoRequestDTO createTodoRequestDTO) {
        CreateTodoRequestDTO createTodoRequestDTOupdate = todoService.createTodo(createTodoRequestDTO);
        if (createTodoRequestDTOupdate != null) {
            return ResponseEntity.status(HttpStatus.CREATED).body(createTodoRequestDTOupdate);
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
    }

    @GetMapping("/Todolist/{userid}")
    public ResponseEntity<Page<Todolist>> getTodoListByuserid(@PathVariable int userid, @RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "3") int size) {
        Page<Todolist> Todolist = todoService.getTodoListByuserid(userid,page, size);
        return ResponseEntity.status(HttpStatus.OK).body(Todolist);
    }








    @PutMapping("/Todolist/{todoid}")
    public ResponseEntity<TodoDetailsDTO> todoUpdate(@PathVariable int todoid , @RequestBody TodoDetailsDTO todoDetails) {

        TodoDetailsDTO todoDetailsDTO = todoService.todoUpdate(todoid,todoDetails);
        if (todoDetailsDTO != null) {
            return ResponseEntity.status(HttpStatus.ACCEPTED).body(todoDetailsDTO);
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(todoDetailsDTO);
        }
    }


    @DeleteMapping("/Todolist/{todoid}")
    public ResponseEntity<Void> deletetodo(@PathVariable int todoid) {
        try {
            todoService.deletetodo(todoid);
            return ResponseEntity.status(HttpStatus.OK).build();
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

}
