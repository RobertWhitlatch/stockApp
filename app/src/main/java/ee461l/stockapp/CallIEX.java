package ee461l.stockapp;

import android.os.AsyncTask;

import com.google.gson.Gson;


import static ee461l.stockapp.SearchStocks.terms;
import static ee461l.stockapp.SearchStocks.info;

//Changed it to public to Test
public class CallIEX extends AsyncTask<String,Void,Void> {

    private String mode;

    public CallIEX(String mode){
        this.mode = mode;
    }

    @Override
    protected Void doInBackground(String... url) {
        HttpHandler httpHandler = new HttpHandler();
        String jsonStr = httpHandler.makeServiceCall(url[0]);
        if (jsonStr != null) {
            Gson gson = new Gson();
            if(mode.equalsIgnoreCase("terms")){
                SymbolRefData[] ref = gson.fromJson(jsonStr, SymbolRefData[].class);
                terms.setRefData(ref);
            } else if(mode.equalsIgnoreCase("search")) {
                info = gson.fromJson(jsonStr, SearchInfo.class);
            }
        }
        return null;
    }

    @Override
    protected void onPostExecute(Void aVoid) {
        super.onPostExecute(aVoid);
    }

}