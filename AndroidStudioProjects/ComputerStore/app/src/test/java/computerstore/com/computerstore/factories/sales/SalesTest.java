package computerstore.com.computerstore.factories.sales;

/**
 * Created by Aidem on 2016/04/17.
 */

import org.junit.Test;
import junit.framework.Assert;
import computerstore.com.computerstore.factories.sales.SalesFactory;
import computerstore.com.computerstore.domain.sales.Sales;

public class SalesTest{

    private SalesFactory factory;

    @Test
    public void testSalesCreation() throws Exception {
        Sales sales = factory.createSales("cs12345",211121614,"2016/04/03",5000.00,100.00);
        Assert.assertEquals(sales.getSalesId(),"cs12345");
        Assert.assertEquals(sales.getEmpID(),211121614);
    }

    @Test
    public void testSalesUpdate() throws Exception {
        Sales sales = factory.createSales("cs12345",211121614,"2016/04/03",5000.00, 100.00);
        Assert.assertEquals(sales.getSalesId(),"cs12345");
        Assert.assertEquals(sales.getEmpID(),211121614);


        // Updated Description

        Sales updateSales = new Sales.Builder()
                .Sales(sales)
                .totalSales(4000.00)
                .build();

        Assert.assertEquals(updateSales.getTotalSales(),4000.00);
        Assert.assertEquals(sales.getSalesId(),updateSales.getSalesId());





    }

}

