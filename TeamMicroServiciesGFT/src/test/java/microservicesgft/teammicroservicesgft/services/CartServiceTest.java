package microservicesgft.teammicroservicesgft.services;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import microservicesgft.teammicroservicesgft.models.Cart;
import microservicesgft.teammicroservicesgft.repositories.CartRepository;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;
import microservicesgft.teammicroservicesgft.models.Cart;
import microservicesgft.teammicroservicesgft.repositories.CartRepository;
import microservicesgft.teammicroservicesgft.services.CartService;


@ExtendWith(MockitoExtension.class)
public class CartServiceTest {
    @InjectMocks
    private CartService cartService;
    @Mock
    private CartRepository cartRepository;

    List<Cart> carts = new ArrayList<>();

    @BeforeEach
    void setup() {

        carts.add(new Cart(3, 5, "product 1"));
        carts.add(new Cart(1, 4, "product 2"));
        when(cartRepository.findAll()).thenReturn(carts);
    }

    @Test
    public void findAllCarts() {
        List<Cart> result = cartService.findAllCarts();
        Object[] cartsArr = carts.toArray();
        Object[] resultArr = result.toArray();
        assertArrayEquals(cartsArr, resultArr);
    }

    public Cart createCart(Cart cart) {
        return cartRepository.save(cart);
    }

    public Cart updateCart(Cart cart) {
        if (cartRepository.existsById(cart.getCartId())) {
            return cartRepository.save(cart);
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Product does not exist");
        }
    }





}
