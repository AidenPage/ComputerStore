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
import computerstore.com.computerstore.domain.components.Speaker;
import computerstore.com.computerstore.respository.components.Impl.SpeakerRepositoryImpl;
import computerstore.com.computerstore.respository.components.SpeakerRepository;
import computerstore.com.computerstore.restapi.components.api.Impl.SpeakerAPIImpl;
import computerstore.com.computerstore.restapi.components.api.SpeakerAPI;
import computerstore.com.computerstore.services.components.SpeakerService;


public class SpeakerServiceImpl extends IntentService implements SpeakerService {
    private final SpeakerAPI api;
    private final SpeakerRepository repo;
    private final IBinder localBinder = new SpeakerServiceLocalBinder();

    public static final String ACTION_ADD = "computerstore.com.computerstore.services.components.Impl.action.ADD";
    public static final String ACTION_UPDATE = "computerstore.com.computerstore.services.components.Impl.action.UPDATE";

    public static final String EXTRA_ADD = "computerstore.com.computerstore.services.components.Impl.extra.ADD";
    public static final String EXTRA_UPDATE = "computerstore.com.computerstore.services.components.Impl.extra.UPDATE";

    private static SpeakerServiceImpl service = null;

    public static SpeakerServiceImpl getInstance() {
        if (service == null)
            service = new SpeakerServiceImpl();
        return service;
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        return localBinder;
    }

    public class SpeakerServiceLocalBinder extends Binder {
        public SpeakerServiceImpl getService() {
            return SpeakerServiceImpl.this;
        }
    }


    public SpeakerServiceImpl() {
        super("SpeakerServiceImpl");
        api = new SpeakerAPIImpl();
        repo = new SpeakerRepositoryImpl(App.getAppContext());
    }

    @Override
    public void addSpeaker(Context context, Speaker contact) {
        Intent intent = new Intent(context, SpeakerServiceImpl.class);
        intent.setAction(ACTION_ADD);
        intent.putExtra(EXTRA_UPDATE, contact);
        context.startService(intent);

    }

    @Override
    public void updateSpeaker(Context context, Speaker contact) {
        Intent intent = new Intent(context, SpeakerServiceImpl.class);
        intent.setAction(ACTION_UPDATE);
        intent.putExtra(EXTRA_UPDATE, contact);
        context.startService(intent);

    }

    @Override
    protected void onHandleIntent(Intent intent) {
        if (intent != null) {
            final String action = intent.getAction();
            if (ACTION_ADD.equals(action)) {
                final Speaker speaker = (Speaker) intent.getSerializableExtra(EXTRA_ADD);
                updateSpeaker(speaker);
            } else if (ACTION_UPDATE.equals(action)) {
                final Speaker speaker = (Speaker) intent.getSerializableExtra(EXTRA_UPDATE);
                postSpeaker(speaker);
            }
        }
    }

    public Speaker updateSpeaker(Speaker speaker) {
        //REMOTE UPADTE AND LOCAL UPDATE
        try {
            Speaker updatedContact = api.updateSpeaker(speaker);
            repo.save(updatedContact);
            return repo.save(updatedContact);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return repo.save(speaker);
    }

    public Speaker postSpeaker(Speaker speaker) {
        //POST AND LOCAL SAVE
        try {
            Speaker createdContact = api.createSpeaker(speaker);
            repo.save(createdContact);
            return repo.save(createdContact);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return repo.save(speaker);
    }

}
