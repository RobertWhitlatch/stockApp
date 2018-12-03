package ee461l.stockapp;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.arch.persistence.room.TypeConverters;
import android.support.annotation.NonNull;

import java.util.List;

/**
 * Created by YongSub on 11/27/2018.
 */

@Entity(tableName = "users")
public class User {

    @PrimaryKey
    @NonNull
    private String id = "";

    @ColumnInfo(name = "user_name")
    private String name;

    @TypeConverters(DataConverter.class)
    @ColumnInfo(name = "user_favorites")
    private List<String> favorites;

    public List<String> getFavorites() {
        return favorites;
    }

    public void setFavorites(List<String> favorites) {
        this.favorites = favorites;
    }

    @NonNull
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
