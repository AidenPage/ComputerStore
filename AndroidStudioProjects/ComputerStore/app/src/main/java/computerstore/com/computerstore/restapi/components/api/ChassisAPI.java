package computerstore.com.computerstore.restapi.components.api;

/**
 * Created by Aidem on 2016/04/17.
 */

import java.io.IOException;

import computerstore.com.computerstore.domain.components.Chassis;
import computerstore.com.computerstore.respository.Repository;


public interface ChassisAPI {
    Chassis createChassis(Chassis chassis) throws IOException;

    Chassis updateChassis(Chassis chassis) throws IOException;
}
