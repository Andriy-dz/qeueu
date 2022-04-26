package com.example.studentqueue.dto;

import lombok.Data;

@Data
public class StudentResponse {
    private Long id;
    private String name;
    private Integer numberInQueue;
}
