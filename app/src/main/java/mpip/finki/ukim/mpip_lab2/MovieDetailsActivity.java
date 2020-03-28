package mpip.finki.ukim.mpip_lab2;

import android.widget.ImageView;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import com.bumptech.glide.Glide;
import mpip.finki.ukim.mpip_lab2.clients.OmdbApiClient;
import mpip.finki.ukim.mpip_lab2.models.OmdbItemFull;
import mpip.finki.ukim.mpip_lab2.repository.OmdbApiInterface;
import mpip.finki.ukim.mpip_lab2.repository.OmdbApiInterfaceById;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MovieDetailsActivity extends AppCompatActivity {

    private ImageView txtImageDetails;
    private TextView txtTitleDetails;
    private TextView txtYearDetails;
    private TextView txtRatedDetails;
    private TextView txtReleasedDetails;
    private TextView txtRunTimeDetails;
    private TextView txtGenreDetails;
    private TextView txtDirectorDetails;
    private TextView txtWriterDetails;
    private TextView txtActorsDetails;
    private TextView txtPlotDetails;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_details);
        initUI();
        loadData();
    }

    private void initUI() {
        txtImageDetails= findViewById(R.id.txtImageDetails);
        txtTitleDetails= findViewById(R.id.txtTitleDetails);
        txtYearDetails= findViewById(R.id.txtYearDetails);
        txtRatedDetails= findViewById(R.id.txtRatedDetails);
        txtReleasedDetails= findViewById(R.id.txtReleasedDetails);
        txtRunTimeDetails= findViewById(R.id.txtRunTimeDetails);
        txtGenreDetails= findViewById(R.id.txtGenreDetails);
        txtDirectorDetails= findViewById(R.id.txtDirectorDetails);
        txtWriterDetails= findViewById(R.id.txtWriterDetails);
        txtActorsDetails= findViewById(R.id.txtActorsDetails);
        txtPlotDetails= findViewById(R.id.txtPlotDetails);
    }

    private void loadData() {
        String id = getIntent().getStringExtra("imdbID");
        OmdbApiInterfaceById serviceApi = OmdbApiClient.getRetrofit().create(OmdbApiInterfaceById.class);

        serviceApi.getMovies(id).enqueue(new Callback<OmdbItemFull>() {
            @Override
            public void onResponse(Call<OmdbItemFull> call, Response<OmdbItemFull> response) {
                if(response.body() != null) {
                    Glide.with(MovieDetailsActivity.this)
                            .load(response.body().Poster)
                            .into(txtImageDetails);
                    txtTitleDetails.setText("Title: " + response.body().Title);
                    txtActorsDetails.setText("Actors: " + response.body().Actors);
                    txtDirectorDetails.setText("Director: " + response.body().Director);
                    txtGenreDetails.setText("Genre: " + response.body().Genre);
                    txtPlotDetails.setText("Plot: " + response.body().Plot);
                    txtRatedDetails.setText("Rated: " + response.body().Rated);
                    txtReleasedDetails.setText("Released: " + response.body().Released);
                    txtRunTimeDetails.setText("Runtime: " + response.body().Runtime);
                    txtWriterDetails.setText("Writer: " + response.body().Writer);
                    txtYearDetails.setText("Year: " + response.body().Year);
                }
            }

            @Override
            public void onFailure(Call<OmdbItemFull> call, Throwable t) {

            }
        });
    }
}
