package ee461l.stockapp;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.Html;
import android.text.method.LinkMovementMethod;

import android.widget.ImageView;
import android.widget.TextView;

import java.io.InputStream;
import java.util.ArrayList;



public class FinancialNews extends AppCompatActivity {

    ArrayList<String> urlList = new ArrayList<String>();
    ArrayList<String> thumbList = new ArrayList<String>();
    ArrayList<Bitmap> bitmapList = new ArrayList<Bitmap>();
    TextView textView1;
    TextView textView2;
    TextView textView3;
    TextView textView4;
    TextView textView5;


    ImageView imageView1;
    ImageView imageView2;
    ImageView imageView3;
    ImageView imageView4;
    ImageView imageView5;
    ArrayList<TextView> textViewList = new ArrayList<TextView>();
    ArrayList<ImageView> imageViewList = new ArrayList<ImageView>();

    /*
    -NewsTask uses bing news search API to find 3 news articles based on user input
    -displays links and thumbnails for the articles
     */
    class NewsTask extends AsyncTask<String, Void, ArrayList<Bitmap>> {
        protected ArrayList<Bitmap> doInBackground(String... str){
            String urldisplay = "";
            try {
                //System.out.println("Searching the Web for: " + BingNewsSearch.searchTerm);
                SearchResults result = BingNewsSearch.SearchNews(str[0]);
                System.out.println("\nRelevant HTTP Headers:\n");
                for (String header : result.relevantHeaders.keySet())
                    System.out.println(header + ": " + result.relevantHeaders.get(header));
                System.out.println("\nJSON Response:\n");
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
            //String s =
            //System.out.println(str);
            textView1.setMovementMethod(LinkMovementMethod.getInstance());
            textView2.setMovementMethod(LinkMovementMethod.getInstance());
            textView3.setMovementMethod(LinkMovementMethod.getInstance());

            for(int i = 0; i < urlList.size(); i++) {
                textViewList.get(i).setText(Html.fromHtml(urlList.get(i), Html.FROM_HTML_MODE_LEGACY));
//                textViewList.get(i).setY(250 + (i*400));
//                textViewList.get(i).setX(700);

                imageViewList.get(i).setImageBitmap(bitm.get(i));
//                imageViewList.get(i).setY(200 + (i*400));
            }

        }
    }

    /*
    -initializes all textViews and imageViews
     */
    public void initialize(){
        ArrayList<TextView> textViewListtemp = new ArrayList<TextView>();
        ArrayList<ImageView> imageViewListtemp = new ArrayList<ImageView>();
        textView1 = (TextView) findViewById(R.id.newsi_text1);
        imageView1 =(ImageView) findViewById(R.id.news_image1);
        textView2 = (TextView) findViewById(R.id.newsi_text2);
        imageView2 =(ImageView) findViewById(R.id.news_image2);
        textView3 = (TextView) findViewById(R.id.newsi_text3);
        imageView3 =(ImageView) findViewById(R.id.news_image3);
        textView4 = (TextView) findViewById(R.id.newsi_text4);
        imageView4 =(ImageView) findViewById(R.id.news_image4);
        textView5 = (TextView) findViewById(R.id.newsi_text5);
        imageView5 =(ImageView) findViewById(R.id.news_image5);
        textViewListtemp.add(textView1);
        textViewListtemp.add(textView2);
        textViewListtemp.add(textView3);
        textViewListtemp.add(textView4);
        textViewListtemp.add(textView5);
        imageViewListtemp.add(imageView1);
        imageViewListtemp.add(imageView2);
        imageViewListtemp.add(imageView3);
        imageViewListtemp.add(imageView4);
        imageViewListtemp.add(imageView5);
        textViewList = textViewListtemp;
        imageViewList = imageViewListtemp;
    }

    //    public void newsfindButton(View c){
//        initialize();
//        EditText editText =(EditText) findViewById(R.id.newsi);
//        String s = editText.getText().toString();
//
//        String news = "stock market";
//        new NewsTask().execute(news);
//    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_financial_news);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        initialize();
        //EditText editText =(EditText) findViewById(R.id.newsi);
        //String s = editText.getText().toString();

        String news = "stock market";
        new NewsTask().execute(news);

    }
}

