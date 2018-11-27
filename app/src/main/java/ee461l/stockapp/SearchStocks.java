package ee461l.stockapp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import org.json.*;
import java.util.ArrayList;
import java.util.List;

public class SearchStocks extends AppCompatActivity {

    private EditText searchQuery;
    public static String searchResults = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_stocks);
        searchQuery = findViewById(R.id.search_query);
    }

    public void executeSearch(View v){
        FetchSearchResults search = new FetchSearchResults(new FetchSearchResults.AsynResponse() {
            @Override
            public void processFinish(Boolean output) {
                TextView tv = findViewById(R.id.search_results);
                tv.setText(searchResults);
            }
        });
        search.execute("https://api.iextrading.com/1.0/stock/" + searchQuery.getText().toString() + "/batch?types=quote,news");
    }

}
