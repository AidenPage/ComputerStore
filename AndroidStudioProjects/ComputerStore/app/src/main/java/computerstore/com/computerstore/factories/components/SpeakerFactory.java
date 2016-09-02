package computerstore.com.computerstore.factories.components;

/**
 * Created by Aidem on 2016/04/17.
 */

import computerstore.com.computerstore.domain.components.Speaker;
import java.util.UUID;

public class SpeakerFactory {

    public static Speaker createSpeaker(String productNumber,int stock, String description,double price) {
        Speaker  cpu = new Speaker
                .Builder()
                .productNumber(productNumber)
                .stock(stock)
                .description(description)
                .price(price)
                .build();
        return cpu;
    }

}

