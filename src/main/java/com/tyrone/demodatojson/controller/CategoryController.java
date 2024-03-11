package com.tyrone.demodatojson.controller;

import com.tyrone.demodatojson.dto.CategoryDto;
import com.tyrone.demodatojson.entity.Category;
import com.tyrone.demodatojson.service.CategoryServiceImpl;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/Category's")
public class CategoryController {

    @Autowired
    private CategoryServiceImpl categoryService;


    @PostMapping
    public ResponseEntity<?> createCategory(@RequestBody CategoryDto classDto) {
        return categoryService.create(classDto);
    }


    @GetMapping("/{id}")
    public ResponseEntity<Category> findById(@PathVariable Long id) {
        return categoryService.findById(id);
    }

    @GetMapping
    public ResponseEntity<Page<Category>> getAll(@PageableDefault(size = 10) Pageable pageable) {
        return categoryService.getAll(pageable);
    }


    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity<String> updateCellphone(@PathVariable Long id, @RequestBody @Valid CategoryDto categoryDto) {
        return categoryService.update(id, categoryDto);
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<String> deleteCellphone(@PathVariable Long id) {
        return categoryService.delete(id);
    }

}

