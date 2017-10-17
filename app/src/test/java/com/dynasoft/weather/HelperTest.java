package com.dynasoft.weather;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.dynasoft.weather.activity.MainActivity;
import com.dynasoft.weather.helper.AppSQLiteHelper;
import com.dynasoft.weather.helper.Helper;

import static org.junit.Assert.assertNotEquals;

/**
 * Created by sundeep on 10/17/17.
 */
public class HelperTest {

    private Helper helper;

    private MainActivity mMainActivity = null;

    @Before
    public void setUp() throws Exception {
        helper = new Helper();
    }

    @After
    public void tearDown() throws Exception {
        helper = null;
    }

    @Test
    public void unixTimeStampToTime() throws Exception {

    }

    @Test
    public void getIcon() throws Exception {

    }

    @Test
    public void getDateNow() throws Exception {

        DateFormat dateFormat = new SimpleDateFormat("ddMMMYYYY hh:mm");
        String date = dateFormat.format(new Date());

        String dateFromHelper = helper.getDateNow();
        if(null != dateFromHelper) {
            assertNotEquals(dateFromHelper, date);
        }

    }

}