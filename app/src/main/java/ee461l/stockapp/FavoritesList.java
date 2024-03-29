package ee461l.stockapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.View;

public class FavoritesList extends AppCompatActivity {

    public static SearchInfo[] favorites;
    public static String[] symbols;
    public static String[] logoURLs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favorites_list);

        RecyclerView recyclerView = findViewById(R.id.favorites_list);
        recyclerView.setAdapter(new FavoritesListAdapter(symbols, logoURLs));
    }

    public void gotoHome(View v){
        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
        startActivity(intent);
    }
}
