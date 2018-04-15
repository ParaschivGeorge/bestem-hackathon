package com.paraciuman.hackathon.repository;

import com.paraciuman.hackathon.model.Friend;
import com.paraciuman.hackathon.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface UserRepository extends JpaRepository<User,Long> {
    User findById(long id);
    User findByEmail(String email);
    //List<User> getAll();
}
