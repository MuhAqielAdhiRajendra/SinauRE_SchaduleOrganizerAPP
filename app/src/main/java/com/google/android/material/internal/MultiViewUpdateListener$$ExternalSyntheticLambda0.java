package com.google.android.material.internal;

import android.animation.ValueAnimator;
import android.view.View;
import com.android.tools.r8.annotations.LambdaMethod;
import com.android.tools.r8.annotations.SynthesizedClassV2;
import com.google.android.material.internal.MultiViewUpdateListener;

/* JADX INFO: compiled from: D8$$SyntheticClass */
/* JADX INFO: loaded from: classes13.dex */
@LambdaMethod(holder = "Lcom/google/android/material/internal/MultiViewUpdateListener;", method = "setTranslationX", proto = "(Landroid/animation/ValueAnimator;Landroid/view/View;)V")
@SynthesizedClassV2(apiLevel = -2, kind = 19, versionHash = "4b55be2c9864cfa0f3e2262a2208567ab6bc862a59e7853c580a1f24fbae9ba1")
public final /* synthetic */ class MultiViewUpdateListener$$ExternalSyntheticLambda0 implements MultiViewUpdateListener.Listener {
    @Override // com.google.android.material.internal.MultiViewUpdateListener.Listener
    public final void onAnimationUpdate(ValueAnimator valueAnimator, View view) {
        MultiViewUpdateListener.setTranslationX(valueAnimator, view);
    }
}
