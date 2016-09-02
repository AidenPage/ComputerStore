package computerstore.com.computerstore.factories.components;

/**
 * Created by Aidem on 2016/04/17.
 */

import computerstore.com.computerstore.domain.components.Printer;
import java.util.UUID;

public class PrinterFactory {

    public static Printer createPrinter(String productNumber,int stock, String description,double price) {
        Printer  cpu = new Printer
                .Builder()
                .productNumber(productNumber)
                .stock(stock)
                .description(description)
                .price(price)
                .build();
        return cpu;
    }
}

