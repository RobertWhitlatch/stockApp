package ee461l.stockapp;

import android.arch.persistence.room.Room;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.Task;

import java.util.ArrayList;
import java.util.List;

import static ee461l.stockapp.Define.*;
import static ee461l.stockapp.FavoritesList.favorites;
import static ee461l.stockapp.FavoritesList.symbols;
import static ee461l.stockapp.FavoritesList.logoURLs;
import static ee461l.stockapp.FinancialNews.newsContainer;


public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private GoogleSignInClient mGoogleSignInClient;
    public static GoogleSignInAccount current_account;
    public static AppDataBase appDataBase;
    public static User currentUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN).requestEmail().build();
        mGoogleSignInClient = GoogleSignIn.getClient(this, gso);

        findViewById(R.id.sign_in_button).setOnClickListener(this);
        appDataBase = Room.databaseBuilder(getApplicationContext(), AppDataBase.class, "userdb").allowMainThreadQueries().build(); //EDITED

    }

    @Override
    protected void onStart(){
        super.onStart();
        GoogleSignInAccount account = GoogleSignIn.getLastSignedInAccount(this);
        if(account != null){
            current_account = account;
        }
        updateUI(account);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.sign_in_button:
                signIn();
                break;
        }
    }

    private void signIn() {
        Intent signInIntent = mGoogleSignInClient.getSignInIntent();
        startActivityForResult(signInIntent, 9001);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        // Result returned from launching the Intent from GoogleSignInClient.getSignInIntent(...);
        if (requestCode == 9001) {
            // The Task returned from this call is always completed, no need to attach
            // a listener.
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            handleSignInResult(task);
        }
    }

    private void handleSignInResult(Task<GoogleSignInAccount> completedTask) {
        try {
            GoogleSignInAccount account = completedTask.getResult(ApiException.class);
            current_account = account;
            // Signed in successfully, show authenticated UI.
            updateUI(account);
        } catch (ApiException e) {
            // The ApiException status code indicates the detailed failure reason.
            // Please refer to the GoogleSignInStatusCodes class reference for more information.
            Log.w("TAG", "signInResult:failed code=" + e.getStatusCode());
            updateUI(null);
        }
    }

    private void updateUI(GoogleSignInAccount account){
        if(account == null){
            return;
        }

        String userId = account.getId();

        currentUser = appDataBase.dao().getUser(userId);
        if(currentUser == null){
            currentUser = new User();
            //noinspection ConstantConditions
            currentUser.setId(userId);
            currentUser.setName(account.getEmail());
            currentUser.setFavorites(new ArrayList<String>());
            appDataBase.dao().addUser(currentUser);
        }else if(currentUser.getFavorites() != null && currentUser.getFavorites().size() != 0){
            currentUser.sortFavorites();
            appDataBase.dao().updateUser(currentUser);
        }

        TextView tv = findViewById(R.id.user_greeting);
        String name = account.getGivenName();
        if(name == null){
            name = account.getDisplayName();
        }
        if(name == null){
            name = account.getEmail();
        }
        String text = "Welcome, " + name + "!";
        tv.setText(text);
        findViewById(R.id.sign_in_button).setVisibility(View.INVISIBLE);
        findViewById(R.id.search_stocks).setVisibility(View.VISIBLE);
        findViewById(R.id.my_favorites).setVisibility(View.VISIBLE);
        findViewById(R.id.crypto).setVisibility(View.VISIBLE);
        findViewById(R.id.financial_news).setVisibility(View.VISIBLE);
    }

    public void gotoSearchStocks(View v){
        Intent intent = new Intent(getApplicationContext(), SearchStocks.class);
        startActivity(intent);
    }

    public void gotoFavorites(View v){
        List<String> symbolList = currentUser.getFavorites();
        symbols = new String[symbolList.size()];
        symbolList.toArray(symbols);
        logoURLs = new String[symbolList.size()];
        favorites = new SearchInfo[symbolList.size()];
        for(int i = 0; i < symbolList.size(); i++){
            CallIEX fetchFavorite = new CallIEX("favorites" , i);
            try {
                Object obj = fetchFavorite.execute(apiEndpoint + stockRequest + symbolList.get(i) + requestCQNSCLP).get();
                logoURLs[i] = favorites[i].getLogo().getUrl();
            } catch (Exception e) {
                e.printStackTrace();
            }
            SentimentTask fetchSentiment = new SentimentTask(favorites[i]);
            try {
                Object obj = fetchSentiment.execute(favorites[i].getSymbol());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        Intent intent = new Intent(getApplicationContext(), FavoritesList.class);
        startActivity(intent);
    }

    public void gotoCryptoList(View v){
        CallIEX fetchCrypto = new CallIEX("crypto");
        try {
            Object obj = fetchCrypto.execute(apiEndpoint + requestCrypto).get();
        } catch (Exception e) {
            e.printStackTrace();
        }
        Intent intent = new Intent(getApplicationContext(), CryptoList.class);
        startActivity(intent);
    }

    public void gotoFinancialNews(View v){
        try{
            NewsTask fetchNews = new NewsTask("news");
            Object obj = fetchNews.execute(newsQuery).get();
        } catch(Exception e) {
            e.printStackTrace();
        }
        for(int i = 0; i < newsContainer.size(); i++){
            try{
                NewsTask fetchNews = new NewsTask("image", i);
                Object obj = fetchNews.execute(newsQuery).get();
            } catch(Exception e) {
                e.printStackTrace();
            }
        }
        for(int i = 0; i < newsContainer.size(); i++){
            if(newsContainer.getBitmapAtIndex(i) == null){
                newsContainer.setBitmapAtIndex(BitmapFactory.decodeResource(getResources(), R.drawable.dummy), i);
            }
        }
        newsContainer.size();
        Intent intent = new Intent(getApplicationContext(), FinancialNews.class);
        startActivity(intent);
    }

}
