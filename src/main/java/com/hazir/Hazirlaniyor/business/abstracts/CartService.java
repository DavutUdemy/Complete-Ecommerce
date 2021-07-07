package com.hazir.Hazirlaniyor.business.abstracts;

import com.hazir.Hazirlaniyor.core.utillities.results.DataResult;
import com.hazir.Hazirlaniyor.core.utillities.results.Result;
import com.hazir.Hazirlaniyor.entity.concretes.Cart;

import java.util.List;

public interface CartService {
    DataResult<List<Cart>> getAllCarts();
    DataResult<List<Cart>> findCartByUserEmail(String email);
    Result  addProductToCart(Cart cart);
    Result deleteCartById(Long Id);

 }
