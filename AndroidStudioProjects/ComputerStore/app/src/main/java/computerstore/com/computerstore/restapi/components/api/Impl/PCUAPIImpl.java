package computerstore.com.computerstore.restapi.components.api.Impl;

/**
 * Created by Aidem on 2016/04/17.
 */

import computerstore.com.computerstore.conf.util.AppUtil;
import computerstore.com.computerstore.domain.components.PCU;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.RequestBody;
import com.google.gson.Gson;
import java.io.IOException;

import computerstore.com.computerstore.restapi.components.api.PCUAPI;


public class PCUAPIImpl implements PCUAPI {
    private static final String postUrl = AppUtil.getBaserURI() + "api/droid/components/pcu/post";
    private static final String updateUrl = AppUtil.getBaserURI() + "api/droid/components/pcu/update";

    @Override
    public PCU createPCU(PCU pcu) throws IOException {
        String json = new Gson().toJson(pcu);
        RequestBody body = RequestBody.create(AppUtil.getJSONMediaType(), json);
        Request request = new Request.Builder()
                .url(postUrl)
                .post(body)
                .build();
        Response response = AppUtil.getConnection().newCall(request).execute();
        String value = response.body().string();
        PCU personAddress = new Gson().fromJson(value, PCU.class);
        return personAddress;
    }

    @Override
    public PCU updatePCU(PCU pcu) throws IOException {
        String json = new Gson().toJson(pcu);
        RequestBody body = RequestBody.create(AppUtil.getJSONMediaType(), json);
        Request request = new Request.Builder()
                .url(updateUrl)
                .post(body)
                .build();
        Response response = AppUtil.getConnection().newCall(request).execute();
        String value = response.body().string();
        PCU personAddress = new Gson().fromJson(value, PCU.class);
        return personAddress;
    }

}
