package computerstore.com.computerstore.repository.components;

import android.test.AndroidTestCase;
import junit.framework.Assert;


import java.util.Set;

import computerstore.com.computerstore.domain.components.Printer;
import computerstore.com.computerstore.respository.components.Impl.PrinterRepositoryImpl;
import computerstore.com.computerstore.respository.components.PrinterRepository;

/**
 * Created by Aidem on 2016/04/09.
 */
public class PrinterRepositoryTest  extends AndroidTestCase {
    private static final String TAG="Printer test";
    private Long id;

    public void testCreateReadUpdateDelete() throws Exception {
        PrinterRepository repo = new PrinterRepositoryImpl(this.getContext());
        // CREATE
        Printer createEntity = new Printer.Builder()
                .stock(50)
                .description("COOLERMASTER CM STORM SCOUT 2")
                .price(1699.00)
                .build();
        Printer insertedEntity = repo.save(createEntity);
        id=insertedEntity.getId();
        Assert.assertNotNull(TAG+" CREATE",insertedEntity);

        //READ ALL
        Set<Printer> printer = repo.findAll();
        Assert.assertTrue(TAG+" READ ALL",printer.size()>0);

        //READ ENTITY
        Printer entity = repo.findById(id);
        Assert.assertNotNull(TAG+" READ ENTITY",entity);



        //UPDATE ENTITY
        Printer updateEntity = new Printer.Builder()
                .Printer(entity)
                .price(1599.00)
                .build();
        repo.update(updateEntity);
        Printer newEntity = repo.findById(id);
        Assert.assertEquals(TAG+ " UPDATE ENTITY",1599.00,newEntity.getPrice());

        // DELETE ENTITY
        repo.delete(updateEntity);
        Printer deletedEntity = repo.findById(id);
        Assert.assertNull(TAG+" DELETE",deletedEntity);

    }

}
