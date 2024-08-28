package com.company.controller;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.company.model.ErrorResponseDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.company.model.Product;
import com.company.service.ProductService;

@RestController
@RequestMapping("/api/v1/product")
public class ProductController {

	@Autowired
	ProductService productService;

	@Operation(
			summary = "Create Product REST API",
			description = "REST API to get all products"
	)
	@ApiResponses({
			@ApiResponse(
					responseCode = "200",
					description = "HTTP Status OK"
			),
			@ApiResponse(
					responseCode = "500",
					description = "HTTP Status Internal Server Error",
					content = @Content(
							schema = @Schema(implementation = ErrorResponseDTO.class)
					)
			)
	})
	@GetMapping("")
	List<Product> getProducts() {
		return productService.getProducts();
	}

	@Operation(
			summary = "Fetch Products Details using Product's Id in REST API",
			description = "REST API to fetch card details based on a product Id"
	)
	@ApiResponses({
			@ApiResponse(
					responseCode = "200",
					description = "HTTP Status OK"
			),
			@ApiResponse(
					responseCode = "500",
					description = "HTTP Status Internal Server Error",
					content = @Content(
							schema = @Schema(implementation = ErrorResponseDTO.class)
					)
			)
	})
	@GetMapping("/{id}")
	public Product getProduct(@PathVariable("id") Long id) {
		return productService.getProduct(id);
	}

	@Operation(
			summary = "Create Product REST API",
			description = "REST API to create new Product inside db"
	)
	@ApiResponses({
			@ApiResponse(
					responseCode = "201",
					description = "HTTP Status CREATED"
			),
			@ApiResponse(
					responseCode = "500",
					description = "HTTP Status Internal Server Error",
					content = @Content(
							schema = @Schema(implementation = ErrorResponseDTO.class)
					)
			)
	})
	@PostMapping(value = "")
	public Map<String, Object> createProduct(@RequestParam(value = "id") Long id,
			@RequestParam(value = "name") String name, @RequestParam(value = "price") Integer price) {

		productService.createProduct(id, name, price);

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("status", "Product added!");
		return map;
	}


	@Operation(
			summary = "Update Product Details using Id REST API",
			description = "REST API to update card details based on a product Id number"
	)
	@ApiResponses({
			@ApiResponse(
					responseCode = "200",
					description = "HTTP Status OK"
			),
			@ApiResponse(
					responseCode = "417",
					description = "Expectation Failed"
			),
			@ApiResponse(
					responseCode = "500",
					description = "HTTP Status Internal Server Error",
					content = @Content(
							schema = @Schema(implementation = ErrorResponseDTO.class)
					)
			)
	})
	@PutMapping(value = "")
	public Product updateProductUsingJson(@RequestBody Product product) {
		productService.updateProduct(product);
		return product;
	}

	@Operation(summary = "Delete a Product", description = "Delete a Product by their ID")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "204", description = "Product deleted"),
			@ApiResponse(responseCode = "404", description = "Product not found", content = @Content)
	})
	@DeleteMapping("/{id}")
	public Map<String, Object> deleteProduct(@PathVariable("id") Long id) {
		productService.deleteProduct(id);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("status", "Product deleted!");
		return map;
	}
}
