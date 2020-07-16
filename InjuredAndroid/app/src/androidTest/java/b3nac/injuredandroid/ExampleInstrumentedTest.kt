package b3nac.injuredandroid

import androidx.test.runner.AndroidJUnit4
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import android.content.Intent
import android.net.Uri
import androidx.test.espresso.intent.Intents
import androidx.test.espresso.intent.Intents.intended
import androidx.test.espresso.intent.matcher.IntentMatchers.hasComponent
import androidx.test.rule.ActivityTestRule

@RunWith(AndroidJUnit4::class)
class DeepLinkingTest {

    @Rule
    @JvmField
    val activityTestRule = ActivityTestRule(MainActivity::class.java)

    @Before
    fun setUp() {
        Intents.init()
    }

    @After
    fun tearDown() {
        Intents.release()
    }

    @Test
    fun should_launch_secondActivity_when_deepLinkingToActivityTwo() {
        val intent = Intent(Intent.ACTION_VIEW, Uri.parse("flag11://"))
        activityTestRule.launchActivity(intent)
        intended(hasComponent(DeepLinkActivity::class.java!!.getName()))
    }

    @Test
    fun should_launch_RCEActivity() {
        val intent = Intent(Intent.ACTION_VIEW, Uri.parse("flag13://rce"))
        activityTestRule.launchActivity(intent)
        intended(hasComponent(RCEActivity::class.java!!.getName()))
    }

}