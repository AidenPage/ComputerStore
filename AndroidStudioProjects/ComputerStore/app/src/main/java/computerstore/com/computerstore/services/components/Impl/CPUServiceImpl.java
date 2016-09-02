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
import computerstore.com.computerstore.domain.components.CPU;
import computerstore.com.computerstore.respository.components.CPURepository;
import computerstore.com.computerstore.respository.components.Impl.CPURepositoryImpl;
import computerstore.com.computerstore.restapi.components.api.CPUAPI;
import computerstore.com.computerstore.restapi.components.api.Impl.CPUAPIImpl;
import computerstore.com.computerstore.services.components.CPUService;


public class CPUServiceImpl extends IntentService implements CPUService {
    private final CPUAPI api;
    private final CPURepository repo;
    private final IBinder localBinder = new CPUServiceLocalBinder();

    public static final String ACTION_ADD = "computerstore.com.computerstore.services.components.Impl.action.ADD";
    public static final String ACTION_UPDATE = "computerstore.com.computerstore.services.components.Impl.action.UPDATE";

    public static final String EXTRA_ADD = "computerstore.com.computerstore.services.components.Impl.extra.ADD";
    public static final String EXTRA_UPDATE = "computerstore.com.computerstore.services.components.Impl.extra.UPDATE";

    private static CPUServiceImpl service = null;

    public static CPUServiceImpl getInstance() {
        if (service == null)
            service = new CPUServiceImpl();
        return service;
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        return localBinder;
    }

    public class CPUServiceLocalBinder extends Binder {
        public CPUServiceImpl getService() {
            return CPUServiceImpl.this;
        }
    }


    public CPUServiceImpl() {
        super("CPUServiceImpl");
        api = new CPUAPIImpl();
        repo = new CPURepositoryImpl(App.getAppContext());
    }

    @Override
    public void addCPU(Context context, CPU contact) {
        Intent intent = new Intent(context, CPUServiceImpl.class);
        intent.setAction(ACTION_ADD);
        intent.putExtra(EXTRA_UPDATE, contact);
        context.startService(intent);

    }

    @Override
    public void updateCPU(Context context, CPU contact) {
        Intent intent = new Intent(context, CPUServiceImpl.class);
        intent.setAction(ACTION_UPDATE);
        intent.putExtra(EXTRA_UPDATE, contact);
        context.startService(intent);

    }

    @Override
    protected void onHandleIntent(Intent intent) {
        if (intent != null) {
            final String action = intent.getAction();
            if (ACTION_ADD.equals(action)) {
                final CPU cpu = (CPU) intent.getSerializableExtra(EXTRA_ADD);
                updateCPU(cpu);
            } else if (ACTION_UPDATE.equals(action)) {
                final CPU cpu = (CPU) intent.getSerializableExtra(EXTRA_UPDATE);
                postCPU(cpu);
            }
        }
    }

    public CPU updateCPU(CPU cpu) {
        //REMOTE UPADTE AND LOCAL UPDATE
        try {
            CPU updatedContact = api.updateCPU(cpu);
            repo.save(updatedContact);
            return repo.save(updatedContact);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return repo.save(cpu);
    }

    public CPU postCPU(CPU cpu) {
        //POST AND LOCAL SAVE
        try {
            CPU createdContact = api.createCPU(cpu);
            repo.save(createdContact);
            return repo.save(createdContact);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return repo.save(cpu);
    }
}
