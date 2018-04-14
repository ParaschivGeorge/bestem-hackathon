package com.paraciuman.hackathon.controls;

import com.paraciuman.hackathon.model.User;
import com.paraciuman.hackathon.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserRepository userRepository;

    @RequestMapping(method = RequestMethod.POST, value = "user/add")
    public void addUser(@RequestBody final String email){
        User user = userRepository.findByEmail(email);
        if(user != null){
            return;
        }
        userRepository.save(user);
    }

    @RequestMapping(method = RequestMethod.PUT, value = "user/update")
    public void updateUser(@RequestBody final String email){
        User user = userRepository.findByEmail(email);
        if(user != null){
            return;
        }
        userRepository.save(user);
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "user/delete/{id}")
    public void deleteUser(@PathVariable("id") long id){
        if(userRepository.findById(id) == null){
            return;
        }
        userRepository.deleteById(id);
    }

    @RequestMapping(method = RequestMethod.GET, value = "user/get/{id}")
    public User getUser(@PathVariable("id") long id){
        User user = userRepository.findById(id);
        if(user == null){
            return null;
        }
        return user;
    }
}
