package computerstore.com.computerstore.factories.components;

/**
 * Created by Aidem on 2016/04/17.
 */

import computerstore.com.computerstore.domain.components.PCU;
import java.util.UUID;

public class PCUFactory {

    public static PCU createPCU(String productNumber,int stock, String description,double price) {
        PCU  cpu = new PCU
                .Builder()
                .productNumber(productNumber)
                .stock(stock)
                .description(description)
                .price(price)
                .build();
        return cpu;
    }
}
