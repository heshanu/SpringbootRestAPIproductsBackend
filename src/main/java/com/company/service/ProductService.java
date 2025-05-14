package com.company.service;

import java.util.List;
import java.util.Optional;

import com.company.entity.ProductEntity;

public interface ProductService {
	List<ProductEntity> getProducts();
	Optional<ProductEntity> getProduct(Long id);
	String createProduct(ProductEntity product);
	ProductEntity updateProduct(ProductEntity product);
	Optional<ProductEntity> deleteProduct(Long id);
}
