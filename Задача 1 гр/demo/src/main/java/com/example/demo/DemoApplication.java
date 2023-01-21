package com.example.demo;

import com.example.demo.task.Record;
import com.example.demo.task.RecordRepo;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@SpringBootApplication
@RequiredArgsConstructor
public class DemoApplication {

	private final RecordRepo repo;

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

	@PostConstruct
	private void pc() {
		List<Record> recs = new ArrayList<>();
		recs.add(new Record("asfdsfsd"));
		recs.add(new Record("fsd"));
		recs.add(new Record("sfsd"));
		recs.add(new Record("abcd"));
		recs.add(new Record("aabb"));
		recs.add(new Record("cd"));
		recs.add(new Record("cd"));
		recs.add(new Record("abba"));
		recs.add(new Record("asdasdddasfdsfsd"));
		repo.saveAll(recs);
	}
}
