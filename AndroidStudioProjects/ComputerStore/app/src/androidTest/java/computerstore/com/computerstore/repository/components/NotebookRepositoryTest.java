package computerstore.com.computerstore.repository.components;

import android.test.AndroidTestCase;
import junit.framework.Assert;


import java.util.Set;

import computerstore.com.computerstore.domain.components.Notebook;
import computerstore.com.computerstore.respository.components.Impl.NotebookRepositoryImpl;
import computerstore.com.computerstore.respository.components.NotebookRepository;

/**
 * Created by Aidem on 2016/04/09.
 */
public class NotebookRepositoryTest  extends AndroidTestCase {
    private static final String TAG="Notebook test";
    private Long id;

    public void testCreateReadUpdateDelete() throws Exception {
        NotebookRepository repo = new NotebookRepositoryImpl(this.getContext());
        // CREATE
        Notebook createEntity = new Notebook.Builder()
                .stock(50)
                .description("COOLERMASTER CM STORM SCOUT 2")
                .price(1699.00)
                .build();
        Notebook insertedEntity = repo.save(createEntity);
        id=insertedEntity.getId();
        Assert.assertNotNull(TAG+" CREATE",insertedEntity);

        //READ ALL
        Set<Notebook> notebook = repo.findAll();
        Assert.assertTrue(TAG+" READ ALL",notebook.size()>0);

        //READ ENTITY
        Notebook entity = repo.findById(id);
        Assert.assertNotNull(TAG+" READ ENTITY",entity);



        //UPDATE ENTITY
        Notebook updateEntity = new Notebook.Builder()
                .Notebook(entity)
                .price(1599.00)
                .build();
        repo.update(updateEntity);
        Notebook newEntity = repo.findById(id);
        Assert.assertEquals(TAG+ " UPDATE ENTITY",1599.00,newEntity.getPrice());

        // DELETE ENTITY
        repo.delete(updateEntity);
        Notebook deletedEntity = repo.findById(id);
        Assert.assertNull(TAG+" DELETE",deletedEntity);

    }

}
