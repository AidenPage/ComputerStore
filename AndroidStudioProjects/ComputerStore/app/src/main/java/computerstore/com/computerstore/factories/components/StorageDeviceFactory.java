package computerstore.com.computerstore.factories.components;

/**
 * Created by Aidem on 2016/04/17.
 */

import computerstore.com.computerstore.domain.components.StorageDevice;
import java.util.UUID;

public class StorageDeviceFactory {

    public static StorageDevice createStorageDevice(String productNumber,int stock, String description,double price) {
        StorageDevice  cpu = new StorageDevice
                .Builder()
                .productNumber(productNumber)
                .stock(stock)
                .description(description)
                .price(price)
                .build();
        return cpu;
    }
}
