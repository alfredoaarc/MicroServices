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

    @Test
    public void testUpdateCart() throws Exception {
        Cart mockCart = new Cart();
        mockCart.setCartId(1);
        mockCart.setUserId(1);
        mockCart.setProducts("product 1");
        
        when(cartService.updateCart(any(Cart.class))).thenReturn(mockCart);
        
        Cart putCart = new Cart();
        putCart.setCartId(1);
        putCart.setUserId(1);
        putCart.setProducts("product 2");
        
        mockMvc.perform(put("/cart/updatecart")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(putCart)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.cartId", is(1)))
                .andExpect(jsonPath("$.userId", is(1)))
                .andExpect(jsonPath("$.products", is("product 1")));
        
        verify(cartService, times(1)).updateCart(any(Cart.class));
    }

    @Test
    public void testFindAllCarts() throws Exception {
        Cart mockCart1 = new Cart();
        mockCart1.setCartId(1);
        mockCart1.setUserId(1);
        mockCart1.setProducts("product 1");
    
        Cart mockCart2 = new Cart();
        mockCart2.setCartId(2);
        mockCart2.setUserId(2);
        mockCart2.setProducts("product 2");
    
        List<Cart> mockCartList = new ArrayList<>();
    mockCartList.add(mockCart1);
    mockCartList.add(mockCart2);
    
    when(cartService.findAllCarts()).thenReturn(mockCartList);
    
    mockMvc.perform(get("/cart/api/cart"))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$", hasSize(2)))
            .andExpect(jsonPath("$[0].cartId", is(1)))
            .andExpect(jsonPath("$[0].userId", is(1)))
            .andExpect(jsonPath("$[0].products", is("product 1")))
            .andExpect(jsonPath("$[1].cartId", is(2)))
            .andExpect(jsonPath("$[1].userId", is(2)))
            .andExpect(jsonPath("$[1].products", is("product 2")));
    
    verify(cartService, times(1)).findAllCarts();
    }

    @Test
    public void testDeleteCart() throws Exception {
    int itemId = 1;
    
    doNothing().when(cartService).deleteCart(itemId);
    
    mockMvc.perform(delete("/cart/deletecart/{item_id}", itemId))
            .andExpect(status().isNoContent());
    
    verify(cartService, times(1)).deleteCart(itemId);
    }



    
    
    

}
