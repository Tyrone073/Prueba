package com.tyrone.demodatojson.repository;

import com.tyrone.demodatojson.entity.Product;
import org.springframework.stereotype.Repository;


@Repository
public interface ProductRepository extends IGenericRepo<Product, Long> {
}