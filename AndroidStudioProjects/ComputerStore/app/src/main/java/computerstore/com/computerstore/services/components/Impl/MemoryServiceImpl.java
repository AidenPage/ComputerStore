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
import computerstore.com.computerstore.domain.components.Memory;
import computerstore.com.computerstore.respository.components.Impl.MemoryRepositoryImpl;
import computerstore.com.computerstore.respository.components.MemoryRepository;
import computerstore.com.computerstore.restapi.components.api.Impl.MemoryAPIImpl;
import computerstore.com.computerstore.restapi.components.api.MemoryAPI;
import computerstore.com.computerstore.services.components.MemoryService;


public class MemoryServiceImpl extends IntentService implements MemoryService {
    private final MemoryAPI api;
    private final MemoryRepository repo;
    private final IBinder localBinder = new MemoryServiceLocalBinder();

    public static final String ACTION_ADD = "computerstore.com.computerstore.services.components.Impl.action.ADD";
    public static final String ACTION_UPDATE = "computerstore.com.computerstore.services.components.Impl.action.UPDATE";

    public static final String EXTRA_ADD = "computerstore.com.computerstore.services.components.Impl.extra.ADD";
    public static final String EXTRA_UPDATE = "computerstore.com.computerstore.services.components.Impl.extra.UPDATE";

    private static MemoryServiceImpl service = null;

    public static MemoryServiceImpl getInstance() {
        if (service == null)
            service = new MemoryServiceImpl();
        return service;
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        return localBinder;
    }

    public class MemoryServiceLocalBinder extends Binder {
        public MemoryServiceImpl getService() {
            return MemoryServiceImpl.this;
        }
    }


    public MemoryServiceImpl() {
        super("MemoryServiceImpl");
        api = new MemoryAPIImpl();
        repo = new MemoryRepositoryImpl(App.getAppContext());
    }

    @Override
    public void addMemory(Context context, Memory contact) {
        Intent intent = new Intent(context, MemoryServiceImpl.class);
        intent.setAction(ACTION_ADD);
        intent.putExtra(EXTRA_UPDATE, contact);
        context.startService(intent);

    }

    @Override
    public void updateMemory(Context context, Memory contact) {
        Intent intent = new Intent(context, MemoryServiceImpl.class);
        intent.setAction(ACTION_UPDATE);
        intent.putExtra(EXTRA_UPDATE, contact);
        context.startService(intent);

    }

    @Override
    protected void onHandleIntent(Intent intent) {
        if (intent != null) {
            final String action = intent.getAction();
            if (ACTION_ADD.equals(action)) {
                final Memory memory = (Memory) intent.getSerializableExtra(EXTRA_ADD);
                updateMemory(memory);
            } else if (ACTION_UPDATE.equals(action)) {
                final Memory memory = (Memory) intent.getSerializableExtra(EXTRA_UPDATE);
                postMemory(memory);
            }
        }
    }

    public Memory updateMemory(Memory memory) {
        //REMOTE UPADTE AND LOCAL UPDATE
        try {
            Memory updatedContact = api.updateMemory(memory);
            repo.save(updatedContact);
            return repo.save(updatedContact);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return repo.save(memory);
    }

    public Memory postMemory(Memory memory) {
        //POST AND LOCAL SAVE
        try {
            Memory createdContact = api.createMemory(memory);
            repo.save(createdContact);
            return repo.save(createdContact);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return repo.save(memory);
    }
}
