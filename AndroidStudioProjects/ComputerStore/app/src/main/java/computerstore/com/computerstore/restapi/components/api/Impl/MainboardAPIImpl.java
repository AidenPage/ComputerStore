package computerstore.com.computerstore.restapi.components.api.Impl;

/**
 * Created by Aidem on 2016/04/17.
 */

import computerstore.com.computerstore.conf.util.AppUtil;
import computerstore.com.computerstore.domain.components.Mainboard;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.RequestBody;
import com.google.gson.Gson;
import java.io.IOException;

import computerstore.com.computerstore.restapi.components.api.MainboardAPI;


public class MainboardAPIImpl implements MainboardAPI {
    private static final String postUrl = AppUtil.getBaserURI() + "api/droid/components/mainboard/post";
    private static final String updateUrl = AppUtil.getBaserURI() + "api/droid/components/mainboard/update";

    @Override
    public Mainboard createMainboard(Mainboard mainboard) throws IOException {
        String json = new Gson().toJson(mainboard);
        RequestBody body = RequestBody.create(AppUtil.getJSONMediaType(), json);
        Request request = new Request.Builder()
                .url(postUrl)
                .post(body)
                .build();
        Response response = AppUtil.getConnection().newCall(request).execute();
        String value = response.body().string();
        Mainboard personAddress = new Gson().fromJson(value, Mainboard.class);
        return personAddress;
    }

    @Override
    public Mainboard updateMainboard(Mainboard mainboard) throws IOException {
        String json = new Gson().toJson(mainboard);
        RequestBody body = RequestBody.create(AppUtil.getJSONMediaType(), json);
        Request request = new Request.Builder()
                .url(updateUrl)
                .post(body)
                .build();
        Response response = AppUtil.getConnection().newCall(request).execute();
        String value = response.body().string();
        Mainboard personAddress = new Gson().fromJson(value, Mainboard.class);
        return personAddress;
    }
}
