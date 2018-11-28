package ee461l.stockapp;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import org.json.*;

import static ee461l.stockapp.Define.apiEndpoint;
import static ee461l.stockapp.Define.requestCQN;

public class SearchStocks extends AppCompatActivity {

    private EditText searchQuery;
    public static SearchInfo info = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_stocks);
        searchQuery = findViewById(R.id.search_query);
    }

    public void executeSearch(View v){
        FetchStockResults fetchStock = new FetchStockResults();
        fetchStock.execute(apiEndpoint + searchQuery.getText().toString() + requestCQN);
    }


    private static class FetchStockResults extends AsyncTask<String,Void,Void> {


        @Override
        protected Void doInBackground(String... url) {
            HttpHandler httpHandler = new HttpHandler();
            String jsonStr = httpHandler.makeServiceCall(url[0]);
            if (jsonStr != null) {
                try {
                    JSONObject searchResults = new JSONObject(jsonStr);

                } catch (final JSONException e) {
                    Log.e("JSON", "Json parsing error: " + e.getMessage());
                }
            }

            return null;
        }
    }


}
