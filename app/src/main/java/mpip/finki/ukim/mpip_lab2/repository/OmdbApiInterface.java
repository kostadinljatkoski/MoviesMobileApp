package mpip.finki.ukim.mpip_lab2.repository;

import mpip.finki.ukim.mpip_lab2.models.OmdbResponse;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface OmdbApiInterface {
    @GET("/?apikey=e86445d1")
    Call<OmdbResponse> getMovies(@Query("s") String title);
}
