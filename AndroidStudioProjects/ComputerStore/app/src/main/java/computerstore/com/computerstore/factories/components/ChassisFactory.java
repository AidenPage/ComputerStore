package computerstore.com.computerstore.factories.components;

/**
 * Created by Aidem on 2016/04/17.
 */
import computerstore.com.computerstore.domain.components.Chassis;
import java.util.UUID;

public class ChassisFactory {

    public static Chassis createChassis(String productNumber,int stock, String description,double price) {
        Chassis  chassis = new Chassis
                .Builder()
                .productNumber(productNumber)
                .stock(stock)
                .description(description)
                .price(price)
                .build();
        return chassis;
    }
}
