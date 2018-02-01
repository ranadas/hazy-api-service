package com.rdas.controller;

import com.rdas.model.Employee;
import com.rdas.service.EmployeeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.*;

@RestController
public class Api {
    private static Logger logger = LoggerFactory.getLogger(Api.class);

    @Autowired
    private EmployeeService employeeService;

    @GetMapping(path = "/health", produces = "application/json")
    public ResponseEntity<?> checkHealth() {
        LocalDateTime timePoint = LocalDateTime.now();
        return new ResponseEntity<Object>(String.format("Pinging on %s/%s/%s. Health OK\n", timePoint.getYear(), timePoint.getMonth(), timePoint.getDayOfMonth()), HttpStatus.OK);
    }

    //curl -X GET localhost:8089/hzt/employees/person/12
    @GetMapping("/employees/person/{id}")
    public Employee findByPersonId(@PathVariable("id") Integer personId) {
        logger.info(String.format("findByPersonId(%d)", personId));
        return employeeService.findByPersonId(personId);
    }

    @GetMapping("/employees/company/{company}")
    public List<Employee> findByCompany(@PathVariable("company") String company) {
        logger.info(String.format("findByCompany(%s)", company));
        return employeeService.findByCompany(company);
    }

    @GetMapping("/employees/{id}")
    public Employee findById(@PathVariable("id") Integer id) {
        logger.info(String.format("findById(%d)", id));
        return employeeService.findById(id);
    }

    //curl -X POST -H "Content-Type: application/json" localhost:8089/hzt/employees/ -d '{"id":2, "personId":"12", "company":"google"}'
    @PostMapping("/employees")
    public Employee add(@RequestBody Employee emp) {
        logger.info(String.format("add(%s)", emp));
        return employeeService.add(emp);
    }
}
