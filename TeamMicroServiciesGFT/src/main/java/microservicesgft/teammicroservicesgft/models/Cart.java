package microservicesgft.teammicroservicesgft.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

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
    private String products;

    public Cart() {}

    public Cart(int cartId, int userId, String products) {
        this.cartId = cartId;
        this.userId = userId;
        this.products = products;
    }

    public int getCartId() {
        return cartId;
    }

    public void setCartId(int cartId) {
        this.cartId = cartId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getProducts() {
        return products;
    }

    public void setProducts(String products) {
        this.products = products;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Cart ID: ").append(cartId).append("\n");
        sb.append("User ID: ").append(userId).append("\n");
        sb.append("Products: ").append(products);
        return sb.toString();
    }
}


