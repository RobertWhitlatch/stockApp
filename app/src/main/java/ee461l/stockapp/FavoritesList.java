package ee461l.stockapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.util.List;

public class FavoritesList extends AppCompatActivity {

    public static SearchInfo[] favorites;
    public static List<String> symbols;
    public static String[] logoURLs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favorites_list);
    }
}
