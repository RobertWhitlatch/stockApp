package ee461l.stockapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.helper.DateAsXAxisLabelFormatter;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import static ee461l.stockapp.Define.apiEndpoint;
import static ee461l.stockapp.Define.requestCQNSCLP;
import static ee461l.stockapp.Define.stockRequest;
import static ee461l.stockapp.FavoritesList.favorites;
import static ee461l.stockapp.FavoritesList.logoURLs;
import static ee461l.stockapp.FavoritesList.symbols;
import static ee461l.stockapp.MainActivity.appDataBase;
import static ee461l.stockapp.MainActivity.currentUser;
import static ee461l.stockapp.SearchStocks.info;

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

        GraphView graph = (GraphView) findViewById(R.id.graph);
        double[] dataWeek = favorites[position].getWeekofData();
        String[] dateWeek = favorites[position].getWeekofDates();

        DateFormat df = new SimpleDateFormat("yyyy-MM-dd", Locale.US);
        Date[] dateArray = new Date[10];
        for(int i = 0; i < 10; i++) {
            try {
                Date startDate;
                startDate = df.parse(dateWeek[i]);
                dateArray[i]=startDate;

                String newDateString = df.format(startDate);
                System.out.println(newDateString);
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
        LineGraphSeries<DataPoint> series = new LineGraphSeries<DataPoint>(new DataPoint[] {
                new DataPoint(dateArray[9], dataWeek[9]),
                new DataPoint(dateArray[8], dataWeek[8]),
                new DataPoint(dateArray[7], dataWeek[7]),
                new DataPoint(dateArray[6], dataWeek[6]),
                new DataPoint(dateArray[5], dataWeek[5]),
                new DataPoint(dateArray[4], dataWeek[4]),
                new DataPoint(dateArray[3], dataWeek[3]),
                new DataPoint(dateArray[2], dataWeek[2]),
                new DataPoint(dateArray[1], dataWeek[1]),
                new DataPoint(dateArray[0], dataWeek[0])

        });
        graph.addSeries(series);
        // set date label formatter
        graph.getGridLabelRenderer().setLabelFormatter(new DateAsXAxisLabelFormatter(this));
        graph.getGridLabelRenderer().setNumHorizontalLabels(7); // only 7 because of the space

        // set manual x bounds to have nice steps
        graph.getViewport().setMinX(dateArray[9].getTime());
        graph.getViewport().setMaxX(dateArray[0].getTime());
        graph.getViewport().setXAxisBoundsManual(true);

        graph.getGridLabelRenderer().setHumanRounding(false);
        graph.getGridLabelRenderer().setHorizontalLabelsAngle(30);

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
