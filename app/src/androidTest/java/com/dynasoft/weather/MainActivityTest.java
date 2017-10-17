package com.dynasoft.weather;

import android.content.Context;
import android.provider.SyncStateContract;
import android.support.test.InstrumentationRegistry;
import android.support.test.rule.ActivityTestRule;
import android.support.v7.widget.SearchView;
import android.view.View;

import com.dynasoft.weather.activity.MainActivity;
import com.dynasoft.weather.model.City;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static junit.framework.Assert.assertNotNull;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotSame;
import static org.junit.Assert.assertThat;

/**
 * Created by sundeep on 10/17/17.
 */
public class MainActivityTest {

    private View cityTextView;
    private View txtDescription;
    private View lastUpdatedTextView;
    private View celsiusTextView;
    private View humidityTextView;
    private View currentTimeTextView;
    private View iconImageView;
    private View txtNoCity;
    private SearchView mSearchView;
    private MainActivity mMainActivity = null;

    @Rule
    public ActivityTestRule<MainActivity> activityTestRule = new ActivityTestRule<>(MainActivity.class);

    @Before
    public void setUp() throws Exception {
        mMainActivity = activityTestRule.getActivity();
    }

    @After
    public void tearDown() throws Exception {
        mMainActivity = null;
    }

    @Test
    public void onCreate() throws Exception {
        cityTextView = mMainActivity.findViewById(R.id.txtCity);
        txtDescription = mMainActivity.findViewById(R.id.txtDescription);
        currentTimeTextView = mMainActivity.findViewById(R.id.txtTime);
        lastUpdatedTextView = mMainActivity.findViewById(R.id.txtLastUpdate);
        celsiusTextView = mMainActivity.findViewById(R.id.txtCelsius);
        humidityTextView = mMainActivity.findViewById(R.id.txtHumidity);
        txtNoCity = mMainActivity.findViewById(R.id.txtNoCity);
        iconImageView = mMainActivity.findViewById(R.id.imageView);

        assertNotNull("mMainActivity is null", mMainActivity);
        assertNotNull("cityTextView is null", cityTextView);
        assertNotNull("txtDescription is null", txtDescription);
        assertNotNull("LastUpdate is null", lastUpdatedTextView);
        assertNotNull("celsiusTextView is null", celsiusTextView);
        assertNotNull("humidityTextView is null", humidityTextView);
        assertNotNull("currentTimeTextView is null", currentTimeTextView);
        assertNotNull("ImageView is null", iconImageView);
    }

    @Test
    public void onCreateOptionsMenu() throws Exception {

        mSearchView = (SearchView) mMainActivity.findViewById(R.id.searchView);

        assertEquals("SearchView not visible!", View.VISIBLE, mSearchView.getVisibility());
        assertNotSame("Search bar has text entered", "Boston", mSearchView.getQuery());
    }

    @Test
    public void clearAllViews() throws Exception {

    }


    @Test
    public void cityListHaveNoElements() {
        assertThat(new ArrayList().size(), is(0));
    }

    @Test
    public void useAppContext() throws Exception {
        // Context of the app under test.
        Context appContext = InstrumentationRegistry.getTargetContext();
        assertEquals("com.dynasoft.weather", appContext.getPackageName());
    }
}