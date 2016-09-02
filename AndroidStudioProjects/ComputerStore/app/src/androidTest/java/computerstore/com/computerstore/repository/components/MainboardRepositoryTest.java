package computerstore.com.computerstore.repository.components;

import android.test.AndroidTestCase;
import junit.framework.Assert;


import java.util.Set;

import computerstore.com.computerstore.domain.components.Mainboard;
import computerstore.com.computerstore.respository.components.Impl.MainboardRepositoryImpl;
import computerstore.com.computerstore.respository.components.MainboardRepository;

/**
 * Created by Aidem on 2016/04/09.
 */
public class MainboardRepositoryTest  extends AndroidTestCase {
    private static final String TAG="Mainboard test";
    private Long id;

    public void testCreateReadUpdateDelete() throws Exception {
        MainboardRepository repo = new MainboardRepositoryImpl(this.getContext());
        // CREATE
        Mainboard createEntity = new Mainboard.Builder()
                .stock(50)
                .description("COOLERMASTER CM STORM SCOUT 2")
                .price(1699.00)
                .build();
        Mainboard insertedEntity = repo.save(createEntity);
        id=insertedEntity.getId();
        Assert.assertNotNull(TAG+" CREATE",insertedEntity);

        //READ ALL
        Set<Mainboard> mainboard = repo.findAll();
        Assert.assertTrue(TAG+" READ ALL",mainboard.size()>0);

        //READ ENTITY
        Mainboard entity = repo.findById(id);
        Assert.assertNotNull(TAG+" READ ENTITY",entity);



        //UPDATE ENTITY
        Mainboard updateEntity = new Mainboard.Builder()
                .Mainboard(entity)
                .price(1599.00)
                .build();
        repo.update(updateEntity);
        Mainboard newEntity = repo.findById(id);
        Assert.assertEquals(TAG+ " UPDATE ENTITY",1599.00,newEntity.getPrice());

        // DELETE ENTITY
        repo.delete(updateEntity);
        Mainboard deletedEntity = repo.findById(id);
        Assert.assertNull(TAG+" DELETE",deletedEntity);

    }

}
