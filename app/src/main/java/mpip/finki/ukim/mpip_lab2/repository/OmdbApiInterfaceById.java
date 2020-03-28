package mpip.finki.ukim.mpip_lab2.repository;

import mpip.finki.ukim.mpip_lab2.models.OmdbItemFull;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface OmdbApiInterfaceById {
    @GET("/?apikey=e86445d1")
    Call<OmdbItemFull> getMovies(@Query("i") String id);
}
