package store.service;

import java.util.List;
import java.util.stream.Collectors;
import store.domain.order.OrderLine;
import store.domain.product.Product;
import store.domain.promotion.FreeProduct;
import store.domain.receipt.Receipt;
import store.dto.OrderItemRequest;
import store.dto.OrderRequest;
import store.validator.ProductValidator;

public class OrderService {
    private final ProductService productService;
    private final PromotionService promotionService;
    private final DiscountService discountService;
    private final ProductValidator productValidator;

    public OrderService(
            ProductService productService,
            PromotionService promotionService,
            DiscountService discountService,
            ProductValidator productValidator
    ) {
        this.productService = productService;
        this.promotionService = promotionService;
        this.discountService = discountService;
        this.productValidator = productValidator;
    }

    public Receipt createOrder(OrderRequest request, boolean useMembership) {
        validateOrder(request);
        List<OrderLine> orderLines = createOrderLines(request);
        List<FreeProduct> freeProducts = promotionService.applyPromotions(orderLines);

        int totalAmount = calculateTotalAmount(orderLines);
        int promotionDiscount = calculatePromotionDiscount(freeProducts);
        int membershipDiscount = calculateMembershipDiscount(totalAmount - promotionDiscount, useMembership);

        return new Receipt(orderLines, freeProducts, totalAmount, promotionDiscount, membershipDiscount);
    }

    private void validateOrder(OrderRequest request) {
        request.getItems().forEach(this::validateOrderItem);
    }

    private void validateOrderItem(OrderItemRequest item) {
        Product product = productService.findByName(item.getProductName());
        productValidator.validateProduct(product, item.getProductName());
        productValidator.validateQuantity(item.getQuantity());
        productValidator.validateStock(product, item.getQuantity());
    }

    private List<OrderLine> createOrderLines(OrderRequest request) {
        return request.getItems().stream()
                .map(this::createOrderLine)
                .collect(Collectors.toList());
    }

    private OrderLine createOrderLine(OrderItemRequest item) {
        Product product = productService.findByName(item.getProductName());
        return new OrderLine(product, item.getQuantity());
    }

    private int calculateTotalAmount(List<OrderLine> orderLines) {
        return orderLines.stream()
                .mapToInt(OrderLine::getAmount)
                .sum();
    }

    private int calculatePromotionDiscount(List<FreeProduct> freeProducts) {
        return freeProducts.stream()
                .mapToInt(FreeProduct::getDiscountAmount)
                .sum();
    }

    private int calculateMembershipDiscount(int amountAfterPromotion, boolean useMembership) {
        return useMembership ? discountService.calculateMembershipDiscount(amountAfterPromotion) : 0;
    }
}

