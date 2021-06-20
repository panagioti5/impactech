package com.exercise.repository;

import com.exercise.entities.Cdr;
import com.exercise.entities.CdrHistory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CdrHistoryRepository extends JpaRepository<CdrHistory,Long> {
    List<CdrHistory> findByCdr(Cdr cdr);
}
