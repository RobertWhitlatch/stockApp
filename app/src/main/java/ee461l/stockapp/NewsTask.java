package ee461l.stockapp;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import java.io.InputStream;

import static ee461l.stockapp.FinancialNews.newsContainer;

public class NewsTask extends AsyncTask<String,Void,Void> {

    private String mode;
    private int index;

    public NewsTask(String mode){
        this.mode = mode;
    }

    public NewsTask(String mode, int index){
        this.mode = mode;
        this.index = index;
    }

    protected Void doInBackground(String... str){
        switch(mode){
            case "news":
                try {
                    SearchResults result = BingNewsSearch.SearchNews(str[0]);
                    if(result != null) {
                        newsContainer.setUrlList(BingNewsSearch.prettifyURL(result.jsonResponse));
                        newsContainer.setBitmapStringList(BingNewsSearch.prettifyThumbnail(result.jsonResponse));
                        newsContainer.calculate();
                    }
                }
                catch (Exception e) {
                    e.printStackTrace(System.out);
                }
                break;
            case "image":
                try{
                    String bitmapUrl = newsContainer.getBitmapStringAtIndex(index);
                    InputStream in = new java.net.URL(bitmapUrl).openStream();
                    Bitmap bitm = BitmapFactory.decodeStream(in);
                    newsContainer.setBitmapAtIndex(bitm, index);
                } catch (Exception e){
                    e.printStackTrace();
                }
                break;
            default:
                break;
        }
        return null;
    }

}
