package computerstore.com.computerstore.services.components;
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.test.AndroidTestCase;

import org.junit.Assert;

import computerstore.com.computerstore.conf.util.App;
import computerstore.com.computerstore.domain.components.Memory;
import computerstore.com.computerstore.services.components.Impl.MemoryServiceImpl;

public class MemoryServiceTest extends AndroidTestCase {
    private MemoryServiceImpl memoryService;
    private boolean isBound;

    @Override
    public void setUp() throws Exception {
        super.setUp();
        Intent intent = new Intent(App.getAppContext(), MemoryServiceImpl.class);
        App.getAppContext().bindService(intent, connection, Context.BIND_AUTO_CREATE);


    }

    private ServiceConnection connection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            MemoryServiceImpl.MemoryServiceLocalBinder binder
                    = (MemoryServiceImpl.MemoryServiceLocalBinder) service;
            memoryService = binder.getService();
            isBound = true;
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            isBound = false;

        }
    };

    public void testUpdateMemory() throws Exception {
        Memory memory = new Memory.Builder()
                .productNumber("SGC-2100-KWN1")
                .stock(50)
                .description("COOLERMASTER CM STORM SCOUT 2")
                .price(2000.00)
                .build();
        Memory chas = memoryService.updateMemory(memory);
        Assert.assertEquals(null, chas.getPrice());

    }

    public void testpostStorageDevice() throws Exception {
        Memory memory = new Memory.Builder()
                .productNumber("SGC-2100-KWN1")
                .stock(50)
                .description("COOLERMASTER CM STORM SCOUT 2")
                .price(2000.00)
                .build();
        Memory chas = memoryService.updateMemory(memory);
        Assert.assertEquals(null, chas.getPrice());

    }
}
