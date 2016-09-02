package computerstore.com.computerstore.restapi.components.api;

/**
 * Created by Aidem on 2016/04/17.
 */

import java.io.IOException;

import computerstore.com.computerstore.domain.components.PCU;
import computerstore.com.computerstore.respository.Repository;

public interface PCUAPI {

    PCU createPCU(PCU pcu) throws IOException;

    PCU updatePCU(PCU pcu) throws IOException;

}
