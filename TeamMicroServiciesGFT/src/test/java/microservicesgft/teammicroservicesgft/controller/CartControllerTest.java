package microservicesgft.teammicroservicesgft.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import microservicesgft.teammicroservicesgft.controllers.CartController;
import microservicesgft.teammicroservicesgft.models.Cart;
import microservicesgft.teammicroservicesgft.services.CartService;

@ExtendWith(MockitoExtension.class)
public class CartControllerTest {

    @Mock
    private CartService cartService;

    @InjectMocks
    private CartController cartController;

    @Test
    public void testReturningAllCarts() {
        // Given
        List<Cart> expectedCarts = Arrays.asList(new Cart(), new Cart());
        when(cartService.findAllCarts()).thenReturn(expectedCarts);

        // When
        ResponseEntity<List<Cart>> response = cartController.returningAllCarts();

        // Then
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(expectedCarts, response.getBody());
    }
    


}
