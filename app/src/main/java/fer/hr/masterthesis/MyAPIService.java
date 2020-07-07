package fer.hr.masterthesis;

import java.util.List;
import retrofit2.Call;
import retrofit2.http.GET;


// tu mijenjas URL
public interface MyAPIService {

    @GET("https://webcataloguee.xyz")
    Call<List<Catalog>> getCatalogs();
}