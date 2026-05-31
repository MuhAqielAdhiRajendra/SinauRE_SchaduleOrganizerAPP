package androidx.compose.foundation.text;

import android.os.Build;
import android.os.Trace;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.CompositionLocalKt;
import androidx.compose.runtime.ProvidableCompositionLocal;
import androidx.compose.runtime.snapshots.MutableSnapshot;
import androidx.compose.runtime.snapshots.Snapshot;
import androidx.compose.ui.platform.CompositionLocalsKt;
import androidx.compose.ui.text.AnnotatedString;
import androidx.compose.ui.text.MultiParagraphIntrinsics;
import androidx.compose.ui.text.ParagraphIntrinsics;
import androidx.compose.ui.text.ParagraphIntrinsicsKt;
import androidx.compose.ui.text.Placeholder;
import androidx.compose.ui.text.TextStyle;
import androidx.compose.ui.text.TextStyleKt;
import androidx.compose.ui.text.font.FontFamily;
import androidx.compose.ui.unit.Density;
import androidx.compose.ui.unit.LayoutDirection;
import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.RejectedExecutionException;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: BasicText.android.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000L\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\t\u001a%\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\fH\u0001¢\u0006\u0002\u0010\r\u001a;\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\u000e2\u0006\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\f2\u0014\u0010\u000f\u001a\u0010\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00120\u0011\u0018\u00010\u0010H\u0001¢\u0006\u0002\u0010\u0013\u001a\u0010\u0010 \u001a\u00020\u00192\u0006\u0010!\u001a\u00020\u0015H\u0000\"\u0019\u0010\u0000\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00020\u0001¢\u0006\b\n\u0000\u001a\u0004\b\u0003\u0010\u0004\"\u000e\u0010\u0014\u001a\u00020\u0015X\u0082T¢\u0006\u0002\n\u0000\"\u000e\u0010\u0016\u001a\u00020\u0015X\u0082T¢\u0006\u0002\n\u0000\"\u000e\u0010\u0017\u001a\u00020\u0015X\u0082T¢\u0006\u0002\n\u0000\"\u0012\u0010\u0018\u001a\u0004\u0018\u00010\u0019X\u0082\u000e¢\u0006\u0004\n\u0002\u0010\u001a\"\u001a\u0010\u001b\u001a\u00020\u00198@X\u0081\u0004¢\u0006\f\u0012\u0004\b\u001c\u0010\u001d\u001a\u0004\b\u001e\u0010\u001f¨\u0006\""}, d2 = {"LocalBackgroundTextMeasurementExecutor", "Landroidx/compose/runtime/ProvidableCompositionLocal;", "Ljava/util/concurrent/Executor;", "getLocalBackgroundTextMeasurementExecutor", "()Landroidx/compose/runtime/ProvidableCompositionLocal;", "BackgroundTextMeasurement", "", "text", "", "style", "Landroidx/compose/ui/text/TextStyle;", "fontFamilyResolver", "Landroidx/compose/ui/text/font/FontFamily$Resolver;", "(Ljava/lang/String;Landroidx/compose/ui/text/TextStyle;Landroidx/compose/ui/text/font/FontFamily$Resolver;Landroidx/compose/runtime/Composer;I)V", "Landroidx/compose/ui/text/AnnotatedString;", "placeholders", "", "Landroidx/compose/ui/text/AnnotatedString$Range;", "Landroidx/compose/ui/text/Placeholder;", "(Landroidx/compose/ui/text/AnnotatedString;Landroidx/compose/ui/text/TextStyle;Landroidx/compose/ui/text/font/FontFamily$Resolver;Ljava/util/List;Landroidx/compose/runtime/Composer;I)V", "PrefetchTextMinimumCoreCount", "", "MinTextLengthThreshold", "MaxTextLengthThreshold", "backingCoreCountSatisfactory", "", "Ljava/lang/Boolean;", "coreCountSatisfactory", "getCoreCountSatisfactory$annotations", "()V", "getCoreCountSatisfactory", "()Z", "shouldPrefetch", "textLength", "foundation"}, k = 2, mv = {2, 1, 0}, xi = 48)
public final class BasicText_androidKt {
    private static final ProvidableCompositionLocal<Executor> LocalBackgroundTextMeasurementExecutor = CompositionLocalKt.staticCompositionLocalOf(new Function0() { // from class: androidx.compose.foundation.text.BasicText_androidKt$$ExternalSyntheticLambda1
        @Override // kotlin.jvm.functions.Function0
        public final Object invoke() {
            return BasicText_androidKt.LocalBackgroundTextMeasurementExecutor$lambda$0();
        }
    });
    private static final int MaxTextLengthThreshold = 1000;
    private static final int MinTextLengthThreshold = 8;
    private static final int PrefetchTextMinimumCoreCount = 4;
    private static Boolean backingCoreCountSatisfactory;

    public static /* synthetic */ void getCoreCountSatisfactory$annotations() {
    }

    static final Executor LocalBackgroundTextMeasurementExecutor$lambda$0() {
        return null;
    }

    public static final ProvidableCompositionLocal<Executor> getLocalBackgroundTextMeasurementExecutor() {
        return LocalBackgroundTextMeasurementExecutor;
    }

    public static final void BackgroundTextMeasurement(final String text, final TextStyle style, final FontFamily.Resolver fontFamilyResolver, Composer $composer, int $changed) {
        ComposerKt.sourceInformationMarkerStart($composer, 1589371739, "C(BackgroundTextMeasurement)N(text,style,fontFamilyResolver)69@3215L7:BasicText.android.kt#423gt5");
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(1589371739, $changed, -1, "androidx.compose.foundation.text.BackgroundTextMeasurement (BasicText.android.kt:68)");
        }
        ProvidableCompositionLocal<Executor> providableCompositionLocal = LocalBackgroundTextMeasurementExecutor;
        ComposerKt.sourceInformationMarkerStart($composer, 2023513938, "CC(<get-current>):CompositionLocal.kt#9igjgp");
        Object objConsume = $composer.consume(providableCompositionLocal);
        ComposerKt.sourceInformationMarkerEnd($composer);
        Executor executor = (Executor) objConsume;
        if (executor == null || !shouldPrefetch(text.length())) {
            $composer.startReplaceGroup(1255914055);
            $composer.endReplaceGroup();
        } else {
            $composer.startReplaceGroup(1254298614);
            ComposerKt.sourceInformation($composer, "71@3333L7,72@3376L7");
            ProvidableCompositionLocal<LayoutDirection> localLayoutDirection = CompositionLocalsKt.getLocalLayoutDirection();
            ComposerKt.sourceInformationMarkerStart($composer, 2023513938, "CC(<get-current>):CompositionLocal.kt#9igjgp");
            Object objConsume2 = $composer.consume(localLayoutDirection);
            ComposerKt.sourceInformationMarkerEnd($composer);
            final LayoutDirection layoutDirection = (LayoutDirection) objConsume2;
            ProvidableCompositionLocal<Density> localDensity = CompositionLocalsKt.getLocalDensity();
            ComposerKt.sourceInformationMarkerStart($composer, 2023513938, "CC(<get-current>):CompositionLocal.kt#9igjgp");
            Object objConsume3 = $composer.consume(localDensity);
            ComposerKt.sourceInformationMarkerEnd($composer);
            final Density density = (Density) objConsume3;
            try {
                executor.execute(new Runnable() { // from class: androidx.compose.foundation.text.BasicText_androidKt$$ExternalSyntheticLambda2
                    @Override // java.lang.Runnable
                    public final void run() {
                        BasicText_androidKt.BackgroundTextMeasurement$lambda$0(style, layoutDirection, text, density, fontFamilyResolver);
                    }
                });
            } catch (RejectedExecutionException e) {
            }
            $composer.endReplaceGroup();
        }
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        ComposerKt.sourceInformationMarkerEnd($composer);
    }

    static final void BackgroundTextMeasurement$lambda$0(TextStyle $style, LayoutDirection $layoutDirection, String $text, Density $density, FontFamily.Resolver $fontFamilyResolver) {
        Trace.beginSection("BackgroundTextMeasurement");
        try {
            Snapshot.Companion this_$iv = Snapshot.INSTANCE;
            MutableSnapshot $this$withMutableSnapshot_u24lambda_u240$iv = Snapshot.Companion.takeMutableSnapshot$default(this_$iv, null, null, 3, null);
            try {
                MutableSnapshot this_$iv$iv = $this$withMutableSnapshot_u24lambda_u240$iv;
                Snapshot previous$iv$iv = this_$iv$iv.makeCurrent();
                try {
                    TextStyle resolvedStyle = TextStyleKt.resolveDefaults($style, $layoutDirection);
                    ParagraphIntrinsics intrinsics = ParagraphIntrinsicsKt.ParagraphIntrinsics$default($text, resolvedStyle, CollectionsKt.emptyList(), $density, $fontFamilyResolver, (List) null, 32, (Object) null);
                    intrinsics.getMaxIntrinsicWidth();
                    intrinsics.getMinIntrinsicWidth();
                    Unit unit = Unit.INSTANCE;
                    $this$withMutableSnapshot_u24lambda_u240$iv.apply().check();
                    $this$withMutableSnapshot_u24lambda_u240$iv.dispose();
                    Unit unit2 = Unit.INSTANCE;
                } finally {
                    this_$iv$iv.restoreCurrent(previous$iv$iv);
                }
            } finally {
            }
        } finally {
            Trace.endSection();
        }
    }

    public static final void BackgroundTextMeasurement(final AnnotatedString text, final TextStyle style, final FontFamily.Resolver fontFamilyResolver, final List<AnnotatedString.Range<Placeholder>> list, Composer $composer, int $changed) {
        ComposerKt.sourceInformationMarkerStart($composer, -650368117, "C(BackgroundTextMeasurement)N(text,style,fontFamilyResolver,placeholders)113@5267L7:BasicText.android.kt#423gt5");
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(-650368117, $changed, -1, "androidx.compose.foundation.text.BackgroundTextMeasurement (BasicText.android.kt:112)");
        }
        ProvidableCompositionLocal<Executor> providableCompositionLocal = LocalBackgroundTextMeasurementExecutor;
        ComposerKt.sourceInformationMarkerStart($composer, 2023513938, "CC(<get-current>):CompositionLocal.kt#9igjgp");
        Object objConsume = $composer.consume(providableCompositionLocal);
        ComposerKt.sourceInformationMarkerEnd($composer);
        Executor executor = (Executor) objConsume;
        if (executor == null || !shouldPrefetch(text.length())) {
            $composer.startReplaceGroup(-517090505);
            $composer.endReplaceGroup();
        } else {
            $composer.startReplaceGroup(-518737659);
            ComposerKt.sourceInformation($composer, "115@5385L7,116@5428L7");
            ProvidableCompositionLocal<LayoutDirection> localLayoutDirection = CompositionLocalsKt.getLocalLayoutDirection();
            ComposerKt.sourceInformationMarkerStart($composer, 2023513938, "CC(<get-current>):CompositionLocal.kt#9igjgp");
            Object objConsume2 = $composer.consume(localLayoutDirection);
            ComposerKt.sourceInformationMarkerEnd($composer);
            final LayoutDirection layoutDirection = (LayoutDirection) objConsume2;
            ProvidableCompositionLocal<Density> localDensity = CompositionLocalsKt.getLocalDensity();
            ComposerKt.sourceInformationMarkerStart($composer, 2023513938, "CC(<get-current>):CompositionLocal.kt#9igjgp");
            Object objConsume3 = $composer.consume(localDensity);
            ComposerKt.sourceInformationMarkerEnd($composer);
            final Density density = (Density) objConsume3;
            try {
                executor.execute(new Runnable() { // from class: androidx.compose.foundation.text.BasicText_androidKt$$ExternalSyntheticLambda0
                    @Override // java.lang.Runnable
                    public final void run() {
                        BasicText_androidKt.BackgroundTextMeasurement$lambda$1(style, layoutDirection, list, text, density, fontFamilyResolver);
                    }
                });
            } catch (RejectedExecutionException e) {
            }
            $composer.endReplaceGroup();
        }
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        ComposerKt.sourceInformationMarkerEnd($composer);
    }

    static final void BackgroundTextMeasurement$lambda$1(TextStyle $style, LayoutDirection $layoutDirection, List $placeholders, AnnotatedString $text, Density $density, FontFamily.Resolver $fontFamilyResolver) {
        Trace.beginSection("BackgroundTextMeasurement");
        try {
            Snapshot.Companion this_$iv = Snapshot.INSTANCE;
            MutableSnapshot $this$withMutableSnapshot_u24lambda_u240$iv = Snapshot.Companion.takeMutableSnapshot$default(this_$iv, null, null, 3, null);
            try {
                MutableSnapshot this_$iv$iv = $this$withMutableSnapshot_u24lambda_u240$iv;
                Snapshot previous$iv$iv = this_$iv$iv.makeCurrent();
                try {
                    TextStyle resolvedStyle = TextStyleKt.resolveDefaults($style, $layoutDirection);
                    MultiParagraphIntrinsics intrinsics = new MultiParagraphIntrinsics($text, resolvedStyle, (List<AnnotatedString.Range<Placeholder>>) ($placeholders == null ? CollectionsKt.emptyList() : $placeholders), $density, $fontFamilyResolver);
                    intrinsics.getMaxIntrinsicWidth();
                    intrinsics.getMinIntrinsicWidth();
                    Unit unit = Unit.INSTANCE;
                    $this$withMutableSnapshot_u24lambda_u240$iv.apply().check();
                    $this$withMutableSnapshot_u24lambda_u240$iv.dispose();
                    Unit unit2 = Unit.INSTANCE;
                } finally {
                    this_$iv$iv.restoreCurrent(previous$iv$iv);
                }
            } finally {
            }
        } finally {
            Trace.endSection();
        }
    }

    public static final boolean getCoreCountSatisfactory() {
        if (backingCoreCountSatisfactory == null) {
            backingCoreCountSatisfactory = Boolean.valueOf(Runtime.getRuntime().availableProcessors() >= 4);
        }
        Boolean bool = backingCoreCountSatisfactory;
        Intrinsics.checkNotNull(bool);
        return bool.booleanValue();
    }

    public static final boolean shouldPrefetch(int textLength) {
        return Build.VERSION.SDK_INT >= 28 && textLength >= 8 && textLength < 1000 && getCoreCountSatisfactory();
    }
}
