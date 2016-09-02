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
import computerstore.com.computerstore.domain.components.DisplayCard;
import computerstore.com.computerstore.respository.components.DisplayCardRepository;
import computerstore.com.computerstore.respository.components.Impl.DisplayCardRepositoryImpl;
import computerstore.com.computerstore.restapi.components.api.DisplayCardAPI;
import computerstore.com.computerstore.restapi.components.api.Impl.DisplayCardAPIImpl;
import computerstore.com.computerstore.services.components.DisplayCardService;


public class DisplayCardServiceImpl extends IntentService implements DisplayCardService {
    private final DisplayCardAPI api;
    private final DisplayCardRepository repo;
    private final IBinder localBinder = new DisplayCardServiceLocalBinder();

    public static final String ACTION_ADD = "computerstore.com.computerstore.services.components.Impl.action.ADD";
    public static final String ACTION_UPDATE = "computerstore.com.computerstore.services.components.Impl.action.UPDATE";

    public static final String EXTRA_ADD = "computerstore.com.computerstore.services.components.Impl.extra.ADD";
    public static final String EXTRA_UPDATE = "computerstore.com.computerstore.services.components.Impl.extra.UPDATE";

    private static DisplayCardServiceImpl service = null;

    public static DisplayCardServiceImpl getInstance() {
        if (service == null)
            service = new DisplayCardServiceImpl();
        return service;
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        return localBinder;
    }

    public class DisplayCardServiceLocalBinder extends Binder {
        public DisplayCardServiceImpl getService() {
            return DisplayCardServiceImpl.this;
        }
    }


    public DisplayCardServiceImpl() {
        super("DisplayCardServiceImpl");
        api = new DisplayCardAPIImpl();
        repo = new DisplayCardRepositoryImpl(App.getAppContext());
    }

    @Override
    public void addDisplayCard(Context context, DisplayCard contact) {
        Intent intent = new Intent(context, DisplayCardServiceImpl.class);
        intent.setAction(ACTION_ADD);
        intent.putExtra(EXTRA_UPDATE, contact);
        context.startService(intent);

    }

    @Override
    public void updateDisplayCard(Context context, DisplayCard contact) {
        Intent intent = new Intent(context, DisplayCardServiceImpl.class);
        intent.setAction(ACTION_UPDATE);
        intent.putExtra(EXTRA_UPDATE, contact);
        context.startService(intent);

    }

    @Override
    protected void onHandleIntent(Intent intent) {
        if (intent != null) {
            final String action = intent.getAction();
            if (ACTION_ADD.equals(action)) {
                final DisplayCard displayCard = (DisplayCard) intent.getSerializableExtra(EXTRA_ADD);
                updateDisplayCard(displayCard);
            } else if (ACTION_UPDATE.equals(action)) {
                final DisplayCard displayCard = (DisplayCard) intent.getSerializableExtra(EXTRA_UPDATE);
                postDisplayCard(displayCard);
            }
        }
    }

    public DisplayCard updateDisplayCard(DisplayCard displayCard) {
        //REMOTE UPADTE AND LOCAL UPDATE
        try {
            DisplayCard updatedContact = api.updateDisplayCard(displayCard);
            repo.save(updatedContact);
            return repo.save(updatedContact);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return repo.save(displayCard);
    }

    public DisplayCard postDisplayCard(DisplayCard displayCard) {
        //POST AND LOCAL SAVE
        try {
            DisplayCard createdContact = api.createDisplayCard(displayCard);
            repo.save(createdContact);
            return repo.save(createdContact);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return repo.save(displayCard);
    }
}
