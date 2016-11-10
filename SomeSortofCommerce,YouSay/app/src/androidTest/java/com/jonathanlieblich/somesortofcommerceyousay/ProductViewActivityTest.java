package com.jonathanlieblich.somesortofcommerceyousay;

import android.support.test.espresso.contrib.RecyclerViewActions;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.*;
import static android.support.test.espresso.matcher.ViewMatchers.*;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

/**
 * Created by jonlieblich on 11/10/16.
 */

@RunWith(AndroidJUnit4.class)
public class ProductViewActivityTest {

    @Rule
    public ActivityTestRule<ProductViewActivity> mActivity = new ActivityTestRule<>(ProductViewActivity.class);

    @Test
    public void testViews() throws Exception {
        onView(withId(R.id.fab)).check(matches(isDisplayed()));
        onView(withId(R.id.appbar)).check(matches(isDisplayed()));
        onView(withId(R.id.product_recycler_view)).check(matches(isDisplayed()));
        onView(withId(R.id.fab)).check(matches(isClickable()));
    }

    @Test
    public void testItemClick() throws Exception {
        onView(withId(R.id.product_recycler_view)).perform(RecyclerViewActions.actionOnItemAtPosition(0, click()));
        onView(withId(R.id.item_detail_appbar)).check(matches(isDisplayed()));
        onView(withId(R.id.item_description)).check(matches(isDisplayed()));
        onView(withId(R.id.item_name)).check(matches(isDisplayed()));
        onView(withId(R.id.add_to_cart)).check(matches(isClickable()));
        onView(withId(R.id.back_btn)).perform(click());
        onView(withId(R.id.product_recycler_view)).check(matches(isDisplayed()));
    }

    @Test
    public void testAddToCart() throws Exception {
        onView(withId(R.id.product_recycler_view)).perform(RecyclerViewActions.actionOnItemAtPosition(1, click()));
        onView(withId(R.id.item_quantity)).perform(click());
        onView(withId(R.id.item_quantity)).perform(typeText("3"));
        onView(withId(R.id.add_to_cart)).perform(click()).perform(pressBack());
        onView(withId(R.id.fab)).perform(click());
        onView(withId(R.id.cart_recycler_view)).check(matches(isDisplayed()));
        onView(withId(R.id.checkout_fab)).perform(click());
    }
}
