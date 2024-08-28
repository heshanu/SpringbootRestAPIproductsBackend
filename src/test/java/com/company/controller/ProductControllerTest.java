package com.company.controller;

import com.company.entity.ProductEntity;
import com.company.service.ProductService;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestTemplate;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.Optional;

import static java.lang.reflect.Array.get;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(ProductController.class)
class ProductControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ProductService productService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void getProducts() throws Exception {
        when(productService.getProducts()).thenReturn(new ArrayList<>());

        mockMvc.perform(get("/api/v1/product")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json("[]"));
    }

    @Test
    void getProduct() throws Exception{
        Long productId = 1L;
        ProductEntity product = new ProductEntity(1l,"Product 1", 10.0);
        product.setProductID(1l);

        when(productService.getProduct(productId)).thenReturn(Optional.of(product));
        mockMvc.perform(get("/api/v1/product/{id}", productId)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isAccepted())
                .andExpect(content().json("{\"id\":1l,\"name\":\"Product 1\",\"price\":10.0}"));
    }

    @Test
    public void testGetProduct_NotFound() throws Exception {
        Long productId = 1L;
        when(productService.getProduct(productId)).thenReturn(Optional.empty());

        mockMvc.perform(get("/api/v1/product/{id}", productId)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isAccepted())
                .andExpect(content().json("null"));
    }
}