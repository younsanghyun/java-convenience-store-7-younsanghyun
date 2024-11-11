package store.service;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.assertj.core.api.Assertions.assertThatCode;
import camp.nextstep.edu.missionutils.test.NsTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import store.constant.ErrorMesage;
import store.domain.product.Product;
import store.exception.InvalidInputException;
import store.exception.OutOfStockException;
import store.validator.ProductValidator;

class ValidatorTest extends NsTest {
    @Nested
    @DisplayName("상품 검증 테스트")
    class ProductValidatorTest {
        private final ProductValidator validator = new ProductValidator();
        private final Product product = new Product("테스트상품", 1000, 5, null);

        @Test
        @DisplayName("상품 존재 여부 - null 상품")
        void 존재하지_않는_상품() {
            assertThatThrownBy(() -> validator.validateProduct(null, "없는상품"))
                    .isInstanceOf(InvalidInputException.class)
                    .hasMessage(ErrorMesage.PRODUCT_NOT_FOUND);
        }

        @Test
        @DisplayName("재고 검증 - 재고 초과 주문")
        void 재고_초과_주문() {
            assertThatThrownBy(() -> validator.validateStock(product, 6))
                    .isInstanceOf(OutOfStockException.class)
                    .hasMessage(ErrorMesage.OUT_OF_STOCK);
        }

        @Test
        @DisplayName("재고 검증 - 재고 범위 내 주문")
        void 재고_범위_내_주문() {
            assertThatCode(() -> validator.validateStock(product, 5))
                    .doesNotThrowAnyException();
        }
    }

    @Override
    protected void runMain() {

    }
}