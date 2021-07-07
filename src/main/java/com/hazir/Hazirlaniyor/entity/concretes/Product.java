package com.hazir.Hazirlaniyor.entity.concretes;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table

@NoArgsConstructor
@Data

public class Product  {
    @SequenceGenerator(
            name = "product_sequence",
            sequenceName = "product_sequence",
            allocationSize = 1
    )
    @Id
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "product_sequence"
    )
    private Long Id;
    @NotNull(message = "ProductName can not be Empty")
    private String productName;
    private String productImage;
    private String productImage1;
    private String productImage2;
    private String productImage3;
	  @NotNull(message = "Product Description can not be Empty")
	  private String productDescription;
	  @NotNull(message = "Product Price can not be Empty")
	  private Integer productPrice;
	  @NotNull(message = "Product Category can not be Empty ")
	  @Enumerated(EnumType.STRING)
    private ProductCategory productCategory;
	  @NotNull(message = "Stock can not be empty ")
	  private Integer unitsInStock;

	public Product(Long id, String productName, String productImage, String productImage1, String productImage2, String productImage3, String productDescription, Integer productPrice, ProductCategory productCategory, Integer unitsInStock) {
		Id = id;
		this.productName = productName;
		this.productImage = productImage;
		this.productImage1 = productImage1;
		this.productImage2 = productImage2;
		this.productImage3 = productImage3;
		this.productDescription = productDescription;
		this.productPrice = productPrice;
		this.productCategory = productCategory;
		this.unitsInStock = unitsInStock;
	}

	public Product(Long id,String productName, String productImage,String productDescription, Integer productPrice, ProductCategory productCategory, Integer unitsInStock) {
		Id = id;
		this.productName = productName;
		this.productImage = productImage;
		this.productDescription = productDescription;
		this.productPrice = productPrice;
		this.productCategory = productCategory;
		this.unitsInStock = unitsInStock;
	}
}
