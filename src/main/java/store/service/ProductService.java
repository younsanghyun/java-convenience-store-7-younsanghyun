package store.service;

import java.util.List;
import store.constant.ErrorMesage;
import store.domain.product.Product;
import store.exception.InvalidInputException;
import store.repository.ProductRepository;

public class ProductService {
    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    private List<Product> getProduct() {
        return productRepository.findAll();
    }

    public Product findByName(String name) {
        return productRepository.findByName(name)
                .orElseThrow(() -> new InvalidInputException(ErrorMesage.PRODUCT_NOT_FOUND));
    }
}
