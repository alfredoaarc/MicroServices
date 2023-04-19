package microservicesgft.teammicroservicesgft.models;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.*;

@Builder
@Entity
@Table(name = "cart")
public class Cart {
  @Id
  private int cartId;
  private int userId;
  private List<Product>products;

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
      StringBuilder sb = new StringBuilder();
      sb.append("Cart ID: ").append(cartId).append("\n");
      sb.append("User ID: ").append(userId).append("\n");
      sb.append("Products:\n");
      for (Product p : products) {
          sb.append("\t").append(p).append("\n");
      }
      return sb.toString();
  }
}



  

