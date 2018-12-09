package ee461l.stockapp;

import android.os.AsyncTask;
import com.google.gson.Gson;

import static ee461l.stockapp.MitchellNews.newsInfo;

public class CallNewsApi extends AsyncTask<String,Void,Void> {

    public CallNewsApi(){
    }


    @Override
    protected Void doInBackground(String... url) {
        HttpHandler httpHandler = new HttpHandler();
        String jsonStr = httpHandler.makeServiceCall(url[0]);
        if (jsonStr != null) {
            Gson gson = new Gson();
            newsInfo =  gson.fromJson(jsonStr, NewsInfo.class);
        }
        return null;
    }

}