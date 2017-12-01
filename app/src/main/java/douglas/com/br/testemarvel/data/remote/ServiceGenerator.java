package douglas.com.br.testemarvel.data.remote;

import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

import java.io.IOException;

import douglas.com.br.testemarvel.Constants;
import douglas.com.br.testemarvel.NetworkConstants;
import douglas.com.br.testemarvel.utils.helpers.Md5HashGenerator;
import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by douglaspanacho on 01/12/2017.
 */

public class ServiceGenerator {

    public static <S> S createService(Class<S> serviceClass, String url) {
        Retrofit.Builder retrofit = new Retrofit.Builder()
                .baseUrl(url)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create());

        OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
        httpClient.addInterceptor(new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {
                Request original = chain.request();
                HttpUrl originalHttpUrl = original.url();
                String mTimeStamp = Md5HashGenerator.getTimeStamp();
                HttpUrl url = originalHttpUrl.newBuilder()
                        .addQueryParameter("ts", mTimeStamp)
                        .addQueryParameter("apikey", Constants.PUBLIC_KEY)
                        .addQueryParameter("hash", Md5HashGenerator.generateMd5(mTimeStamp))
                        .build();

                // Request customization: add request headers
                Request.Builder requestBuilder = original.newBuilder()
                        .url(url);

                Request request = requestBuilder.build();
                return chain.proceed(request);
            }
        });
        retrofit.client(httpClient.build());
        return retrofit.build().create(serviceClass);
    }
}
