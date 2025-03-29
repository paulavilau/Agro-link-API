package com.myproject.agrolink.controller;


import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.myproject.agrolink.entity.Cart;
import com.myproject.agrolink.entity.CartItem;
import com.myproject.agrolink.requestmodel.CartItemRequest;
import com.myproject.agrolink.requestmodel.CartRequest;
import com.myproject.agrolink.requestmodel.ModifyCartItemRequest;
import com.myproject.agrolink.service.CartService;

@RestController
@RequestMapping("/agrolink/carts")
public class CartController {

    private CartService cartService;

    @Autowired
    public CartController(CartService cartService) {
        this.cartService = cartService;
    }

    @PutMapping("/addCart")
    public Cart createCart(@RequestBody CartRequest cartRequest) throws Exception {
        return cartService.addCart(cartRequest);
    }

    @PutMapping("/addCartItem")
    public CartItem addItemToCart(@RequestBody CartItemRequest cartItemRequest) throws Exception {
        return cartService.addCartItem(cartItemRequest);
    }

    @PatchMapping("/modifyCartItem")
    public CartItem modCartItem(@RequestBody ModifyCartItemRequest cartItemRequest) throws Exception {
        return cartService.modifyCartItem(cartItemRequest);
    }
}