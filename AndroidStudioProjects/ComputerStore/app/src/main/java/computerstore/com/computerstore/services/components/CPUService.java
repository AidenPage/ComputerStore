package computerstore.com.computerstore.services.components;

/**
 * Created by Aidem on 2016/04/17.
 */

import android.content.Context;

import computerstore.com.computerstore.domain.components.CPU;

public interface CPUService{
    void addCPU(Context context, CPU cpu);

    void updateCPU(Context context, CPU cpu);
}
