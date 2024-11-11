package store.repository;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import store.constant.ErrorMesage;
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
            return parseProductsWithNoStock(lines.subList(1, lines.size()));
        } catch (IOException e) {
            throw new RuntimeException(ErrorMesage.PRODUCT_FILE_UNREADABLE);
        }
    }

    private List<String> readLines() throws IOException {
        return Files.readAllLines(Paths.get(PRODUCT_FILE_PATH));
    }

    private List<Product> parseProductsWithNoStock(List<String> lines) {
        List<Product> result = new ArrayList<>();
        for(String line : lines) {
            addProductWithNoStock(result, parseProduct(line));
        }
        return result;
    }

    private void addProductWithNoStock(List<Product> products, Product product) {
        products.add(product);
        if(needsNoStockProduct(product)) {
            products.add(createNoStockProduct(product));
        }
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

    private boolean needsNoStockProduct(Product product) {
        return "MD추천상품".equals(product.getPromotionName()) ||
                "탄산2+1".equals(product.getPromotionName());
    }

    private Product createNoStockProduct(Product product) {
        return new Product(
                product.getName(),
                product.getPrice(),
                0,
                product.getPromotionName()
        );
    }
}