package com.oumoi.springdatatable.controller;


import com.oumoi.springdatatable.entity.Student;
import com.oumoi.springdatatable.repository.StudentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class BaseController {

    private final StudentRepository studentRepository;


    @GetMapping("/")
    public String getBase(){
        return "base";
    }


    @GetMapping("/students/{page}/{size}")
    @ResponseBody
    public List<Student> studentList(@PathVariable("page") Integer page, @PathVariable("size") Integer size){
        Pageable pageable = PageRequest.of(page,size);

        return studentRepository.findAll(pageable).stream().toList();
    }

}
