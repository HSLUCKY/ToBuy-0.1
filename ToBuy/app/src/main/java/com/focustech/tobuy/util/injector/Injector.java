package com.focustech.tobuy.util.injector;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;

import android.app.Activity;

import com.focustech.tobuy.util.injector.AnnotationInject.InjectView;

/**
 * Very lightweight form of injection, inspired by RoboGuice, for injecting common ui elements.
 * <p>
 * Usage is very simple. In your Activity, define some fields as follows:
 *
 * <pre class="code">
 * @InjectView(R.id.fetch_button)
 * private Button mFetchButton;
 * @InjectView(R.id.submit_button)
 * private Button mSubmitButton;
 * @InjectView(R.id.main_view)
 * private TextView mTextView;
 * </pre>
 * <p>
 * Then, inside your Activity's onCreate() method, perform the injection like this:
 *
 * <pre class="code">
 * setContentView(R.layout.main_layout);
 * Injector.get(this).inject();
 * </pre>
 * <p>
 * See the {@link #inject()} method for full details of how it works. Note that the fields are
 * fetched and assigned at the time you call {@link #inject()}, consequently you should not do this
 * until after you've called the setContentView() method.
 */
public final class Injector {
    private final Activity mActivity;

    private Injector(Activity activity) {
        mActivity = activity;
    }

    /**
     * Gets an {@link Injector} capable of injecting fields for the given Activity.
     */
    public static Injector get(Activity activity) {
        return new Injector(activity);
    }

    /**
     * Injects all fields that are marked with the {@link InjectView} annotation.
     * <p>
     * For each field marked with the InjectView annotation, a call to
     * {@link Activity#findViewById(int)} will be made, passing in the resource id stored in the
     * value() method of the InjectView annotation as the int parameter, and the result of this call
     * will be assigned to the field.
     *
     * @throws IllegalStateException if injection fails, common causes being that you have used an
     *             invalid id value, or you haven't called setContentView() on your Activity.
     */
    public void inject() {
        for (Field field : mActivity.getClass().getDeclaredFields()) {
            for (Annotation annotation : field.getAnnotations()) {
                if (annotation.annotationType().equals(InjectView.class)) {
                    try {
                        Class<?> fieldType = field.getType();
                        int idValue = InjectView.class.cast(annotation).value();
                        field.setAccessible(true);
                        Object injectedValue = fieldType.cast(mActivity.findViewById(idValue));
                        if (injectedValue == null) {
                            throw new IllegalStateException("findViewById(" + idValue
                                    + ") gave null for " +
                                    field + ", can't inject");
                        }
                        field.set(mActivity, injectedValue);
                        field.setAccessible(false);
                    } catch (IllegalAccessException e) {
                        throw new IllegalStateException(e);
                    }
                }
            }
        }
    }
}