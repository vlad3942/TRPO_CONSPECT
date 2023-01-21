package com.example.demo.task;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class RecordService {

    private final RecordRepo repo;

    public List<Record> findRecords() {
        return repo.findAll().stream().filter(r -> repo.existsByValEndingWith(r.getId(), r.getVal()) > 0).collect(Collectors.toList());
    }
}
