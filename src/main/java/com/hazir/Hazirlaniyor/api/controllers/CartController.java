package com.hazir.Hazirlaniyor.api.controllers;

import com.hazir.Hazirlaniyor.business.concretes.CartManager;
import com.hazir.Hazirlaniyor.core.utillities.results.DataResult;
import com.hazir.Hazirlaniyor.core.utillities.results.Result;
import com.hazir.Hazirlaniyor.entity.concretes.Cart;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.extern.java.Log;


@RestController
@RequestMapping("api/v1/Cart")
@AllArgsConstructor
@CrossOrigin
public class CartController {
	private final CartManager cartManager;

	@GetMapping
	public DataResult<List<Cart>> getAllCarts() {
		return this.cartManager.getAllCarts ();
	}

	@GetMapping(path = "{email}")
	public DataResult<List<Cart>> findCartByUserEmail(@PathVariable("email") String email) {
		return this.cartManager.findCartByUserEmail (email);
	}

	@DeleteMapping(path = "{id}")
	public Result deleteCartById(@PathVariable("id") Long Id) {
		return this.cartManager.deleteCartById (Id);
	}

	@PostMapping
	public Result addProductToCart(@RequestBody Cart cart) {
		return this.cartManager.addProductToCart (cart);
	}

}
