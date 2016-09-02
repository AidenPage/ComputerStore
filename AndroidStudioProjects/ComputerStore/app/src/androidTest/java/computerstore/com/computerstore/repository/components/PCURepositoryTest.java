package computerstore.com.computerstore.repository.components;

import android.test.AndroidTestCase;
import junit.framework.Assert;


import java.util.Set;

import computerstore.com.computerstore.domain.components.PCU;
import computerstore.com.computerstore.respository.components.Impl.PCURepositoryImpl;
import computerstore.com.computerstore.respository.components.PCURepository;

/**
 * Created by Aidem on 2016/04/09.
 */
public class PCURepositoryTest  extends AndroidTestCase {
    private static final String TAG="PCU test";
    private Long id;

    public void testCreateReadUpdateDelete() throws Exception {
        PCURepository repo = new PCURepositoryImpl(this.getContext());
        // CREATE
        PCU createEntity = new PCU.Builder()
                .stock(50)
                .description("COOLERMASTER CM STORM SCOUT 2")
                .price(1699.00)
                .build();
        PCU insertedEntity = repo.save(createEntity);
        id=insertedEntity.getId();
        Assert.assertNotNull(TAG+" CREATE",insertedEntity);

        //READ ALL
        Set<PCU> pcu = repo.findAll();
        Assert.assertTrue(TAG+" READ ALL",pcu.size()>0);

        //READ ENTITY
        PCU entity = repo.findById(id);
        Assert.assertNotNull(TAG+" READ ENTITY",entity);



        //UPDATE ENTITY
        PCU updateEntity = new PCU.Builder()
                .PCU(entity)
                .price(1599.00)
                .build();
        repo.update(updateEntity);
        PCU newEntity = repo.findById(id);
        Assert.assertEquals(TAG+ " UPDATE ENTITY",1599.00,newEntity.getPrice());

        // DELETE ENTITY
        repo.delete(updateEntity);
        PCU deletedEntity = repo.findById(id);
        Assert.assertNull(TAG+" DELETE",deletedEntity);

    }

}
