package mpip.finki.ukim.mpip_lab2;

import android.view.Menu;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.appcompat.widget.SearchView;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import mpip.finki.ukim.mpip_lab2.adapters.OmdbListAdapter;
import mpip.finki.ukim.mpip_lab2.clients.OmdbApiClient;
import mpip.finki.ukim.mpip_lab2.db.OmdbItemRepository;
import mpip.finki.ukim.mpip_lab2.models.OmdbItem;
import mpip.finki.ukim.mpip_lab2.models.OmdbResponse;
import mpip.finki.ukim.mpip_lab2.repository.OmdbApiInterface;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private List<OmdbItem> data;
    private RecyclerView recyclerView;
    private OmdbListAdapter omdbListAdapter;
    private LinearLayoutManager linearLayoutManager;
    private OmdbItemRepository omdbItemRepository;
    private SearchView searchView = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        omdbItemRepository = new OmdbItemRepository(this);
        initToolbar();
        initList();
    }


    private void initList() {
        data = new ArrayList<>();
        recyclerView = findViewById(R.id.recyclerView);
        omdbListAdapter = new OmdbListAdapter(getApplicationContext(), data);
        linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(omdbListAdapter);
        syncData("taxi");
    }

    private void initToolbar() {
        Toolbar toolbar = findViewById(R.id.custom_toolbar);
        setSupportActionBar(toolbar);
    }

    private void syncData(String searchTag) {
        OmdbApiInterface service = OmdbApiClient.getRetrofit().create(OmdbApiInterface.class);
        service.getMovies(searchTag).enqueue(new Callback<OmdbResponse>() {
            @Override
            public void onResponse(Call<OmdbResponse> call, Response<OmdbResponse> response) {
                if(response.isSuccessful()) {
                    omdbItemRepository.deleteAll();
                    for(OmdbItem item : response.body().Search) {
                        omdbItemRepository.insertItem(item);
                    }
                    loadDataFromDatabase();
                }
            }

            @Override
            public void onFailure(Call<OmdbResponse> call, Throwable t) {

            }
        });
    }

    private void loadDataFromDatabase() {
        omdbItemRepository.listAllMovieItems().observe(this, data -> {
            omdbListAdapter.updateData(data);
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.action_bar_menu, menu);
        SearchView searchView = (SearchView) menu.findItem(R.id.search_action).getActionView();
        searchView.setOnQueryTextListener(getOnQueryTextListener());
        return true;
    }

    private SearchView.OnQueryTextListener getOnQueryTextListener() {
        return new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                syncData(query);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        };
    }
}
