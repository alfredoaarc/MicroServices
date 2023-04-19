package microservicesgft.teammicroservicesgft.models;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.*;

public class Product {

  private String name;


  public Product(String name) {
    this.name = name;
  }

  public Product() {
  }


  @Override
  public String toString() {
      return "Product{" +
              ", name='" + name + '\'' +
              '}';
  }
}
