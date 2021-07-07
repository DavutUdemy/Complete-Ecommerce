package com.hazir.Hazirlaniyor.business.concretes;

import com.hazir.Hazirlaniyor.business.abstracts.CartService;
import com.hazir.Hazirlaniyor.business.abstracts.Facade;
import com.hazir.Hazirlaniyor.core.utillities.results.DataResult;
import com.hazir.Hazirlaniyor.core.utillities.results.Result;
import com.hazir.Hazirlaniyor.core.utillities.results.SuccessDataResult;
import com.hazir.Hazirlaniyor.core.utillities.results.SuccessResult;
import com.hazir.Hazirlaniyor.dataAccess.abstracts.CartDao;
import com.hazir.Hazirlaniyor.entity.concretes.Cart;

import org.springframework.stereotype.Service;

import java.util.List;

import lombok.AllArgsConstructor;

@Service
public class CartManager implements CartService {
	private final CartDao cartDao;
	private final Facade  mFacade;

	public CartManager(CartDao cartDao, Facade facade) {
		this.cartDao = cartDao;
		mFacade = facade;
	}

	@Override
	public DataResult<List<Cart>> getAllCarts() {
		return new SuccessDataResult<List<Cart>>
				(this.cartDao.findAll (), "Data listelendi");

	}

	@Override
	public DataResult<List<Cart>> findCartByUserEmail(String email) {
		return new SuccessDataResult<List<Cart>>
				(this.cartDao.findCartByEmail (email), "Data Listelendi");
	}


	@Override
	public Result addProductToCart(Cart cart) {
		this.mFacade.productCheckerService.existProduct (cart.getProductName ());
		this.cartDao.save (cart);
		return new SuccessResult ("Ürün eklendi");
	}

	@Override
	public Result deleteCartById(Long Id) {
		this.cartDao.deleteById (Id);
		return new SuccessResult ("Ürün Silindi");
	}


}