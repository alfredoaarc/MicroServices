package microservicesgft.teammicroservicesgft.models;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;


@Entity
@Table(name = "cart")
public class Cart {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cart_id")
    private int cartId;

    @Column(name = "user_id")
    private int userId;

    @Column(name = "products", columnDefinition = "TEXT")
    private List<Product> products;

    public Cart() {
    }

    public Cart(int cartId, int userId, List<Product> products) {
        this.cartId = cartId;
        this.userId = userId;
        this.products = products;
    }

    public int getCartId() {
        return this.cartId;
    }

    public void setCartId(int cartId) {
        this.cartId = cartId;
    }

    public int getUserId() {
        return this.userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public List<Product> getProducts() {
        return this.products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }



    @Override
    public String toString() {
        return "{" +
            " cartId='" + getCartId() + "'" +
            ", userId='" + getUserId() + "'" +
            ", products='" + getProducts() + "'" +
            "}";
    }
 
    

}
   

