package ee461l.stockapp;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.support.v4.view.ViewCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.Html;
import android.text.method.LinkMovementMethod;

import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.InputStream;
import java.util.ArrayList;



public class FinancialNews extends AppCompatActivity {

    ArrayList<String> urlList = new ArrayList<String>();
    ArrayList<String> thumbList = new ArrayList<String>();
    ArrayList<Bitmap> bitmapList = new ArrayList<Bitmap>();
    ListView lView;
    ListAdapter lAdapter;
    /*
    -NewsTask uses bing news search API to find 3 news articles based on user input
    -displays links and thumbnails for the articles
     */
    class NewsTask extends AsyncTask<String, Void, ArrayList<Bitmap>> {
        protected ArrayList<Bitmap> doInBackground(String... str){
            String urldisplay = "";
            try {
                SearchResults result = BingNewsSearch.SearchNews(str[0]);

                urlList = BingNewsSearch.prettifyURL(result.jsonResponse);
                thumbList = BingNewsSearch.prettifyThumbnail(result.jsonResponse);
                ArrayList<Bitmap>  tempbitList = new ArrayList<Bitmap>();
                for(int i = 0; i < thumbList.size(); i++) {
                    urldisplay = thumbList.get(i);
                    Bitmap bitm = null;
                    InputStream in = new java.net.URL(urldisplay).openStream();
                    bitm = BitmapFactory.decodeStream(in);
                    tempbitList.add(bitm);
                }
                bitmapList=tempbitList;
            }
            catch (Exception e) {
                e.printStackTrace(System.out);
            }
            return bitmapList;
        }
        protected void onPostExecute(ArrayList<Bitmap> bitm){

            lAdapter = new NewsListAdapter(FinancialNews.this, urlList, bitm);

            lView.setAdapter(lAdapter);
            ViewCompat.setNestedScrollingEnabled(lView, true);

            lView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                    Toast.makeText(FinancialNews.this, urlList.get(i)+" "+bitmapList.get(i), Toast.LENGTH_LONG).show();

                }
            });

        }
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_financial_news);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        String news = "stock market";
        lView = (ListView) findViewById(R.id.newsList);
        new NewsTask().execute(news);



    }
}

