package microservicesgft.teammicroservicesgft.services;

import org.springframework.stereotype.Service;

import microservicesgft.teammicroservicesgft.models.Cart;
import microservicesgft.teammicroservicesgft.repositories.CartRepository;

import java.util.List;

@Service
public class CartService {
    private CartRepository cartRepository;

    public CartService(CartRepository cartRepository) {
        this.cartRepository = cartRepository;
    }

    public List<Cart>findAllCarts(){
        return cartRepository.findAll();

    }

public Cart createCart(Cart cart){
    return cartRepository.save(cart);
    
}
}