package mpip.finki.ukim.mpip_lab2.db;

import android.content.Context;
import android.os.AsyncTask;
import androidx.lifecycle.LiveData;
import androidx.room.Room;
import mpip.finki.ukim.mpip_lab2.models.OmdbItem;

import java.util.List;

public class OmdbItemRepository {

    private static OmdbItemDatabase omdbItemDatabase = null;
    private Context context;

    public OmdbItemRepository(Context context) {
        if(omdbItemDatabase == null) {
            omdbItemDatabase = Room.databaseBuilder(context, OmdbItemDatabase.class, "omdb_items").build();
        }
    }

    public void insertItem(final OmdbItem item) {
        new AsyncTask<Void, Void, Void>() {
            @Override
            protected Void doInBackground(Void... voids) {
                omdbItemDatabase.omdbDao().insert(item);
                return null;
            }
        }.execute();
    }

    public LiveData<List<OmdbItem>> listAllMovieItems() {
        return omdbItemDatabase.omdbDao().getAllAsync();
    }

    public void deleteAll() {
        new AsyncTask<Void, Void, Void>() {
            @Override
            protected Void doInBackground(Void... voids) {
                omdbItemDatabase.omdbDao().deleteAll();
                return null;
            }
        }.execute();
    }

}
