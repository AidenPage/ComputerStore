package computerstore.com.computerstore.services.components;

/**
 * Created by Aidem on 2016/04/17.
 */

import android.content.Context;

import computerstore.com.computerstore.domain.components.Monitor;
import computerstore.com.computerstore.respository.Repository;

public interface MonitorService{
    void addMonitor(Context context, Monitor monitor);

    void updateMonitor(Context context, Monitor monitor);


}

