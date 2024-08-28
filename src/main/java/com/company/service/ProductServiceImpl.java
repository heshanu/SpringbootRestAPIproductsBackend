package com.company.service;

import java.util.List;
import java.util.Optional;

import com.company.Repo.ProductRepo;
import com.company.entity.ProductEntity;
import com.company.exception.handler.ProductsNotFound;
import com.company.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductServiceImpl implements ProductService {

	@Autowired
	private ProductRepo productRepo;

	private Product mapToProductResponse(ProductEntity productEntity) {
		return new Product(productEntity.getProductID(), productEntity.getProductName(), productEntity.getProductPrice());
	}

	private ProductEntity mapToProductEntity(Product product){
		return new ProductEntity(product.getProductID(), product.getProductName(), product.getProductPrice());
	}


	public List<ProductEntity> getProducts() {
		return productRepo.findAll();
	}

	@Override
	public Optional<ProductEntity> getProduct(Long id) {
		List<ProductEntity> products = productRepo.findAll();
		if ( products.isEmpty()) {
			throw new ProductsNotFound("No employees found", "NO_MORE_EMPLOYEES");
		}

		return Optional.of((ProductEntity) products);

	}

	@Override
	public void createProduct(ProductEntity productEntity) {
		productRepo.save(productEntity);
	}

	@Override
	public void updateProduct(ProductEntity productEntity) {
		productRepo.save(productEntity);
	}

	@Override
	public void deleteProduct(Long id) {
		productRepo.deleteById(id);
	}


}
