package computerstore.com.computerstore.restapi.components.api.Impl;

/**
 * Created by Aidem on 2016/04/17.
 */

import computerstore.com.computerstore.conf.util.AppUtil;
import computerstore.com.computerstore.domain.components.Speaker;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.RequestBody;
import com.google.gson.Gson;
import java.io.IOException;

import computerstore.com.computerstore.restapi.components.api.SpeakerAPI;


public class SpeakerAPIImpl implements SpeakerAPI {
    private static final String postUrl = AppUtil.getBaserURI() + "api/droid/components/speaker/post";
    private static final String updateUrl = AppUtil.getBaserURI() + "api/droid/components/speaker/update";

    @Override
    public Speaker createSpeaker(Speaker speaker) throws IOException {
        String json = new Gson().toJson(speaker);
        RequestBody body = RequestBody.create(AppUtil.getJSONMediaType(), json);
        Request request = new Request.Builder()
                .url(postUrl)
                .post(body)
                .build();
        Response response = AppUtil.getConnection().newCall(request).execute();
        String value = response.body().string();
        Speaker personAddress = new Gson().fromJson(value, Speaker.class);
        return personAddress;
    }

    @Override
    public Speaker updateSpeaker(Speaker speaker) throws IOException {
        String json = new Gson().toJson(speaker);
        RequestBody body = RequestBody.create(AppUtil.getJSONMediaType(), json);
        Request request = new Request.Builder()
                .url(updateUrl)
                .post(body)
                .build();
        Response response = AppUtil.getConnection().newCall(request).execute();
        String value = response.body().string();
        Speaker personAddress = new Gson().fromJson(value, Speaker.class);
        return personAddress;
    }
}
