package com.example.demo.boxes;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface BoxRepo extends JpaRepository<Box, Long> {
    @Query(nativeQuery = true, value = "SELECT * FROM box ORDER BY x DESC")
    List<Box> getSorted();
}
