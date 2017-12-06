package douglas.com.br.testemarvel;

import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import douglas.com.br.testemarvel.ui.main.MainActivity;

import static android.app.PendingIntent.getActivity;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.pressImeActionButton;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.assertion.ViewAssertions.doesNotExist;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.RootMatchers.withDecorView;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.CoreMatchers.is;
import static org.mockito.AdditionalMatchers.not;

/**
 * Created by douglaspanacho on 05/12/2017.
 */

@RunWith(AndroidJUnit4.class)
public class MainActivityEspressoTest {

    @Rule
    public ActivityTestRule<MainActivity> mActivityRule =
            new ActivityTestRule<>(MainActivity.class);

    @Test
    public void ensureSearchWorks() {
        // checks that the search works
        onView(withId(R.id.search)).perform(click());
        onView(withId(android.support.design.R.id.search_src_text)).perform(typeText("iron man"),pressImeActionButton());
        onView(withId(R.id.item_hero_name)).check(matches(withText("Iron Man")));
    }

    @Test
    public void ensureSearcNotFound() {
        onView(withId(R.id.search)).perform(click());
        onView(withId(android.support.design.R.id.search_src_text)).perform(typeText("maasdasd"),pressImeActionButton());
        onView(withText(R.string.placeholder_no_results_label)).inRoot(withDecorView(is(mActivityRule.getActivity().getWindow().getDecorView()))).check(matches(isDisplayed()));
    }
}
