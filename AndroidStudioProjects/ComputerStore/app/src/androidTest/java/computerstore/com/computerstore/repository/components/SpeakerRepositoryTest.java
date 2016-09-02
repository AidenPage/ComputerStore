package computerstore.com.computerstore.repository.components;

import android.test.AndroidTestCase;
import junit.framework.Assert;


import java.util.Set;

import computerstore.com.computerstore.domain.components.Speaker;
import computerstore.com.computerstore.respository.components.Impl.SpeakerRepositoryImpl;
import computerstore.com.computerstore.respository.components.SpeakerRepository;

/**
 * Created by Aidem on 2016/04/09.
 */
public class SpeakerRepositoryTest  extends AndroidTestCase {
    private static final String TAG="Speaker test";
    private Long id;

    public void testCreateReadUpdateDelete() throws Exception {
        SpeakerRepository repo = new SpeakerRepositoryImpl(this.getContext());
        // CREATE
        Speaker createEntity = new Speaker.Builder()
                .stock(50)
                .description("COOLERMASTER CM STORM SCOUT 2")
                .price(1699.00)
                .build();
        Speaker insertedEntity = repo.save(createEntity);
        id=insertedEntity.getId();
        Assert.assertNotNull(TAG+" CREATE",insertedEntity);

        //READ ALL
        Set<Speaker> speaker = repo.findAll();
        Assert.assertTrue(TAG+" READ ALL",speaker.size()>0);

        //READ ENTITY
        Speaker entity = repo.findById(id);
        Assert.assertNotNull(TAG+" READ ENTITY",entity);



        //UPDATE ENTITY
        Speaker updateEntity = new Speaker.Builder()
                .Speaker(entity)
                .price(1599.00)
                .build();
        repo.update(updateEntity);
        Speaker newEntity = repo.findById(id);
        Assert.assertEquals(TAG+ " UPDATE ENTITY",1599.00,newEntity.getPrice());

        // DELETE ENTITY
        repo.delete(updateEntity);
        Speaker deletedEntity = repo.findById(id);
        Assert.assertNull(TAG+" DELETE",deletedEntity);

    }

}
