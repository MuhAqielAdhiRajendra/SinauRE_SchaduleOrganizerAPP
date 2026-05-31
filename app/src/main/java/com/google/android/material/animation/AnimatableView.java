package com.google.android.material.animation;

/* JADX INFO: loaded from: classes13.dex */
public interface AnimatableView {

    public interface Listener {
        void onAnimationEnd();
    }

    void startAnimation(Listener listener);

    void stopAnimation();
}
