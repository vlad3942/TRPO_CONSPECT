package com.example.demo.task;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "my_record")
@Data
public class Record {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String val;

    public Record() {}

    public Record(String value) {
        this.val = value;
    }
}
