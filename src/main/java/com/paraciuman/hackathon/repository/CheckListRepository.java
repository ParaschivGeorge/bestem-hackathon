package com.paraciuman.hackathon.repository;

import com.paraciuman.hackathon.model.CheckList;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

@Component
public interface CheckListRepository extends JpaRepository<CheckList, Long> {
}
