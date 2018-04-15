package com.paraciuman.hackathon.controls;

import com.paraciuman.hackathon.model.User;
import com.paraciuman.hackathon.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class UserController {
    @Autowired
    private UserRepository userRepository;

    @GetMapping(path = "/user")
    public User getUserTest() {
        User user = new User();
        user.setId(123);
        user.setEmail("vasile@email.com");
        user.setAgendas(null);
        user.setFriends(null);
        user.setName("Vasile");
        user.setPictureUrl("https://www.blogintandem.ro/wp-content/uploads/ngg_featured/tourist-sitting-trolltunga-norway.ngsversion.1485378003501.adapt_.1900.1-670x400.jpg");

        return user;
    }

    @RequestMapping(method = RequestMethod.POST, value = "/user/add")
    public void addUser(@RequestBody final User user){
        if(userRepository.findByEmail(user.getEmail())!= null){
            return;
        }
        userRepository.save(user);
    }

    @RequestMapping(method = RequestMethod.PUT, value = "/user/update")
    public void updateUser(@RequestBody final User user){
        User user1 = userRepository.findByEmail(user.getEmail());

        if(user1 != null){
            user.setId(user1.getId());
            userRepository.save(user);
        }

    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/user/delete")
    public void deleteUser(@RequestBody final String email){
        User user = userRepository.findByEmail(email);
        if(user == null){
            return;
        }
        userRepository.deleteById(user.getId());
    }

    @RequestMapping(method = RequestMethod.GET, value = "/user/get")
    public List<User> getUser(){
        List<User> users = new ArrayList<>();
        users = userRepository.getAll();
       // users.removeAll(userRepository.get)
        return users;
    }
}
