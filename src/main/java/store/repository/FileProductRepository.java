package store.repository;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import store.domain.product.Product;

public class FileProductRepository implements ProductRepository {
    private static final String PRODUCT_FILE_PATH = "src/main/resources/products.md";
    private static final String DELIMITER = ",";

    private final List<Product> products;

    public FileProductRepository() {
        this.products = loadProducts();
    }

    @Override
    public List<Product> findAll() {
        return new ArrayList<>(products);
    }

    @Override
    public Optional<Product> findByName(String name) {
        return products.stream()
                .filter(product -> product.getName().equals(name))
                .findFirst();
    }

    private List<Product> loadProducts() {
        try {
            List<String> lines = readLines();
            return parseProducts(lines.subList(1, lines.size()));
        } catch (IOException e) {
            throw new RuntimeException("[ERROR] 상품 파일을 읽을 수 없습니다.");
        }
    }

    private List<String> readLines() throws IOException {
        return Files.readAllLines(Paths.get(PRODUCT_FILE_PATH));
    }

    private List<Product> parseProducts(List<String> lines) {
        return lines.stream()
                .map(this::parseProduct)
                .collect(Collectors.toList());
    }

    private Product parseProduct(String line) {
        String[] parts = line.split(DELIMITER);
        return new Product(
                parts[0].trim(),
                Integer.parseInt(parts[1].trim()),
                Integer.parseInt(parts[2].trim()),
                parts[3].trim().equals("null") ? null : parts[3].trim()
        );
    }
}
