package ee461l.stockapp;

import android.os.AsyncTask;

public class FetchSearchResults extends AsyncTask<String,Void,Void> {

    public interface AsynResponse {
        void processFinish(Boolean output);
    }

    public AsynResponse asynResponse;
    public String search;

    public FetchSearchResults(AsynResponse asynResponse) {
        this.asynResponse = asynResponse;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected Void doInBackground(String... url) {

        HttpHandler httpHandler = new HttpHandler();
        search = httpHandler.makeServiceCall(url[0]);
        if (search != null) {

            SearchStocks.searchResults = search;
        }
        return null;
    }

    @Override
    protected void onPostExecute(Void aVoid) {
        super.onPostExecute(aVoid);
        asynResponse.processFinish(true);
    }

}
