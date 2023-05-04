package microservicesgft.teammicroservicesgft.services;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import com.fasterxml.jackson.databind.ObjectMapper;

import microservicesgft.teammicroservicesgft.models.Cart;
import microservicesgft.teammicroservicesgft.models.Product;
import microservicesgft.teammicroservicesgft.repositories.CartRepository;
import java.io.IOException;


import com.fasterxml.jackson.core.type.TypeReference;

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
    ObjectMapper objectMapper = new ObjectMapper();
    File file = new File("C:/MicroServicesGit/MicroServices/TeamMicroServiciesGFT/src/main/resources/products.json");



    List<Cart> carts = new ArrayList<>();
    List<Product> products;


    @BeforeEach
    void setup() {
try {
    products = objectMapper.readValue(new File("C:/MicroServicesGit/MicroServices/TeamMicroServiciesGFT/src/main/resources/products.json"), new TypeReference< List<Product>>(){});
} catch (IOException e) {
    e.printStackTrace();
}


    }
    @Test

    public void findAllCarts() {
    // Crear una lista de Cart para simular la respuesta del repositorio
    List<Cart> expectedCarts = new ArrayList<>();
    
    // Configurar el mock del repositorio para que devuelva la lista esperada
    when(cartRepository.findAll()).thenReturn(expectedCarts);
    
    // Llamar al método findAllCarts del servicio
    List<Cart> resultCarts = cartService.findAllCarts();
    
    // Verificar que la lista devuelta por el servicio es igual a la lista esperada
    assertEquals(expectedCarts, resultCarts);
    
    // Verificar que se llamó al método findAll del repositorio exactamente una vez
    verify(cartRepository, times(1)).findAll();
    }



    @Test
    public void createCart() {
    Cart newCart = new Cart(1, 10, products);
    when(cartRepository.save(any(Cart.class))).thenReturn(newCart);
    Cart savedCart = cartService.createCart(newCart);
    assertEquals(newCart, savedCart);
    verify(cartRepository, times(1)).save(any(Cart.class));
    }

    @Test

    public void updateCart_existingCart() {
    Cart cartToUpdate = new Cart(1, 10, products);
    when(cartRepository.existsById(cartToUpdate.getCartId())).thenReturn(true);
    when(cartRepository.save(any(Cart.class))).thenReturn(cartToUpdate);
    Cart updatedCart = cartService.updateCart(cartToUpdate);
    assertEquals(cartToUpdate, updatedCart);
    verify(cartRepository, times(1)).save(any(Cart.class));
    }

    @Test
    public void updateCart_nonExistingCart() {
    Cart cartToUpdate = new Cart(99, 10, products);
    when(cartRepository.existsById(cartToUpdate.getCartId())).thenReturn(false);
    assertThrows(ResponseStatusException.class, () -> cartService.updateCart(cartToUpdate),
        "Product does not exist");
    verify(cartRepository, never()).save(any(Cart.class));
    }

    @Test
    public void deleteCart_existingCart() {
    int cartId = 3;
    Cart cartToDelete = new Cart(cartId, 5, products);
    when(cartRepository.findById(cartId)).thenReturn(Optional.of(cartToDelete));
    doNothing().when(cartRepository).delete(cartToDelete);
    cartService.deleteCart(cartId);
    verify(cartRepository, times(1)).delete(cartToDelete);
    }

    @Test
    public void deleteCart_nonExistingCart() {
    int cartId = 99;
    when(cartRepository.findById(cartId)).thenReturn(Optional.empty());
    assertThrows(ResponseStatusException.class, () -> cartService.deleteCart(cartId),
        "Cart does not exist");
    verify(cartRepository, never()).delete(any(Cart.class));
}






}
