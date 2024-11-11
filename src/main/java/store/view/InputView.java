package store.view;

import camp.nextstep.edu.missionutils.Console;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import store.constant.ErrorMesage;
import store.dto.OrderItemRequest;
import store.dto.OrderRequest;
import store.exception.InvalidInputException;
import store.validator.InputValidator;

public class InputView {
    private final InputValidator inputValidator;

    public InputView(InputValidator inputValidator) {
        this.inputValidator = inputValidator;
    }

    public OrderRequest readOrder() {
        System.out.println("구매하실 상품명과 수량을 입력해 주세요. (예: [사이다-2],[감자칩-1])");
        String input = Console.readLine();
        inputValidator.validateOrderFormat(input);
        return parseOrder(input);
    }

    public boolean readUseMembership() {
        System.out.println("멤버십 할인을 받으시겠습니까? (Y/N)");
        String input = Console.readLine();
        inputValidator.validateYesNo(input);
        return "Y".equalsIgnoreCase(input);
    }

    public boolean readContinueShopping() {
        System.out.println("구매하고 싶은 다른 상품이 있나요? (Y/N)");
        String input = Console.readLine();
        inputValidator.validateYesNo(input);
        return "Y".equalsIgnoreCase(input);
    }

    public boolean readAcceptRegularPrice(String productName, int quantity) {
        System.out.printf("현재 %s %d개는 프로모션 할인이 적용되지 않습니다. 그래도 구매하시겠습니까? (Y/N)\n",
                productName, quantity);
        String input = Console.readLine();
        inputValidator.validateYesNo(input);
        return "Y".equalsIgnoreCase(input);
    }

    private OrderRequest parseOrder(String input) {
        return new OrderRequest(parseOrderItems(input));
    }

    private List<OrderItemRequest> parseOrderItems(String input) {
        return Arrays.stream(input.split(","))
                .map(this::parseOrderItem)
                .collect(Collectors.toList());
    }

    private OrderItemRequest parseOrderItem(String item) {
        Matcher matcher = Pattern.compile("\\[(.*?)-(\\d+)\\]").matcher(item);
        if (!matcher.find()) {
            throw new InvalidInputException(ErrorMesage.INVALID_INPUT_FORMAT);
        }
        return new OrderItemRequest(matcher.group(1), Integer.parseInt(matcher.group(2)));
    }
}