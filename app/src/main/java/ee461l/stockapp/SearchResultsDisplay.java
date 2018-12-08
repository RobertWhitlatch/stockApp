package ee461l.stockapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageButton;

import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.helper.DateAsXAxisLabelFormatter;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

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

        GraphView graph = (GraphView) findViewById(R.id.graph);
        double[] dataWeek = info.getWeekofData();
        String[] dateWeek = info.getWeekofDates();

        DateFormat df = new SimpleDateFormat("yyyy-MM-dd",Locale.US);
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

    public void goBackToSearch(View v){
        Intent intent = new Intent(getApplicationContext(), SearchStocks.class);
        startActivity(intent);
    }

    public void gotoHome(View v){
        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
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
