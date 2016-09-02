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
import computerstore.com.computerstore.domain.components.Chassis;
import computerstore.com.computerstore.respository.components.ChassisRepository;
import computerstore.com.computerstore.respository.components.Impl.ChassisRepositoryImpl;
import computerstore.com.computerstore.restapi.components.api.ChassisAPI;
import computerstore.com.computerstore.restapi.components.api.Impl.ChassisAPIImpl;
import computerstore.com.computerstore.services.components.ChassisService;


public class ChassisServiceImpl extends IntentService implements ChassisService {
    private final ChassisAPI api;
    private final ChassisRepository repo;
    private final IBinder localBinder = new ChassisServiceLocalBinder();

    public static final String TAG = "computerstore.com.computerstore.services.components.Impl";

    public static final String ACTION_ADD = "computerstore.com.computerstore.services.components.Impl.action.ADD";
    public static final String ACTION_UPDATE = "computerstore.com.computerstore.services.components.Impl.action.UPDATE";

    public static final String EXTRA_ADD = "computerstore.com.computerstore.services.components.Impl.extra.ADD";
    public static final String EXTRA_UPDATE = "computerstore.com.computerstore.services.components.Impl.extra.UPDATE";


    private static ChassisServiceImpl service = null;

    public static ChassisServiceImpl getInstance() {
        if (service == null)
            service = new ChassisServiceImpl();
        return service;
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        return localBinder;
    }

    public class ChassisServiceLocalBinder extends Binder {
        public ChassisServiceImpl getService() {
            return ChassisServiceImpl.this;
        }
    }

    public ChassisServiceImpl() {
        super("ChassisServiceImpl");
        api = new ChassisAPIImpl();
        repo = new ChassisRepositoryImpl(App.getAppContext());
    }

    @Override
    public void addChassis(Context context, Chassis chassis) {
        Intent intent = new Intent(context, ChassisServiceImpl.class);
        intent.setAction(ACTION_ADD);
        intent.putExtra(EXTRA_UPDATE, chassis);
        context.startService(intent);

    }

    @Override
    public void updateChassis(Context context, Chassis chassis) {
        Intent intent = new Intent(context, ChassisServiceImpl.class);
        intent.setAction(ACTION_UPDATE);
        intent.putExtra(EXTRA_UPDATE, chassis);
        context.startService(intent);

    }

    @Override
    protected void onHandleIntent(Intent intent) {
        if (intent != null) {
            final String action = intent.getAction();
            if (ACTION_ADD.equals(action)) {
                final Chassis personContact = (Chassis) intent.getSerializableExtra(EXTRA_ADD);
                postChassis(personContact);
            } else if (ACTION_UPDATE.equals(action)) {
                final Chassis personContact = (Chassis) intent.getSerializableExtra(EXTRA_UPDATE);
                updateChassis(personContact);
            }
        }
    }

    public Chassis updateChassis(Chassis chassis) {
        //REMOTE UPADTE AND LOCAL UPDATE
        try {
            Chassis updatedContact = api.updateChassis(chassis);
            repo.save(updatedContact);
            return repo.save(updatedContact);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return repo.save(chassis);
    }

    public Chassis postChassis(Chassis chassis) {
        //POST AND LOCAL SAVE
        try {
            Chassis createdContact = api.createChassis(chassis);
            repo.save(createdContact);
            return repo.save(createdContact);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return repo.save(chassis);
    }

}
