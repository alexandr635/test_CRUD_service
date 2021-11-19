import retrofit2.Call;
import retrofit2.http.*;

import java.util.UUID;

public interface ICrudServiceApi {
    @GET("/{id}")
    Call<Data> GetDataById(@Path("id") UUID id);

    @POST("/")
    Call<UUID> PostData(@Body Data data);

    @PUT("/")
    Call<Data> PutData(@Body Data data);

    @DELETE("/{id}")
    Call<UUID> DeleteData(@Path("id") UUID id);
}
