package computerstore.com.computerstore.factories.sales;

/**
 * Created by Aidem on 2016/04/17.
 */

import computerstore.com.computerstore.domain.sales.SalesComponents;
import java.util.UUID;

public class SalesComponentsFactory {

    public static SalesComponents createSalesComponents(String productNumber, String saleID,int amount) {
        SalesComponents  salesComponents = new SalesComponents
                .Builder()
                .productNumber(productNumber)
                .saleID(saleID)
                .amount(amount)
                .build();
        return salesComponents;
    }
}

