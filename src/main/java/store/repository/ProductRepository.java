package store.repository;

import java.util.List;
import java.util.Optional;
import store.domain.product.Product;

public interface ProductRepository {
    List<Product> findAll();

    Optional<Product> findByName(String name);
}


