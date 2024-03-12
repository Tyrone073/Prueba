package com.tyrone.demodatojson.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.tyrone.demodatojson.dto.ProductDtoForGson;
import com.tyrone.demodatojson.entity.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;


public interface CrudService1<T,S> {
    ResponseEntity<Product> create(ProductDtoForGson classDto); // especifique el dto para probar con el ,metodo de gson

    ResponseEntity<Product> create1(T classDto) throws JsonProcessingException;

    ResponseEntity<Product> create2(T classDto);
    ResponseEntity<String> update(Long id, T classDto);

    ResponseEntity<String> delete(Long id);

    ResponseEntity<Page<S>> getAll(Pageable pageable);

    ResponseEntity<T> findById(Long id) throws JsonProcessingException;
}
