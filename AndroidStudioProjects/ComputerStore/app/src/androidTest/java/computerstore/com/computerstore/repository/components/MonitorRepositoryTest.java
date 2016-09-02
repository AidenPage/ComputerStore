package computerstore.com.computerstore.repository.components;

import android.test.AndroidTestCase;
import junit.framework.Assert;


import java.util.Set;

import computerstore.com.computerstore.domain.components.Monitor;
import computerstore.com.computerstore.respository.components.Impl.MonitorRepositoryImpl;
import computerstore.com.computerstore.respository.components.MonitorRepository;

/**
 * Created by Aidem on 2016/04/09.
 */
public class MonitorRepositoryTest  extends AndroidTestCase {
    private static final String TAG="Monitor test";
    private Long id;

    public void testCreateReadUpdateDelete() throws Exception {
        MonitorRepository repo = new MonitorRepositoryImpl(this.getContext());
        // CREATE
        Monitor createEntity = new Monitor.Builder()
                .stock(50)
                .description("COOLERMASTER CM STORM SCOUT 2")
                .price(1699.00)
                .build();
        Monitor insertedEntity = repo.save(createEntity);
        id=insertedEntity.getId();
        Assert.assertNotNull(TAG+" CREATE",insertedEntity);

        //READ ALL
        Set<Monitor> monitor = repo.findAll();
        Assert.assertTrue(TAG+" READ ALL",monitor.size()>0);

        //READ ENTITY
        Monitor entity = repo.findById(id);
        Assert.assertNotNull(TAG+" READ ENTITY",entity);



        //UPDATE ENTITY
        Monitor updateEntity = new Monitor.Builder()
                .Monitor(entity)
                .price(1599.00)
                .build();
        repo.update(updateEntity);
        Monitor newEntity = repo.findById(id);
        Assert.assertEquals(TAG+ " UPDATE ENTITY",1599.00,newEntity.getPrice());

        // DELETE ENTITY
        repo.delete(updateEntity);
        Monitor deletedEntity = repo.findById(id);
        Assert.assertNull(TAG+" DELETE",deletedEntity);

    }

}
