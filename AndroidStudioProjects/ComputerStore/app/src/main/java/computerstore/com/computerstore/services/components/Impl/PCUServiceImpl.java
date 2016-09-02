package computerstore.com.computerstore.services.components.Impl;

/**
 * Created by Aidem on 2016/04/17.
 */

import android.app.IntentService;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Binder;
import android.os.IBinder;
import android.util.Log;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

import computerstore.com.computerstore.conf.databases.DBConstants;
import computerstore.com.computerstore.conf.util.App;
import computerstore.com.computerstore.domain.components.PCU;
import computerstore.com.computerstore.respository.components.Impl.PCURepositoryImpl;
import computerstore.com.computerstore.respository.components.PCURepository;
import computerstore.com.computerstore.restapi.components.api.Impl.PCUAPIImpl;
import computerstore.com.computerstore.restapi.components.api.PCUAPI;
import computerstore.com.computerstore.services.components.PCUService;


public class PCUServiceImpl extends IntentService implements PCUService {
    private final PCUAPI api;
    private final PCURepository repo;
    private final IBinder localBinder = new PCUServiceLocalBinder();

    public static final String ACTION_ADD = "computerstore.com.computerstore.services.components.Impl.action.ADD";
    public static final String ACTION_UPDATE = "computerstore.com.computerstore.services.components.Impl.action.UPDATE";

    public static final String EXTRA_ADD = "computerstore.com.computerstore.services.components.Impl.extra.ADD";
    public static final String EXTRA_UPDATE = "computerstore.com.computerstore.services.components.Impl.extra.UPDATE";

    private static PCUServiceImpl service = null;

    public static PCUServiceImpl getInstance() {
        if (service == null)
            service = new PCUServiceImpl();
        return service;
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        return localBinder;
    }

    public class PCUServiceLocalBinder extends Binder {
        public PCUServiceImpl getService() {
            return PCUServiceImpl.this;
        }
    }


    public PCUServiceImpl() {
        super("PCUServiceImpl");
        api = new PCUAPIImpl();
        repo = new PCURepositoryImpl(App.getAppContext());
    }

    @Override
    public void addPCU(Context context, PCU contact) {
        Intent intent = new Intent(context, PCUServiceImpl.class);
        intent.setAction(ACTION_ADD);
        intent.putExtra(EXTRA_UPDATE, contact);
        context.startService(intent);

    }

    @Override
    public void updatePCU(Context context, PCU contact) {
        Intent intent = new Intent(context, PCUServiceImpl.class);
        intent.setAction(ACTION_UPDATE);
        intent.putExtra(EXTRA_UPDATE, contact);
        context.startService(intent);

    }

    @Override
    protected void onHandleIntent(Intent intent) {
        if (intent != null) {
            final String action = intent.getAction();
            if (ACTION_ADD.equals(action)) {
                final PCU pcu = (PCU) intent.getSerializableExtra(EXTRA_ADD);
                updatePCU(pcu);
            } else if (ACTION_UPDATE.equals(action)) {
                final PCU pcu = (PCU) intent.getSerializableExtra(EXTRA_UPDATE);
                postPCU(pcu);
            }
        }
    }

    public PCU updatePCU(PCU pcu) {
        //REMOTE UPADTE AND LOCAL UPDATE
        try {
            PCU updatedContact = api.updatePCU(pcu);
            repo.save(updatedContact);
            return repo.save(updatedContact);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return repo.save(pcu);
    }

    public PCU postPCU(PCU pcu) {
        //POST AND LOCAL SAVE
        try {
            PCU createdContact = api.createPCU(pcu);
            repo.save(createdContact);
            return repo.save(createdContact);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return repo.save(pcu);
    }
}
