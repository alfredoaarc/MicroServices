package microservicesgft.teammicroservicesgft.controller;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.fasterxml.jackson.databind.ObjectMapper;

import microservicesgft.teammicroservicesgft.controllers.CartController;
import microservicesgft.teammicroservicesgft.models.Cart;
import microservicesgft.teammicroservicesgft.services.CartService;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import java.util.ArrayList;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import static org.hamcrest.Matchers.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class CartControllerTest {

    @Mock
    private CartService cartService;

    @InjectMocks
    private CartController cartController;

    private MockMvc mockMvc;

    private final ObjectMapper objectMapper = new ObjectMapper();

    @BeforeEach
    public void setup() {
        mockMvc = MockMvcBuilders.standaloneSetup(cartController).build();
    }

    @Test
    public void testCreateCart() throws Exception {
        Cart mockCart = new Cart();
        mockCart.setCartId(1);
        mockCart.setUserId(1);
        mockCart.setProducts("product 1");
        
        when(cartService.createCart(any(Cart.class))).thenReturn(mockCart);
        
        Cart postCart = new Cart();
        postCart.setUserId(1);
        postCart.setProducts("product 1");
        
        mockMvc.perform(post("/cart/createcart")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(postCart)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.cartId", is(1)))
                .andExpect(jsonPath("$.userId", is(1)))
                .andExpect(jsonPath("$.products", is("product 1")));
        
        verify(cartService, times(1)).createCart(any(Cart.class));
    }
    

}
