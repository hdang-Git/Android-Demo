package com.example.hai.slidingtabnav.FragmentTesting;

import com.example.hai.slidingtabnav.R;
import com.example.hai.slidingtabnav.Tab1;
import com.example.hai.slidingtabnav.Tab2;

import org.junit.Rule;
import org.junit.Test;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;

/**
 * Created by Hai on 3/10/2017.
 */

public class FragmentTest {
    @Rule
    public FragmentTestRule<Tab2> mFragmentTestRule = new FragmentTestRule<>(Tab2.class);

    @Test
    public void fragment_can_be_instantiated() {

        // Launch the activity to make the fragment visible
        mFragmentTestRule.launchActivity(null);

        // Then use Espresso to test the Fragment
        onView(withId(R.id.button2)).check(matches(isDisplayed()));
    }
}
