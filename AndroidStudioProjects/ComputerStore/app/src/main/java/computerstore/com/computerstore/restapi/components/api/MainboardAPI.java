package computerstore.com.computerstore.restapi.components.api;

/**
 * Created by Aidem on 2016/04/17.
 */

import java.io.IOException;

import computerstore.com.computerstore.domain.components.Mainboard;
import computerstore.com.computerstore.respository.Repository;

public interface MainboardAPI {
    Mainboard createMainboard(Mainboard mainboard) throws IOException;

    Mainboard updateMainboard (Mainboard mainboard) throws IOException;

}

