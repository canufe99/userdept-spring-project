package com.project.userdept.controllers;

import java.util.List;
import java.util.Optional;

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
import com.project.userdept.exceptions.UserNotFoundException;
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
    public User findById(@PathVariable Long id) throws UserNotFoundException {        
        Optional<User> userOptional = repository.findById(id);
    if (userOptional.isPresent()) {
        return userOptional.get();
    } else {
        throw new UserNotFoundException("User with ID " + id + " not found");
    }
}

    @PostMapping
    public User insert(@RequestBody User user){        
       User result = repository.save(user);      
        return result;      
    }

    @PutMapping(value = "/{id}")
    public User update(@PathVariable Long id, @RequestBody User user) throws UserNotFoundException {
        User existingUser = repository.findById(id).orElse(null);

        if(existingUser != null) {
            existingUser.setName(user.getName());
            existingUser.setName(user.getEmail());
            
            User updatedUser = repository.save(existingUser);
            return updatedUser;
        } else {
            return null;
        }     
    }

    @DeleteMapping(value = "/{id}")
    public void deleteUser(@PathVariable long id) throws UserNotFoundException{
        User existingUser = repository.findById(id).orElse(null);
        if(existingUser != null) {
            repository.delete(existingUser);
        } else {
            throw new UserNotFoundException("User with ID " + id + " not found");
        }
    }
}
