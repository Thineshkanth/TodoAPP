package com.BackEnd.Service;

import com.BackEnd.DTO.CreateTodoRequestDTO;
import com.BackEnd.DTO.TodoDetailsDTO;
import com.BackEnd.Entity.Todolist;
import com.BackEnd.Repository.TodoRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class TodoService {

    @Autowired
    private TodoRepo todoRepo;



    public CreateTodoRequestDTO createTodo(CreateTodoRequestDTO createTodoRequestDTO) {

        Todolist todolist=new Todolist();


        todolist.setTitle(createTodoRequestDTO.getTitle());
        todolist.setDescription(createTodoRequestDTO.getDescription());
        todolist.setDuedate(createTodoRequestDTO.getDueDate());
        todolist.setPriority(createTodoRequestDTO.getPriority());
        todolist.setUserid(createTodoRequestDTO.getUserId());
        todoRepo.save(todolist);
        return new CreateTodoRequestDTO(todolist.getTitle(),todolist.getDescription(),todolist.getDuedate(),todolist.getPriority(),todolist.getCompleted(),todolist.getUserid());

    }

    public Page<Todolist> getTodoListByuserid(int userid, int page, int size) {
        Pageable pageable = PageRequest.of(page, size );
        return todoRepo.findByuserid(userid,pageable);
    }



    public TodoDetailsDTO todoUpdate(int todoid, TodoDetailsDTO todoDetails){

        Optional<Todolist> existingtodoitem = todoRepo.findById(todoid);
        if (existingtodoitem.isPresent()) {
            Todolist updatetodo = existingtodoitem.get();
            updatetodo.setTitle(todoDetails.getTitle());
            updatetodo.setDescription(todoDetails.getDescription());
            updatetodo.setDuedate(todoDetails.getDueDate());
            updatetodo.setPriority(todoDetails.getPriority());
            updatetodo.setCompleted(todoDetails.getCompleted());
            todoRepo.save(updatetodo);
            return new TodoDetailsDTO(todoDetails.getTodoId(), todoDetails.getTitle(),todoDetails.getDescription(),todoDetails.getDueDate(),todoDetails.getPriority(),todoDetails.getCompleted(),todoDetails.getUserId());
        } else {
            throw new RuntimeException("User not found with id: " + todoid);
        }
    }

    public void deletetodo(int todoid) {
        if (todoRepo.existsById( todoid)) {
            todoRepo.deleteById( todoid);
        } else {
            throw new RuntimeException("User not found with id: " + todoid);
        }
    }



}
