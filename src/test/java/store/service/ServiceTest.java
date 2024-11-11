package store.service;

import static camp.nextstep.edu.missionutils.test.Assertions.assertSimpleTest;
import static org.assertj.core.api.Assertions.assertThat;

import camp.nextstep.edu.missionutils.test.NsTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import store.Application;
import store.constant.ErrorMesage;


class ServiceTest extends NsTest {
    @Nested
    @DisplayName("주문 서비스 테스트")
    class OrderServiceTest {
        @Test
        void 단일_상품_주문() {
            assertSimpleTest(() -> {
                run("[물-1]", "N", "N");
                assertThat(output())
                        .contains("물\t\t1\t500")
                        .contains("총구매액\t\t1\t500");
            });
        }
    }

    @Test
    @DisplayName("탄산2+1 프로모션 적용")
    void 탄산2plus1_프로모션_적용() {
        assertSimpleTest(() -> {
            run("[콜라-3]", "N", "N");
            assertThat(output())
                    .contains("=============증\t정===============")
                    .contains("콜라\t\t1");
        });
    }

    @Test
    @DisplayName("잘못된 멤버십 입력")
    void 멤버십_입력_형식_검증() {
        assertSimpleTest(() -> {
            runException("[콜라-1]", "y", "N");
            assertThat(output()).contains(ErrorMesage.INVALID_YES_NO);
        });
    }

    @Test
    @DisplayName("잘못된 주문 형식")
    void 잘못된_주문_형식() {
        assertSimpleTest(() -> {
            runException("물-1", "N", "N");
            assertThat(output()).contains(ErrorMesage.INVALID_INPUT_FORMAT);
        });
    }

    @Test
    @DisplayName("존재하지 않는 상품 구매 시도")
    void 존재하지_않는_상품_구매_시도() {
        assertSimpleTest(() -> {
            runException("[존재안함-1]", "N", "N");
            assertThat(output()).contains(ErrorMesage.PRODUCT_NOT_FOUND);
        });
    }

    @Test
    @DisplayName("멤버십 할인 테스트")
    void 멤버십_할인_테스트() {
        assertSimpleTest(() -> {
            run("[물-1]", "Y", "N");
            assertThat(output())
                    .contains("멤버십할인")
                    .contains("-150");
        });
    }

    @Test
    @DisplayName("멤버십 최대 할인 적용")
    void 멤버십_최대_할인_적용() {
        assertSimpleTest(() -> {
            run("[정식도시락-8]", "Y", "N");
            assertThat(output())
                    .contains("멤버십할인")
                    .contains("-8,000");
        });
    }

    @Override
    public void runMain() {
        Application.main(new String[]{});
    }
}