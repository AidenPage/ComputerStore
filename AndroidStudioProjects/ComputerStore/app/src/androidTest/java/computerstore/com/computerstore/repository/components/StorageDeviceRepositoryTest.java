package computerstore.com.computerstore.repository.components;

import android.test.AndroidTestCase;
import junit.framework.Assert;


import java.util.Set;

import computerstore.com.computerstore.domain.components.StorageDevice;
import computerstore.com.computerstore.respository.components.Impl.StorageDeviceRepositoryImpl;
import computerstore.com.computerstore.respository.components.StorageDeviceRepository;

/**
 * Created by Aidem on 2016/04/09.
 */
public class StorageDeviceRepositoryTest  extends AndroidTestCase {
    private static final String TAG="Storage device test";
    private Long id;

    public void testCreateReadUpdateDelete() throws Exception {
        StorageDeviceRepository repo = new StorageDeviceRepositoryImpl(this.getContext());
        // CREATE
        StorageDevice createEntity = new StorageDevice.Builder()
                .stock(50)
                .description("COOLERMASTER CM STORM SCOUT 2")
                .price(1699.00)
                .build();
        StorageDevice insertedEntity = repo.save(createEntity);
        id=insertedEntity.getId();
        Assert.assertNotNull(TAG+" CREATE",insertedEntity);

        //READ ALL
        Set<StorageDevice> storageDevice = repo.findAll();
        Assert.assertTrue(TAG+" READ ALL",storageDevice.size()>0);

        //READ ENTITY
        StorageDevice entity = repo.findById(id);
        Assert.assertNotNull(TAG+" READ ENTITY",entity);



        //UPDATE ENTITY
        StorageDevice updateEntity = new StorageDevice.Builder()
                .StorageDevice(entity)
                .price(1599.00)
                .build();
        repo.update(updateEntity);
        StorageDevice newEntity = repo.findById(id);
        Assert.assertEquals(TAG+ " UPDATE ENTITY",1599.00,newEntity.getPrice());

        // DELETE ENTITY
        repo.delete(updateEntity);
        StorageDevice deletedEntity = repo.findById(id);
        Assert.assertNull(TAG+" DELETE",deletedEntity);

    }

}
