package baking.strawbericreations.com.bakingrecipes.IdlingResources;

import java.util.concurrent.atomic.AtomicBoolean;
import android.support.test.espresso.IdlingResource;



/**
 * Created by redrose on 2/27/18.
 */

public class MyIdlingResources implements IdlingResource {

    private  volatile IdlingResource.ResourceCallback mResourceCallback;

    private AtomicBoolean mIsIdleNow = new AtomicBoolean(true);


    @Override
    public String getName() {
        return this.getClass().getName();
    }

    @Override
    public boolean isIdleNow() {
        return mIsIdleNow.get();
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
