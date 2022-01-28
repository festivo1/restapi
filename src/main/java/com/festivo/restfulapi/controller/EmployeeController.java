/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.festivo.restfulapi.controller;

import com.festivo.restfulapi.Exception.ResourceNotFoundException;
import com.festivo.restfulapi.model.Employee;
import com.festivo.restfulapi.repository.EmployeeRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class EmployeeController {
   
    @Autowired
    EmployeeRepository empRepo;
    
    @GetMapping("/employees")
    public ResponseEntity<List<Employee>> getAllEmployees(){
        List stdList = empRepo.findAll();
        return new ResponseEntity<>(stdList, HttpStatus.OK);
    }
    
    @PostMapping("/employees") // it always creates new one
    public ResponseEntity<Employee> saveEmployee(@RequestBody Employee emp){
        emp = empRepo.save(emp);
        return new ResponseEntity<>(emp, HttpStatus.CREATED);
    }
    
//     used to update the emp else create new one
//     In this example we just want to update the employee otherwise throw an error
    
    @PutMapping("/employees/{id}") 
    public ResponseEntity<Employee> saveEmployee(@RequestBody Employee emp,
            @PathVariable Long id) throws ResourceNotFoundException{
        Employee employee = empRepo.findById(id)
                .orElseThrow(()->
                        {
            return new ResourceNotFoundException("Emp id not found");
        });
        employee.setFirstName(emp.getFirstName());
        employee.setLastName(emp.getLastName());
        Employee updatedEmployee = empRepo.save(employee);
        return ResponseEntity.ok(updatedEmployee);
    }
    
    @DeleteMapping("/employees/{id}") 
    public ResponseEntity<Long> deleteEmployee(
            @PathVariable Long id)throws ResourceNotFoundException{
         empRepo.findById(id)
                .orElseThrow(()->{
             return new ResourceNotFoundException("Emp id not found");
         });
         empRepo.deleteById(id);
        return new ResponseEntity<>(id,HttpStatus.OK);
    }
   
}
