package computerstore.com.computerstore.repository.employees;

import android.test.AndroidTestCase;
import junit.framework.Assert;


import java.util.Set;

import computerstore.com.computerstore.domain.employees.Employees;
import computerstore.com.computerstore.respository.employees.Impl.EmployeesRepositoryImpl;
import computerstore.com.computerstore.respository.employees.EmployeesRepository;

/**
 * Created by Aidem on 2016/04/09.
 */
public class EmployeesRepositoryTest  extends AndroidTestCase {
    private static final String TAG="SETTINGS TEST";
    private Long id;

    public void testCreateReadUpdateDelete() throws Exception {
        EmployeesRepository repo = new EmployeesRepositoryImpl(this.getContext());
        // CREATE
        Employees createEntity = new Employees.Builder()
                .empName("Aiden")
                .empSurname("Page")
                .empJob("Cashier")
                .build();
        Employees insertedEntity = repo.save(createEntity);
        id=insertedEntity.getId();
        Assert.assertNotNull(TAG+" CREATE",insertedEntity);

        //READ ALL
        Set<Employees> emp = repo.findAll();
        Assert.assertTrue(TAG+" READ ALL",emp.size()>0);

        //READ ENTITY
        Employees entity = repo.findById(id);
        Assert.assertNotNull(TAG+" READ ENTITY",entity);



        //UPDATE ENTITY
        Employees updateEntity = new Employees.Builder()
                .Employees(entity)
                .empJob("Manager")
                .build();
        repo.update(updateEntity);
        Employees newEntity = repo.findById(id);
        Assert.assertEquals(TAG+ " UPDATE ENTITY","Manager",newEntity.getEmpJob());

        // DELETE ENTITY
        repo.delete(updateEntity);
        Employees deletedEntity = repo.findById(id);
        Assert.assertNull(TAG+" DELETE",deletedEntity);

    }

}
