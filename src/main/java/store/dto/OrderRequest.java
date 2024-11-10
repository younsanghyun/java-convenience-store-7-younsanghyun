package store.dto;

import java.util.ArrayList;
import java.util.List;
import store.constant.ErrorMesage;
import store.exception.InvalidInputException;

public class OrderRequest {
    private final List<OrderItemRequest> items;

    public OrderRequest(List<OrderItemRequest> items) {
        this.items = new ArrayList<>(items);
        validateItems(items);

    }

    private void  validateItems(List<OrderItemRequest> items) {
        if (items == null || items.isEmpty()) {
            throw new InvalidInputException(ErrorMesage.INVALID_ORDER_ITEMS);
        }
    }

    public List<OrderItemRequest> getItems() {
        return new ArrayList<>(items);
    }
}
