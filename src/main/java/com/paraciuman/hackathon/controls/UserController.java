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
    public void addUser(@RequestBody final User user){
        if(userRepository.findByEmail(user.getEmail())!= null){
            return;
        }
        userRepository.save(user);
    }

    @RequestMapping(method = RequestMethod.PUT, value = "user/update")
    public void updateUser(@RequestBody final User user){
        //User user = userRepository.findByEmail(email);
        if(userRepository.findByEmail(user.getEmail()) != null){
            return;
        }
        userRepository.save(user);
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "user/delete/")
    public void deleteUser(@RequestBody final String email){
        User user = userRepository.findByEmail(email);
        if(user == null){
            return;
        }
        userRepository.deleteById(user.getId());
    }

    @RequestMapping(method = RequestMethod.GET, value = "user/get/{id}")
    public User getUser(@RequestBody final String email){
        User user = userRepository.findByEmail(email);
        if(user == null){
            return null;
        }
        return user;
    }
}
