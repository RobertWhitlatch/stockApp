package ee461l.stockapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.View;

public class CryptoList extends AppCompatActivity {

    @SuppressWarnings("CanBeFinal")
    public static volatile CryptoInfo cryptoInfo = new CryptoInfo();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crypto_list);

        cryptoInfo.buildCryptoSymbols();

        RecyclerView recyclerView = findViewById(R.id.crypto_list);
        recyclerView.setAdapter(new CryptoListAdapter(cryptoInfo.getCryptoSymbols()));
    }

    public void gotoHome(View v){
        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
        startActivity(intent);
    }
}
