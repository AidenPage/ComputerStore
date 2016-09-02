package computerstore.com.computerstore.services.components;

/**
 * Created by Aidem on 2016/04/17.
 */

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.test.AndroidTestCase;

import org.junit.Assert;

import computerstore.com.computerstore.conf.util.App;
import computerstore.com.computerstore.domain.components.Mainboard;
import computerstore.com.computerstore.services.components.Impl.MainboardServiceImpl;

public class MainboardServiceTest extends AndroidTestCase {
    private MainboardServiceImpl mainboardService;
    private boolean isBound;

    @Override
    public void setUp() throws Exception {
        super.setUp();
        Intent intent = new Intent(App.getAppContext(), MainboardServiceImpl.class);
        App.getAppContext().bindService(intent, connection, Context.BIND_AUTO_CREATE);


    }

    private ServiceConnection connection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            MainboardServiceImpl.MainboardServiceLocalBinder binder
                    = (MainboardServiceImpl.MainboardServiceLocalBinder) service;
            mainboardService = binder.getService();
            isBound = true;
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            isBound = false;

        }
    };

    public void testUpdateMainboard() throws Exception {
        Mainboard mainboard = new Mainboard.Builder()
                .productNumber("SGC-2100-KWN1")
                .stock(50)
                .description("COOLERMASTER CM STORM SCOUT 2")
                .price(2000.00)
                .build();
        Mainboard chas = mainboardService.updateMainboard(mainboard);
        Assert.assertEquals(null, chas.getPrice());

    }

    public void testpostStorageDevice() throws Exception {
        Mainboard mainboard = new Mainboard.Builder()
                .productNumber("SGC-2100-KWN1")
                .stock(50)
                .description("COOLERMASTER CM STORM SCOUT 2")
                .price(2000.00)
                .build();
        Mainboard chas = mainboardService.updateMainboard(mainboard);
        Assert.assertEquals(null, chas.getPrice());

    }
}

