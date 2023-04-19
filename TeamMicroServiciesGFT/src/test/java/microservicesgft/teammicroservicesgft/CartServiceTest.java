package microservicesgft.teammicroservicesgft;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.assertj.core.util.Arrays;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import microservicesgft.teammicroservicesgft.models.Cart;
import microservicesgft.teammicroservicesgft.models.Product;
import microservicesgft.teammicroservicesgft.repositories.CartRepository;
import microservicesgft.teammicroservicesgft.services.CartService;

@RunWith(MockitoJUnitRunner.class)
public class CartServiceTest {
    @InjectMocks
    private CartService cartService;
    @Mock
    private CartRepository cartRepository;

   

    List<Cart>carts =new ArrayList<>();
   

    @BeforeEach
    void setUp(){
        List<Product> products =new ArrayList<>(){{
        add (new Product("Iphone"));
        add (new Product("Mac"));
        }};
        carts.add(new Cart(3,5,products));
        carts.add(new Cart(1,4,products));
        when(cartRepository.findAll()).thenReturn(carts);
   }

  @Test
  public void findAllCarts(){
    List<Cart>result=cartService.findAllCarts();
    Object[]cartsArr=carts.toArray();
    Object[] resultArr=result.toArray();
        assertArrayEquals(cartsArr,resultArr);

  }


}



    

