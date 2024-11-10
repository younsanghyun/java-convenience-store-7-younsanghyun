package store.repository;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import store.domain.promotion.Promotion;

public class FilePromotionRepository implements PromotionRepository {
    private static String PROMOTION_FILE_PATH = "src/main/resources/promotions.md";
    private static final String DELIMITER = ",";

    private final List<Promotion> promotions;

    public FilePromotionRepository() {
        this.promotions = loadPromotions();
    }

    @Override
    public List<Promotion> findAll() {
        return new ArrayList<>(promotions);
    }

    @Override
    public Optional<Promotion> findByName(String name) {
        return promotions.stream()
                .filter(promotion -> promotion.getName().equals(name))
                .findFirst();
    }

    private List<Promotion> loadPromotions() {
        try {
            List<String> lines = readLines();
            return parsePromotions(lines.subList(1, lines.size()));
        } catch (IOException e) {
            throw new RuntimeException("[ERROR] 프로모션 파일을 읽을 수 없습니다.");
        }
    }

    private List<String> readLines() throws IOException {
        return Files.readAllLines(Paths.get(PROMOTION_FILE_PATH));
    }

    private List<Promotion> parsePromotions(List<String> lines) {
        return lines.stream()
                .map(this::parsePromotion)
                .collect(Collectors.toList());
    }

    private Promotion parsePromotion(String line) {
        String[] parts = line.split(DELIMITER);
        return new Promotion(
                parts[0].trim(),
                LocalDate.parse(parts[3].trim()),
                LocalDate.parse(parts[4].trim())
        );
    }
}