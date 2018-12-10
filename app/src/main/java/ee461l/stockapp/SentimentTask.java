package ee461l.stockapp;

import android.graphics.Color;
import android.os.AsyncTask;

import com.jjoe64.graphview.GraphView;

import java.lang.ref.WeakReference;

class SentimentTask extends AsyncTask<String, Void, String> {

    @SuppressWarnings("CanBeFinal")
    private WeakReference<GraphView> gv;

    public SentimentTask(WeakReference<GraphView> gv){
        this.gv = gv;
    }

    protected  String doInBackground(String ... str){
        String prettyRespNews;
        String prettyRespSenti = "";
        try {
            SearchResults result = BingNewsSearch.SearchNews(str[0] +" opinion");
            prettyRespNews = BingNewsSearch.prettify(result.jsonResponse);
            Documents documents = new Documents ();
            documents.add("1", "en", prettyRespNews);
            String response = SentimentAnalyst.GetSentiment (documents);
            prettyRespSenti  = SentimentAnalyst.prettify (response);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return prettyRespSenti;
    }

    protected void onPostExecute(String str){
        String sentimentString = "Sentiment: ";
        Double num = Double.parseDouble(str);
        num = (double)Math.round(num * 1000d) / 1000d;
        String numString = Double.toString(num);
        sentimentString += numString + setSentimentRange(Double.parseDouble(str));
        gv.get().setTitle(sentimentString);
        gv.get().setTitleTextSize(50.0f);
        gv.get().setTitleColor(Color.RED);
    }

    private String setSentimentRange(double sentiment){

        String s = "";
        if(sentiment>=0.0 && sentiment<0.2){
            s = " (very negative)";

        }
        else if(sentiment>=0.2 && sentiment<0.4){
            s = " (negative)";
        }
        else if(sentiment>=0.4 && sentiment<0.6){
            s = " (neutral)";
        }
        else if(sentiment>=0.6 && sentiment<0.8){
            s = " (positive)";
        }
        else if(sentiment>=0.8 && sentiment<=1.0){
            s = " (very positive)";
        }
        return s;

    }
}
