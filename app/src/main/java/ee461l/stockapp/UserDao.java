package ee461l.stockapp;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;

import ee461l.stockapp.User;

/**
 * Created by YongSub on 11/27/2018.
 */

@Dao
public interface UserDao {
    @Insert
    public void addUser(User user);

    @Query("SELECT * FROM users")
    public List<User> getUsers();

    @Update
    public void updateUser(User user);
}
