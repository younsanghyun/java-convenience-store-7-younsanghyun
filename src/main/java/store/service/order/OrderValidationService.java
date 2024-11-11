package store.service.order;

import store.domain.product.Product;
import store.dto.OrderItemRequest;
import store.dto.OrderRequest;
import store.service.product.ProductService;
import store.validator.ProductValidator;

public class OrderValidationService {
    private final ProductService productService;
    private final ProductValidator productValidator;

    public OrderValidationService(ProductService productService, ProductValidator productValidator) {
        this.productService = productService;
        this.productValidator = productValidator;
    }

    public void validateOrder(OrderRequest request) {
        request.getItems().forEach(this::validateOrderItem);
    }

    private void validateOrderItem(OrderItemRequest item) {
        Product product = productService.findByName(item.getProductName());
        productValidator.validateProduct(product, item.getProductName());
        productValidator.validateQuantity(item.getQuantity());
        productValidator.validateStock(product, item.getQuantity());
    }
}

