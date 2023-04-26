package microservicesgft.teammicroservicesgft.services;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import microservicesgft.teammicroservicesgft.models.Cart;
import microservicesgft.teammicroservicesgft.repositories.CartRepository;

import java.util.List;
import java.util.Optional;

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

public Cart updateCart(Cart cart) {
    if (cartRepository.existsById(cart.getCartId())) {
        return cartRepository.save(cart);
    } else {
        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Product does not exist");
    }
}

public void deleteCart(int item_id) {
    Optional<Cart> optionalCart = cartRepository.findById(item_id);
    if (optionalCart.isPresent()) {
        cartRepository.delete(optionalCart.get());
    } else {
        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Cart does not exist"); 
    }
}
}