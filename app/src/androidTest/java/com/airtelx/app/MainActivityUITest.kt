package com.airtelx.app

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.action.ViewActions.*
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.rules.activityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.MediumTest
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith


@RunWith(AndroidJUnit4ClassRunner::class)
@MediumTest
class MainActivityUITest{


    @get:Rule var activityScenarioRule = activityScenarioRule<MainActivity>()
    val STRING_TO_BE_TYPED: String = "Airtel"

    @Test
    fun changeText_sameActivity() {

        onView(withId(R.id.floating_search_view))
            .perform(typeText(STRING_TO_BE_TYPED), closeSoftKeyboard())


    }

}