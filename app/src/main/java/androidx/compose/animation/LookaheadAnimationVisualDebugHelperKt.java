package androidx.compose.animation;

import androidx.collection.MutableScatterMap;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.CompositionLocalKt;
import androidx.compose.runtime.ProvidedValue;
import androidx.compose.runtime.RecomposeScopeImplKt;
import androidx.compose.runtime.ScopeUpdateScope;
import androidx.compose.ui.graphics.Color;
import androidx.compose.ui.graphics.ColorKt;
import androidx.profileinstaller.ProfileVerifier;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function2;

/* JADX INFO: compiled from: LookaheadAnimationVisualDebugHelper.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u00000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0007\u001aT\u0010\u0006\u001a\u00020\u00072\b\b\u0002\u0010\b\u001a\u00020\t2\b\b\u0002\u0010\n\u001a\u00020\u00052\b\b\u0002\u0010\u000b\u001a\u00020\u00052\b\b\u0002\u0010\f\u001a\u00020\u00052\b\b\u0002\u0010\r\u001a\u00020\t2\u0011\u0010\u000e\u001a\r\u0012\u0004\u0012\u00020\u00070\u000f¢\u0006\u0002\b\u0010H\u0007¢\u0006\u0004\b\u0011\u0010\u0012\u001a*\u0010\u0013\u001a\u00020\u00072\u0006\u0010\u0014\u001a\u00020\u00052\u0011\u0010\u000e\u001a\r\u0012\u0004\u0012\u00020\u00070\u000f¢\u0006\u0002\b\u0010H\u0007¢\u0006\u0004\b\u0015\u0010\u0016\"\u000e\u0010\u0000\u001a\u00020\u0001X\u0082\u000e¢\u0006\u0002\n\u0000\"\u001a\u0010\u0002\u001a\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00050\u0003X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0017"}, d2 = {"colorIndex", "", "keyToColor", "Landroidx/collection/MutableScatterMap;", "", "Landroidx/compose/ui/graphics/Color;", "LookaheadAnimationVisualDebugging", "", "isEnabled", "", "overlayColor", "multipleMatchesColor", "unmatchedElementColor", "isShowKeyLabelEnabled", "content", "Lkotlin/Function0;", "Landroidx/compose/runtime/Composable;", "LookaheadAnimationVisualDebugging-gUzqikQ", "(ZJJJZLkotlin/jvm/functions/Function2;Landroidx/compose/runtime/Composer;II)V", "CustomizedLookaheadAnimationVisualDebugging", "debugColor", "CustomizedLookaheadAnimationVisualDebugging-Iv8Zu3U", "(JLkotlin/jvm/functions/Function2;Landroidx/compose/runtime/Composer;I)V", "animation"}, k = 2, mv = {2, 1, 0}, xi = 48)
public final class LookaheadAnimationVisualDebugHelperKt {
    private static int colorIndex;
    private static final MutableScatterMap<Object, Color> keyToColor = new MutableScatterMap<>(0, 1, null);

    /* JADX INFO: renamed from: LookaheadAnimationVisualDebugging-gUzqikQ, reason: not valid java name */
    public static final void m134LookaheadAnimationVisualDebugginggUzqikQ(boolean isEnabled, long overlayColor, long multipleMatchesColor, long unmatchedElementColor, boolean isShowKeyLabelEnabled, final Function2<? super Composer, ? super Integer, Unit> function2, Composer $composer, final int $changed, final int i) {
        boolean z;
        long j;
        long j2;
        int $dirty;
        boolean z2;
        final boolean isEnabled2;
        final long overlayColor2;
        final boolean isShowKeyLabelEnabled2;
        final long multipleMatchesColor2;
        final long unmatchedElementColor2;
        boolean isEnabled3;
        long overlayColor3;
        long multipleMatchesColor3;
        long unmatchedElementColor3;
        boolean isShowKeyLabelEnabled3;
        Composer $composer2 = $composer.startRestartGroup(1722790302);
        ComposerKt.sourceInformation($composer2, "C(LookaheadAnimationVisualDebugging)N(isEnabled,overlayColor:c#ui.graphics.Color,multipleMatchesColor:c#ui.graphics.Color,unmatchedElementColor:c#ui.graphics.Color,isShowKeyLabelEnabled,content)559@20834L353:LookaheadAnimationVisualDebugHelper.kt#xbi5r1");
        int $dirty2 = $changed;
        int i2 = i & 1;
        if (i2 != 0) {
            $dirty2 |= 6;
            z = isEnabled;
        } else if (($changed & 6) == 0) {
            z = isEnabled;
            $dirty2 |= $composer2.changed(z) ? 4 : 2;
        } else {
            z = isEnabled;
        }
        int i3 = i & 2;
        if (i3 != 0) {
            $dirty2 |= 48;
            j = overlayColor;
        } else if (($changed & 48) == 0) {
            j = overlayColor;
            $dirty2 |= $composer2.changed(j) ? 32 : 16;
        } else {
            j = overlayColor;
        }
        int i4 = i & 4;
        if (i4 != 0) {
            $dirty2 |= 384;
            j2 = multipleMatchesColor;
        } else if (($changed & 384) == 0) {
            j2 = multipleMatchesColor;
            $dirty2 |= $composer2.changed(j2) ? 256 : 128;
        } else {
            j2 = multipleMatchesColor;
        }
        int i5 = i & 8;
        if (i5 != 0) {
            $dirty = $dirty2 | 3072;
        } else if (($changed & 3072) == 0) {
            $dirty = $dirty2 | ($composer2.changed(unmatchedElementColor) ? 2048 : 1024);
        } else {
            $dirty = $dirty2;
        }
        int i6 = i & 16;
        if (i6 != 0) {
            $dirty |= 24576;
            z2 = isShowKeyLabelEnabled;
        } else if (($changed & 24576) == 0) {
            z2 = isShowKeyLabelEnabled;
            $dirty |= $composer2.changed(z2) ? 16384 : 8192;
        } else {
            z2 = isShowKeyLabelEnabled;
        }
        if (($changed & ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE) == 0) {
            $dirty |= $composer2.changedInstance(function2) ? 131072 : 65536;
        }
        if (!$composer2.shouldExecute(($dirty & 74899) != 74898, $dirty & 1)) {
            $composer2.skipToGroupEnd();
            isEnabled2 = z;
            overlayColor2 = j;
            isShowKeyLabelEnabled2 = z2;
            multipleMatchesColor2 = j2;
            unmatchedElementColor2 = unmatchedElementColor;
        } else {
            if (i2 != 0) {
                isEnabled3 = true;
            } else {
                isEnabled3 = z;
            }
            if (i3 == 0) {
                overlayColor3 = j;
            } else {
                overlayColor3 = ColorKt.Color(2150934611L);
            }
            if (i4 == 0) {
                multipleMatchesColor3 = j2;
            } else {
                multipleMatchesColor3 = ColorKt.Color(4293542709L);
            }
            if (i5 == 0) {
                unmatchedElementColor3 = unmatchedElementColor;
            } else {
                unmatchedElementColor3 = ColorKt.Color(4288323750L);
            }
            if (i6 == 0) {
                isShowKeyLabelEnabled3 = z2;
            } else {
                isShowKeyLabelEnabled3 = false;
            }
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(1722790302, $dirty, -1, "androidx.compose.animation.LookaheadAnimationVisualDebugging (LookaheadAnimationVisualDebugHelper.kt:558)");
            }
            CompositionLocalKt.CompositionLocalProvider(CompositionLocalsKt.getLocalLookaheadAnimationVisualDebugConfig().provides(new LookaheadAnimationVisualDebugConfig(isEnabled3, overlayColor3, multipleMatchesColor3, unmatchedElementColor3, isShowKeyLabelEnabled3, null)), function2, $composer2, ProvidedValue.$stable | (($dirty >> 12) & 112));
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
            isEnabled2 = isEnabled3;
            overlayColor2 = overlayColor3;
            multipleMatchesColor2 = multipleMatchesColor3;
            unmatchedElementColor2 = unmatchedElementColor3;
            isShowKeyLabelEnabled2 = isShowKeyLabelEnabled3;
        }
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup = $composer2.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            scopeUpdateScopeEndRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.animation.LookaheadAnimationVisualDebugHelperKt$LookaheadAnimationVisualDebugging$1
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                /* JADX WARN: Multi-variable type inference failed */
                {
                    super(2);
                }

                @Override // kotlin.jvm.functions.Function2
                public /* bridge */ /* synthetic */ Unit invoke(Composer composer, Integer num) {
                    invoke(composer, num.intValue());
                    return Unit.INSTANCE;
                }

                public final void invoke(Composer composer, int i7) {
                    LookaheadAnimationVisualDebugHelperKt.m134LookaheadAnimationVisualDebugginggUzqikQ(isEnabled2, overlayColor2, multipleMatchesColor2, unmatchedElementColor2, isShowKeyLabelEnabled2, function2, composer, RecomposeScopeImplKt.updateChangedFlags($changed | 1), i);
                }
            });
        }
    }

    /* JADX INFO: renamed from: CustomizedLookaheadAnimationVisualDebugging-Iv8Zu3U, reason: not valid java name */
    public static final void m133CustomizedLookaheadAnimationVisualDebuggingIv8Zu3U(final long debugColor, final Function2<? super Composer, ? super Integer, Unit> function2, Composer $composer, final int $changed) {
        Composer $composer2 = $composer.startRestartGroup(1657127863);
        ComposerKt.sourceInformation($composer2, "C(CustomizedLookaheadAnimationVisualDebugging)N(debugColor:c#ui.graphics.Color,content)592@22107L129:LookaheadAnimationVisualDebugHelper.kt#xbi5r1");
        int $dirty = $changed;
        if (($changed & 6) == 0) {
            $dirty |= $composer2.changed(debugColor) ? 4 : 2;
        }
        if (($changed & 48) == 0) {
            $dirty |= $composer2.changedInstance(function2) ? 32 : 16;
        }
        if (!$composer2.shouldExecute(($dirty & 19) != 18, $dirty & 1)) {
            $composer2.skipToGroupEnd();
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(1657127863, $dirty, -1, "androidx.compose.animation.CustomizedLookaheadAnimationVisualDebugging (LookaheadAnimationVisualDebugHelper.kt:591)");
            }
            CompositionLocalKt.CompositionLocalProvider(CompositionLocalsKt.getLocalLookaheadAnimationVisualDebugColor().provides(Color.m5303boximpl(debugColor)), function2, $composer2, ProvidedValue.$stable | ($dirty & 112));
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup = $composer2.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            scopeUpdateScopeEndRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.animation.LookaheadAnimationVisualDebugHelperKt$CustomizedLookaheadAnimationVisualDebugging$1
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                /* JADX WARN: Multi-variable type inference failed */
                {
                    super(2);
                }

                @Override // kotlin.jvm.functions.Function2
                public /* bridge */ /* synthetic */ Unit invoke(Composer composer, Integer num) {
                    invoke(composer, num.intValue());
                    return Unit.INSTANCE;
                }

                public final void invoke(Composer composer, int i) {
                    LookaheadAnimationVisualDebugHelperKt.m133CustomizedLookaheadAnimationVisualDebuggingIv8Zu3U(debugColor, function2, composer, RecomposeScopeImplKt.updateChangedFlags($changed | 1));
                }
            });
        }
    }
}
