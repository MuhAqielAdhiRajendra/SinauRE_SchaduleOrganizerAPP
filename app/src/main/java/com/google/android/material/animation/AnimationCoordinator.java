package com.google.android.material.animation;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import androidx.dynamicanimation.animation.DynamicAnimation;
import androidx.dynamicanimation.animation.SpringAnimation;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: loaded from: classes13.dex */
public class AnimationCoordinator {
    private final List<Animator> durationAnimations = new ArrayList();
    private final List<DynamicAnimation<?>> dynamicAnimations = new ArrayList();
    private final List<Listener> listeners = new ArrayList();
    private int animationsRunning = 0;
    private boolean started = false;

    public interface Listener {
        void onAnimationsEnd();

        void onAnimationsStart();
    }

    public void addAnimator(Animator animator) {
        this.durationAnimations.add(animator);
    }

    public void addDynamicAnimation(DynamicAnimation<?> dynamicAnimation) {
        this.dynamicAnimations.add(dynamicAnimation);
    }

    public void addListener(Listener listener) {
        this.listeners.add(listener);
    }

    public void removeListener(Listener listener) {
        this.listeners.remove(listener);
    }

    public void clear() {
        List<Animator> animatorsToEnd = new ArrayList<>(this.durationAnimations);
        this.durationAnimations.clear();
        for (Animator animator : animatorsToEnd) {
            animator.end();
        }
        List<DynamicAnimation<?>> dynamicAnimsToClear = new ArrayList<>(this.dynamicAnimations);
        this.dynamicAnimations.clear();
        for (DynamicAnimation<?> dynamicAnimation : dynamicAnimsToClear) {
            if (dynamicAnimation instanceof SpringAnimation) {
                SpringAnimation springAnimation = (SpringAnimation) dynamicAnimation;
                if (springAnimation.canSkipToEnd()) {
                    springAnimation.skipToEnd();
                } else {
                    springAnimation.cancel();
                }
            } else {
                dynamicAnimation.cancel();
            }
        }
        this.listeners.clear();
        this.animationsRunning = 0;
        this.started = false;
    }

    public void start() {
        if (this.started) {
            return;
        }
        this.started = true;
        for (Listener listener : this.listeners) {
            listener.onAnimationsStart();
        }
        this.animationsRunning = this.dynamicAnimations.size();
        if (!this.durationAnimations.isEmpty()) {
            this.animationsRunning++;
        }
        if (this.animationsRunning == 0) {
            notifyAnimationsEnd();
            return;
        }
        DynamicAnimation.OnAnimationEndListener dynamicListener = new DynamicAnimation.OnAnimationEndListener() { // from class: com.google.android.material.animation.AnimationCoordinator.1
            @Override // androidx.dynamicanimation.animation.DynamicAnimation.OnAnimationEndListener
            public void onAnimationEnd(DynamicAnimation animation, boolean canceled, float value, float velocity) {
                animation.removeEndListener(this);
                AnimationCoordinator.this.onAnimationFinished();
            }
        };
        for (DynamicAnimation<?> dynamicAnimation : this.dynamicAnimations) {
            dynamicAnimation.addEndListener(dynamicListener);
            dynamicAnimation.start();
        }
        if (!this.durationAnimations.isEmpty()) {
            AnimatorSet animatorSet = new AnimatorSet();
            AnimatorSetCompat.playTogether(animatorSet, new ArrayList(this.durationAnimations));
            animatorSet.addListener(new AnimatorListenerAdapter() { // from class: com.google.android.material.animation.AnimationCoordinator.2
                @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
                public void onAnimationEnd(Animator animation) {
                    AnimationCoordinator.this.onAnimationFinished();
                }
            });
            animatorSet.start();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void onAnimationFinished() {
        this.animationsRunning--;
        if (this.animationsRunning == 0) {
            notifyAnimationsEnd();
        }
    }

    private void notifyAnimationsEnd() {
        for (Listener listener : this.listeners) {
            listener.onAnimationsEnd();
        }
        this.started = false;
    }
}
