package computerstore.com.computerstore.restapi.components.api;

/**
 * Created by Aidem on 2016/04/17.
 */

import java.io.IOException;

import computerstore.com.computerstore.domain.components.Monitor;
import computerstore.com.computerstore.respository.Repository;

public interface MonitorAPI {

    Monitor createMonitor(Monitor monitor) throws IOException;

    Monitor updateMonitor(Monitor monitor) throws IOException;

}

