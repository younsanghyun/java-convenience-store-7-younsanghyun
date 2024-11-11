package store.config;

import store.controller.MainController;
import store.controller.OrderController;
import store.controller.ReceiptController;
import store.repository.FileProductRepository;
import store.repository.FilePromotionRepository;
import store.repository.ProductRepository;
import store.repository.PromotionRepository;
import store.service.DiscountService;
import store.service.OrderPricingService;
import store.service.OrderProcessingService;
import store.service.OrderService;
import store.service.OrderValidationService;
import store.service.ProductService;
import store.service.PromotionService;
import store.service.PromotionStockService;
import store.validator.InputValidator;
import store.validator.ProductValidator;
import store.view.InputView;
import store.view.OutputView;
import store.viewhandler.ViewHandler;

public class AppConfig {
    public MainController mainController() {
        return new MainController(
                orderController(),
                receiptController(),
                viewHandler()
        );
    }

    public OrderController orderController() {
        return new OrderController(
                orderService(),
                productService(),
                viewHandler()

        );
    }

    public ReceiptController receiptController() {
        return new ReceiptController(viewHandler());
    }

    public ViewHandler viewHandler() {
        return new ViewHandler(inputView(), outputView());
    }

    public OrderService orderService() {
        return new OrderService(
                orderValidationService(),
                orderProcessingService(),
                orderPricingService(),
                productService()
        );
    }

    public OrderValidationService orderValidationService() {
        return new OrderValidationService(
                productService(),
                productValidator()
        );
    }

    public OrderPricingService orderPricingService() {
        return new OrderPricingService(
                promotionService(),
                discountService()
        );
    }

    public ProductService productService() {
        return new ProductService(productRepository());
    }

    public PromotionService promotionService() {
        return new PromotionService(promotionRepository());
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

    public OrderProcessingService orderProcessingService() {
        return new OrderProcessingService(promotionStockService());
    }

    public PromotionStockService promotionStockService() {
        return new PromotionStockService(productRepository());
    }
}