package com.livinu.portfolio.service;

import java.util.List;

import com.livinu.portfolio.dto.ProductRequset;
import com.livinu.portfolio.dto.ProductResponse;
import com.livinu.portfolio.util.DeleteAPIResponse;

public interface ProductService {

	public List<ProductResponse> findAll();

	public ProductResponse getProductById(Long productId);

	public ProductResponse save(ProductRequset productRequset);

	public ProductResponse update(ProductRequset productRequset);

	public DeleteAPIResponse delete(Long productId);

}
