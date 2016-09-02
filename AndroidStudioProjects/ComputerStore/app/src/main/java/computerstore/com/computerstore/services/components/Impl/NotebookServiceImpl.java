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
import computerstore.com.computerstore.domain.components.Notebook;
import computerstore.com.computerstore.respository.components.Impl.NotebookRepositoryImpl;
import computerstore.com.computerstore.respository.components.NotebookRepository;
import computerstore.com.computerstore.restapi.components.api.Impl.NotebookAPIImpl;
import computerstore.com.computerstore.restapi.components.api.NotebookAPI;
import computerstore.com.computerstore.services.components.NotebookService;


public class NotebookServiceImpl extends IntentService implements NotebookService {
    private final NotebookAPI api;
    private final NotebookRepository repo;
    private final IBinder localBinder = new NotebookServiceLocalBinder();

    public static final String ACTION_ADD = "computerstore.com.computerstore.services.components.Impl.action.ADD";
    public static final String ACTION_UPDATE = "computerstore.com.computerstore.services.components.Impl.action.UPDATE";

    public static final String EXTRA_ADD = "computerstore.com.computerstore.services.components.Impl.extra.ADD";
    public static final String EXTRA_UPDATE = "computerstore.com.computerstore.services.components.Impl.extra.UPDATE";

    private static NotebookServiceImpl service = null;

    public static NotebookServiceImpl getInstance() {
        if (service == null)
            service = new NotebookServiceImpl();
        return service;
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        return localBinder;
    }

    public class NotebookServiceLocalBinder extends Binder {
        public NotebookServiceImpl getService() {
            return NotebookServiceImpl.this;
        }
    }


    public NotebookServiceImpl() {
        super("NotebookServiceImpl");
        api = new NotebookAPIImpl();
        repo = new NotebookRepositoryImpl(App.getAppContext());
    }

    @Override
    public void addNotebook(Context context, Notebook contact) {
        Intent intent = new Intent(context, NotebookServiceImpl.class);
        intent.setAction(ACTION_ADD);
        intent.putExtra(EXTRA_UPDATE, contact);
        context.startService(intent);

    }

    @Override
    public void updateNotebook(Context context, Notebook contact) {
        Intent intent = new Intent(context, NotebookServiceImpl.class);
        intent.setAction(ACTION_UPDATE);
        intent.putExtra(EXTRA_UPDATE, contact);
        context.startService(intent);

    }

    @Override
    protected void onHandleIntent(Intent intent) {
        if (intent != null) {
            final String action = intent.getAction();
            if (ACTION_ADD.equals(action)) {
                final Notebook notebook = (Notebook) intent.getSerializableExtra(EXTRA_ADD);
                updateNotebook(notebook);
            } else if (ACTION_UPDATE.equals(action)) {
                final Notebook notebook = (Notebook) intent.getSerializableExtra(EXTRA_UPDATE);
                postNotebook(notebook);
            }
        }
    }

    public Notebook updateNotebook(Notebook notebook) {
        //REMOTE UPADTE AND LOCAL UPDATE
        try {
            Notebook updatedContact = api.updateNotebook(notebook);
            repo.save(updatedContact);
            return repo.save(updatedContact);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return repo.save(notebook);
    }

    public Notebook postNotebook(Notebook notebook) {
        //POST AND LOCAL SAVE
        try {
            Notebook createdContact = api.createNotebook(notebook);
            repo.save(createdContact);
            return repo.save(createdContact);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return repo.save(notebook);
    }
}
