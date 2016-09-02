package computerstore.com.computerstore.restapi.components.api.Impl;

/**
 * Created by Aidem on 2016/04/17.
 */

import computerstore.com.computerstore.conf.util.AppUtil;
import computerstore.com.computerstore.domain.components.Notebook;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.RequestBody;
import com.google.gson.Gson;
import java.io.IOException;

import computerstore.com.computerstore.restapi.components.api.NotebookAPI;


public class NotebookAPIImpl implements NotebookAPI {
    private static final String postUrl = AppUtil.getBaserURI() + "api/droid/components/notebook/post";
    private static final String updateUrl = AppUtil.getBaserURI() + "api/droid/components/notebook/update";

    @Override
    public Notebook createNotebook(Notebook notebook) throws IOException {
        String json = new Gson().toJson(notebook);
        RequestBody body = RequestBody.create(AppUtil.getJSONMediaType(), json);
        Request request = new Request.Builder()
                .url(postUrl)
                .post(body)
                .build();
        Response response = AppUtil.getConnection().newCall(request).execute();
        String value = response.body().string();
        Notebook personAddress = new Gson().fromJson(value, Notebook.class);
        return personAddress;
    }

    @Override
    public Notebook updateNotebook(Notebook notebook) throws IOException {
        String json = new Gson().toJson(notebook);
        RequestBody body = RequestBody.create(AppUtil.getJSONMediaType(), json);
        Request request = new Request.Builder()
                .url(updateUrl)
                .post(body)
                .build();
        Response response = AppUtil.getConnection().newCall(request).execute();
        String value = response.body().string();
        Notebook personAddress = new Gson().fromJson(value, Notebook.class);
        return personAddress;
    }

}
