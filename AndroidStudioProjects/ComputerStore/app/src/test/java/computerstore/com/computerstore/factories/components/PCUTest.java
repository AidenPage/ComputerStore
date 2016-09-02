package computerstore.com.computerstore.factories.components;

/**
 * Created by Aidem on 2016/04/17.
 */

import org.junit.Test;
import computerstore.com.computerstore.factories.components.PCUFactory;
import computerstore.com.computerstore.domain.components.PCU;
import junit.framework.Assert;

public class PCUTest {

    private PCUFactory factory;


    @Test
    public void testPCUCreation() throws Exception {
        PCU pcu = factory.createPCU("RS-A50-SPHA-D3",50,"COOLERMASTER SILENT PRO HYBRID 1050W PSU",2799.00);
        Assert.assertEquals(pcu.getDescription(),"COOLERMASTER SILENT PRO HYBRID 1050W PSU");
        Assert.assertEquals(pcu.getProductNumber(),"RS-A50-SPHA-D3");
    }

    @Test
    public void testPCUUpdate() throws Exception {
        PCU pcu = factory.createPCU("RS-A50-SPHA-D3",50,"COOLERMASTER SILENT PRO HYBRID 1050W PSU",2799.00);
        Assert.assertEquals(pcu.getDescription(),"COOLERMASTER SILENT PRO HYBRID 1050W PSU");
        Assert.assertEquals(pcu.getProductNumber(),"RS-A50-SPHA-D3");

        // Updated Description

        PCU updatePCU = new PCU.Builder()
                .PCU(pcu)
                .price(3099.00)
                .build();

        Assert.assertEquals(updatePCU.getPrice(),3099.00);
        Assert.assertEquals(pcu.getProductNumber(),updatePCU.getProductNumber());

    }
}
