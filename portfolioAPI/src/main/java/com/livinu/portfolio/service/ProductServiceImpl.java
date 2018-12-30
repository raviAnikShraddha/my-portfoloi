package com.livinu.portfolio.service;

import java.util.List;
import javax.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.livinu.portfolio.dto.ProductRequset;
import com.livinu.portfolio.dto.ProductResponse;
import com.livinu.portfolio.model.Product;
import com.livinu.portfolio.repository.ProductRepository;
import com.livinu.portfolio.util.BeanMapperUtil;
import com.livinu.portfolio.util.DeleteAPIResponse;

@Service
@Transactional
public class ProductServiceImpl implements ProductService {

	private static final Logger log = LoggerFactory.getLogger(ProductServiceImpl.class);

	@Autowired
	ProductRepository productRepository;

	@Override
	public List<ProductResponse> findAll() {

		log.info("-->>>> Start of findAll");
		log.info("-->>>> End of findAll");
		return new BeanMapperUtil().mapAsList(productRepository.findAll(), ProductResponse.class);
	}

	@Override
	public ProductResponse getProductById(Long productId) {

		Product product = productRepository.findById(productId).get();
		return new BeanMapperUtil().map(product, ProductResponse.class);
	}

	@Override
	public ProductResponse save(ProductRequset productRequset) {

		Product product = new Product();
		product.setProductName(productRequset.getProductName());
		product.setProductDesc(productRequset.getProductDesc());
		productRepository.save(product);
		return new BeanMapperUtil().map(product, ProductResponse.class);
	}

	@Override
	public ProductResponse update(ProductRequset productRequset) {

		Product product = (productRepository.findById(productRequset.getProductId())).get();
		product.setProductName(productRequset.getProductName());
		product.setProductDesc(productRequset.getProductDesc());
		productRepository.save(product);
		return new BeanMapperUtil().map(product, ProductResponse.class);
	}

	@Override
	public DeleteAPIResponse delete(Long productId) {

		Product product = (productRepository.findById(productId)).get();
		productRepository.delete(product);
		return new DeleteAPIResponse(productId);
	}

}
