package ee461l.stockapp;

import android.support.test.espresso.Espresso;
import android.support.test.espresso.intent.Intents;
import android.support.test.rule.ActivityTestRule;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import java.io.InputStream;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import javax.net.ssl.HttpsURLConnection;

import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.closeSoftKeyboard;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isClickable;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static ee461l.stockapp.Define.*;
import static org.hamcrest.Matchers.not;
import static org.junit.Assert.*;
import static android.support.test.espresso.intent.Intents.intended;
import static android.support.test.espresso.intent.matcher.IntentMatchers.hasComponent;
/**
 * Created by YongSub on 11/30/2018.
 */
public class MainActivityTest {

    @Rule
    public ActivityTestRule<MainActivity> main = new ActivityTestRule<>(MainActivity.class);

    @Before
    public void setUp(){
        Intents.init();
    }

    @After
    public void Done(){
        Intents.release();
    }

//    @Test
//    public void LoginTest() {
//        /*
//        Test if Google Signin works
//         */
//    }

    @Test
    public void DisplayTest() {


        if(MainActivity.current_account.getAccount() != null){
            Espresso.onView(withId(R.id.search_stocks)).check(matches(isDisplayed()));
            Espresso.onView(withId(R.id.my_favorites)).check(matches(isDisplayed()));
            Espresso.onView(withId(R.id.crypto)).check(matches(isDisplayed()));
            Espresso.onView(withId(R.id.financial_news)).check(matches(isDisplayed()));
        }else{
            Espresso.onView(withId(R.id.search_stocks)).check(matches(not(isDisplayed())));
            Espresso.onView(withId(R.id.my_favorites)).check(matches(not(isDisplayed())));
            Espresso.onView(withId(R.id.crypto)).check(matches(not(isDisplayed())));
            Espresso.onView(withId(R.id.financial_news)).check(matches(not(isDisplayed())));
        }

        /*
        This test checks if all the buttons are displayed on the landing page
         */
    }

    @Test
    public void ButtonClickTest() {
        /*
        This test checks if all the buttons on the main page are clickable
         */
        Espresso.onView(withId(R.id.search_stocks)).check(matches(isClickable()));
        Espresso.onView(withId(R.id.my_favorites)).check(matches(isClickable()));
        Espresso.onView(withId(R.id.crypto)).check(matches(isClickable()));
        Espresso.onView(withId(R.id.financial_news)).check(matches(isClickable()));
    }

    @Test
    public void DoesSearchStockPageLoad() {
        /*
        This test checks if SearchStock intent is able to be launched
         */
        Espresso.onView(withId(R.id.search_stocks)).perform(click());
        intended(hasComponent(SearchStocks.class.getName()));
    }

    @Test
    public void DoesSearchButtonWork() {
        Espresso.onView(withId(R.id.search_stocks)).perform(click());
        Espresso.onView(withId(R.id.search_query)).perform(typeText("GOOGL"), closeSoftKeyboard());
        Espresso.onView(withId(R.id.search_go)).perform(click());
        intended(hasComponent(SearchResultsDisplay.class.getName()));
    }

    @Test
    public void DoesFavoritePageLoad() {
        /*
        This test checks if Favorite page intent is able to be launched
         */
        Espresso.onView(withId(R.id.my_favorites)).perform(click());
        intended(hasComponent(FavoritesList.class.getName()));

    }

    @Test
    public void DoesTidBitPageLoad() {
        /*
        This test checks if Todays TidBits page intent is able to be launched
         */
        Espresso.onView(withId(R.id.crypto)).perform(click());
        intended(hasComponent(Crypto.class.getName()));
    }

    @Test
    public void DoesNewsPageLoad() {
        /*
        This test checks if Financial News page intent is able to be launched
         */
        Espresso.onView(withId(R.id.financial_news)).perform(click());
        intended(hasComponent(FinancialNews.class.getName()));
    }

    @Test
    public void SearchStockSearchTest() {
        /*
        This test check if search is functional in search stock page.
        It's checking if Favorite button and Reset button get displayed after searching
        We might want to test if there are needed information(i.e stock symbol, graph, stock value etc.) on individual stock page after we implement individual stock page
         */
        Espresso.onView(withId(R.id.search_stocks)).perform(click());
        Espresso.onView(withId(R.id.search_query)).perform(typeText("GOOGL"), closeSoftKeyboard());
        Espresso.onView(withId(R.id.search_go)).perform(click());

        Espresso.onView(withId(R.id.go_back_search)).check(matches(isDisplayed()));
        Espresso.onView(withId(R.id.add_favorites)).check(matches(isDisplayed()));
        Espresso.onView(withId(R.id.go_back_search)).check(matches(isClickable()));
        Espresso.onView(withId(R.id.add_favorites)).check(matches(isClickable()));

    }

    @Test
    public void StockAPITest() throws Exception {
        CallIEX fetchStock = new CallIEX("search");
        String searchText = "GOOGL";
        @SuppressWarnings("unused") Void result = fetchStock.execute(apiEndpoint + stockRequest + searchText + requestCQNSCLP).get();
        assertNotNull(SearchStocks.info);
    }

    @Test
    public void StockAPIFormatTest() throws Exception {
        CallIEX fetchStock = new CallIEX("search");
        String searchText = "GOOGL";
        @SuppressWarnings("unused") Void result = fetchStock.execute(apiEndpoint + stockRequest + searchText + requestCQNSCLP).get();
        Company expectedcompany = new Company();
        expectedcompany.setSymbol("GOOGL");
        assertEquals(expectedcompany.getSymbol(), SearchStocks.info.getSymbol());
    }

    @Test
    public void NewsAPITest() throws Exception {
        URL url = new URL(BingNewsSearch.host + BingNewsSearch.path + "?q=" +  URLEncoder.encode("stock market", "UTF-8"));
        HttpsURLConnection connection = (HttpsURLConnection)url.openConnection();
        connection.setRequestProperty("Ocp-Apim-Subscription-Key", BingNewsSearch.subscriptionKey);
        InputStream stream = connection.getInputStream();
        String response = new Scanner(stream).useDelimiter("\\A").next();
        assertNotNull(response);
    }

    @Test
    public void NewsAPIFormatTest() throws Exception {
        URL url = new URL(BingNewsSearch.host + BingNewsSearch.path + "?q=" +  URLEncoder.encode("stock market", "UTF-8"));
        HttpsURLConnection connection = (HttpsURLConnection)url.openConnection();
        connection.setRequestProperty("Ocp-Apim-Subscription-Key", BingNewsSearch.subscriptionKey);
        InputStream stream = connection.getInputStream();
        String response = new Scanner(stream).useDelimiter("\\A").next();
        String expected = "stock market";
        assertTrue(response.contains(expected));
    }

    @Test
    public void SentimentAPITest() throws Exception {
        SearchResults result = BingNewsSearch.SearchNews("GOOGL" +" opinion");
        String prettyRespNews = BingNewsSearch.prettify(result.jsonResponse);

        Documents documents = new Documents ();
        documents.add("1", "en", prettyRespNews);
        String response = SentimentAnalyst.GetSentiment (documents);
        assertNotNull(response);
    }

    @Test
    public void SentimentAPIFormatTest() throws Exception {
        SearchResults result = BingNewsSearch.SearchNews("aaasdasdawga" +" opinion");
        String prettyRespNews = BingNewsSearch.prettify(result.jsonResponse);

        Documents documents = new Documents ();
        documents.add("1", "en", prettyRespNews);
        String response = SentimentAnalyst.GetSentiment (documents);
        assertNotNull(response);
    }

    @Test
    public void DataBaseTest() {
        /*
        This tests if you can read our database
         */
        User user = new User();       // <-------- We added this user already in our Database
        //noinspection ConstantConditions
        user.setId(MainActivity.current_account.getId());
        user.setName("EE461L");
        List<String> favorite = new ArrayList<>();
        favorite.add("GOOGL");
        user.setFavorites(favorite);
        List<User> userlist = MainActivity.appDataBase.dao().getUsers();
        User gotuser = new User();
        for(User u : userlist){
            if(u.getId().equalsIgnoreCase(user.getId())){
                gotuser = u;
            }
        }
        assertEquals(user.getFavorites(), gotuser.getFavorites());
    }
}