package com.tyrone.demodatojson.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.tyrone.demodatojson.dto.ProductDto;
import com.tyrone.demodatojson.dto.ProductDtoForGson;
import com.tyrone.demodatojson.entity.Product;
import com.tyrone.demodatojson.service.ProductServiceImpl;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/products")
public class ProductController {

    @Autowired
    private ProductServiceImpl productServiceImpl;


    @PostMapping("Gson")
    public ResponseEntity<?> crearProductoGson(@RequestBody ProductDtoForGson classDto) {
        return productServiceImpl.create(classDto);
    }

    @PostMapping("Jackon")
    public ResponseEntity<?> crearProductoJackson(@RequestBody ProductDto classDto) throws JsonProcessingException {
        return productServiceImpl.create1(classDto);
    }
    @PostMapping("Na")
    public ResponseEntity<?> crearProductoNa(@RequestBody ProductDto classDto) {
        return productServiceImpl.create2(classDto);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductDto> findById(@PathVariable Long id) throws JsonProcessingException {
        return productServiceImpl.findById(id);
    }
    @GetMapping("/Gson{id}")
    public ResponseEntity<ProductDtoForGson> findByIdforGson(@PathVariable Long id) {
        return productServiceImpl.findByIdforGson(id);
    }

    @GetMapping
    public ResponseEntity<Page<Product>> getAll(@PageableDefault(size = 10) Pageable pageable) {
        return productServiceImpl.getAll(pageable);
    }


    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity<String> updateCellphone(@PathVariable Long id, @RequestBody @Valid ProductDto productDto) {
        return productServiceImpl.update(id, productDto);
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<String> deleteCellphone(@PathVariable Long id) {
        return productServiceImpl.delete(id);
    }

}
