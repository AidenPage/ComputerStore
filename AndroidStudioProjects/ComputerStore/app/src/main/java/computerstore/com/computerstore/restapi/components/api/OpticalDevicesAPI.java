package computerstore.com.computerstore.restapi.components.api;

/**
 * Created by Aidem on 2016/04/17.
 */

import java.io.IOException;

import computerstore.com.computerstore.domain.components.OpticalDevices;
import computerstore.com.computerstore.respository.Repository;

public interface OpticalDevicesAPI {


    OpticalDevices createOpticalDevices(OpticalDevices opticalDevices) throws IOException;

    OpticalDevices updateOpticalDevices(OpticalDevices opticalDevices) throws IOException;
}
