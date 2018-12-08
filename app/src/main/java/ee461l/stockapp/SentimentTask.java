package ee461l.stockapp;

import android.os.AsyncTask;

public class SentimentTask extends AsyncTask<String, Void, String> {
    public static String[] companyInfo;
//    public interface AsyncResponse {
//        public void processFinish(String output);
//    }
//
//    public AsyncResponse delegate = null;
//
//    public SentimentTask(AsyncResponse delegate){
//        this.delegate = delegate;
//    }
    protected  String doInBackground(String ... str){
        String prettyRespNews = "";
        String prettyRespSenti = "";
        try {
            companyInfo = str;
            // System.out.println("Searching the Web for: " + BingNewsSearch.searchTerm);
            SearchResults result = BingNewsSearch.SearchNews(str[0] +" opinion");
            System.out.println("\nRelevant HTTP Headers:\n");
            for (String header : result.relevantHeaders.keySet())
                System.out.println(header + ": " + result.relevantHeaders.get(header));
            System.out.println("\nJSON Response:\n");
            System.out.println(BingNewsSearch.prettify(result.jsonResponse));
            prettyRespNews = BingNewsSearch.prettify(result.jsonResponse);


            Documents documents = new Documents ();
            documents.add("1", "en", prettyRespNews);
            String response = SentimentAnalyst.GetSentiment (documents);
            prettyRespSenti  = SentimentAnalyst.prettify (response);
            System.out.println (SentimentAnalyst.prettify (response));

        }
        catch (Exception e) {
            e.printStackTrace(System.out);
            System.out.println(e);
            // System.exit(1);
        }


        return prettyRespSenti;// + "  " + prettyRespNews;
    }
    protected void onPostExecute(String str){
        System.out.println(str);
        String sentimentString = "";
        sentimentString = str + setSentimentRange(Double.parseDouble(str));

        companyInfo[0] = sentimentString;
//        delegate.processFinish(sentimentString);

    }
    public String setSentimentRange(double sentiment){
//        TextView textViews = new TextView(this);
//        textViews = (TextView) findViewById(R.id.senti_text_range);
//        textViews.setTextSize(50);
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