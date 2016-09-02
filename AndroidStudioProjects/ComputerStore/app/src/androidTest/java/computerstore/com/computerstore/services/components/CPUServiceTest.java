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
import computerstore.com.computerstore.domain.components.CPU;
import computerstore.com.computerstore.services.components.Impl.CPUServiceImpl;

public class CPUServiceTest extends AndroidTestCase {
    private CPUServiceImpl cpuService;
    private boolean isBound;

    @Override
    public void setUp() throws Exception {
        super.setUp();
        Intent intent = new Intent(App.getAppContext(), CPUServiceImpl.class);
        App.getAppContext().bindService(intent, connection, Context.BIND_AUTO_CREATE);


    }

    private ServiceConnection connection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            CPUServiceImpl.CPUServiceLocalBinder binder
                    = (CPUServiceImpl.CPUServiceLocalBinder) service;
            cpuService = binder.getService();
            isBound = true;
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            isBound = false;

        }
    };

    public void testUpdateCPU() throws Exception {
        CPU cpu = new CPU.Builder()
                .productNumber("SGC-2100-KWN1")
                .stock(50)
                .description("COOLERMASTER CM STORM SCOUT 2")
                .price(2000.00)
                .build();
        CPU chas = cpuService.updateCPU(cpu);
        Assert.assertEquals(null, chas.getPrice());

    }

    public void testpostStorageDevice() throws Exception {
        CPU cpu = new CPU.Builder()
                .productNumber("SGC-2100-KWN1")
                .stock(50)
                .description("COOLERMASTER CM STORM SCOUT 2")
                .price(2000.00)
                .build();
        CPU chas = cpuService.updateCPU(cpu);
        Assert.assertEquals(null, chas.getPrice());

    }
}
