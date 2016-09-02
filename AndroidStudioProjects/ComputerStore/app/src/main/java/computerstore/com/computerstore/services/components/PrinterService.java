package computerstore.com.computerstore.services.components;

/**
 * Created by Aidem on 2016/04/17.
 */

import android.content.Context;

import computerstore.com.computerstore.domain.components.Printer;
import computerstore.com.computerstore.respository.Repository;

public interface PrinterService{


    void addPrinter(Context context, Printer printer);

    void updatePrinter(Context context, Printer printer);
}

