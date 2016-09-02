package computerstore.com.computerstore.restapi.components.api.Impl;

/**
 * Created by Aidem on 2016/04/17.
 */

import computerstore.com.computerstore.conf.util.AppUtil;
import computerstore.com.computerstore.domain.components.Monitor;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.RequestBody;
import com.google.gson.Gson;
import java.io.IOException;

import computerstore.com.computerstore.restapi.components.api.MonitorAPI;


public class MonitorAPIImpl implements MonitorAPI {
    private static final String postUrl = AppUtil.getBaserURI() + "api/droid/components/monitor/post";
    private static final String updateUrl = AppUtil.getBaserURI() + "api/droid/components/monitor/update";

    @Override
    public Monitor createMonitor(Monitor monitor) throws IOException {
        String json = new Gson().toJson(monitor);
        RequestBody body = RequestBody.create(AppUtil.getJSONMediaType(), json);
        Request request = new Request.Builder()
                .url(postUrl)
                .post(body)
                .build();
        Response response = AppUtil.getConnection().newCall(request).execute();
        String value = response.body().string();
        Monitor personAddress = new Gson().fromJson(value, Monitor.class);
        return personAddress;
    }

    @Override
    public Monitor updateMonitor(Monitor monitor) throws IOException {
        String json = new Gson().toJson(monitor);
        RequestBody body = RequestBody.create(AppUtil.getJSONMediaType(), json);
        Request request = new Request.Builder()
                .url(updateUrl)
                .post(body)
                .build();
        Response response = AppUtil.getConnection().newCall(request).execute();
        String value = response.body().string();
        Monitor personAddress = new Gson().fromJson(value, Monitor.class);
        return personAddress;
    }
}
