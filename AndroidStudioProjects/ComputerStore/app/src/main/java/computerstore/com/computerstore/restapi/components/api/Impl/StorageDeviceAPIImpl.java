package computerstore.com.computerstore.restapi.components.api.Impl;

/**
 * Created by Aidem on 2016/04/17.
 */

import computerstore.com.computerstore.conf.util.AppUtil;
import computerstore.com.computerstore.domain.components.StorageDevice;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.RequestBody;
import com.google.gson.Gson;
import java.io.IOException;

import computerstore.com.computerstore.restapi.components.api.StorageDeviceAPI;


public class StorageDeviceAPIImpl implements StorageDeviceAPI {
    private static final String postUrl = AppUtil.getBaserURI() + "api/droid/components/storageDevice/post";
    private static final String updateUrl = AppUtil.getBaserURI() + "api/droid/components/storageDevice/update";

    @Override
    public StorageDevice createStorageDevice(StorageDevice storageDevice) throws IOException {
        String json = new Gson().toJson(storageDevice);
        RequestBody body = RequestBody.create(AppUtil.getJSONMediaType(), json);
        Request request = new Request.Builder()
                .url(postUrl)
                .post(body)
                .build();
        Response response = AppUtil.getConnection().newCall(request).execute();
        String value = response.body().string();
        StorageDevice personAddress = new Gson().fromJson(value, StorageDevice.class);
        return personAddress;
    }

    @Override
    public StorageDevice updateStorageDevice(StorageDevice storageDevice) throws IOException {
        String json = new Gson().toJson(storageDevice);
        RequestBody body = RequestBody.create(AppUtil.getJSONMediaType(), json);
        Request request = new Request.Builder()
                .url(updateUrl)
                .post(body)
                .build();
        Response response = AppUtil.getConnection().newCall(request).execute();
        String value = response.body().string();
        StorageDevice personAddress = new Gson().fromJson(value, StorageDevice.class);
        return personAddress;
    }
}
