package store.validator;

import store.constant.ErrorMesage;
import store.exception.InvalidInputException;

public class InputValidator {
    private static final String ORDER_FORMAT = "\\[([^-]+-\\d+)\\](,\\[([^-]+-\\d+)\\])*";
    private static final String YES_NO_FORMAT = "[YN]";

    public void validateOrderFormat(String input) {
        if (!matches(input, ORDER_FORMAT)) {
            throw new InvalidInputException(ErrorMesage.INVALID_INPUT_FORMAT);
        }
    }

    public void validateYesNo(String input) {
        if (!matches(input, YES_NO_FORMAT)) {
            throw new InvalidInputException(ErrorMesage.INVALID_YES_NO);
        }
    }

    private boolean matches(String input, String regex) {
        return input != null && input.matches(regex);
    }
}