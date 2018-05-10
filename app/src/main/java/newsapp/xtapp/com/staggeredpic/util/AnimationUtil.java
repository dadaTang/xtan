package newsapp.xtapp.com.staggeredpic.util;

import android.support.annotation.AnimRes;
import android.support.annotation.NonNull;
import android.support.transition.Transition;
import android.view.View;
import android.view.animation.Animation;

public class AnimationUtil {
    public static void animateIn(final View v, @AnimRes int anim) {
        if (v.getVisibility() == View.VISIBLE) {
            return;
        }

        v.clearAnimation();

        Animation animation = android.view.animation.AnimationUtils.loadAnimation(v.getContext(), anim);

        animation.setAnimationListener(new AnimationListenerAdapter() {
            @Override
            public void onAnimationStart(Animation animation) {
                v.setVisibility(View.VISIBLE);
            }
        });

        v.startAnimation(animation);
    }

    public static void animateOut(final View v, @AnimRes int anim) {
        if (v.getVisibility() != View.VISIBLE) {
            return;
        }

        v.clearAnimation();

        Animation animation = android.view.animation.AnimationUtils.loadAnimation(v.getContext(), anim);

        animation.setAnimationListener(new AnimationListenerAdapter() {
            @Override
            public void onAnimationEnd(Animation animation) {
                v.setVisibility(View.INVISIBLE);
            }
        });

        v.startAnimation(animation);
    }

    public static class AnimationListenerAdapter implements Animation.AnimationListener {

        @Override
        public void onAnimationStart(Animation animation) {
        }

        @Override
        public void onAnimationEnd(Animation animation) {
        }

        @Override
        public void onAnimationRepeat(Animation animation) {
        }

    }

    public static class TransitionListenerAdapter implements Transition.TransitionListener {


        @Override
        public void onTransitionStart(@NonNull Transition transition) {

        }

        @Override
        public void onTransitionEnd(@NonNull Transition transition) {

        }

        @Override
        public void onTransitionCancel(@NonNull Transition transition) {

        }

        @Override
        public void onTransitionPause(@NonNull Transition transition) {

        }

        @Override
        public void onTransitionResume(@NonNull Transition transition) {

        }
    }
}
