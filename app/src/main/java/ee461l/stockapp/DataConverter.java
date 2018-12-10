package ee461l.stockapp;

import android.arch.persistence.room.TypeConverter;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.lang.reflect.Type;
import java.util.List;

class DataConverter {

    @TypeConverter // note this annotation
    public String fromFavList(List<String> Favlist) {
        if (Favlist == null) {
            return (null);
        }
        Gson gson = new Gson();
        Type type = new TypeToken<List<String>>() {
        }.getType();
        return gson.toJson(Favlist, type);
    }

    @TypeConverter // note this annotation
    public List<String> toFavList(String Favlist) {
        if (Favlist == null) {
            return (null);
        }
        Gson gson = new Gson();
        Type type = new TypeToken<List<String>>() {
        }.getType();
        return gson.fromJson(Favlist, type);
    }

}
