package com.project.userdept.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.userdept.entities.User;
import com.project.userdept.repository.UserRepository;

@RestController
@RequestMapping(value =  "/users")
public class UserController {
    
    @Autowired
    private UserRepository repository;
    
    @GetMapping()
    public List<User> findAll(){
        List<User> result = repository.findAll();
        return result;
    }

  
    @GetMapping( value = "/{id}")
    public User findById(@PathVariable Long id) {        
        User user = repository.findById(id).get();
        return user;    
    }
   

    @PostMapping
    public User insert(@RequestBody User user){        
       User result = repository.save(user);      
        return result;      
    }

    @PutMapping(value = "/{id}")
    public User update(@PathVariable Long id, @RequestBody User user) {
        User existingUser = repository.findById(id).get();
        existingUser.setName(user.getName());
        existingUser.setEmail(user.getEmail());
        existingUser.setDepartment(user.getDepartment());

        User updatedUser = repository.save(existingUser);
        return updatedUser;
    }

    @DeleteMapping(value = "/{id}")
    public void deleteUser(@PathVariable long id) {
        User existingUser =  repository.findById(id).get();
        repository.delete(existingUser);
    }
}
