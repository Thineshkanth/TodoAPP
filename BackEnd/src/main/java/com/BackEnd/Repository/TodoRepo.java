package com.BackEnd.Repository;

import com.BackEnd.Entity.Todolist;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
    public interface TodoRepo extends JpaRepository<Todolist,Integer> {
    Page<Todolist> findByuserid(int userId, Pageable pageable);
    }
