package com.hazir.Hazirlaniyor.business.concretes;

import com.hazir.Hazirlaniyor.business.abstracts.ProductService;
import com.hazir.Hazirlaniyor.core.utillities.results.DataResult;
import com.hazir.Hazirlaniyor.core.utillities.results.Result;
import com.hazir.Hazirlaniyor.core.utillities.results.SuccessDataResult;
import com.hazir.Hazirlaniyor.core.utillities.results.SuccessResult;
import com.hazir.Hazirlaniyor.dataAccess.abstracts.ProductDao;
import com.hazir.Hazirlaniyor.entity.concretes.AppUser;
import com.hazir.Hazirlaniyor.entity.concretes.Product;
import com.hazir.Hazirlaniyor.entity.concretes.ProductCategory;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class ProductManager implements ProductService {
	private final ProductDao productDao;

	@Override
	public DataResult<List<Product>> getAllProducts() {
		return new SuccessDataResult<List<Product>>
				(this.productDao.findAll (), "Data listelendi");
	}

	@Override
	public DataResult<List<Product>> findProductsByCategory(ProductCategory productCategory) {
		return new SuccessDataResult<List<Product>> (this.productDao.findProductByCategory (productCategory), "Data Listelendi");

	}

	@Override
	public Result addProduct(Product product) {
		boolean existsProductByProductName = this.productDao.existsProductByProductName (product.getProductName()).isPresent();
		if(existsProductByProductName){
			throw new IllegalStateException("There is a product with this name please try again!");
		}
		this.productDao.save(product);
		return new SuccessResult("Urun basarili bir sekilde kaydedildi");
	}

	@Override
	public Result deleteProductById(Long Id) {
		this.productDao.deleteById (Id);
		return new SuccessResult("Urun basarili bir sekilde silindi");
	}

	@Override
	public Optional<Product> findProductByName(String productName) {
		return this.productDao.findProductByProductName(productName);
	}

	@Override
	public DataResult<Optional<Product>> findProductById(Long id) {
		return new SuccessDataResult<Optional<Product>> (this.productDao.findById (id),
				"Data Listelendi");
	}


}
