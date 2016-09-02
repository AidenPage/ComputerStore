package computerstore.com.computerstore.restapi.components.api.Impl;

/**
 * Created by Aidem on 2016/04/17.
 */

import computerstore.com.computerstore.conf.util.AppUtil;
import computerstore.com.computerstore.domain.components.CPU;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import com.google.gson.Gson;
import java.io.IOException;

import computerstore.com.computerstore.restapi.components.api.CPUAPI;


public class CPUAPIImpl implements CPUAPI {
    private static final String postUrl = AppUtil.getBaserURI() + "api/droid/components/cpu/post";
    private static final String updateUrl = AppUtil.getBaserURI() + "api/droid/components/cpu/update";

    @Override
    public CPU createCPU(CPU cpu) throws IOException {
        String json = new Gson().toJson(cpu);
        RequestBody body = RequestBody.create(AppUtil.getJSONMediaType(), json);
        Request request = new Request.Builder()
                .url(postUrl)
                .post(body)
                .build();
        Response response = AppUtil.getConnection().newCall(request).execute();
        String value = response.body().string();
        CPU personAddress = new Gson().fromJson(value, CPU.class);
        return personAddress;
    }

    @Override
    public CPU updateCPU(CPU cpu) throws IOException {
        String json = new Gson().toJson(cpu);
        RequestBody body = RequestBody.create(AppUtil.getJSONMediaType(), json);
        Request request = new Request.Builder()
                .url(updateUrl)
                .post(body)
                .build();
        Response response = AppUtil.getConnection().newCall(request).execute();
        String value = response.body().string();
        CPU personAddress = new Gson().fromJson(value, CPU.class);
        return personAddress;
    }
}
