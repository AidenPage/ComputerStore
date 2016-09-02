package computerstore.com.computerstore.factories.components;

/**
 * Created by Aidem on 2016/04/17.
 */

import computerstore.com.computerstore.domain.components.Mainboard;
import java.util.UUID;

public class MainboardFactory {

    public static Mainboard createMainboard(String productNumber,int stock, String description,double price) {
        Mainboard  cpu = new Mainboard
                .Builder()
                .productNumber(productNumber)
                .stock(stock)
                .description(description)
                .price(price)
                .build();
        return cpu;
    }
}

