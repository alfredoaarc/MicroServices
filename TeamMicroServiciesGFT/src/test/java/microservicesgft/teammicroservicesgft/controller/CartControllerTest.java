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

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import microservicesgft.teammicroservicesgft.controllers.CartController;
import microservicesgft.teammicroservicesgft.models.Cart;
import microservicesgft.teammicroservicesgft.models.Product;
import microservicesgft.teammicroservicesgft.services.CartService;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;

import java.io.File;
import java.io.IOException;
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

    ObjectMapper objectMapper = new ObjectMapper();
    File file = new File("C:/MicroServicesGit/MicroServices/TeamMicroServiciesGFT/src/main/resources/products.json");

    private List<Product> products;


    @BeforeEach
    public void setup() {
        mockMvc = MockMvcBuilders.standaloneSetup(cartController).build();

        try {
            products = objectMapper.readValue(new File("C:/MicroServicesGit/MicroServices/TeamMicroServiciesGFT/src/main/resources/products.json"), new TypeReference< List<Product>>(){});
        } catch (IOException e) {
            e.printStackTrace();  
        }
        
    }

    
    @Test
    public void testCreateCart() throws Exception {
        Cart mockCart = new Cart();
        mockCart.setCartId(1);
        mockCart.setUserId(1);
        mockCart.setProducts(products);
        
        when(cartService.createCart(any(Cart.class))).thenReturn(mockCart);
        
        Cart postCart = new Cart();
        postCart.setUserId(1);
        postCart.setProducts(products);
        
        mockMvc.perform(post("/cart/createcart")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(postCart)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.cartId", is(1)))
                .andExpect(jsonPath("$.userId", is(1)))
                .andExpect(jsonPath("$.products", is(postCart.getProducts())));
         
        verify(cartService, times(1)).createCart(any(Cart.class));
    }

    @Test
    public void testUpdateCart() throws Exception {
        Cart mockCart = new Cart();
        mockCart.setCartId(1);
        mockCart.setUserId(1);
        mockCart.setProducts(products);
        
        when(cartService.updateCart(any(Cart.class))).thenReturn(mockCart);
        
        Cart putCart = new Cart();
        putCart.setCartId(1);
        putCart.setUserId(1);
        putCart.setProducts(products);
        
        mockMvc.perform(put("/cart/updatecart")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(putCart)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.cartId", is(1)))
                .andExpect(jsonPath("$.userId", is(1)))
                .andExpect(jsonPath("$.products", is(products)));
        
        verify(cartService, times(1)).updateCart(any(Cart.class));
    }

    @Test
    public void testFindAllCarts() throws Exception {
        Cart mockCart1 = new Cart();
        mockCart1.setCartId(1);
        mockCart1.setUserId(1);
        mockCart1.setProducts(products);
    
        Cart mockCart2 = new Cart();
        mockCart2.setCartId(2);
        mockCart2.setUserId(2);
        mockCart2.setProducts(products);
    
        List<Cart> mockCartList = new ArrayList<>();
    mockCartList.add(mockCart1);
    mockCartList.add(mockCart2);
    
    when(cartService.findAllCarts()).thenReturn(mockCartList);
    
    mockMvc.perform(get("/cart/api/cart"))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$", hasSize(2)))
            .andExpect(jsonPath("$[0].cartId", is(1)))
            .andExpect(jsonPath("$[0].userId", is(1)))
            .andExpect(jsonPath("$[0].products", is(products)))
            .andExpect(jsonPath("$[1].cartId", is(2)))
            .andExpect(jsonPath("$[1].userId", is(2)))
            .andExpect(jsonPath("$[1].products", is(products)));
    
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
