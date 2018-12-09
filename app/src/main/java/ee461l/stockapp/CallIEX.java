package ee461l.stockapp;

import android.os.AsyncTask;

import com.google.gson.Gson;


import static ee461l.stockapp.CryptoList.cryptoInfo;
import static ee461l.stockapp.SearchStocks.terms;
import static ee461l.stockapp.SearchStocks.info;
import static ee461l.stockapp.FavoritesList.favorites;

//Changed it to public to Test
public class CallIEX extends AsyncTask<String,Void,Void> {

    private String mode;
    private int index;

    public CallIEX(String mode){
        this.mode = mode;
    }

    public CallIEX(String mode, int index){
        this.mode = mode;
        this.index = index;
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
            } else if(mode.equalsIgnoreCase("favorites")) {
                favorites[index] = gson.fromJson(jsonStr, SearchInfo.class);
            } else if(mode.equalsIgnoreCase("crypto")) {
                Crypto[] ref = gson.fromJson(jsonStr, Crypto[].class);
                cryptoInfo.setCrypto(ref);
            }
        }
        return null;
    }

    @Override
    protected void onPostExecute(Void aVoid) {
        super.onPostExecute(aVoid);
    }

}