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
import computerstore.com.computerstore.domain.components.Monitor;
import computerstore.com.computerstore.respository.components.Impl.MonitorRepositoryImpl;
import computerstore.com.computerstore.respository.components.MonitorRepository;
import computerstore.com.computerstore.restapi.components.api.Impl.MonitorAPIImpl;
import computerstore.com.computerstore.restapi.components.api.MonitorAPI;
import computerstore.com.computerstore.services.components.MonitorService;


public class MonitorServiceImpl extends IntentService implements MonitorService {
    private final MonitorAPI api;
    private final MonitorRepository repo;
    private final IBinder localBinder = new MonitorServiceLocalBinder();

    public static final String ACTION_ADD = "computerstore.com.computerstore.services.components.Impl.action.ADD";
    public static final String ACTION_UPDATE = "computerstore.com.computerstore.services.components.Impl.action.UPDATE";

    public static final String EXTRA_ADD = "computerstore.com.computerstore.services.components.Impl.extra.ADD";
    public static final String EXTRA_UPDATE = "computerstore.com.computerstore.services.components.Impl.extra.UPDATE";

    private static MonitorServiceImpl service = null;

    public static MonitorServiceImpl getInstance() {
        if (service == null)
            service = new MonitorServiceImpl();
        return service;
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        return localBinder;
    }

    public class MonitorServiceLocalBinder extends Binder {
        public MonitorServiceImpl getService() {
            return MonitorServiceImpl.this;
        }
    }


    public MonitorServiceImpl() {
        super("MonitorServiceImpl");
        api = new MonitorAPIImpl();
        repo = new MonitorRepositoryImpl(App.getAppContext());
    }

    @Override
    public void addMonitor(Context context, Monitor contact) {
        Intent intent = new Intent(context, MonitorServiceImpl.class);
        intent.setAction(ACTION_ADD);
        intent.putExtra(EXTRA_UPDATE, contact);
        context.startService(intent);

    }

    @Override
    public void updateMonitor(Context context, Monitor contact) {
        Intent intent = new Intent(context, MonitorServiceImpl.class);
        intent.setAction(ACTION_UPDATE);
        intent.putExtra(EXTRA_UPDATE, contact);
        context.startService(intent);

    }

    @Override
    protected void onHandleIntent(Intent intent) {
        if (intent != null) {
            final String action = intent.getAction();
            if (ACTION_ADD.equals(action)) {
                final Monitor monitor = (Monitor) intent.getSerializableExtra(EXTRA_ADD);
                updateMonitor(monitor);
            } else if (ACTION_UPDATE.equals(action)) {
                final Monitor monitor = (Monitor) intent.getSerializableExtra(EXTRA_UPDATE);
                postMonitor(monitor);
            }
        }
    }

    public Monitor updateMonitor(Monitor monitor) {
        //REMOTE UPADTE AND LOCAL UPDATE
        try {
            Monitor updatedContact = api.updateMonitor(monitor);
            repo.save(updatedContact);
            return repo.save(updatedContact);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return repo.save(monitor);
    }

    public Monitor postMonitor(Monitor monitor) {
        //POST AND LOCAL SAVE
        try {
            Monitor createdContact = api.createMonitor(monitor);
            repo.save(createdContact);
            return repo.save(createdContact);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return repo.save(monitor);
    }

}
