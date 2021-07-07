package com.hazir.Hazirlaniyor.business.abstracts;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.hazir.Hazirlaniyor.core.utillities.BadRequest.BadRequestException;
import com.hazir.Hazirlaniyor.dataAccess.abstracts.ProductDao;
import com.hazir.Hazirlaniyor.entity.concretes.Product;
import com.hazir.Hazirlaniyor.entity.concretes.ProductCategory;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ContextConfiguration(classes = {ProductCheckerDal.class})
@ExtendWith(SpringExtension.class)
public class ProductCheckerDalTest {
	@Autowired
	private ProductCheckerDal productCheckerDal;

	@MockBean
	private ProductDao productDao;


	@Test
	public void testExistProduct() {
		Product product = new Product ();
		product.setProductImage1 ("Product Image1");
		product.setProductImage2 ("Product Image2");
		product.setProductPrice (1);
		product.setProductImage3 ("Product Image3");
		product.setUnitsInStock (1);
		product.setId (123L);
		product.setProductName ("Product Name");
		product.setProductCategory (ProductCategory.Electronic);
		product.setProductDescription ("Product Description");
		product.setProductImage ("Product Image");
		Optional<Product> ofResult = Optional.<Product>of (product);
		when (this.productDao.existsProductByProductName (anyString ())).thenReturn (ofResult);
		this.productCheckerDal.existProduct ("Product Name");
		verify (this.productDao).existsProductByProductName (anyString ());
	}

	@Test
	public void testExistProduct2() {
		when (this.productDao.existsProductByProductName (anyString ())).thenReturn (Optional.<Product>empty ());
		assertThrows (BadRequestException.class, () -> this.productCheckerDal.existProduct ("Product Name"));
		verify (this.productDao).existsProductByProductName (anyString ());
	}
}

