package com.example.studentqueue.service.mapping;

public interface RequestDtoMapper<D, T> {
    T mapToModel(D dto);
}
