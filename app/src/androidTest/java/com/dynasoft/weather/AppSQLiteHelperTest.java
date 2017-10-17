package com.dynasoft.weather;

import android.database.sqlite.SQLiteDatabase;
import android.support.test.rule.ActivityTestRule;
import android.test.AndroidTestCase;
import android.test.RenamingDelegatingContext;
import android.util.Log;

import com.dynasoft.weather.helper.AppSQLiteHelper;
import com.dynasoft.weather.helper.DatabaseConstants;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

/**
 * Created by sundeep on 10/17/17.
 */
public class AppSQLiteHelperTest extends AndroidTestCase{

    private String TAG = AppSQLiteHelperTest.class.getSimpleName();

    private AppSQLiteHelper appSQLiteHelper;

    private static String cityName;

    @Rule
    public ActivityTestRule activityTestRule = new ActivityTestRule(AppSQLiteHelper.class);


    @Before
    public void setUp() throws Exception {
        RenamingDelegatingContext context = new RenamingDelegatingContext(activityTestRule.getActivity(), "test_");
        appSQLiteHelper = new AppSQLiteHelper(mContext);
    }

    @After
    public void tearDown() throws Exception {
        appSQLiteHelper.close();
        appSQLiteHelper = null;

    }

    @Test
    public void onCreate() throws Exception {

    }

    @Test
    public void onUpgrade() throws Exception {

    }

    @Test
    public void insertCity() throws Exception {

    }

    @Test
    public void getAllCities() throws Exception {

    }

    @Test
    public void deleteCity() throws Exception {

    }

    @Test
    public void deleteAll() throws Exception {

    }

    @Test
    public void testCreateDB(){

        SQLiteDatabase sqLiteDatabase = appSQLiteHelper.getWritableDatabase();
        assertTrue(sqLiteDatabase.isOpen());
        sqLiteDatabase.close();

        Log.d(TAG, "test create database pass");
    }

    @Test
    public void testDBDrop(){
        assertTrue(mContext.deleteDatabase(DatabaseConstants.DB_NAME));
        Log.d(TAG, "test drop database pass");
    }

}