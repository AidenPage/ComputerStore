package computerstore.com.computerstore.repository.sales;

import android.test.AndroidTestCase;
import junit.framework.Assert;


import java.util.Set;

import computerstore.com.computerstore.domain.sales.SalesComponents;
import computerstore.com.computerstore.respository.sales.Impl.SalesComponentsRepositoryImpl;
import computerstore.com.computerstore.respository.sales.SalesComponentsRepository;

/**
 * Created by Aidem on 2016/04/09.
 */
public class SalesComponentsRepositoryTest  extends AndroidTestCase {
    private static final String TAG="SETTINGS TEST";
    private Long id;

    public void testCreateReadUpdateDelete() throws Exception {
        SalesComponentsRepository repo = new SalesComponentsRepositoryImpl(this.getContext());
        // CREATE
        SalesComponents createEntity = new SalesComponents.Builder()
                .saleID("1234")
                .productNumber("1234")
                .amount(2)
                .build();
        SalesComponents insertedEntity = repo.save(createEntity);
        id=insertedEntity.getId();
        Assert.assertNotNull(TAG+" CREATE",insertedEntity);

        //READ ALL
        Set<SalesComponents> salesCom = repo.findAll();
        Assert.assertTrue(TAG+" READ ALL",salesCom.size()>0);

        //READ ENTITY
        SalesComponents entity = repo.findById(id);
        Assert.assertNotNull(TAG+" READ ENTITY",entity);



        //UPDATE ENTITY
        SalesComponents updateEntity = new SalesComponents.Builder()
                .SalesComponents(entity)
                .amount(1)
                .build();
        repo.update(updateEntity);
        SalesComponents newEntity = repo.findById(id);
        Assert.assertEquals(TAG+ " UPDATE ENTITY",1,newEntity.getAmount());

        // DELETE ENTITY
        repo.delete(updateEntity);
        SalesComponents deletedEntity = repo.findById(id);
        Assert.assertNull(TAG+" DELETE",deletedEntity);

    }

}
