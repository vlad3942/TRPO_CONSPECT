package com.example.demo.boxes;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table
@Data
public class Box {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Integer x;
    private Integer y;

    public Box() {}

    public Box(int x, int y) {
        this.x = x;
        this.y = y;
    }
}
