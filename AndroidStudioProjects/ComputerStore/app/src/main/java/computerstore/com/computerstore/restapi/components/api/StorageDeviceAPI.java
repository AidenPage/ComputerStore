package computerstore.com.computerstore.restapi.components.api;

/**
 * Created by Aidem on 2016/04/17.
 */

import java.io.IOException;

import computerstore.com.computerstore.domain.components.StorageDevice;
import computerstore.com.computerstore.respository.Repository;

public interface StorageDeviceAPI {

    StorageDevice createStorageDevice(StorageDevice storageDevice) throws IOException;

    StorageDevice updateStorageDevice(StorageDevice storageDevice) throws IOException;

}
