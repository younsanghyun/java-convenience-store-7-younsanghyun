package store.service.order;

import java.util.List;
import java.util.stream.Collectors;
import store.domain.order.OrderLine;
import store.domain.receipt.Receipt;
import store.dto.OrderRequest;
import store.service.product.ProductService;
import store.viewhandler.ViewHandler;

public class OrderService {
    private final OrderValidationService orderValidationService;
    private final OrderProcessingService orderProcessingService;
    private final OrderPricingService orderPricingService;
    private final ProductService productService;

    public OrderService(
            OrderValidationService orderValidationService,
            OrderProcessingService orderProcessingService,
            OrderPricingService orderPricingService,
            ProductService productService
    ) {
        this.orderValidationService = orderValidationService;
        this.orderProcessingService = orderProcessingService;
        this.orderPricingService = orderPricingService;
        this.productService = productService;
    }

    public Receipt createOrder(OrderRequest request, boolean useMembership, ViewHandler viewHandler) {
        orderValidationService.validateOrder(request);
        List<OrderLine> orderLines = createOrderLines(request, viewHandler);
        return orderPricingService.calculateFinalPrice(orderLines, useMembership);
    }

    private List<OrderLine> createOrderLines(OrderRequest request, ViewHandler viewHandler) {
        return request.getItems().stream()
                .map(item -> {
                    var product = productService.findByName(item.getProductName());
                    if (product.hasPromotion()) {
                        return orderProcessingService.processOrder(item, viewHandler);
                    }
                    return new OrderLine(product, item.getQuantity());
                })
                .collect(Collectors.toList());
    }
}