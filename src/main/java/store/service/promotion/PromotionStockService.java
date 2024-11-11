package store.service.promotion;

import java.util.List;
import store.constant.ErrorMesage;
import store.domain.product.Product;
import store.domain.promotion.PromotionStock;
import store.exception.InvalidInputException;
import store.repository.ProductRepository;

public class PromotionStockService {
    private final ProductRepository productRepository;

    public PromotionStockService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public PromotionStock findPromotionStock(String productName) {
        List<Product> products = productRepository.findAll();
        Product promotionProduct = findProductWithPromotion(products, productName);
        Product regularProduct = findRegularProduct(products, productName);

        return new PromotionStock(promotionProduct, regularProduct);
    }

    private Product findProductWithPromotion(List<Product> products, String productName) {
        return products.stream().filter(p -> p.getName().equals(productName)).filter(Product::hasPromotion).findFirst()
                .orElseThrow(() -> new InvalidInputException(ErrorMesage.PRODUCT_NOT_FOUND));
    }

    private Product findRegularProduct(List<Product> products, String productName) {
        return products.stream().filter(p -> p.getName().equals(productName)).filter(p -> !p.hasPromotion()).findFirst()
                .orElseThrow(() -> new InvalidInputException(ErrorMesage.PRODUCT_NOT_FOUND));
    }
}

