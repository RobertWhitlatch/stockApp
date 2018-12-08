package ee461l.stockapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import static ee461l.stockapp.Define.*;

public class SearchStocks extends AppCompatActivity {

    private EditText searchQuery;
    private TextView invalidSymbol;
    public static String searchText;
    public static volatile SearchTerms terms = new SearchTerms();
    public static volatile SearchInfo info = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_stocks);
        searchQuery = findViewById(R.id.search_query);
        invalidSymbol = findViewById(R.id.invalid_symbol);
        searchText = null;
        terms.setRefData(null);
        info = null;
        CallIEX fetchTerms = new CallIEX("terms");
        fetchTerms.execute(apiEndpoint + symbolRequest);
    }

    public void executeSearch(View v){
        searchText = searchQuery.getText().toString().toUpperCase();
        if(terms.isValidSearch(searchText)){
            CallIEX fetchStock = new CallIEX("search");
            try {
                Object obj = fetchStock.execute(apiEndpoint + stockRequest + searchText + requestCQNSCLP).get();
            } catch (Exception e) {
                e.printStackTrace();
            }
            Intent intent = new Intent(getApplicationContext(), SearchResultsDisplay.class);
            startActivity(intent);
        } else {
            invalidSymbol.setVisibility(View.VISIBLE);
        }
    }

    public void gotoHome(View v){
        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
        startActivity(intent);
    }

}
