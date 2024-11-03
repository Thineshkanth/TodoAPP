package com.BackEnd.DTO;


import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter

public class TodoDetailsDTO {
    private int todoId;
    private String title;
    private String description;
    private Date dueDate;
    private String priority;
    private Boolean completed;
    private int userId;

    public TodoDetailsDTO(int todoId, String title, String description, Date dueDate,String priority, Boolean completed ,int userId){

        this.todoId=todoId;
        this.title = title;
        this.description=description;
        this.dueDate = dueDate;
        this.priority=priority;
        this.completed = completed;
        this.userId=userId;
    }
}
