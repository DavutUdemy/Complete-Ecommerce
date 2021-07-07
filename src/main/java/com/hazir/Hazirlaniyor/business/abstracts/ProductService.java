package com.hazir.Hazirlaniyor.business.abstracts;

import com.hazir.Hazirlaniyor.core.utillities.results.DataResult;
import com.hazir.Hazirlaniyor.core.utillities.results.Result;
import com.hazir.Hazirlaniyor.entity.concretes.Product;
import com.hazir.Hazirlaniyor.entity.concretes.ProductCategory;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
public interface ProductService {
  DataResult<List<Product>> getAllProducts();
  DataResult<List<Product>> findProductsByCategory(ProductCategory productCategory);
  Result addProduct(Product product);
  Result deleteProductById(Long Id);
	Optional<Product> findProductByName(String productName);

	DataResult<Optional<Product>> findProductById(Long id);
}
