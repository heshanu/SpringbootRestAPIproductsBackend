package com.company.service;

import java.util.List;
import java.util.Optional;

import com.company.repo.ProductRepo;
import org.springframework.stereotype.Service;

import com.company.entity.ProductEntity;

@Service
public class ProductServiceImpl implements ProductService {

	private final ProductRepo productRepo;

	public ProductServiceImpl(ProductRepo productRepo) {
		this.productRepo = productRepo;
	}

	public List<ProductEntity> getProducts() {

		List<ProductEntity> allProductList=productRepo.findAll();
		return allProductList;
	}

	@Override
	public Optional<ProductEntity> getProduct(Long id) {
		Optional<ProductEntity> selectedProduct=productRepo.findById(id);
		return selectedProduct;
	}

	@Override
	public String  createProduct(ProductEntity product) {
			if(product!=null) {
			productRepo.save(product);
			return "Successfully created product:"+product.getProductID();
			}
			//ProductEntity newProduct=productRepo.getById(product.getProductID());
			return "Unable to create product:"+product.getProductID();
	}

	public ProductEntity updateProduct(ProductEntity product) {
			Optional<ProductEntity> prevProduct=productRepo.findById(product.getProductID());
			if(prevProduct.isPresent()){
				ProductEntity updateProduct=ProductEntity.builder()
							.productID(product.getProductID())
							.productPrice(product.getProductPrice())
							.productName(product.getProductName()).build();
				productRepo.save(updateProduct);
				return updateProduct;
			}
			return new ProductEntity(null,null,null);
	}

	public Optional<ProductEntity> deleteProduct(Long id) {
		//System.out.println("Status.. "+products.remove(getProduct(id)));
		Optional<ProductEntity> deletedProduct=productRepo.findById(id);
		if (deletedProduct.isPresent()){
			productRepo.deleteById(id);
			return deletedProduct;
		}
		return deletedProduct;
	}
}
