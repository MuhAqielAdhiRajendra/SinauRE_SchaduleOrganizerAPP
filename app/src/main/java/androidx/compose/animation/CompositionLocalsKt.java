package androidx.compose.animation;

import androidx.compose.runtime.CompositionLocalKt;
import androidx.compose.runtime.ProvidableCompositionLocal;
import androidx.compose.ui.graphics.Color;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.Metadata;
import kotlin.jvm.functions.Function0;

/* JADX INFO: compiled from: CompositionLocals.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0016\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0004\"'\u0010\u0000\u001a\b\u0012\u0004\u0012\u00020\u00020\u00018@X\u0080\u0084\u0002¢\u0006\u0012\n\u0004\b\u0007\u0010\b\u0012\u0004\b\u0003\u0010\u0004\u001a\u0004\b\u0005\u0010\u0006\"'\u0010\t\u001a\b\u0012\u0004\u0012\u00020\n0\u00018@X\u0080\u0084\u0002¢\u0006\u0012\n\u0004\b\r\u0010\b\u0012\u0004\b\u000b\u0010\u0004\u001a\u0004\b\f\u0010\u0006¨\u0006\u000e"}, d2 = {"LocalLookaheadAnimationVisualDebugConfig", "Landroidx/compose/runtime/ProvidableCompositionLocal;", "Landroidx/compose/animation/LookaheadAnimationVisualDebugConfig;", "getLocalLookaheadAnimationVisualDebugConfig$annotations", "()V", "getLocalLookaheadAnimationVisualDebugConfig", "()Landroidx/compose/runtime/ProvidableCompositionLocal;", "LocalLookaheadAnimationVisualDebugConfig$delegate", "Lkotlin/Lazy;", "LocalLookaheadAnimationVisualDebugColor", "Landroidx/compose/ui/graphics/Color;", "getLocalLookaheadAnimationVisualDebugColor$annotations", "getLocalLookaheadAnimationVisualDebugColor", "LocalLookaheadAnimationVisualDebugColor$delegate", "animation"}, k = 2, mv = {2, 1, 0}, xi = 48)
public final class CompositionLocalsKt {
    private static final Lazy LocalLookaheadAnimationVisualDebugConfig$delegate = LazyKt.lazy(new Function0<ProvidableCompositionLocal<LookaheadAnimationVisualDebugConfig>>() { // from class: androidx.compose.animation.CompositionLocalsKt$LocalLookaheadAnimationVisualDebugConfig$2
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // kotlin.jvm.functions.Function0
        public final ProvidableCompositionLocal<LookaheadAnimationVisualDebugConfig> invoke() {
            return CompositionLocalKt.staticCompositionLocalOf(new Function0<LookaheadAnimationVisualDebugConfig>() { // from class: androidx.compose.animation.CompositionLocalsKt$LocalLookaheadAnimationVisualDebugConfig$2.1
                /* JADX WARN: Can't rename method to resolve collision */
                @Override // kotlin.jvm.functions.Function0
                public final LookaheadAnimationVisualDebugConfig invoke() {
                    return new LookaheadAnimationVisualDebugConfig(false, 0L, 0L, 0L, false, 30, null);
                }
            });
        }
    });
    private static final Lazy LocalLookaheadAnimationVisualDebugColor$delegate = LazyKt.lazy(new Function0<ProvidableCompositionLocal<Color>>() { // from class: androidx.compose.animation.CompositionLocalsKt$LocalLookaheadAnimationVisualDebugColor$2
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // kotlin.jvm.functions.Function0
        public final ProvidableCompositionLocal<Color> invoke() {
            return CompositionLocalKt.staticCompositionLocalOf(new Function0<Color>() { // from class: androidx.compose.animation.CompositionLocalsKt$LocalLookaheadAnimationVisualDebugColor$2.1
                @Override // kotlin.jvm.functions.Function0
                public /* bridge */ /* synthetic */ Color invoke() {
                    return Color.m5303boximpl(m84invoke0d7_KjU());
                }

                /* JADX INFO: renamed from: invoke-0d7_KjU, reason: not valid java name */
                public final long m84invoke0d7_KjU() {
                    return Color.INSTANCE.m5349getUnspecified0d7_KjU();
                }
            });
        }
    });

    public static /* synthetic */ void getLocalLookaheadAnimationVisualDebugColor$annotations() {
    }

    public static /* synthetic */ void getLocalLookaheadAnimationVisualDebugConfig$annotations() {
    }

    public static final ProvidableCompositionLocal<LookaheadAnimationVisualDebugConfig> getLocalLookaheadAnimationVisualDebugConfig() {
        return (ProvidableCompositionLocal) LocalLookaheadAnimationVisualDebugConfig$delegate.getValue();
    }

    public static final ProvidableCompositionLocal<Color> getLocalLookaheadAnimationVisualDebugColor() {
        return (ProvidableCompositionLocal) LocalLookaheadAnimationVisualDebugColor$delegate.getValue();
    }
}
