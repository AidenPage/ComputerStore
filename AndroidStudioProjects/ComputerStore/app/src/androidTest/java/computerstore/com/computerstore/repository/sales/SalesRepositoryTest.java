package computerstore.com.computerstore.repository.sales;

import android.test.AndroidTestCase;
import junit.framework.Assert;


import java.util.Set;

import computerstore.com.computerstore.domain.sales.Sales;
import computerstore.com.computerstore.respository.sales.Impl.SalesRepositoryImpl;
import computerstore.com.computerstore.respository.sales.SalesRepository;

/**
 * Created by Aidem on 2016/04/09.
 */
public class SalesRepositoryTest  extends AndroidTestCase {
    private static final String TAG="SETTINGS TEST";
    private Long id;

    public void testCreateReadUpdateDelete() throws Exception {
        SalesRepository repo = new SalesRepositoryImpl(this.getContext());
        // CREATE
        Sales createEntity = new Sales.Builder()
                .empID(211121614)
                .date("2016/04/03")
                .totalSales(5000.00)
                .discount(100.00)
                .build();
        Sales insertedEntity = repo.save(createEntity);
        id=insertedEntity.getId();
        Assert.assertNotNull(TAG+" CREATE",insertedEntity);

        //READ ALL
        Set<Sales> sales = repo.findAll();
        Assert.assertTrue(TAG+" READ ALL",sales.size()>0);

        //READ ENTITY
        Sales entity = repo.findById(id);
        Assert.assertNotNull(TAG+" READ ENTITY",entity);



        //UPDATE ENTITY
        Sales updateEntity = new Sales.Builder()
                .Sales(entity)
                .discount(200.00)
                .build();
        repo.update(updateEntity);
        Sales newEntity = repo.findById(id);
        Assert.assertEquals(TAG+ " UPDATE ENTITY",200.00,newEntity.getDiscount());

        // DELETE ENTITY
        repo.delete(updateEntity);
        Sales deletedEntity = repo.findById(id);
        Assert.assertNull(TAG+" DELETE",deletedEntity);

    }

}
