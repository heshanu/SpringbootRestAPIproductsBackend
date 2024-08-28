package com.company.service;

import java.util.List;
import java.util.Optional;

import com.company.entity.ProductEntity;

public interface ProductService {
	List<ProductEntity> getProducts();
	Optional<ProductEntity> getProduct(Long id);
	void createProduct(ProductEntity productEntity);
	void updateProduct(ProductEntity productEntity);
	void deleteProduct(Long id);
}
