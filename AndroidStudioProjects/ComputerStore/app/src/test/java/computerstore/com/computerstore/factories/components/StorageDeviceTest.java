package computerstore.com.computerstore.factories.components;

/**
 * Created by Aidem on 2016/04/17.
 */

import org.junit.Test;
import computerstore.com.computerstore.factories.components.StorageDeviceFactory;
import computerstore.com.computerstore.domain.components.StorageDevice;
import junit.framework.Assert;

public class StorageDeviceTest {

    private StorageDeviceFactory factory;

    @Test
    public void testStorageDeviceCreation() throws Exception {
        StorageDevice storageDevice = factory.createStorageDevice("WD4003FZEX",50,"Western Digital 4TB WD4003FZEX",3699.00);
        Assert.assertEquals(storageDevice.getDescription(),"Western Digital 4TB WD4003FZEX");
        Assert.assertEquals(storageDevice.getProductNumber(),"WD4003FZEX");
    }

    @Test
    public void testStorageDeviceUpdate() throws Exception {
        StorageDevice storageDevice = factory.createStorageDevice("WD4003FZEX",50,"Western Digital 4TB WD4003FZEX",3699.00);
        Assert.assertEquals(storageDevice.getDescription(),"Western Digital 4TB WD4003FZEX");
        Assert.assertEquals(storageDevice.getProductNumber(),"WD4003FZEX");

        // Updated Description

        StorageDevice updateStorageDevice = new StorageDevice.Builder()
                .StorageDevice(storageDevice)
                .price(3999.00)
                .build();

        Assert.assertEquals(updateStorageDevice.getPrice(),3999.00);
        Assert.assertEquals(storageDevice.getProductNumber(),updateStorageDevice.getProductNumber());




    }
}
