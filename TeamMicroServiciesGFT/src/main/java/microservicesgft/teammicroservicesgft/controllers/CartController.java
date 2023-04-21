package microservicesgft.teammicroservicesgft.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import microservicesgft.teammicroservicesgft.models.Cart;
import microservicesgft.teammicroservicesgft.services.CartService;

import java.util.List;

@RestController
@RequestMapping("/cart")

public class CartController {

    private CartService cartService;

    public CartController (CartService cartService){
        this.cartService = cartService;
    }

    @GetMapping("api/cart")
    ResponseEntity<List<Cart>> returningAllCarts (){
        return new ResponseEntity<>(cartService.findAllCarts(), HttpStatus.OK);
    }
}
