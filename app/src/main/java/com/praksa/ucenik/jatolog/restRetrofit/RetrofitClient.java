package com.praksa.ucenik.jatolog.restRetrofit;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.security.KeyStore;
import java.security.cert.Certificate;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;

import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManagerFactory;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class RetrofitClient {
    /********
     * URLS
     *******/
//    public static final String ROOT_URL = "http://10.226.19.153:8000/api/";
    public static final String ROOT_URL = "https://api.myjson.com/bins/";
    private  static Retrofit retrofit = null;

    /**
     * Get Retrofit Instance
     */

    private static Retrofit getRetrofitInstance() {
        if (retrofit==null) {

            //interceptor which will be used in the Retrofit call
            Interceptor interceptor = new Interceptor() {
                @Override
                public Response intercept(Chain chain) throws IOException {
                    Request newRequest = chain.request().newBuilder()
                            //This is where you would add headers if need be.
                            .addHeader("Content-Type", "application/json")
                            .build();
                    return chain.proceed(newRequest);
                }
            };

            HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
            // set your desired log level
            logging.setLevel(HttpLoggingInterceptor.Level.BODY);

            OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
            // add your other interceptors …
            httpClient.addInterceptor(interceptor);
            // add logging as last interceptor
            httpClient.addInterceptor(logging);


            //TODO otkomentarisati kad dodje certifikat sa servera za https komunikaciju
            /*try {
                httpClient.sslSocketFactory(getSSLConfig().getSocketFactory());
            }catch (Exception ex){
                ex.printStackTrace();

            }*/

        return new Retrofit.Builder()
                .baseUrl(ROOT_URL)
                .addConverterFactory(buildGsonConverterFactory())
                .client(httpClient.build())
                .build();
        }
        return retrofit;
    }

    /**
     * Get API Service
     *
     * @return API Service
     */
    public static ApiService getApiService() {
        return getRetrofitInstance().create(ApiService.class);
    }

    //custom GSON converter
    private static GsonConverterFactory buildGsonConverterFactory () {
        GsonBuilder gsonBuilder = new GsonBuilder();
        //gsonBuilder.se();
        gsonBuilder.excludeFieldsWithoutExposeAnnotation();
        Gson myGson = gsonBuilder.create();
        return GsonConverterFactory.create(myGson);
    }

//    private static SSLContext getSSLConfig () throws Exception {
//
//            //Loading CAs from ImputStream
//            //could be from a resource or ByteArrayInputStream or ...
//            CertificateFactory cf = CertificateFactory.getInstance("X.509");
//
//            // kada se certifikat postavi na server
//            // "http://msumesrpske.com/WebApiSume/api/ca/load-ca.crt"
//            InputStream caInput = new BufferedInputStream(new FileInputStream("load-ca.crt"));
//            Certificate ca;
//
//            try {
//                ca = cf.generateCertificate(caInput);
//                System.out.println("ca=" + ((X509Certificate) ca).getSubjectDN());
//            } finally {
//                caInput.close();
//            }
//
//            // Creating a KeyStore containing our trusted CAs
//            String keyStoreType = KeyStore.getDefaultType();
//            KeyStore keyStore = KeyStore.getInstance(keyStoreType);
//            keyStore.load(null, null);
//            keyStore.setCertificateEntry("ca", ca);
//
//            //Create a TrustManager that
//            String tmfAlgorithm = TrustManagerFactory.getDefaultAlgorithm();
//            TrustManagerFactory tmf = TrustManagerFactory.getInstance(tmfAlgorithm);
//            tmf.init(keyStore);
//
//            // Creating an SSLSocketFactory that uses our TrustManager
//            SSLContext sslContext = SSLContext.getInstance("TLS");
//            sslContext.init(null, tmf.getTrustManagers(), null);
//
//        return sslContext;
//    }

}
