package computerstore.com.computerstore.services.components;

/**
 * Created by Aidem on 2016/04/17.
 */

import android.content.Context;

import computerstore.com.computerstore.domain.components.Mainboard;
import computerstore.com.computerstore.respository.Repository;

public interface MainboardService{

    void addMainboard(Context context, Mainboard mainboard);

    void updateMainboard(Context context, Mainboard mainboard);
}

