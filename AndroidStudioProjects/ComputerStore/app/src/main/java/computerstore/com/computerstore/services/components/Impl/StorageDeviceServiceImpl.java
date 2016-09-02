package computerstore.com.computerstore.services.components.Impl;

/**
 * Created by Aidem on 2016/04/17.
 */

import android.app.IntentService;
import android.content.Context;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;

import java.io.IOException;

import computerstore.com.computerstore.conf.util.App;
import computerstore.com.computerstore.domain.components.StorageDevice;
import computerstore.com.computerstore.respository.components.Impl.StorageDeviceRepositoryImpl;
import computerstore.com.computerstore.respository.components.StorageDeviceRepository;
import computerstore.com.computerstore.restapi.components.api.Impl.StorageDeviceAPIImpl;
import computerstore.com.computerstore.restapi.components.api.StorageDeviceAPI;
import computerstore.com.computerstore.services.components.StorageDeviceService;


public class StorageDeviceServiceImpl extends IntentService implements StorageDeviceService {
    private final StorageDeviceAPI api;
    private final StorageDeviceRepository repo;
    private final IBinder localBinder = new StorageDeviceServiceLocalBinder();

    public static final String ACTION_ADD = "computerstore.com.computerstore.services.components.Impl.action.ADD";
    public static final String ACTION_UPDATE = "computerstore.com.computerstore.services.components.Impl.action.UPDATE";

    public static final String EXTRA_ADD = "computerstore.com.computerstore.services.components.Impl.extra.ADD";
    public static final String EXTRA_UPDATE = "computerstore.com.computerstore.services.components.Impl.extra.UPDATE";

    private static StorageDeviceServiceImpl service = null;

    public static StorageDeviceServiceImpl getInstance() {
        if (service == null)
            service = new StorageDeviceServiceImpl();
        return service;
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        return localBinder;
    }

    public class StorageDeviceServiceLocalBinder extends Binder {
        public StorageDeviceServiceImpl getService() {
            return StorageDeviceServiceImpl.this;
        }
    }


    public StorageDeviceServiceImpl() {
        super("StorageDeviceServiceImpl");
        api = new StorageDeviceAPIImpl();
        repo = new StorageDeviceRepositoryImpl(App.getAppContext());
    }

    @Override
    public void addStorageDevice(Context context, StorageDevice contact) {
        Intent intent = new Intent(context, StorageDeviceServiceImpl.class);
        intent.setAction(ACTION_ADD);
        intent.putExtra(EXTRA_UPDATE, contact);
        context.startService(intent);

    }

    @Override
    public void updateStorageDevice(Context context, StorageDevice contact) {
        Intent intent = new Intent(context, StorageDeviceServiceImpl.class);
        intent.setAction(ACTION_UPDATE);
        intent.putExtra(EXTRA_UPDATE, contact);
        context.startService(intent);

    }

    @Override
    protected void onHandleIntent(Intent intent) {
        if (intent != null) {
            final String action = intent.getAction();
            if (ACTION_ADD.equals(action)) {
                final StorageDevice storageDevice = (StorageDevice) intent.getSerializableExtra(EXTRA_ADD);
                updateStorageDevice(storageDevice);
            } else if (ACTION_UPDATE.equals(action)) {
                final StorageDevice storageDevice = (StorageDevice) intent.getSerializableExtra(EXTRA_UPDATE);
                postStorageDevice(storageDevice);
            }
        }
    }

    public StorageDevice updateStorageDevice(StorageDevice storageDevice) {
        //REMOTE UPADTE AND LOCAL UPDATE
        try {
            StorageDevice updatedContact = api.updateStorageDevice(storageDevice);
            repo.save(updatedContact);
            return repo.save(updatedContact);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return repo.save(storageDevice);
    }

    public StorageDevice postStorageDevice(StorageDevice storageDevice) {
        //POST AND LOCAL SAVE
        try {
            StorageDevice createdContact = api.createStorageDevice(storageDevice);
            repo.save(createdContact);
            return repo.save(createdContact);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return repo.save(storageDevice);
    }
}
