package com.example.demo.task;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface RecordRepo extends JpaRepository<Record, Long> {
    @Query(nativeQuery = true, value = "SELECT count(id) FROM my_record WHERE val LIKE %:value AND id != :id")
    int existsByValEndingWith(@Param("id") Long id, @Param("value") String value);
}
