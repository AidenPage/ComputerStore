package computerstore.com.computerstore.restapi.components.api.Impl;

/**
 * Created by Aidem on 2016/04/17.
 */

import okhttp3.Request;
import okhttp3.Response;
import com.google.gson.Gson;
import java.io.IOException;
import okhttp3.RequestBody;

import computerstore.com.computerstore.conf.util.AppUtil;
import computerstore.com.computerstore.domain.components.Chassis;
import computerstore.com.computerstore.restapi.components.api.ChassisAPI;



public class ChassisAPIImpl implements ChassisAPI {

    private static final String postUrl = AppUtil.getBaserURI() + "api/droid/components/chassis/post";
    private static final String updateUrl = AppUtil.getBaserURI() + "api/droid/components/chassis/update";

    @Override
    public Chassis createChassis(Chassis chassis) throws IOException {
        String json = new Gson().toJson(chassis);
        RequestBody body = RequestBody.create(AppUtil.getJSONMediaType(), json);
        Request request = new Request.Builder()
                .url(postUrl)
                .post(body)
                .build();
        Response response = AppUtil.getConnection().newCall(request).execute();
        String value = response.body().string();
        Chassis personAddress = new Gson().fromJson(value, Chassis.class);
        return personAddress;
    }

    @Override
    public Chassis updateChassis(Chassis chassis) throws IOException {
        String json = new Gson().toJson(chassis);
        RequestBody body = RequestBody.create(AppUtil.getJSONMediaType(), json);
        Request request = new Request.Builder()
                .url(updateUrl)
                .post(body)
                .build();
        Response response = AppUtil.getConnection().newCall(request).execute();
        String value = response.body().string();
        Chassis personAddress = new Gson().fromJson(value, Chassis.class);
        return personAddress;
    }
}
