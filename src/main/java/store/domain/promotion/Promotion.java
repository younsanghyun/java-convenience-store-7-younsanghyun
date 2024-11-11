package store.domain.promotion;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class Promotion {
    private final String name;
    private final LocalDate startDate;
    private final LocalDate endDate;

    public Promotion(String name, LocalDate startDate, LocalDate endDate) {
        this.name = name;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public boolean isValid(LocalDateTime dateTime) {
        LocalDate date = dateTime.toLocalDate();
        return !date.isBefore(startDate) && !date.isAfter(endDate);
    }

    public String getName() {
        return name;
    }
}
