package computerstore.com.computerstore.services.components;

/**
 * Created by Aidem on 2016/04/17.
 */

import android.content.Context;

import computerstore.com.computerstore.domain.components.StorageDevice;
import computerstore.com.computerstore.respository.Repository;

public interface StorageDeviceService{
    void addStorageDevice(Context context, StorageDevice storageDevice);

    void updateStorageDevice(Context context, StorageDevice storageDevice);


}
