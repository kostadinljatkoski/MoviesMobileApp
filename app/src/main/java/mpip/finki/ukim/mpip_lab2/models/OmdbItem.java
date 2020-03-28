package mpip.finki.ukim.mpip_lab2.models;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "omdb_item")
public class OmdbItem {

    @PrimaryKey(autoGenerate = true)
    public int id;

    @ColumnInfo
    public String Title;

    @ColumnInfo
    public String Year;

    @ColumnInfo
    public String imdbID;

    @ColumnInfo
    public String Poster;
}
