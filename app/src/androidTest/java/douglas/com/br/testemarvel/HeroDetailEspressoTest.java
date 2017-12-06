package douglas.com.br.testemarvel;

import android.app.Activity;
import android.app.Instrumentation;
import android.content.Intent;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.io.Serializable;

import douglas.com.br.testemarvel.data.local.Hero;
import douglas.com.br.testemarvel.data.remote.models.response.CharactersResponse;
import douglas.com.br.testemarvel.ui.hero_detail.HeroDetailActivity;
import douglas.com.br.testemarvel.ui.main.MainActivity;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.pressImeActionButton;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.assertion.ViewAssertions.doesNotExist;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.intent.Intents.intending;
import static android.support.test.espresso.intent.matcher.IntentMatchers.hasData;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.CoreMatchers.allOf;
import static org.mockito.AdditionalMatchers.not;

/**
 * Created by douglaspanacho on 06/12/2017.
 */

@RunWith(AndroidJUnit4.class)
public class HeroDetailEspressoTest {

    @Rule
    public ActivityTestRule<HeroDetailActivity> mActivityRule =
            new ActivityTestRule<>(HeroDetailActivity.class);

    @Before
    public void stubContactIntent() {
        CharactersResponse.Result mHeroItem;
        mHeroItem = new CharactersResponse.Result(1011334, "3-D Man", "teste", new CharactersResponse.Thumbnail("", ""));
        Intent intent = new Intent();
        intent.putExtra(Constants.HERO_EXTRA, mHeroItem);
        mActivityRule.launchActivity(intent);
    }

    @Test
    public void isDescriptionEnabled() {
        onView(withId(R.id.hero_detail_description_tv)).check(matches(withText("teste")));
    }

}
