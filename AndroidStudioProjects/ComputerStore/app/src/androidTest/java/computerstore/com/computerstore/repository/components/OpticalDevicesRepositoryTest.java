package computerstore.com.computerstore.repository.components;

import android.test.AndroidTestCase;
import junit.framework.Assert;


import java.util.Set;

import computerstore.com.computerstore.domain.components.OpticalDevices;
import computerstore.com.computerstore.respository.components.Impl.OpticalDevicesRepositoryImpl;
import computerstore.com.computerstore.respository.components.OpticalDevicesRepository;

/**
 * Created by Aidem on 2016/04/09.
 */
public class OpticalDevicesRepositoryTest  extends AndroidTestCase {
    private static final String TAG="Optical devices test ";
    private Long id;

    public void testCreateReadUpdateDelete() throws Exception {
        OpticalDevicesRepository repo = new OpticalDevicesRepositoryImpl(this.getContext());
        // CREATE
        OpticalDevices createEntity = new OpticalDevices.Builder()
                .stock(50)
                .description("COOLERMASTER CM STORM SCOUT 2")
                .price(1699.00)
                .build();
        OpticalDevices insertedEntity = repo.save(createEntity);
        id=insertedEntity.getId();
        Assert.assertNotNull(TAG+" CREATE",insertedEntity);

        //READ ALL
        Set<OpticalDevices> opticalDevices = repo.findAll();
        Assert.assertTrue(TAG+" READ ALL",opticalDevices.size()>0);

        //READ ENTITY
        OpticalDevices entity = repo.findById(id);
        Assert.assertNotNull(TAG+" READ ENTITY",entity);



        //UPDATE ENTITY
        OpticalDevices updateEntity = new OpticalDevices.Builder()
                .OpticalDevices(entity)
                .price(1599.00)
                .build();
        repo.update(updateEntity);
        OpticalDevices newEntity = repo.findById(id);
        Assert.assertEquals(TAG+ " UPDATE ENTITY",1599.00,newEntity.getPrice());

        // DELETE ENTITY
        repo.delete(updateEntity);
        OpticalDevices deletedEntity = repo.findById(id);
        Assert.assertNull(TAG+" DELETE",deletedEntity);

    }

}
