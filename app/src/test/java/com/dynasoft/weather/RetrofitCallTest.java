package com.dynasoft.weather;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricGradleTestRunner;
import org.robolectric.annotation.Config;
import org.robolectric.util.ActivityController;

import com.dynasoft.weather.activity.MainActivity;
import com.dynasoft.weather.client.RetrofitAPIClient;
import com.dynasoft.weather.model.OpenWeatherMap;

/**
 * Created by sundeep on 10/17/17.
 */
@Config(constants = BuildConfig.class, sdk = 18,
        manifest = "app/src/main/AndroidManifest.xml")
@RunWith(RobolectricGradleTestRunner.class)
public class RetrofitCallTest {

    private MainActivity mainActivity;

    @Mock
    public RetrofitAPIClient mockRetrofitApiImpl;

    @Captor
    private OpenWeatherMap callbackArgumentCaptor;

    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
    }

    public RetrofitCallTest(RetrofitAPIClient mockRetrofitApiImpl, OpenWeatherMap callbackArgumentCaptor) {
        this.mockRetrofitApiImpl = mockRetrofitApiImpl;
        this.callbackArgumentCaptor = callbackArgumentCaptor;
    }

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);

        ActivityController<MainActivity> controller = Robolectric.buildActivity(MainActivity.class);
        mainActivity = controller.get();
        controller.create();
    }

    @Test
    public void isCallBackSuccess(){

    }
}