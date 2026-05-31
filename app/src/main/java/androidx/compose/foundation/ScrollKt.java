package androidx.compose.foundation;

import androidx.compose.foundation.gestures.FlingBehavior;
import androidx.compose.foundation.gestures.Orientation;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.saveable.RememberSaveableKt;
import androidx.compose.runtime.saveable.Saver;
import androidx.compose.ui.Modifier;
import kotlin.Metadata;
import kotlin.jvm.functions.Function0;

/* JADX INFO: compiled from: Scroll.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000.\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\u001a\u0017\u0010\u0000\u001a\u00020\u00012\b\b\u0002\u0010\u0002\u001a\u00020\u0003H\u0007¢\u0006\u0002\u0010\u0004\u001a2\u0010\u0005\u001a\u00020\u0006*\u00020\u00062\u0006\u0010\u0007\u001a\u00020\u00012\b\b\u0002\u0010\b\u001a\u00020\t2\n\b\u0002\u0010\n\u001a\u0004\u0018\u00010\u000b2\b\b\u0002\u0010\f\u001a\u00020\t\u001a<\u0010\u0005\u001a\u00020\u0006*\u00020\u00062\u0006\u0010\u0007\u001a\u00020\u00012\b\u0010\r\u001a\u0004\u0018\u00010\u000e2\b\b\u0002\u0010\b\u001a\u00020\t2\n\b\u0002\u0010\n\u001a\u0004\u0018\u00010\u000b2\b\b\u0002\u0010\f\u001a\u00020\t\u001a2\u0010\u000f\u001a\u00020\u0006*\u00020\u00062\u0006\u0010\u0007\u001a\u00020\u00012\b\b\u0002\u0010\b\u001a\u00020\t2\n\b\u0002\u0010\n\u001a\u0004\u0018\u00010\u000b2\b\b\u0002\u0010\f\u001a\u00020\t\u001a<\u0010\u000f\u001a\u00020\u0006*\u00020\u00062\u0006\u0010\u0007\u001a\u00020\u00012\b\u0010\r\u001a\u0004\u0018\u00010\u000e2\b\b\u0002\u0010\b\u001a\u00020\t2\n\b\u0002\u0010\n\u001a\u0004\u0018\u00010\u000b2\b\b\u0002\u0010\f\u001a\u00020\t\u001aJ\u0010\u0010\u001a\u00020\u0006*\u00020\u00062\u0006\u0010\u0007\u001a\u00020\u00012\u0006\u0010\f\u001a\u00020\t2\b\u0010\n\u001a\u0004\u0018\u00010\u000b2\u0006\u0010\u0011\u001a\u00020\t2\u0006\u0010\u0012\u001a\u00020\t2\u0006\u0010\u0013\u001a\u00020\t2\n\b\u0002\u0010\r\u001a\u0004\u0018\u00010\u000eH\u0002¨\u0006\u0014"}, d2 = {"rememberScrollState", "Landroidx/compose/foundation/ScrollState;", "initial", "", "(ILandroidx/compose/runtime/Composer;II)Landroidx/compose/foundation/ScrollState;", "verticalScroll", "Landroidx/compose/ui/Modifier;", "state", "enabled", "", "flingBehavior", "Landroidx/compose/foundation/gestures/FlingBehavior;", "reverseScrolling", "overscrollEffect", "Landroidx/compose/foundation/OverscrollEffect;", "horizontalScroll", "scroll", "isScrollable", "isVertical", "useLocalOverscrollFactory", "foundation"}, k = 2, mv = {2, 1, 0}, xi = 48)
public final class ScrollKt {
    public static final ScrollState rememberScrollState(final int initial, Composer $composer, int $changed, int i) {
        ComposerKt.sourceInformationMarkerStart($composer, -1464256199, "C(rememberScrollState)N(initial)71@3346L34,71@3302L78:Scroll.kt#71ulvw");
        boolean invalid$iv = true;
        if ((i & 1) != 0) {
            initial = 0;
        }
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(-1464256199, $changed, -1, "androidx.compose.foundation.rememberScrollState (Scroll.kt:70)");
        }
        Object[] objArr = new Object[0];
        Saver<ScrollState, ?> saver = ScrollState.INSTANCE.getSaver();
        ComposerKt.sourceInformationMarkerStart($composer, 1599069467, "CC(remember):Scroll.kt#9igjgp");
        if (((($changed & 14) ^ 6) <= 4 || !$composer.changed(initial)) && ($changed & 6) != 4) {
            invalid$iv = false;
        }
        Object it$iv = $composer.rememberedValue();
        if (invalid$iv || it$iv == Composer.INSTANCE.getEmpty()) {
            Object value$iv = new Function0() { // from class: androidx.compose.foundation.ScrollKt$$ExternalSyntheticLambda0
                @Override // kotlin.jvm.functions.Function0
                public final Object invoke() {
                    return ScrollKt.rememberScrollState$lambda$0$0(initial);
                }
            };
            $composer.updateRememberedValue(value$iv);
            it$iv = value$iv;
        }
        ComposerKt.sourceInformationMarkerEnd($composer);
        ScrollState scrollState = (ScrollState) RememberSaveableKt.m4704rememberSaveable(objArr, (Saver) saver, (Function0) it$iv, $composer, 0);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        ComposerKt.sourceInformationMarkerEnd($composer);
        return scrollState;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final ScrollState rememberScrollState$lambda$0$0(int $initial) {
        return new ScrollState($initial);
    }

    public static /* synthetic */ Modifier verticalScroll$default(Modifier modifier, ScrollState scrollState, boolean z, FlingBehavior flingBehavior, boolean z2, int i, Object obj) {
        if ((i & 2) != 0) {
            z = true;
        }
        if ((i & 4) != 0) {
            flingBehavior = null;
        }
        if ((i & 8) != 0) {
            z2 = false;
        }
        return verticalScroll(modifier, scrollState, z, flingBehavior, z2);
    }

    public static final Modifier verticalScroll(Modifier $this$verticalScroll, ScrollState state, boolean enabled, FlingBehavior flingBehavior, boolean reverseScrolling) {
        return scroll$default($this$verticalScroll, state, reverseScrolling, flingBehavior, enabled, true, true, null, 64, null);
    }

    public static final Modifier verticalScroll(Modifier $this$verticalScroll, ScrollState state, OverscrollEffect overscrollEffect, boolean enabled, FlingBehavior flingBehavior, boolean reverseScrolling) {
        return scroll($this$verticalScroll, state, reverseScrolling, flingBehavior, enabled, true, false, overscrollEffect);
    }

    public static /* synthetic */ Modifier horizontalScroll$default(Modifier modifier, ScrollState scrollState, boolean z, FlingBehavior flingBehavior, boolean z2, int i, Object obj) {
        if ((i & 2) != 0) {
            z = true;
        }
        if ((i & 4) != 0) {
            flingBehavior = null;
        }
        if ((i & 8) != 0) {
            z2 = false;
        }
        return horizontalScroll(modifier, scrollState, z, flingBehavior, z2);
    }

    public static final Modifier horizontalScroll(Modifier $this$horizontalScroll, ScrollState state, boolean enabled, FlingBehavior flingBehavior, boolean reverseScrolling) {
        return scroll$default($this$horizontalScroll, state, reverseScrolling, flingBehavior, enabled, false, true, null, 64, null);
    }

    public static final Modifier horizontalScroll(Modifier $this$horizontalScroll, ScrollState state, OverscrollEffect overscrollEffect, boolean enabled, FlingBehavior flingBehavior, boolean reverseScrolling) {
        return scroll($this$horizontalScroll, state, reverseScrolling, flingBehavior, enabled, false, false, overscrollEffect);
    }

    static /* synthetic */ Modifier scroll$default(Modifier modifier, ScrollState scrollState, boolean z, FlingBehavior flingBehavior, boolean z2, boolean z3, boolean z4, OverscrollEffect overscrollEffect, int i, Object obj) {
        OverscrollEffect overscrollEffect2;
        if ((i & 64) == 0) {
            overscrollEffect2 = overscrollEffect;
        } else {
            overscrollEffect2 = null;
        }
        return scroll(modifier, scrollState, z, flingBehavior, z2, z3, z4, overscrollEffect2);
    }

    private static final Modifier scroll(Modifier $this$scroll, ScrollState state, boolean reverseScrolling, FlingBehavior flingBehavior, boolean isScrollable, boolean isVertical, boolean useLocalOverscrollFactory, OverscrollEffect overscrollEffect) {
        Modifier scrollableArea;
        Orientation orientation = isVertical ? Orientation.Vertical : Orientation.Horizontal;
        if (useLocalOverscrollFactory) {
            scrollableArea = ScrollableAreaKt.scrollableArea($this$scroll, state, orientation, (64 & 4) != 0 ? true : isScrollable, (64 & 8) != 0 ? false : reverseScrolling, (64 & 16) != 0 ? null : flingBehavior, (64 & 32) != 0 ? null : state.getInternalInteractionSource(), (64 & 64) != 0 ? null : null);
        } else {
            scrollableArea = ScrollableAreaKt.scrollableArea($this$scroll, state, orientation, overscrollEffect, (128 & 8) != 0 ? true : isScrollable, (128 & 16) != 0 ? false : reverseScrolling, (128 & 32) != 0 ? null : flingBehavior, (128 & 64) != 0 ? null : state.getInternalInteractionSource(), (128 & 128) != 0 ? null : null);
        }
        return scrollableArea.then(new ScrollingLayoutElement(state, reverseScrolling, isVertical));
    }
}
