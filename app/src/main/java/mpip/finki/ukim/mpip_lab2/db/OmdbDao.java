package mpip.finki.ukim.mpip_lab2.db;

import androidx.lifecycle.LiveData;
import androidx.room.*;
import mpip.finki.ukim.mpip_lab2.models.OmdbItem;

import java.util.List;

@Dao
public interface OmdbDao {

    @Query("SELECT * FROM omdb_item ORDER BY Title")
    LiveData<List<OmdbItem>> getAllAsync();

    @Query("SELECT * FROM omdb_item WHERE Title LIKE :title ORDER BY Title")
    LiveData<List<OmdbItem>> getItemsByTitle(String title);

    @Insert
    void insert(OmdbItem item);

    @Update
    void update(OmdbItem item);

    @Delete
    void delete(OmdbItem item);

    @Query("DELETE FROM omdb_item")
    void deleteAll();

}
