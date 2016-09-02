package computerstore.com.computerstore.factories.components;

/**
 * Created by Aidem on 2016/04/17.
 */

import computerstore.com.computerstore.domain.components.DisplayCard;
import java.util.UUID;


public class DisplayCardFactory {

    public static DisplayCard createDisplayCard(String productNumber,int stock, String description,double price) {
        DisplayCard  cpu = new DisplayCard
                .Builder()
                .productNumber(productNumber)
                .stock(stock)
                .description(description)
                .price(price)
                .build();
        return cpu;
    }
}
