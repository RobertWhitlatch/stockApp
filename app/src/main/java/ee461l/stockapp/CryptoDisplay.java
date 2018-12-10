package ee461l.stockapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import static ee461l.stockapp.Define.apiEndpoint;
import static ee461l.stockapp.Define.requestCrypto;
import static ee461l.stockapp.CryptoList.cryptoInfo;

public class CryptoDisplay extends AppCompatActivity {

    private int position;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crypto_display);
        position = getIntent().getIntExtra("position",-1);

        RecyclerView resultsView = findViewById(R.id.crypto_display);
        resultsView.setLayoutManager(new LinearLayoutManager(this));
        Crypto[] crypto = cryptoInfo.getCrypto();
        resultsView.setAdapter(new CryptoDisplayAdapter(crypto[position].getDisplaySet()));
    }

    public void goBackToCryptoList(View v){
        CallIEX fetchCrypto = new CallIEX("crypto");
        try {
            Object obj = fetchCrypto.execute(apiEndpoint + requestCrypto).get();
        } catch (Exception e) {
            e.printStackTrace();
        }
        Intent intent = new Intent(getApplicationContext(), CryptoList.class);
        startActivity(intent);
    }

    public void gotoHome(View v){
        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
        startActivity(intent);
    }

}
