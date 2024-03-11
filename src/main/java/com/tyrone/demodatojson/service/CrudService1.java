package com.tyrone.demodatojson.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;


public interface CrudService1<T,S> {
    ResponseEntity<String> create(T classDto);

    ResponseEntity<String> create1(T classDto) throws JsonProcessingException;

    ResponseEntity<String> create2(T classDto);
    ResponseEntity<String> update(Long id, T classDto);

    ResponseEntity<String> delete(Long id);

    ResponseEntity<Page<S>> getAll(Pageable pageable);

    ResponseEntity<T> findById(Long id);
}
