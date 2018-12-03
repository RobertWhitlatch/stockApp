package ee461l.stockapp;

import android.arch.persistence.room.Room;
import android.content.Intent;
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

import static java.lang.Integer.parseInt;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    GoogleSignInClient mGoogleSignInClient;
    public static GoogleSignInAccount current_account; //EDITED
    public static AppDataBase appDataBase; //EDITED

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
        if(account != null){ //EDITED  (Maybe edge case?) // RESPONSE: It is guarded against in updateUI();
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
            current_account = account; //EDITED
            // Signed in successfully, show authenticated UI.
            updateUI(account);
        } catch (ApiException e) {
            // The ApiException status code indicates the detailed failure reason.
            // Please refer to the GoogleSignInStatusCodes class reference for more information.
            Log.w("TAG", "signInResult:failed code=" + e.getStatusCode());
            updateUI(null);
        }
    }

    protected void updateUI(GoogleSignInAccount account){
        if(account == null){
            return;
        }
        String userId = account.getId();
        User user = appDataBase.dao().getUser(userId);
        if(user == null){ // EDIT: Changes tested, much simpler implementation possible with getUser();
            user = new User();
            user.setId(userId);
            user.setName(account.getEmail());
            user.setFavorites(new ArrayList<String>());
            appDataBase.dao().addUser(user);
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
        findViewById(R.id.my_stocks).setVisibility(View.VISIBLE);
        findViewById(R.id.todays_tidbits).setVisibility(View.VISIBLE);
        findViewById(R.id.financial_news).setVisibility(View.VISIBLE);
    }

    public void gotoSearchStocks(View v){
        Intent intent = new Intent(getApplicationContext(), SearchStocks.class);
        startActivity(intent);
    }

    public void gotoMyStocks(View v){
        Intent intent = new Intent(getApplicationContext(), StockListActivity.class);
        startActivity(intent);
    }

    public void gotoTodaysTidbits(View v){
        Intent intent = new Intent(getApplicationContext(), TodaysTidbits.class);
        startActivity(intent);
    }

    public void gotoFinancialNews(View v){
        Intent intent = new Intent(getApplicationContext(), FinancialNews.class);
        startActivity(intent);
    }

}
