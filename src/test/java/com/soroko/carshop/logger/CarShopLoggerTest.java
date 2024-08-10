package com.soroko.carshop.logger;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

/**
 * @author yuriy.soroko
 * @version 1.0
 */
public class CarShopLoggerTest {

    @Test
    @DisplayName("Check file handler is OK")
    public void CarShopLogger_FileHandler_isOk() {
        CarShopLogger carShopLogger = new CarShopLogger();
        carShopLogger.setFileHandler();
    }

    @Test
    @DisplayName("Check console handler is OK")
    public void CarShopLogger_Console_isOk() {
        CarShopLogger carShopLogger = new CarShopLogger();
        carShopLogger.setConsoleHandler();
    }
}
