package computerstore.com.computerstore.respository;

/**
 * Created by Aidem on 2016/04/17.
 */

import java.util.Set;

public interface Repository<E, ID> {

    E findById(ID id);

    E save(E entity);

    E update(E entity);

    E delete(E entity);

    Set<E> findAll();

    int deleteAll();
}