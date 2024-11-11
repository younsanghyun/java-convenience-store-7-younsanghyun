package store;

import store.config.AppConfig;
import store.controller.MainController;

public class Application {
    public static void main(String[] args) {
        try {
            AppConfig appConfig = new AppConfig();
            MainController mainController = appConfig.mainController();
            mainController.start();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}