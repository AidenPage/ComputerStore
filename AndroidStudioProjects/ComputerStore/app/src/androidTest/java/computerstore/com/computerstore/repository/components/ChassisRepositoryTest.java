package computerstore.com.computerstore.repository.components;

import android.test.AndroidTestCase;
import junit.framework.Assert;


import java.util.Set;

import computerstore.com.computerstore.domain.components.Chassis;
import computerstore.com.computerstore.respository.components.Impl.ChassisRepositoryImpl;
import computerstore.com.computerstore.respository.components.ChassisRepository;

/**
 * Created by Aidem on 2016/04/09.
 */
public class ChassisRepositoryTest  extends AndroidTestCase {
    private static final String TAG="Chassis test";
    private Long id;

    public void testCreateReadUpdateDelete() throws Exception {
        ChassisRepository repo = new ChassisRepositoryImpl(this.getContext());
        // CREATE
        Chassis createEntity = new Chassis.Builder()
                .stock(50)
                .description("COOLERMASTER CM STORM SCOUT 2")
                .price(1699.00)
                .build();
        Chassis insertedEntity = repo.save(createEntity);
        id=insertedEntity.getId();
        Assert.assertNotNull(TAG+" CREATE",insertedEntity);

        //READ ALL
        Set<Chassis> chassis = repo.findAll();
        Assert.assertTrue(TAG+" READ ALL",chassis.size()>0);

        //READ ENTITY
        Chassis entity = repo.findById(id);
        Assert.assertNotNull(TAG+" READ ENTITY",entity);



        //UPDATE ENTITY
        Chassis updateEntity = new Chassis.Builder()
                .Chassis(entity)
                .price(1599.00)
                .build();
        repo.update(updateEntity);
        Chassis newEntity = repo.findById(id);
        Assert.assertEquals(TAG+ " UPDATE ENTITY",1599.00,newEntity.getPrice());

        // DELETE ENTITY
        repo.delete(updateEntity);
        Chassis deletedEntity = repo.findById(id);
        Assert.assertNull(TAG+" DELETE",deletedEntity);

    }

}
