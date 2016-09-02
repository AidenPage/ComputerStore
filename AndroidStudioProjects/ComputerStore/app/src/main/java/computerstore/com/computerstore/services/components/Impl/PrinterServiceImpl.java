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
import computerstore.com.computerstore.domain.components.Printer;
import computerstore.com.computerstore.respository.components.Impl.PrinterRepositoryImpl;
import computerstore.com.computerstore.respository.components.PrinterRepository;
import computerstore.com.computerstore.restapi.components.api.Impl.PrinterAPIImpl;
import computerstore.com.computerstore.restapi.components.api.PrinterAPI;
import computerstore.com.computerstore.services.components.PrinterService;


public class PrinterServiceImpl extends IntentService implements PrinterService {
    private final PrinterAPI api;
    private final PrinterRepository repo;
    private final IBinder localBinder = new PrinterServiceLocalBinder();

    public static final String ACTION_ADD = "computerstore.com.computerstore.services.components.Impl.action.ADD";
    public static final String ACTION_UPDATE = "computerstore.com.computerstore.services.components.Impl.action.UPDATE";

    public static final String EXTRA_ADD = "computerstore.com.computerstore.services.components.Impl.extra.ADD";
    public static final String EXTRA_UPDATE = "computerstore.com.computerstore.services.components.Impl.extra.UPDATE";

    private static PrinterServiceImpl service = null;

    public static PrinterServiceImpl getInstance() {
        if (service == null)
            service = new PrinterServiceImpl();
        return service;
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        return localBinder;
    }

    public class PrinterServiceLocalBinder extends Binder {
        public PrinterServiceImpl getService() {
            return PrinterServiceImpl.this;
        }
    }


    public PrinterServiceImpl() {
        super("PrinterServiceImpl");
        api = new PrinterAPIImpl();
        repo = new PrinterRepositoryImpl(App.getAppContext());
    }

    @Override
    public void addPrinter(Context context, Printer contact) {
        Intent intent = new Intent(context, PrinterServiceImpl.class);
        intent.setAction(ACTION_ADD);
        intent.putExtra(EXTRA_UPDATE, contact);
        context.startService(intent);

    }

    @Override
    public void updatePrinter(Context context, Printer contact) {
        Intent intent = new Intent(context, PrinterServiceImpl.class);
        intent.setAction(ACTION_UPDATE);
        intent.putExtra(EXTRA_UPDATE, contact);
        context.startService(intent);

    }

    @Override
    protected void onHandleIntent(Intent intent) {
        if (intent != null) {
            final String action = intent.getAction();
            if (ACTION_ADD.equals(action)) {
                final Printer printer = (Printer) intent.getSerializableExtra(EXTRA_ADD);
                updatePrinter(printer);
            } else if (ACTION_UPDATE.equals(action)) {
                final Printer printer = (Printer) intent.getSerializableExtra(EXTRA_UPDATE);
                postPrinter(printer);
            }
        }
    }

    public Printer updatePrinter(Printer printer) {
        //REMOTE UPADTE AND LOCAL UPDATE
        try {
            Printer updatedContact = api.updatePrinter(printer);
            repo.save(updatedContact);
            return repo.save(updatedContact);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return repo.save(printer);
    }

    public Printer postPrinter(Printer printer) {
        //POST AND LOCAL SAVE
        try {
            Printer createdContact = api.createPrinter(printer);
            repo.save(createdContact);
            return repo.save(createdContact);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return repo.save(printer);
    }
}
