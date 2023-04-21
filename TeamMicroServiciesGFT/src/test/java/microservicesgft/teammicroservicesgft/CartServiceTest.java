package microservicesgft.teammicroservicesgft;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import microservicesgft.teammicroservicesgft.models.Cart;
import microservicesgft.teammicroservicesgft.models.Product;
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
}
