package ee461l.stockapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageButton;

import static ee461l.stockapp.MainActivity.appDataBase;
import static ee461l.stockapp.MainActivity.currentUser;
import static ee461l.stockapp.SearchStocks.info;

public class SearchResultsDisplay extends AppCompatActivity {

    private ImageButton favorite;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_results_display);


        favorite = findViewById(R.id.add_favorites);
        if(currentUser.isFavorite(info.getSymbol())){
            favorite.setImageResource(android.R.drawable.btn_star_big_on);
        }

        RecyclerView resultsView = findViewById(R.id.search_results);
        resultsView.setLayoutManager(new LinearLayoutManager(this));
        resultsView.setAdapter(new StockDisplayAdapter(info.getDisplaySet()));
    }

    public void goBackToSearch(View v){
        Intent intent = new Intent(getApplicationContext(), SearchStocks.class);
        startActivity(intent);
    }

    public void addFavorite(View v){
        String newFavoriteSymbol = info.getSymbol();
        //currentFireUser.addFavorite(newFavoriteSymbol);
        //FireDB.storeUser(currentFireUser);
        if(currentUser.isFavorite(newFavoriteSymbol)){
            return;
        }
        favorite.setImageResource(android.R.drawable.btn_star_big_on);
        currentUser.addFavorite(newFavoriteSymbol);
        appDataBase.dao().updateUser(currentUser);
    }

}
