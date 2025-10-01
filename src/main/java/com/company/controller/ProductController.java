package com.company.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.company.entity.ProductEntity;
import com.company.service.ProductService;

@RestController
@RequestMapping("api/v1/product")
public class ProductController {

	@Autowired
	ProductService productService;

	@GetMapping("")
	List<ProductEntity> getProducts() {
		return productService.getProducts();
	}
	@GetMapping("/{id}")
	public ResponseEntity<ProductEntity> getProduct(@PathVariable("id") Long id) {
		Optional<ProductEntity> product = productService.getProduct(id);
		if (product != null) {
			return new ResponseEntity(product, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@PostMapping(value = "")
	public ResponseEntity<String> createProduct(@RequestBody ProductEntity product) {
		String result = productService.createProduct(product);
		return new ResponseEntity<>(result, HttpStatus.CREATED);
	}

	@PutMapping(value = "")
	public ResponseEntity<ProductEntity> updateProductUsingJson(@RequestBody ProductEntity product) {
		ProductEntity updatedProduct = productService.updateProduct(product);
		return new ResponseEntity<>(updatedProduct, HttpStatus.OK);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Map<String, Object>> deleteProduct(@PathVariable("id") Long id) {
		productService.deleteProduct(id);
		Map<String, Object> map = new HashMap<>();
		map.put("status", "Product deleted!");
		return new ResponseEntity<>(map, HttpStatus.OK);
	}

	@GetMapping()
	public String sayHello() {
		return "Hi";
	}

	@GetMapping()
	public String sayHelloTwo() {
		return "Hi";
	}

	@GetMapping()
	public String sayHelloThree() {
		return "Hi";
	}

	@GetMapping()
	public String sayHelloFive() {
		return "Hi";
	}

	@GetMapping()
	public String sayHelloSix(){
		return "Hi";
	}

	@GetMapping()
	public String sayHelloSeven(){
		return "Hi";
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Map<String, Object>> deleteProductOne(@PathVariable("id") Long id) {
		productService.deleteProduct(id);
		Map<String, Object> map = new HashMap<>();
		map.put("status", "Product deleted!");
		return new ResponseEntity<>(map, HttpStatus.OK);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Map<String, Object>> deleteProductTwo(@PathVariable("id") Long id) {
		productService.deleteProduct(id);
		Map<String, Object> map = new HashMap<>();
		map.put("status", "Product deleted!");
		return new ResponseEntity<>(map, HttpStatus.OK);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Map<String, Object>> deleteProductThree(@PathVariable("id") Long id) {
		productService.deleteProduct(id);
		Map<String, Object> map = new HashMap<>();
		map.put("status", "Product deleted!");
		return new ResponseEntity<>(map, HttpStatus.OK);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Map<String, Object>> deleteProductFour(@PathVariable("id") Long id) {
		productService.deleteProduct(id);
		Map<String, Object> map = new HashMap<>();
		map.put("status", "Product deleted!");
		return new ResponseEntity<>(map, HttpStatus.OK);
	}

}
