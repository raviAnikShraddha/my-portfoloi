package com.livinu.portfolio.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.livinu.portfolio.model.Product;

public interface ProductRepository extends JpaRepository<Product, Long> {

}
