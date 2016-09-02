package computerstore.com.computerstore.services.components;

/**
 * Created by Aidem on 2016/04/17.
 */

import android.content.Context;

import computerstore.com.computerstore.domain.components.PCU;
import computerstore.com.computerstore.respository.Repository;

public interface PCUService{


    void addPCU(Context context, PCU pcu);

    void updatePCU(Context context, PCU pcu);
}
