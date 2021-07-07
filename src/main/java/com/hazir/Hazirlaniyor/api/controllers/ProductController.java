package com.hazir.Hazirlaniyor.api.controllers;

import com.hazir.Hazirlaniyor.business.concretes.ProductManager;
import com.hazir.Hazirlaniyor.core.utillities.results.DataResult;
import com.hazir.Hazirlaniyor.core.utillities.results.Result;
import com.hazir.Hazirlaniyor.entity.concretes.Product;
import com.hazir.Hazirlaniyor.entity.concretes.ProductCategory;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import java.util.List;
import java.util.Optional;

import lombok.AllArgsConstructor;
@RestController
@RequestMapping("api/v1/products")
@CrossOrigin(origins = {"http://localhost:4200"})

public class ProductController {
	private final ProductManager productManager;

	public ProductController(ProductManager productManager) {
		this.productManager = productManager;
	}

	//5000$ < MUTA FAKIR
 @GetMapping
	DataResult<List<Product>> getAllProducts() {
		return this.productManager.getAllProducts ();
	}

	@PostMapping
	public Result addNewProduct(@RequestBody Product product) {
		return this.productManager.addProduct (product);
	}

	@GetMapping(path = "/{productCategory}")
	DataResult<List<Product>> findProductsByCategory(@PathVariable("productCategory") ProductCategory productCategory) {
		return this.productManager.findProductsByCategory (productCategory);
	}

	@DeleteMapping(path = "/{productId}")
	Result deleteProductById(@PathVariable("productId") Long Id) {
		return this.productManager.deleteProductById(Id);
	}
	@GetMapping(path = "productId/{productId}")
	DataResult<Optional<Product>> findProductById(@PathVariable("productId") Long Id){
		return  this.productManager.findProductById(Id);
	}

	@GetMapping(path = "productname/{productName}")
	Optional<Product> findProductByName(@PathVariable("productName")String productName){
		return this.productManager.findProductByName(productName);
	}
}
