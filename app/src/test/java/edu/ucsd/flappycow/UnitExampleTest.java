package edu.ucsd.flappycow;

import androidx.test.core.app.ActivityScenario;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;

import androidx.lifecycle.Lifecycle;

import static org.hamcrest.MatcherAssert.assertThat;

@RunWith(RobolectricTestRunner.class)
public class UnitExampleTest {
    @Test
    public void testActivityResumes() {
        // GIVEN
        ActivityScenario<MainActivity> scenario = ActivityScenario.launch(MainActivity.class);

        // WHEN
        scenario.moveToState(Lifecycle.State.CREATED);
        scenario.moveToState(Lifecycle.State.RESUMED);

        // THEN
        scenario.onActivity(activity -> {
            assertThat("The main activity is not null." , activity != null);
        });
    }
}
