package computerstore.com.computerstore.factories.components;

/**
 * Created by Aidem on 2016/04/17.
 */

import org.junit.Test;
import computerstore.com.computerstore.factories.components.OpticalDevicesFactory;
import computerstore.com.computerstore.domain.components.OpticalDevices;
import junit.framework.Assert;

public class OpticalDevicesTest {

    private OpticalDevicesFactory factory;

    @Test
    public void testOpticalDevicesCreation() throws Exception {
        OpticalDevices opticalDevices = factory.createOpticalDevices("GH24LS70",50,"Internal SATA 24x Super-Multi DVD Rewriter",199.00);
        Assert.assertEquals(opticalDevices.getDescription(),"Internal SATA 24x Super-Multi DVD Rewriter");
        Assert.assertEquals(opticalDevices.getProductNumber(),"GH24LS70");
    }

    @Test
    public void testOpticalDevicesUpdate() throws Exception {
        OpticalDevices opticalDevices = factory.createOpticalDevices("GH24LS70",50,"Internal SATA 24x Super-Multi DVD Rewriter",199.00);
        Assert.assertEquals(opticalDevices.getDescription(),"Internal SATA 24x Super-Multi DVD Rewriter");
        Assert.assertEquals(opticalDevices.getProductNumber(),"GH24LS70");

        // Updated Description

        OpticalDevices updateOpticalDevices = new OpticalDevices.Builder()
                .OpticalDevices(opticalDevices)
                .price(250.00)
                .build();

        Assert.assertEquals(updateOpticalDevices.getPrice(),250.00);
        Assert.assertEquals(opticalDevices.getProductNumber(),updateOpticalDevices.getProductNumber());




    }

}
