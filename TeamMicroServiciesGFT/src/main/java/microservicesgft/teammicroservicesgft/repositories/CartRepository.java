package microservicesgft.teammicroservicesgft.repositories;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import microservicesgft.teammicroservicesgft.models.Cart;

@Repository

public interface CartRepository extends JpaRepository <Cart, Integer> {

}
