package fer.hr.masterthesis;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class RetrofitClientInstance {
    // promijenio si http u https
    private static final String BASE_URL = "https://webcataloguee.xyz";
    private static final String FULL_URL = "https://webcataloguee.xyz/";
    private static Retrofit retrofit;

    public static Retrofit getRetrofitInstance() {
        if (retrofit == null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }

    public static String getFULLUrl(){
        return FULL_URL;
    }
}
