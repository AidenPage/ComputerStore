package computerstore.com.computerstore.repository.components;

import android.test.AndroidTestCase;
import junit.framework.Assert;


import java.util.Set;

import computerstore.com.computerstore.domain.components.Memory;
import computerstore.com.computerstore.respository.components.Impl.MemoryRepositoryImpl;
import computerstore.com.computerstore.respository.components.MemoryRepository;

/**
 * Created by Aidem on 2016/04/09.
 */
public class MemoryRepositoryTest  extends AndroidTestCase {
    private static final String TAG="Memory test";
    private Long id;

    public void testCreateReadUpdateDelete() throws Exception {
        MemoryRepository repo = new MemoryRepositoryImpl(this.getContext());
        // CREATE
        Memory createEntity = new Memory.Builder()
                .stock(50)
                .description("COOLERMASTER CM STORM SCOUT 2")
                .price(1699.00)
                .build();
        Memory insertedEntity = repo.save(createEntity);
        id=insertedEntity.getId();
        Assert.assertNotNull(TAG+" CREATE",insertedEntity);

        //READ ALL
        Set<Memory> memory = repo.findAll();
        Assert.assertTrue(TAG+" READ ALL",memory.size()>0);

        //READ ENTITY
        Memory entity = repo.findById(id);
        Assert.assertNotNull(TAG+" READ ENTITY",entity);



        //UPDATE ENTITY
        Memory updateEntity = new Memory.Builder()
                .Memory(entity)
                .price(1599.00)
                .build();
        repo.update(updateEntity);
        Memory newEntity = repo.findById(id);
        Assert.assertEquals(TAG+ " UPDATE ENTITY",1599.00,newEntity.getPrice());

        // DELETE ENTITY
        repo.delete(updateEntity);
        Memory deletedEntity = repo.findById(id);
        Assert.assertNull(TAG+" DELETE",deletedEntity);

    }

}
