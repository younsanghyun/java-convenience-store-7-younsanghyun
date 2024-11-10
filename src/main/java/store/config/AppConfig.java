package store.config;

import java.time.Clock;
import store.controller.OrderController;
import store.repository.FileProductRepository;
import store.repository.FilePromotionRepository;
import store.repository.ProductRepository;
import store.repository.PromotionRepository;
import store.service.DiscountService;
import store.service.OrderService;
import store.service.ProductService;
import store.service.PromotionService;
import store.validator.InputValidator;
import store.validator.ProductValidator;
import store.view.InputView;
import store.view.OutputView;

public class AppConfig {
    private final Clock clock;

    public AppConfig() {
        this.clock = Clock.systemDefaultZone();
    }

    public AppConfig(Clock clock) {
        this.clock = clock;
    }

    public OrderController orderController() {
        return new OrderController(
                orderService(),
                inputView(),
                outputView(),
                productService()
        );
    }

    public OrderService orderService() {
        return new OrderService(
                productService(),
                promotionService(),
                discountService(),
                productValidator()
        );
    }

    public ProductService productService() {
        return new ProductService(productRepository());
    }

    public PromotionService promotionService() {
        return new PromotionService(promotionRepository(), clock);
    }

    public DiscountService discountService() {
        return new DiscountService();
    }

    public ProductRepository productRepository() {
        return new FileProductRepository();
    }

    public PromotionRepository promotionRepository() {
        return new FilePromotionRepository();
    }

    private ProductValidator productValidator() {
        return new ProductValidator();
    }

    private InputValidator inputValidator() {
        return new InputValidator();
    }

    private InputView inputView() {
        return new InputView(inputValidator());
    }

    private OutputView outputView() {
        return new OutputView();
    }
}

