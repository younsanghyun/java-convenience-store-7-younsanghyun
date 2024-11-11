package store.service;

import static org.assertj.core.api.Assertions.assertThat;

import camp.nextstep.edu.missionutils.test.NsTest;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Collections;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import store.domain.order.OrderLine;
import store.domain.product.Product;
import store.domain.promotion.FreeProduct;
import store.domain.promotion.Promotion;
import store.domain.receipt.Receipt;

class DomainTest extends NsTest {
    @Test
    @DisplayName("프로모션 존재 여부 확인")
    void 프로모션_존재_여부() {
        Product noPromotion = new Product("물", 500, 10, "null");
        Product withPromotion = new Product("콜라", 1000, 10, "탄산2+1");

        assertThat(noPromotion.hasPromotion()).isFalse();
        assertThat(withPromotion.hasPromotion()).isTrue();
    }

    @Test
    @DisplayName("증정 상품이 있는 영수증 생성")
    void 증정_상품_영수증() {
        Product product = new Product("콜라", 1000, 10, "탄산2+1");
        OrderLine orderLine = new OrderLine(product, 3);
        FreeProduct freeProduct = new FreeProduct(product, 1);

        Receipt receipt = new Receipt(
                Collections.singletonList(orderLine),
                Collections.singletonList(freeProduct),
                3000,
                1000,
                0
        );

        assertThat(receipt.getFreeProducts()).hasSize(1);
        assertThat(receipt.getPromotionDiscountAmount()).isEqualTo(1000);
        assertThat(receipt.getFinalAmount()).isEqualTo(2000);
    }

    @Nested
    @DisplayName("프로모션 도메인 테스트")
    class PromotionTest {
        @Test
        @DisplayName("프로모션 기간 경계값 테스트")
        void 프로모션_기간_경계값() {
            LocalDate startDate = LocalDate.of(2024, 11, 1);
            LocalDate endDate = LocalDate.of(2024, 11, 30);
            Promotion promotion = new Promotion("반짝할인", startDate, endDate);

            assertThat(promotion.isValid(LocalDateTime.of(2024, 11, 1, 0, 0)))
                    .isTrue();
            assertThat(promotion.isValid(LocalDateTime.of(2024, 11, 30, 23, 59)))
                    .isTrue();
            assertThat(promotion.isValid(LocalDateTime.of(2024, 10, 31, 23, 59)))
                    .isFalse();
            assertThat(promotion.isValid(LocalDateTime.of(2024, 12, 1, 0, 0)))
                    .isFalse();
        }
    }

    @Override
    protected void runMain() {

    }
}
