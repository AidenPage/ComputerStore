package computerstore.com.computerstore.factories.components;

/**
 * Created by Aidem on 2016/04/17.
 */

import computerstore.com.computerstore.domain.components.Notebook;
import java.util.UUID;

public class NotebookFactory {

    public static Notebook createNotebook(String productNumber,int stock, String description,double price) {
        Notebook  cpu = new Notebook
                .Builder()
                .productNumber(productNumber)
                .stock(stock)
                .description(description)
                .price(price)
                .build();
        return cpu;
    }
}
