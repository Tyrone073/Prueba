package com.tyrone.demodatojson.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.tyrone.demodatojson.dto.ProductDto;
import com.tyrone.demodatojson.entity.Product;
import com.tyrone.demodatojson.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class ProductServiceImpl implements CrudService1<ProductDto, Product> {

    @Autowired
    private ProductRepository productRepository;

    @Override
    public ResponseEntity<String> create(ProductDto productDto) {
        Gson gson = new Gson();
        Product fromJson = gson.fromJson(productDto.features(), Product.class);
        // Serializa el objeto Product a JSON
        String featuresJson = gson.toJson(fromJson);
        var newProduct = Product.builder()
                .SKU(productDto.SKU())
                .description(productDto.description())
                .price(productDto.price())
                .stock(productDto.stock())
                .features(featuresJson)
                .build();
        productRepository.save(newProduct);
        return new ResponseEntity<>("new Product created",HttpStatus.CREATED);
    }


    @Override
    public ResponseEntity<String> create1(ProductDto productDto) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        String featuresJson = mapper.writeValueAsString(productDto.features());
        Product newProduct = Product.builder()
                .SKU(productDto.SKU())
                .description(productDto.description())
                .price(productDto.price())
                .stock(productDto.stock())
                .features(featuresJson)
                .build();
        productRepository.save(newProduct);


        return new ResponseEntity<>("new Product created",HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<String> create2(ProductDto productDto) {
        var newProduct = Product.builder()
                .SKU(productDto.SKU())
                .description(productDto.description())
                .price(productDto.price())
                .stock(productDto.stock())
                .features(productDto.features())
                .build();
        productRepository.save(newProduct);
        return new ResponseEntity<>("new Product created",HttpStatus.CREATED);
    }




    @Override
//    @Transactional(rollbackFor = Exception.class)
    public ResponseEntity<String> update(Long id, ProductDto productDto) {
        if (id > 0 && productRepository.existsById(id)) {

            var productUpdate = Product.builder()
            .id(id)
            .SKU(productDto.SKU())
            .description(productDto.description())
            .price(productDto.price())
            .stock(productDto.stock())
            .build();
            productRepository.save(productUpdate);

            return new ResponseEntity<>("Product Updated",HttpStatus.OK);
        }
        return new ResponseEntity<>("Product can't be update",HttpStatus.BAD_REQUEST);
    }

    @Override
//    @Transactional(rollbackFor = Exception.class)
    public ResponseEntity<String> delete(Long id) {
        if (productRepository.existsById(id) && id > 0) {
            productRepository.deleteById(id);
            return new ResponseEntity<>("Product Deleted", HttpStatus.OK);
        } else return new ResponseEntity<>("Does not exist", HttpStatus.BAD_REQUEST);
    }

    @Override
    public ResponseEntity<Page<Product>> getAll(Pageable pageable) {
        if(!productRepository.findAll().isEmpty()) {
            return new ResponseEntity<>(productRepository.findAll(pageable), HttpStatus.OK);
        }
        else{
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @Override
    public ResponseEntity<ProductDto> findById(Long id) {
        if (id != null && id > 0 && productRepository.existsById(id)) {
            var productReference = productRepository.getReferenceById(id);

            var productDto = ProductDto.builder()
            .SKU(productReference.getSKU())
            .description(productReference.getDescription())
            .price(productReference.getPrice())
            .stock(productReference.getStock())
            .build();

            return new ResponseEntity<>(productDto, HttpStatus.OK);
        } else return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

//
//    @Override
//    public String toString() {
//        ObjectMapper objectMapper = new ObjectMapper();
//        try {
//            return objectMapper.writeValueAsString();
//        } catch (JsonProcessingException e) {
//            e.printStackTrace();
//            return null;
//        }
}
