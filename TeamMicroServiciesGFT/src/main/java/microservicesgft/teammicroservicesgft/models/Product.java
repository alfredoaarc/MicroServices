package microservicesgft.teammicroservicesgft.models;
import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.Builder;


@Entity
@Table(name = "product")
@Builder
public class Product implements Serializable {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "item_id")
  private int itemId;
  @NotNull
  private String name;
  @NotNull
  private String description;
  @NotNull
  private double price;
  @NotNull
  @Min(0)
  private int stock;

  public Product() {}
     
  public Product(
    int itemId,
    String name,
    String description,
    double price,
    int stock
  ) {
    this.itemId = itemId;
    this.name = name;
    this.description = description;
    this.price = price;
    this.stock = stock;
  }

  public int getStock() {
    return this.stock;
  }

  public void setStock(int stock) {
    this.stock = stock;
  }

  public int getItemId() {
    return itemId;
  }

  public void setItemId(int itemId) {
    this.itemId = itemId;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public double getPrice() {
    return price;
  }

  public void setPrice(double price) {
    this.price = price;
  }
  @Override
  public String toString() {
      try {
          return new ObjectMapper().writeValueAsString(this);
      } catch (JsonProcessingException e) {
          e.printStackTrace();
          return null;
      }
  }

  

}
  