package com.dngwjy.finalproject

import android.support.test.espresso.Espresso.onView
import com.dngwjy.finalproject.activities.MainActivity
import org.junit.Rule
import android.support.test.espresso.action.ViewActions.click
import android.support.test.espresso.assertion.ViewAssertions.matches
import android.support.test.espresso.matcher.ViewMatchers.isDisplayed
import android.support.test.espresso.matcher.ViewMatchers.withId
import android.support.test.rule.ActivityTestRule

import android.support.test.runner.AndroidJUnit4
import android.support.v7.widget.RecyclerView
import com.dngwjy.finalproject.R.id.*
import org.jetbrains.anko.Android
import org.junit.Test
import org.junit.runner.RunWith

class IntrumentTest {
//	@Rule
//	@JvmField var actRule = ActivityTestRule(MainActivity::class.java)
	@Rule
	@JvmField var actRule = ActivityTestRule(MainActivity::class.java)

	@Test
	fun TestAppBehavior(){
		onView(withId(navigate)).check(matches(isDisplayed()))
		onView(withId(navigate)).perform(click())
		onView(withId(spinnerLiga)).check(matches(isDisplayed()))
		onView(withId(recTeam)).check(matches(isDisplayed()))
		onView(withId(search)).check(matches(isDisplayed()))
		onView(withId(search)).perform(click())
		onView(withId(searchEdit)).check(matches(isDisplayed()))
		onView(withId(recSearch)).check(matches(isDisplayed()))
		onView(withId(recSearch)).perform(click())
	}
}