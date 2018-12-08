package ee461l.stockapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import java.util.List;

import static ee461l.stockapp.Define.apiEndpoint;
import static ee461l.stockapp.Define.requestCQNSCLP;
import static ee461l.stockapp.Define.stockRequest;
import static ee461l.stockapp.FavoritesList.favorites;
import static ee461l.stockapp.FavoritesList.logoURLs;
import static ee461l.stockapp.FavoritesList.symbols;
import static ee461l.stockapp.MainActivity.appDataBase;
import static ee461l.stockapp.MainActivity.currentUser;

public class FavoriteDisplay extends AppCompatActivity {

    private int position;
    private String symbol;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favorite_display);
        position = getIntent().getIntExtra("position",-1);
        symbol = getIntent().getStringExtra("symbol");

        RecyclerView resultsView = findViewById(R.id.favorite_display);
        resultsView.setLayoutManager(new LinearLayoutManager(this));
        resultsView.setAdapter(new StockDisplayAdapter(favorites[position].getDisplaySet()));
    }

    public void removeFavorite(View view){
        currentUser.removeFavorite(symbol);
        appDataBase.dao().updateUser(currentUser);
    }

    public void gotoHome(View v){
        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
        startActivity(intent);
    }

    public void goBackToFavorites(View v){
        List<String> symbolList = currentUser.getFavorites();
        symbols = new String[symbolList.size()];
        symbolList.toArray(symbols);
        logoURLs = new String[symbolList.size()];
        favorites = new SearchInfo[symbolList.size()];
        for(int i = 0; i < symbolList.size(); i++){
            CallIEX fetchFavorite = new CallIEX("favorites" , i);
            try {
                Object obj = fetchFavorite.execute(apiEndpoint + stockRequest + symbolList.get(i) + requestCQNSCLP).get();
                logoURLs[i] = favorites[i].getLogo().getUrl();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        Intent intent = new Intent(getApplicationContext(), FavoritesList.class);
        startActivity(intent);
    }
}
