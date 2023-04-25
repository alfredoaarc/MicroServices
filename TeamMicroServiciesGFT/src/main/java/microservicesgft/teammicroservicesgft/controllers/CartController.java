package microservicesgft.teammicroservicesgft.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import microservicesgft.teammicroservicesgft.models.Cart;
import microservicesgft.teammicroservicesgft.services.CartService;

import java.util.List;

@RestController
@RequestMapping("/cart")
public class CartController {

    private CartService cartService;

    public CartController(CartService cartService) {
        this.cartService = cartService;
    }

    @GetMapping("/api/cart")
    public ResponseEntity<List<Cart>> findAllCarts() {
        return new ResponseEntity<>(cartService.findAllCarts(), HttpStatus.OK);
    }



    @PostMapping("/createcart")
    public ResponseEntity<Cart> createCart(@RequestBody Cart cart) {
        return ResponseEntity.status(HttpStatus.CREATED).body(cartService.createCart(cart));
    }
}
