package computerstore.com.computerstore.restapi.components.api.Impl;

/**
 * Created by Aidem on 2016/04/17.
 */

import computerstore.com.computerstore.conf.util.AppUtil;
import computerstore.com.computerstore.domain.components.DisplayCard;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.RequestBody;
import com.google.gson.Gson;
import java.io.IOException;

import computerstore.com.computerstore.restapi.components.api.DisplayCardAPI;


public class DisplayCardAPIImpl implements DisplayCardAPI {
    private static final String postUrl = AppUtil.getBaserURI() + "api/droid/components/displayCard/post";
    private static final String updateUrl = AppUtil.getBaserURI() + "api/droid/components/displayCard/update";

    @Override
    public DisplayCard createDisplayCard(DisplayCard displayCard) throws IOException {
        String json = new Gson().toJson(displayCard);
        RequestBody body = RequestBody.create(AppUtil.getJSONMediaType(), json);
        Request request = new Request.Builder()
                .url(postUrl)
                .post(body)
                .build();
        Response response = AppUtil.getConnection().newCall(request).execute();
        String value = response.body().string();
        DisplayCard personAddress = new Gson().fromJson(value, DisplayCard.class);
        return personAddress;
    }

    @Override
    public DisplayCard updateDisplayCard(DisplayCard displayCard) throws IOException {
        String json = new Gson().toJson(displayCard);
        RequestBody body = RequestBody.create(AppUtil.getJSONMediaType(), json);
        Request request = new Request.Builder()
                .url(updateUrl)
                .post(body)
                .build();
        Response response = AppUtil.getConnection().newCall(request).execute();
        String value = response.body().string();
        DisplayCard personAddress = new Gson().fromJson(value, DisplayCard.class);
        return personAddress;
    }

}
