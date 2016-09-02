package computerstore.com.computerstore.restapi.components.api;

/**
 * Created by Aidem on 2016/04/17.
 */

import java.io.IOException;

import computerstore.com.computerstore.domain.components.DisplayCard;
import computerstore.com.computerstore.respository.Repository;


public interface DisplayCardAPI {
    DisplayCard createDisplayCard(DisplayCard displayCard) throws IOException;

    DisplayCard updateDisplayCard(DisplayCard displayCard) throws IOException;

}
