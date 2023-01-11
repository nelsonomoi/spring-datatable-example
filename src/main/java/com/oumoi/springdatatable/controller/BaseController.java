package com.oumoi.springdatatable.controller;


import com.oumoi.springdatatable.entity.Student;
import com.oumoi.springdatatable.repository.StudentRepository;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.datatables.mapping.DataTablesInput;
import org.springframework.data.jpa.datatables.mapping.DataTablesOutput;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

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


    @RequestMapping(value = "/studentsList", method = RequestMethod.GET)
    @ResponseBody
    public DataTablesOutput<Student> list(@Valid DataTablesInput input) {
        return studentRepository.findAll(input);
    }

    @PostMapping(value = "/studentsList")
    @ResponseBody
    public DataTablesOutput<Student> studentList1(@RequestBody DataTablesInput input){
        System.out.println(input);
        return studentRepository.findAll(input);
    }

}
