package ee461l.stockapp;

import android.app.Instrumentation;
import android.content.Intent;
import android.support.test.espresso.Espresso;
import android.support.test.espresso.intent.Intents;
import android.support.test.rule.ActivityTestRule;
import android.widget.ImageButton;
import android.widget.ListView;

import static android.support.test.espresso.Espresso.onView;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static android.support.test.InstrumentationRegistry.getInstrumentation;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.action.ViewActions.typeTextIntoFocusedView;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isClickable;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static ee461l.stockapp.Define.apiEndpoint;
import static ee461l.stockapp.Define.requestCQNSC;
import static org.hamcrest.Matchers.not;
import static org.junit.Assert.*;
import static android.support.test.espresso.intent.Intents.intended;
import static android.support.test.espresso.intent.matcher.IntentMatchers.hasComponent;
/**
 * Created by YongSub on 11/30/2018.
 */
public class MainActivityTest {
    @Rule
    public ActivityTestRule<MainActivity> main = new ActivityTestRule<MainActivity>(MainActivity.class);

    @Before
    public void setUp(){
        Intents.init();
    }

    @After
    public void Done(){
        Intents.release();
    }

    @Test
    public void LoginTest() throws Exception {
        /*
        Test if Google Signin works
         */
    }

    @Test
    public void DisplayTest() throws Exception {

        /*We probably want to test if those buttons are displayed when the user is not logged in
        ex)
        if(MainActivity.current_account.getAccount() != null){
            Espresso.onView(withId(R.id.search_stocks)).check(matches(isDisplayed()));
        }else{
            Espresso.onView(withId(R.id.search_stocks)).check(matches(not(isDisplayed())));
        }
        */

        /*
        This test checks if all the buttons are displayed on the landing page
         */
        Espresso.onView(withId(R.id.search_stocks)).check(matches(isDisplayed()));
        Espresso.onView(withId(R.id.my_stocks)).check(matches(isDisplayed()));
        Espresso.onView(withId(R.id.todays_tidbits)).check(matches(isDisplayed()));
        Espresso.onView(withId(R.id.financial_news)).check(matches(isDisplayed()));
    }

    @Test
    public void ButtonClickTest() throws Exception {
        /*
        This test checks if all the button on the ladning page are clickable
         */
        Espresso.onView(withId(R.id.search_stocks)).check(matches(isClickable()));
        Espresso.onView(withId(R.id.my_stocks)).check(matches(isClickable()));
        Espresso.onView(withId(R.id.todays_tidbits)).check(matches(isClickable()));
        Espresso.onView(withId(R.id.financial_news)).check(matches(isClickable()));
    }

    @Test
    public void DoesSearchStockPageLoad() throws Exception {
        /*
        This test checks if SearchStock intent is able to be launched
         */
        Espresso.onView(withId(R.id.search_stocks)).perform(click());
        intended(hasComponent(SearchStocks.class.getName()));
    }

    @Test
    public void DoesFavoritePageLoad() throws Exception {
        /*
        This test checks if Favorite page intent is able to be launched
         */
        Espresso.onView(withId(R.id.my_stocks)).perform(click());
        intended(hasComponent(StockListActivity.class.getName()));

    }

    @Test
    public void DoesTidBitPageLoad() throws Exception {
        /*
        This test checks if Todays TidBits page intent is able to be launched
         */
        Espresso.onView(withId(R.id.todays_tidbits)).perform(click());
        intended(hasComponent(TodaysTidbits.class.getName()));
    }

    @Test
    public void DoesNewsPageLoad() throws Exception {
        /*
        This test checks if Financial News page intent is able to be launched
         */
        Espresso.onView(withId(R.id.financial_news)).perform(click());
        intended(hasComponent(FinancialNews.class.getName()));
    }

    @Test
    public void SearchStockSearchTest() throws Exception {
        /*
        This test check if search is functional in search stock page.
        It's checking if Favorite button and Reset button get displayed after searching
        We might want to test if there are needed information(i.e stock symbol, graph, stock value etc.) on individual stock page after we implement individual stock page
         */
        Espresso.onView(withId(R.id.search_stocks)).perform(click());
        Espresso.onView(withId(R.id.search_query)).perform(typeText("GOOGL"));
        Espresso.onView(withId(R.id.search_go)).perform(click());

        Espresso.onView(withId(R.id.reset_search)).check(matches(isDisplayed()));
        Espresso.onView(withId(R.id.add_favorites)).check(matches(isDisplayed()));
        Espresso.onView(withId(R.id.reset_search)).check(matches(isClickable()));
        Espresso.onView(withId(R.id.add_favorites)).check(matches(isClickable()));

    }

    @Test
    public void SearchStockSearchEdgeCase() throws Exception {
        /*
        We might want to test if search button does something witl null input text or any stock symbol that does not exist
         */

    }

    @Test
    public void StockAPITest() throws Exception {
        SearchStocks.FetchStockResults fetchStock = new SearchStocks.FetchStockResults(null, null);
        String searchText = "GOOGL";
        Void result = fetchStock.execute(apiEndpoint + searchText + requestCQNSC).get();
        assertNotNull(SearchStocks.info);
    }

    @Test
    public void NewsAPITest() throws Exception {

    }

    @Test
    public void BingSearchAPITest() throws Exception {

    }

    @Test
    public void DataBaseTest() throws Exception {
        /*
        This tests if you can read our database
         */
        User user = new User();       // <-------- We added this user already in our Database
        user.setId(1);
        user.setName("EE461L");
        List<String> favorite = new ArrayList<>();
        favorite.add("GOOGL");
        user.setFavorites(favorite);
        List<User> userlist = MainActivity.appDataBase.dao().getUsers();
        User gotuser = new User();
        for(User u : userlist){
            if(u.getId() == 1){
                gotuser = u;
            }
        }
        assertEquals(user.getFavorites(), gotuser.getFavorites());
    }

}