package computerstore.com.computerstore.factories.components;

import org.junit.Test;
import computerstore.com.computerstore.factories.components.ChassisFactory;
import computerstore.com.computerstore.domain.components.Chassis;
import junit.framework.Assert;
/**
 * Created by Aidem on 2016/04/17.
 */
public class ChassisTest {

    private ChassisFactory factory;

    @Test
    public void testChassisCreation() throws Exception {
        Chassis chassis = factory.createChassis("SGC-2100-KWN1",50,"COOLERMASTER CM STORM SCOUT 2",1699.00);
        Assert.assertEquals(chassis.getDescription(),"COOLERMASTER CM STORM SCOUT 2");
        Assert.assertEquals(chassis.getProductNumber(),"SGC-2100-KWN1");
    }

    @Test
    public void testChassisUpdate() throws Exception {
        Chassis chassis = factory.createChassis("SGC-2100-KWN1",50,"COOLERMASTER CM STORM SCOUT 2",1699.00);
        Assert.assertEquals(chassis.getDescription(),"COOLERMASTER CM STORM SCOUT 2");
        Assert.assertEquals(chassis.getProductNumber(),"SGC-2100-KWN1");

        // Updated Description

        Chassis updateChassis = new Chassis.Builder()
                .Chassis(chassis)
                .price(2000.00)
                .build();

        Assert.assertEquals(updateChassis.getPrice(),2000.00);
        Assert.assertEquals(chassis.getProductNumber(),updateChassis.getProductNumber());




    }
}
