package computerstore.com.computerstore.services.components;

/**
 * Created by Aidem on 2016/04/17.
 */

import android.content.Context;

import computerstore.com.computerstore.domain.components.Speaker;
import computerstore.com.computerstore.respository.Repository;

public interface SpeakerService{

    void addSpeaker(Context context, Speaker speaker);

    void updateSpeaker(Context context, Speaker speaker);
}

