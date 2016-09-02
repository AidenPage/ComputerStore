package computerstore.com.computerstore.restapi.components.api;

/**
 * Created by Aidem on 2016/04/17.
 */

import java.io.IOException;

import computerstore.com.computerstore.domain.components.Printer;
import computerstore.com.computerstore.respository.Repository;

public interface PrinterAPI {

    Printer createPrinter(Printer printer) throws IOException;

    Printer updatePrinter(Printer printer) throws IOException;

}

