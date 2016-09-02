package computerstore.com.computerstore.restapi.components.api;

/**
 * Created by Aidem on 2016/04/17.
 */

import java.io.IOException;

import computerstore.com.computerstore.domain.components.Speaker;

public interface SpeakerAPI {

    Speaker createSpeaker(Speaker speaker) throws IOException;

    Speaker updateSpeaker(Speaker speaker) throws IOException;
}

