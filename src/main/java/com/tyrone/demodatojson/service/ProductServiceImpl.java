package com.tyrone.demodatojson.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;
import com.tyrone.demodatojson.dto.ProductDto;
import com.tyrone.demodatojson.dto.ProductDtoForGson;
import com.tyrone.demodatojson.entity.Product;
import com.tyrone.demodatojson.repository.ProductRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.lang.reflect.Type;
import java.util.Map;

@Service
public class ProductServiceImpl implements CrudService1<ProductDto, Product> {

    @Autowired
    private ProductRepository productRepository;

    @Override
    public ResponseEntity<Product> create(ProductDtoForGson productDto) {
        Gson gson = new Gson();
        String jsonString = gson.toJson(productDto.features());
        var newProduct = Product.builder()
                .SKU(productDto.SKU())
                .description(productDto.description())
                .price(productDto.price())
                .stock(productDto.stock())
                .features(jsonString)
                .build();
        productRepository.save(newProduct);
        return new ResponseEntity<>(newProduct,HttpStatus.CREATED);
    }


    @Override
    public ResponseEntity<Product> create1(ProductDto productDto) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        String jsonString = objectMapper.writeValueAsString(productDto.features());
        Product newProduct = Product.builder()
                .SKU(productDto.SKU())
                .description(productDto.description())
                .price(productDto.price())
                .stock(productDto.stock())
                .features(jsonString)
                .build();
        productRepository.save(newProduct);


        return new ResponseEntity<>(newProduct ,HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<Product> create2(ProductDto productDto) {
        var newProduct = Product.builder()
                .SKU(productDto.SKU())
                .description(productDto.description())
                .price(productDto.price())
                .stock(productDto.stock())
                .features(productDto.features().toString())
                .build();
        productRepository.save(newProduct);
        return new ResponseEntity<>(newProduct,HttpStatus.CREATED);
//        return null;
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
    public ResponseEntity<ProductDto> findById(Long id) throws JsonProcessingException {
        if (id != null && id > 0 && productRepository.existsById(id)) {
            var productReference = productRepository.getReferenceById(id);

            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode stringJson = objectMapper.readValue(productReference.getFeatures(), JsonNode.class);

            var productDto = ProductDto.builder()
            .SKU(productReference.getSKU())
            .description(productReference.getDescription())
            .price(productReference.getPrice())
            .stock(productReference.getStock())
            .features(stringJson)
            .build();

            return new ResponseEntity<>(productDto, HttpStatus.OK);
        } else return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    public ResponseEntity<ProductDtoForGson> findByIdforGson(Long id) {
        if (id != null && id > 0 && productRepository.existsById(id)) {
            var productReference = productRepository.getReferenceById(id);

            Type mapType = new TypeToken<Map<String, Object>>(){}.getType();
            Gson gson = new Gson();
            Map<String, Object> stringJson = gson.fromJson(productReference.getFeatures(),/*Map.class*/  mapType);

            var productDto = ProductDtoForGson.builder()
                    .SKU(productReference.getSKU())
                    .description(productReference.getDescription())
                    .price(productReference.getPrice())
                    .stock(productReference.getStock())
                    .features(stringJson)
                    .build();

            return new ResponseEntity<>(productDto, HttpStatus.OK);
        } else return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

}
