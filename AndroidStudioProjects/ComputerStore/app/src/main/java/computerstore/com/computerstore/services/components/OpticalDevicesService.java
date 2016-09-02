package computerstore.com.computerstore.services.components;

/**
 * Created by Aidem on 2016/04/17.
 */

import android.content.Context;

import computerstore.com.computerstore.domain.components.OpticalDevices;
import computerstore.com.computerstore.respository.Repository;

public interface OpticalDevicesService{

    void addOpticalDevices(Context context, OpticalDevices opticalDevices);

    void updateOpticalDevices(Context context, OpticalDevices opticalDevices);

}
