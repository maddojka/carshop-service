package com.soroko.carshop.logger;

import java.io.IOException;
import java.util.logging.*;

/**
 * @author yuriy.soroko
 */
public class CarShopLogger {
    public static final Logger LOGGER = Logger.getGlobal();

    public CarShopLogger() {
        LOGGER.setUseParentHandlers(false);
    }

    public void setFileHandler() {

        FileHandler fileHandler = null;
        try {
            fileHandler = new FileHandler("CarShop.log");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        LOGGER.addHandler(fileHandler);
    }

    public void setConsoleHandler() {
        ConsoleHandler consoleHandler = new ConsoleHandler();
        LOGGER.addHandler(new StreamHandler(System.out, new SimpleFormatter()));
        LOGGER.setUseParentHandlers(true);
    }
}
