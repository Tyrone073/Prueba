package com.tyrone.demodatojson.repository;

import com.tyrone.demodatojson.entity.Category;
import org.springframework.stereotype.Repository;


@Repository
public interface CategoryRepository extends IGenericRepo<Category, Long> {
}