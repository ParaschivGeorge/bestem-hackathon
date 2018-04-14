package com.paraciuman.hackathon.repository;

import com.paraciuman.hackathon.model.Friend;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

@Component
public interface FriendRepository extends JpaRepository<Friend, Integer> {
}
