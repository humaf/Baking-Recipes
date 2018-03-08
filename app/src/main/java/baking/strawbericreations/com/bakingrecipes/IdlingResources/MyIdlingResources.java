package baking.strawbericreations.com.bakingrecipes.IdlingResources;


import java.util.concurrent.atomic.AtomicBoolean;
import android.support.test.espresso.IdlingPolicies;
import android.support.test.espresso.IdlingResource;

/**
 * Created by redrose on 2/27/18.
 */

public class MyIdlingResources implements IdlingResource {

    private final long startTime;
    private final long waitingTime;


    private  volatile IdlingResource.ResourceCallback mResourceCallback;

    private AtomicBoolean mIsIdleNow = new AtomicBoolean(true);


    public MyIdlingResources(long waitingTime) {
        this.startTime = System.currentTimeMillis();
        this.waitingTime = waitingTime;
    }

    @Override
    public String getName() {
        return this.getClass().getName();
    }

    @Override
    public boolean isIdleNow() {
        long elapsed = System.currentTimeMillis() - startTime;
        boolean idle = (elapsed >= waitingTime);
        if (idle) {
            mResourceCallback.onTransitionToIdle();
        }

        return idle;
    }

    @Override
    public void registerIdleTransitionCallback(IdlingResource.ResourceCallback callback) {
        mResourceCallback = callback;
    }

    public void setIdleState(boolean isIdleNow) {
        mIsIdleNow.set(isIdleNow);
        if (isIdleNow && mResourceCallback != null) {
            mResourceCallback.onTransitionToIdle();
        }
    }

}
