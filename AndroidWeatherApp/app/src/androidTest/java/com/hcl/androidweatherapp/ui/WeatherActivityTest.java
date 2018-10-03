package com.hcl.androidweatherapp.ui;


import android.test.ActivityInstrumentationTestCase2;
import com.hcl.androidweatherapp.R;
import org.junit.Test;

import static android.support.test.espresso.Espresso.onData;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withSpinnerText;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.anything;
import static org.hamcrest.Matchers.containsString;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.assertion.ViewAssertions.matches;



public class WeatherActivityTest extends
        ActivityInstrumentationTestCase2<WeatherActivity> {

    public WeatherActivityTest() {
        super(WeatherActivity.class);
    }

    protected void setUp() throws Exception {
        super.setUp();
        getActivity();
    }
    @Test
    public void	testFindSpinnerItem(){

        onView(withId(R.id.spinner)).check(matches(withSpinnerText(containsString("Amsterdam"))));

    }
    @Test
    public void	testClickOnSpinnerItem(){

        try {
            Thread.sleep(8000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        onView(withId(R.id.spinner)).perform(click());
        onData(anything()).atPosition(2).perform(click());
        onView(withId(R.id.spinner)).check(matches(withSpinnerText(containsString("London"))));

    }

    @Test
    public void	testClickOnSpinnerItemPosition(){

        try {
            Thread.sleep(6000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        onView(withId(R.id.spinner)).perform(click());
        onData(anything()).atPosition(4).perform(click());
        onView(withId(R.id.spinner)).check(matches(withSpinnerText(containsString("Tokyo"))));

    }
    @Test
    public void testFindCityText() throws Exception {

        onData(anything()).atPosition(2).perform(click());

        try {
            Thread.sleep(6000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        onView(withId(R.id.city)).check(matches(withText("London")));
    }



}