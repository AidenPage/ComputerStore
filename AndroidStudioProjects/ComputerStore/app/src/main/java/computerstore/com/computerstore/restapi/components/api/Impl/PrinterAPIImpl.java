package computerstore.com.computerstore.restapi.components.api.Impl;

/**
 * Created by Aidem on 2016/04/17.
 */

import computerstore.com.computerstore.conf.util.AppUtil;
import computerstore.com.computerstore.domain.components.Printer;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.RequestBody;
import com.google.gson.Gson;
import java.io.IOException;

import computerstore.com.computerstore.restapi.components.api.PrinterAPI;


public class PrinterAPIImpl implements PrinterAPI {
    private static final String postUrl = AppUtil.getBaserURI() + "api/droid/components/printer/post";
    private static final String updateUrl = AppUtil.getBaserURI() + "api/droid/components/printer/update";

    @Override
    public Printer createPrinter(Printer printer) throws IOException {
        String json = new Gson().toJson(printer);
        RequestBody body = RequestBody.create(AppUtil.getJSONMediaType(), json);
        Request request = new Request.Builder()
                .url(postUrl)
                .post(body)
                .build();
        Response response = AppUtil.getConnection().newCall(request).execute();
        String value = response.body().string();
        Printer personAddress = new Gson().fromJson(value, Printer.class);
        return personAddress;
    }

    @Override
    public Printer updatePrinter(Printer printer) throws IOException {
        String json = new Gson().toJson(printer);
        RequestBody body = RequestBody.create(AppUtil.getJSONMediaType(), json);
        Request request = new Request.Builder()
                .url(updateUrl)
                .post(body)
                .build();
        Response response = AppUtil.getConnection().newCall(request).execute();
        String value = response.body().string();
        Printer personAddress = new Gson().fromJson(value, Printer.class);
        return personAddress;
    }

}
