package ee461l.stockapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;

import android.view.View;

public class FinancialNews extends AppCompatActivity {

    @SuppressWarnings("CanBeFinal")
    public static NewsContainer newsContainer = new NewsContainer();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_financial_news);
        RecyclerView recyclerView = findViewById(R.id.financial_list);
        recyclerView.setAdapter(new NewsListAdapter(newsContainer));
    }

    public void gotoHome(View v){
        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
        startActivity(intent);
    }

}

