package ee461l.stockapp;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;
import java.util.List;

/**
 * Created by YongSub on 11/27/2018.
 */

@Dao
public interface UserDao {
    @Insert
    void addUser(User user);

    @Query("SELECT * FROM users")
    List<User> getUsers();

    @Query("SELECT * FROM users WHERE id LIKE :id")
   User getUser(String id);

    @Update
    void updateUser(User user);
}
