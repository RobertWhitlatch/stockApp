package ee461l.stockapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageButton;
import java.util.List;

import static ee461l.stockapp.SearchStocks.info;

public class SearchResultsDisplay extends AppCompatActivity {

    private ImageButton favorite;
    private User currentUser;
    private List<String> favoritesList;
    private RecyclerView resultsView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_results_display);

        String userId = MainActivity.current_account.getId();
        currentUser = MainActivity.appDataBase.dao().getUser(userId);
        favorite = findViewById(R.id.add_favorites);
        favoritesList = currentUser.getFavorites();
        if(favoritesList.contains(info.getSymbol().toUpperCase())){
            favorite.setImageResource(android.R.drawable.btn_star_big_on);
        }

        resultsView = findViewById(R.id.search_results);
        resultsView.setLayoutManager(new LinearLayoutManager(this));
        resultsView.setAdapter(new StockDisplayAdapter(info.getDisplaySet()));
    }

    public void goBackToSearch(View v){
        Intent intent = new Intent(getApplicationContext(), SearchStocks.class);
        startActivity(intent);
    }

    public void addFavorite(View v){
        String newFavoriteSymbol = info.getSymbol().toUpperCase();
        if(favoritesList.contains(newFavoriteSymbol.toUpperCase())){
            return;
        }
        favorite.setImageResource(android.R.drawable.btn_star_big_on);
        favoritesList.add(newFavoriteSymbol);
        currentUser.setFavorites(favoritesList);
        MainActivity.appDataBase.dao().updateUser(currentUser);
    }
}
