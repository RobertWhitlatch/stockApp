package ee461l.stockapp;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;

import com.google.gson.Gson;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;

import static ee461l.stockapp.Define.*;

public class SearchStocks extends AppCompatActivity {

    private EditText searchQuery;
    private Button searchGo;
    private Button reset;
    private ImageButton favorite;
    private ListView searchResults;
    private static String searchText;
    public static SearchInfo info = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_stocks);
        searchQuery = findViewById(R.id.search_query);
        searchGo = findViewById(R.id.search_go);
        favorite = findViewById(R.id.add_favorites);
        reset = findViewById(R.id.reset_search);
        searchResults = findViewById(R.id.search_results);
    }

    public void executeSearch(View v){
        FetchStockResults fetchStock = new FetchStockResults(favorite, searchResults);
        searchText = searchQuery.getText().toString();
        fetchStock.execute(apiEndpoint + searchText + requestCQNSC);
        searchQuery.setVisibility(View.INVISIBLE);
        searchGo.setVisibility(View.INVISIBLE);
        reset.setVisibility(View.VISIBLE);
        favorite.setVisibility(View.VISIBLE);
    }

    public void addFavorite(View v){
        String favoriteSymbol = info.getCompany().getSymbol();
        favorite.setImageResource(android.R.drawable.btn_star_big_on);
        boolean EXIST = false;
        /*
         Basically create a new user whenever a user clicks the Start icon.
         Added current user's information to the object such as id, name and favorite list
         If the user already exists in our database, I pulled their exist favorite list and added the current symbol to it and updated.
         If not, added a new user to the database so that you can pull it whenever this user wants to add another stock symbol
        */
        User user = new User();
        user.setId(Integer.parseInt(MainActivity.current_account.getId()));  //This could be Null but I don't know
        user.setName(MainActivity.current_account.getEmail());
        List<String> favlist = new ArrayList<>();
        favlist.add(favoriteSymbol);
        user.setFavorites(favlist);

        for(User userlist : MainActivity.appDataBase.dao().getUsers()){
            if(userlist.getId() == user.getId()){
                List<String> existfav = userlist.getFavorites();
                existfav.add(favoriteSymbol);
                user.setFavorites(existfav);
                EXIST = true;
            }
        }

        if(EXIST){
            MainActivity.appDataBase.dao().updateUser(user);
        }else {
            MainActivity.appDataBase.dao().addUser(user);
        }

//        TODO: Add symbol to user account when that becomes possible //  **ONPROGRESS
    }

    public void resetSearch(View v){
        searchQuery.setVisibility(View.VISIBLE);
        searchGo.setVisibility(View.VISIBLE);
        reset.setVisibility(View.INVISIBLE);
        favorite.setVisibility(View.INVISIBLE);
        favorite.setImageResource(android.R.drawable.btn_star_big_off);
        info = null;
        searchText = null;
//        TODO: Clear previous results from screen
    }

    private static class FetchStockResults extends AsyncTask<String,Void,Void> {

        private WeakReference<ImageButton> fav;
        private WeakReference<ListView> results;

        FetchStockResults(ImageButton fav, ListView results){
            this.fav = new WeakReference<>(fav);
            this.results = new WeakReference<>(results);
        }

        @Override
        protected Void doInBackground(String... url) {
            HttpHandler httpHandler = new HttpHandler();
            String jsonStr = httpHandler.makeServiceCall(url[0]);
            if (jsonStr != null) {
                Gson gson = new Gson();
                info = gson.fromJson(jsonStr,SearchInfo.class);
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
//            TODO: Check if searched symbol is already a favorite and adjust icon accordingly
//            if(symbolIsStored(searchText)){
//                fav.get().setImageResource(android.R.drawable.btn_star_big_on);
//            }
//            TODO: Fill out results display
        }

    }


}
