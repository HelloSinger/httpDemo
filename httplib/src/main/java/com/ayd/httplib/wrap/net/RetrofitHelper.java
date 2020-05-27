package com.ayd.httplib.wrap.net;

import java.io.IOException;
import java.lang.reflect.Field;
import java.security.SecureRandom;
import java.util.concurrent.TimeUnit;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import okhttp3.ConnectionPool;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import okio.Buffer;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * 文件描述
 *
 * @author AYD
 * @date 2019年10月22日 14:11
 */

public class RetrofitHelper {
    private static String TGA = "RetrofitHelper";
    private long CONNECT_TIMEOUT = 5000L;
    private long READ_TIMEOUT = 3000L;
    private long WRITE_TIMEOUT = 3000L;
    private static RetrofitHelper mInstance = null;
    private Retrofit mRetrofit = null;
    private static String baseUrl;

    public static RetrofitHelper getInstance() {
        synchronized (RetrofitHelper.class) {
            if (mInstance == null) {
                mInstance = new RetrofitHelper();
            }
        }
        return mInstance;
    }

    public RetrofitHelper initBaseUrl(String url) {
        baseUrl = url;
        init();
        return mInstance;
    }


    private void init() {
        resetApp(baseUrl);
    }

    private void resetApp(String baseUrl) {
        mRetrofit = new Retrofit.Builder()
                .baseUrl(baseUrl)
                .client(getOkHttpClient())
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
    }

    /**
     * 获取OkHttpClient实例
     *
     * @return
     */

    private OkHttpClient getOkHttpClient() {
        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .retryOnConnectionFailure(false)
                .connectTimeout(CONNECT_TIMEOUT, TimeUnit.SECONDS)
                .readTimeout(READ_TIMEOUT, TimeUnit.SECONDS)
                .writeTimeout(WRITE_TIMEOUT, TimeUnit.SECONDS)
                .connectionPool(new ConnectionPool(5, 5, TimeUnit.SECONDS))
//                .addInterceptor(new RqInterceptor())
                .addInterceptor(new HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
//                .addInterceptor(new Interceptor() {
//                    @Override
//                    public Response intercept(Chain chain) throws IOException {
//                        //获取到response
//                        Response response = chain.proceed(chain.request());
//                        //打印响应头
//                        LogUtils.d(TGA, response.headers().toString());
//                        LogUtils.d(TGA, "get4-SP1-->" + response.headers("Cs-Token"));
//                        LogUtils.d(TGA, response.headers("Cs-Token-Expirytime"));
//                        for (int i = 0; i < response.headers().size(); i++) {
//                            if ("Cs-Token".equals(response.headers().name(i))) {
//                                LogUtils.e(TGA, "get4-SP2-->" + response.headers().name(i) + "---->" + response.headers().value(i));
//                                SPUtils.getInstance().put("CsToken", "" + response.headers().value(i));
//                            }
//                        }
//                        //得到Response  对它的response 进行包装  用我们自己定义的DownLoadResponseBody
//                        return response;
//                    }
//                })
                .build();
        ignoreSSLCheck(okHttpClient);
        return okHttpClient;
    }

    /**
     * 请求拦截器
     */
    private class RqInterceptor implements Interceptor {
        @Override
        public Response intercept(Chain chain) throws IOException {
            Request request = chain.request()
                    .newBuilder()
                    .addHeader("X-APP-TYPE", "android")
                    .build();
            Response response = chain.proceed(request);
            return response;
        }
    }


    private String requestBodyToString(final RequestBody request) {
        try {
            final RequestBody copy = request;
            final Buffer buffer = new Buffer();
            if (copy != null) {

                copy.writeTo(buffer);
            } else {
                return "";
            }
            return buffer.readUtf8();
        } catch (final IOException e) {
            return "did not work";
        }
    }

    public <T> T getServer(Class<T> convertClass) {
        return mRetrofit.create(convertClass);
    }

    private void ignoreSSLCheck(OkHttpClient okHttpClient) {
        SSLContext sc = null;
        try {
            sc = SSLContext.getInstance("SSL");
            sc.init(null, new TrustManager[]{new X509TrustManager() {
                @Override
                public void checkClientTrusted(java.security.cert.X509Certificate[] chain, String authType) throws java.security.cert.CertificateException {

                }

                @Override
                public void checkServerTrusted(java.security.cert.X509Certificate[] chain, String authType) throws java.security.cert.CertificateException {

                }

                @Override
                public java.security.cert.X509Certificate[] getAcceptedIssuers() {
                    return null;
                }
            }}, new SecureRandom());
        } catch (Exception e) {
            e.printStackTrace();
        }

        HostnameVerifier hv1 = new HostnameVerifier() {
            @Override
            public boolean verify(String hostname, SSLSession session) {
                return true;
            }
        };

        String workerClassName = "okhttp3.OkHttpClient";
        try {
            Class workerClass = Class.forName(workerClassName);
            Field hostnameVerifier = workerClass.getDeclaredField("hostnameVerifier");
            hostnameVerifier.setAccessible(true);
            hostnameVerifier.set(okHttpClient, hv1);

            Field sslSocketFactory = workerClass.getDeclaredField("sslSocketFactory");
            sslSocketFactory.setAccessible(true);
            sslSocketFactory.set(okHttpClient, sc.getSocketFactory());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
