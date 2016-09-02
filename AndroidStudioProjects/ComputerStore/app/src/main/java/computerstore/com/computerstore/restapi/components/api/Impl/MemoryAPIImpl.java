package computerstore.com.computerstore.restapi.components.api.Impl;

/**
 * Created by Aidem on 2016/04/17.
 */
import computerstore.com.computerstore.conf.util.AppUtil;
import computerstore.com.computerstore.domain.components.Memory;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.RequestBody;
import com.google.gson.Gson;
import java.io.IOException;

import computerstore.com.computerstore.restapi.components.api.MemoryAPI;


public class MemoryAPIImpl implements MemoryAPI {
    private static final String postUrl = AppUtil.getBaserURI() + "api/droid/components/memory/post";
    private static final String updateUrl = AppUtil.getBaserURI() + "api/droid/components/memory/update";

    @Override
    public Memory createMemory(Memory memory) throws IOException {
        String json = new Gson().toJson(memory);
        RequestBody body = RequestBody.create(AppUtil.getJSONMediaType(), json);
        Request request = new Request.Builder()
                .url(postUrl)
                .post(body)
                .build();
        Response response = AppUtil.getConnection().newCall(request).execute();
        String value = response.body().string();
        Memory personAddress = new Gson().fromJson(value, Memory.class);
        return personAddress;
    }

    @Override
    public Memory updateMemory(Memory memory) throws IOException {
        String json = new Gson().toJson(memory);
        RequestBody body = RequestBody.create(AppUtil.getJSONMediaType(), json);
        Request request = new Request.Builder()
                .url(updateUrl)
                .post(body)
                .build();
        Response response = AppUtil.getConnection().newCall(request).execute();
        String value = response.body().string();
        Memory personAddress = new Gson().fromJson(value, Memory.class);
        return personAddress;
    }
}
