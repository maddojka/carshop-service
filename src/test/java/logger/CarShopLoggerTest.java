package logger;

import com.soroko.carshop.logger.CarShopLogger;
import org.junit.jupiter.api.Test;

/**
 * @author yuriy.soroko
 */
public class CarShopLoggerTest {

    @Test
    public void CarShopLogger_FileHandler_isOk() {
        CarShopLogger carShopLogger = new CarShopLogger();
        carShopLogger.setFileHandler();
    }

    @Test
    public void CarShopLogger_Console_isOk() {
        CarShopLogger carShopLogger = new CarShopLogger();
        carShopLogger.setConsoleHandler();
    }
}
