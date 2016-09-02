package computerstore.com.computerstore.factories.components;

/**
 * Created by Aidem on 2016/04/17.
 */

import org.junit.Test;
import computerstore.com.computerstore.factories.components.MainboardFactory;
import computerstore.com.computerstore.domain.components.Mainboard;
import junit.framework.Assert;

public class MainboardTest {

    private MainboardFactory factory;

    @Test
    public void testMainboardCreation() throws Exception {
        Mainboard mainboard = factory.createMainboard("MB-AS-X99-A",50,"ASUS X99-A",5299.00);
        Assert.assertEquals(mainboard.getDescription(),"ASUS X99-A");
        Assert.assertEquals(mainboard.getProductNumber(),"MB-AS-X99-A");
    }

    @Test
    public void testMainboardUpdate() throws Exception {
        Mainboard mainboard = factory.createMainboard("MB-AS-X99-A",50,"ASUS X99-A",5299.00);
        Assert.assertEquals(mainboard.getDescription(),"ASUS X99-A");
        Assert.assertEquals(mainboard.getProductNumber(),"MB-AS-X99-A");

        // Updated Description

        Mainboard updateMainboard = new Mainboard.Builder()
                .Mainboard(mainboard)
                .price(5699.00)
                .build();

        Assert.assertEquals(updateMainboard.getPrice(),5699.00);
        Assert.assertEquals(mainboard.getProductNumber(),updateMainboard.getProductNumber());




    }
}

