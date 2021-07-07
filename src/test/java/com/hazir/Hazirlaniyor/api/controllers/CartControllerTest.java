package com.hazir.Hazirlaniyor.api.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.hazir.Hazirlaniyor.dataAccess.abstracts.CartDao;
import com.hazir.Hazirlaniyor.entity.concretes.Cart;
import com.hazir.Hazirlaniyor.entity.concretes.Product;
import com.hazir.Hazirlaniyor.entity.concretes.ProductCategory;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import java.util.Objects;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class CartControllerTest {
	@Autowired
	private CartDao cartDao;
	@Autowired
	private MockMvc mvc;
	@Test
	void itShouldRetrieveCartOfUser() throws Exception{
		Product product = new Product (1L, "Macbook Pro", "https://store.storeimages.cdn-apple.com/4982/as-images.apple" + ".com/is/mbp-spacegray-select-202011?wid=904&hei=840&fmt=jpeg&qlt=80&.v=1613672894000", "Macbook Pro M1", 4500, ProductCategory.Electronic, 24);
		Cart cart = new Cart("Macbook Pro", "https://store.storeimages.cdn-apple.com/4982/as-images" +
				".apple" + ".com/is/mbp-spacegray-select-202011?wid=904&hei=840&fmt=jpeg&qlt=80&" +
				".v=1613672894000", "Macbook Pro M1", 4500, ProductCategory.Electronic,5,0,
				"nailmemmedova12@gmail.com");
		ResultActions userRegResultActions = mvc.perform (get ("/api/v1/Cart/" +cart.getEmail())
				.contentType (MediaType.APPLICATION_JSON));
		userRegResultActions.andExpect (status ().isOk ())
				.andExpect (jsonPath ("productName").value ("Macbook Pro"))
				.andExpect (jsonPath ("productPrice").value (4500))
				.andExpect (jsonPath ("productCategory").value ("Electronic")).andExpect (jsonPath (
				"productPrice").value(4500)).
				andExpect(jsonPath("email").value ("nailmemmedova12@gmail.com"));
	}
	@Test
	void itShouldAddProductToCart() throws Exception {
		Cart cart = new Cart(3L,"Macbook Pro",
				"https://store.storeimages.cdn-apple.com/4982/as-images" + ".apple" + ".com/is/mbp-spacegray-select-202011?wid=904&hei=840&fmt=jpeg&qlt=80&" + ".v=1613672894000", "Macbook Pro M1", 4500, ProductCategory.Electronic,5,0, "nailmemmedova12@gmail.com");
		this.cartDao.save (cart);
		ResultActions cartRegResultActions = mvc.perform (post ("/api/v1/Cart")
				.contentType(MediaType.APPLICATION_JSON)
				.content(Objects.requireNonNull(asJsonString(cart))));
		cartRegResultActions.andExpect (status ().isOk ());
		assertThat(this.cartDao.findById(cart.getId()))
				.isPresent()
				.hasValueSatisfying(p -> assertThat(p).isEqualToComparingFieldByField(cart));


	}
	public static String asJsonString(final Object obj) {
		try {
			final ObjectMapper mapper = new ObjectMapper();
			final String jsonContent = mapper.writeValueAsString(obj);
			System.out.println(jsonContent);
			return jsonContent;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
}
