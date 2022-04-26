package com.example.studentqueue.service.mapping;

public interface ResponseDtoMapper<D, T> {
    D mapToDto(T t);
}
