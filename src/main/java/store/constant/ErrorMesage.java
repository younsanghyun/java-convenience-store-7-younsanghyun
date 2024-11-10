package store.constant;

public class ErrorMesage {
    private ErrorMesage() {}

    public static final String INVALID_INPUT_FORMAT = "[ERROR] 올바르지 않은 형식으로 입력했습니다. 다시 입력해 주세요.";
    public static final String INVALID_YES_NO = "[ERROR] Y 또는 N만 입력 가능합니다. 다시 입력해 주세요.";
    public static final String PRODUCT_NOT_FOUND = "[ERROR] 존재하지 않는 상품입니다. 다시 입력해 주세요.";
    public static final String OUT_OF_STOCK = "[ERROR] 재고 수량을 초과하여 구매할 수 없습니다. 다시 입력해 주세요.";
    public static final String INVALID_QUANTITY = "[ERROR] 수량은 1개 이상이어야 합니다. 다시 입력해 주세요.";
    public static final String INVALID_PRODUCT_NAME = "[ERROR] 올바르지 않은 상품명입니다. 다시 입력해 주세요.";
    public static final String INVALID_ORDER_ITEMS = "[ERROR] 주문 상품 목록이 비어있습니다. 다시 입력해 주세요.";
}
