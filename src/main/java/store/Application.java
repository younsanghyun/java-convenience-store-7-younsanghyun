package store;

import store.config.AppConfig;
import store.controller.OrderController;

public class Application {
    public static void main(String[] args) {
        try {
            AppConfig appConfig = new AppConfig();
            OrderController orderController = appConfig.orderController();
            orderController.start();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}