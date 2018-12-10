package ee461l.stockapp;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

/**
 * Created by YongSub on 11/27/2018.
 */

@Database(entities = {User.class}, version = 1, exportSchema = false)
public abstract class AppDataBase extends RoomDatabase{

    public abstract UserDao dao();

}
