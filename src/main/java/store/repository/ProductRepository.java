package store.repository;

import java.util.List;
import store.domain.product.Product;

public class ProductRepository {
    public List<Product> findAll() {
        return null;
    }

    public <T> ScopedValue<T> findByName(String name) {
    }
}
