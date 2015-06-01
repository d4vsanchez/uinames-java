package name.davidsanchez.uiname;

import com.google.gson.Gson;
import com.squareup.okhttp.*;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * @author David Sanchez
 * @version 1.0.0
 * @since 2015-05-31
 */
class UINameRequest {
    private final OkHttpClient HTTP_CLIENT;
    private UIRequestResponse uiRequestResponse;

    UINameRequest(File cacheFile, UIRequestResponse uiRequestResponse) {
        this.uiRequestResponse = uiRequestResponse;

        int cacheSize = 1024 * 1024; // 1 MiB
        Cache cache = new Cache(cacheFile, cacheSize);

        HTTP_CLIENT = new OkHttpClient();
        HTTP_CLIENT.setCache(cache);
    }

    /**
     * Do a GET request to UINames API
     *
     * @param parameters The parameters passed by the user
     * @param uiNameResponse The callback to be executed after a successful response
     */
    void getData(Map<String, String> parameters, final UINameResponse uiNameResponse) {
        // Build the URL with the parameters passed by the user
        HttpUrl url = buildUrl(parameters);

        // Create the request
        Request request = new Request.Builder()
                .url(url)
                .build();

        // Execute the request
        HTTP_CLIENT.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Request request, IOException e) {
                e.printStackTrace();
            }

            @Override
            public void onResponse(Response response) throws IOException {
                if (!response.isSuccessful())
                    throw new IOException("Unexpected code: " + response.code());

                Gson gson = new Gson();
                List<Map<String, Object>> rawValues = gson.fromJson(response.body().charStream(), List.class);

                uiRequestResponse.receiveData(rawValues, uiNameResponse);
                response.body().close();
            }
        });
    }

    /**
     * Builds the URL with the given parameters
     *
     * @param parameters The parameters passed by the user
     * @return The URL
     */
    private HttpUrl buildUrl(Map<String, String> parameters) {
        HttpUrl.Builder url = new HttpUrl.Builder()
                .scheme("http")
                .host("api.uinames.com");

        for (Map.Entry<String, String> entry : parameters.entrySet()) {
            url.addQueryParameter(entry.getKey(), entry.getValue());
        }

        return url.build();
    }
}
