package mpip.finki.ukim.mpip_lab2.db;

import androidx.room.Database;
import androidx.room.RoomDatabase;
import mpip.finki.ukim.mpip_lab2.models.OmdbItem;

@Database(entities = {OmdbItem.class}, version = 1, exportSchema = false)
public abstract class OmdbItemDatabase extends RoomDatabase {

    public abstract OmdbDao omdbDao();
}
