package com.company.model;


import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.*;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Tag(
		name = "CRUD REST API for Product",
		description = "CRUD REST APIs for Product to CREATE, UPDATE, FETCH AND DELETE product details"
)
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Product {
	private Long productID;
	private String productName;
	private double productPrice;

}
