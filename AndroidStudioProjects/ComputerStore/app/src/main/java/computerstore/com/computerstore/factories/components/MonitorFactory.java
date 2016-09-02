package computerstore.com.computerstore.factories.components;

/**
 * Created by Aidem on 2016/04/17.
 */
import computerstore.com.computerstore.domain.components.Monitor;
import java.util.UUID;

public class MonitorFactory {

    public static Monitor createMonitor(String productNumber,int stock, String description,double price) {
        Monitor  cpu = new Monitor
                .Builder()
                .productNumber(productNumber)
                .stock(stock)
                .description(description)
                .price(price)
                .build();
        return cpu;
    }

}

