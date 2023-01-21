package com.example.demo.task;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequiredArgsConstructor
public class RecordController {
    private final RecordService service;

    @GetMapping("/get-records")
    public List<Record> records() {
        return service.findRecords();
    }
}
