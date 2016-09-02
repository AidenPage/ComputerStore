package computerstore.com.computerstore.repository.components;

import android.test.AndroidTestCase;
import junit.framework.Assert;


import java.util.Set;

import computerstore.com.computerstore.domain.components.DisplayCard;
import computerstore.com.computerstore.respository.components.Impl.DisplayCardRepositoryImpl;
import computerstore.com.computerstore.respository.components.DisplayCardRepository;

/**
 * Created by Aidem on 2016/04/09.
 */
public class DisplayCardRepositoryTest extends AndroidTestCase {
    private static final String TAG="Display card test";
    private Long id;

    public void testCreateReadUpdateDelete() throws Exception {
        DisplayCardRepository repo = new DisplayCardRepositoryImpl(this.getContext());
        // CREATE
        DisplayCard createEntity = new DisplayCard.Builder()
                .stock(50)
                .description("COOLERMASTER CM STORM SCOUT 2")
                .price(1699.00)
                .build();
        DisplayCard insertedEntity = repo.save(createEntity);
        id=insertedEntity.getId();
        Assert.assertNotNull(TAG+" CREATE",insertedEntity);

        //READ ALL
        Set<DisplayCard> displayCard = repo.findAll();
        Assert.assertTrue(TAG+" READ ALL",displayCard.size()>0);

        //READ ENTITY
        DisplayCard entity = repo.findById(id);
        Assert.assertNotNull(TAG+" READ ENTITY",entity);



        //UPDATE ENTITY
        DisplayCard updateEntity = new DisplayCard.Builder()
                .DisplayCard(entity)
                .price(1599.00)
                .build();
        repo.update(updateEntity);
        DisplayCard newEntity = repo.findById(id);
        Assert.assertEquals(TAG+ " UPDATE ENTITY",1599.00,newEntity.getPrice());

        // DELETE ENTITY
        repo.delete(updateEntity);
        DisplayCard deletedEntity = repo.findById(id);
        Assert.assertNull(TAG+" DELETE",deletedEntity);

    }

}
