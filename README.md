# java-convenience-store-precourse

# 🛍️ W편의점

## 📋 구현할 기능 목록

### 1. 상품 관리

- [ ] 상품 정보 파일(products.md) 읽기
  - 상품명, 가격, 수량, 프로모션 정보 파싱
- [ ] 재고 관리
  - 일반 재고와 프로모션 재고 분리 관리
  - 주문 시 재고 차감
  - 재고 0개일 때 "재고 없음" 표시

### 2. 프로모션 관리

- [ ] 프로모션 정보 파일(promotions.md) 읽기
  - 프로모션명, 구매수량, 증정수량, 시작일, 종료일 파싱
- [ ] 프로모션 적용
  - 현재 날짜가 프로모션 기간인지 확인
  - 프로모션 재고 확인 후 혜택 적용
  - 프로모션 재고 부족 시 일반 재고로 전환

### 3. 주문 처리

- [ ] 상품 주문 입력 처리
  - [상품명-수량] 형식 검증
  - 여러 상품 동시 주문 처리
- [ ] 주문 유효성 검증
  - 존재하는 상품인지 확인
  - 주문 수량이 재고보다 적은지 확인

### 4. 할인 적용

- [ ] 프로모션 할인
  - N+1 증정 프로모션 계산
  - 프로모션 조건 충족 여부 확인
- [ ] 멤버십 할인
  - 프로모션 미적용 금액의 30% 할인
  - 최대 8,000원 한도 적용

### 5. 영수증 출력

- [ ] 구매 상품 출력
  - 상품명, 수량, 금액 정보
- [ ] 증정 상품 출력
  - 프로모션으로 증정되는 상품 정보
- [ ] 금액 정보 출력
  - 총구매액
  - 행사할인 금액
  - 멤버십할인 금액
  - 최종 결제 금액

### 6. 추가 주문 처리

- [ ] 추가 주문 여부 확인
- [ ] 갱신된 재고 상태로 상품 목록 출력
- [ ] 이전 주문과 독립적으로 새로운 주문 처리

### 7. 예외 처리

- [ ] 입력값 예외
  - 잘못된 형식 입력
  - 존재하지 않는 상품명
- [ ] 재고 예외
  - 재고 수량 초과 주문
  - 프로모션 재고 소진
- [ ] 할인 예외
  - 프로모션 기간이 아닌 경우
  - 멤버십 할인 한도 초과
