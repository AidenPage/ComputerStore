package computerstore.com.computerstore.services.components;

/**
 * Created by Aidem on 2016/04/17.
 */

import android.content.Context;

import computerstore.com.computerstore.domain.components.Chassis;


public interface ChassisService {

    void addChassis(Context context, Chassis chassis);

    void updateChassis(Context context, Chassis chassis);
}
