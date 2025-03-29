package com.myproject.agrolink.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.myproject.agrolink.dao.CartItemRepository;
import com.myproject.agrolink.dao.CartRepository;
import com.myproject.agrolink.dao.ClientRepository;
import com.myproject.agrolink.dao.ProductRepository;
import com.myproject.agrolink.entity.Cart;
import com.myproject.agrolink.entity.CartItem;
import com.myproject.agrolink.entity.Client;
import com.myproject.agrolink.entity.Product;
import com.myproject.agrolink.requestmodel.CartItemRequest;
import com.myproject.agrolink.requestmodel.CartRequest;
import com.myproject.agrolink.requestmodel.ModifyCartItemRequest;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
@Transactional
public class CartService {

    private CartRepository cartRepository;

    private CartItemRepository cartItemRepository;

    private ProductRepository productRepository;

    private ClientRepository clientRepository;

    public CartService(CartRepository cartRepository, CartItemRepository cartItemRepository,
            ProductRepository productRepository, ClientRepository clientRepository) {
        this.cartRepository = cartRepository;
        this.productRepository = productRepository;
        this.cartItemRepository = cartItemRepository;
        this.clientRepository = clientRepository;
    }

    public Cart getCartById(Integer cartId) throws Exception {
        Optional<Cart> cart = cartRepository.findById(cartId);
        if (cart.isPresent()) {
            return cart.get();
        } else {
            throw new Exception("Cart with id " + cartId + " not found");
        }

    }

    public Cart addCart(CartRequest cartRequest) throws Exception {
        Optional<Client> client = clientRepository.findById(cartRequest.getClientId());

        if (!client.isPresent()) {
            throw new Exception("Client doesn't exist");
        }

        Cart validatedCart = cartRepository.findByClient(client.get());

        if (validatedCart != null) {
            // value
            if (cartRequest.getValue() != null && cartRequest.getValue().isPresent()) {
                validatedCart.setValue(cartRequest.getValue().get());

            }

            return validatedCart;
        }

        Cart cart = new Cart();
        cart.setClient(client.get());

        // value
        if (cartRequest.getValue() != null && cartRequest.getValue().isPresent()) {
            cart.setValue(cartRequest.getValue().get());

        }

        cart.setCreationDt(LocalDateTime.now());

        cartRepository.save(cart);

        return cart;
    }

    public CartItem findCartItemById(Integer cartItemId) throws Exception {
        Optional<CartItem> cartItem = cartItemRepository.findById(cartItemId);

        if(!cartItem.isPresent()){
            throw new Exception("Cart item not found");
        }

        return cartItem.get();
    }


    public CartItem addCartItem(CartItemRequest cartItemRequest) throws Exception {

        Cart cart = getCartById(cartItemRequest.getCartId());
    
        Optional<Product> product = productRepository.findById(cartItemRequest.getProductId());

        if (!product.isPresent()) {
            throw new Exception("Product doesn't exist");
        }

        CartItem cartItem = cartItemRepository.findByCartAndProduct(cart, product.get());

        if (cartItem != null) {
            // Update quantity if the item already exists in cart
            CartItem existingCartItem = cartItem;
            existingCartItem.setQuantity(cartItem.getQuantity().add(cartItemRequest.getQuantity()));
            System.out.println(cartItem.getId());
            return existingCartItem;
        } else {
            // Create new cart item if item isn't already added to cart
            Product productToAdd = product.get();
            CartItem newCartItem = new CartItem();

            newCartItem.setCart(cart);
            newCartItem.setProduct(productToAdd);
            newCartItem.setQuantity(cartItemRequest.getQuantity());

            cartItemRepository.save(newCartItem);

            System.out.println(newCartItem.getId());

            return newCartItem;
        }
    }

    public CartItem modifyCartItem(ModifyCartItemRequest modifyCartItemRequest) throws Exception{
        CartItem cartItem = findCartItemById(modifyCartItemRequest.getCartItemId());

        cartItem.setQuantity(modifyCartItemRequest.getQuantity());

        return cartItem;
    }
}
