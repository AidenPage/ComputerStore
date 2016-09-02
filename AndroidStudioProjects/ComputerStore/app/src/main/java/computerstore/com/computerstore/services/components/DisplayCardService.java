package computerstore.com.computerstore.services.components;

/**
 * Created by Aidem on 2016/04/17.
 */

import android.content.Context;

import computerstore.com.computerstore.domain.components.DisplayCard;
import computerstore.com.computerstore.respository.Repository;


public interface DisplayCardService {
    void addDisplayCard(Context context, DisplayCard displayCard);

    void updateDisplayCard(Context context, DisplayCard displayCard);

}
