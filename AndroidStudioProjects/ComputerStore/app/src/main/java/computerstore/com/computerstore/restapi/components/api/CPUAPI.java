package computerstore.com.computerstore.restapi.components.api;

/**
 * Created by Aidem on 2016/04/17.
 */

import java.io.IOException;

import computerstore.com.computerstore.domain.components.CPU;
import computerstore.com.computerstore.respository.Repository;

public interface CPUAPI {
    CPU createCPU(CPU cpu) throws IOException;

    CPU updateCPU(CPU cpu) throws IOException;
}
