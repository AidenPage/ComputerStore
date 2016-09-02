package computerstore.com.computerstore.restapi.components.api.Impl;

/**
 * Created by Aidem on 2016/04/17.
 */

import computerstore.com.computerstore.conf.util.AppUtil;
import computerstore.com.computerstore.domain.components.OpticalDevices;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.RequestBody;
import com.google.gson.Gson;
import java.io.IOException;

import computerstore.com.computerstore.restapi.components.api.OpticalDevicesAPI;


public class OpticalDevicesAPIImpl implements OpticalDevicesAPI {
    private static final String postUrl = AppUtil.getBaserURI() + "api/droid/components/opticalDevices/post";
    private static final String updateUrl = AppUtil.getBaserURI() + "api/droid/components/opticalDevices/update";

    @Override
    public OpticalDevices createOpticalDevices(OpticalDevices opticalDevices) throws IOException {
        String json = new Gson().toJson(opticalDevices);
        RequestBody body = RequestBody.create(AppUtil.getJSONMediaType(), json);
        Request request = new Request.Builder()
                .url(postUrl)
                .post(body)
                .build();
        Response response = AppUtil.getConnection().newCall(request).execute();
        String value = response.body().string();
        OpticalDevices personAddress = new Gson().fromJson(value, OpticalDevices.class);
        return personAddress;
    }

    @Override
    public OpticalDevices updateOpticalDevices(OpticalDevices opticalDevices) throws IOException {
        String json = new Gson().toJson(opticalDevices);
        RequestBody body = RequestBody.create(AppUtil.getJSONMediaType(), json);
        Request request = new Request.Builder()
                .url(updateUrl)
                .post(body)
                .build();
        Response response = AppUtil.getConnection().newCall(request).execute();
        String value = response.body().string();
        OpticalDevices personAddress = new Gson().fromJson(value, OpticalDevices.class);
        return personAddress;
    }
}
