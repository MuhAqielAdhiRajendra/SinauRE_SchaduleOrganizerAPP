package androidx.compose.material3;

import androidx.appcompat.app.AppCompatDelegate;
import androidx.compose.foundation.BackgroundKt;
import androidx.compose.foundation.layout.Arrangement;
import androidx.compose.foundation.layout.ColumnScope;
import androidx.compose.foundation.layout.RowKt;
import androidx.compose.foundation.layout.RowScope;
import androidx.compose.foundation.layout.RowScopeInstance;
import androidx.compose.foundation.layout.SizeKt;
import androidx.compose.foundation.layout.SpacerKt;
import androidx.compose.material3.tokens.DialogTokens;
import androidx.compose.runtime.Applier;
import androidx.compose.runtime.ComposablesKt;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.CompositionLocalMap;
import androidx.compose.runtime.RecomposeScopeImplKt;
import androidx.compose.runtime.ScopeUpdateScope;
import androidx.compose.runtime.Updater;
import androidx.compose.runtime.composer.linkbuffer.GroupFlagsKt;
import androidx.compose.runtime.internal.ComposableLambdaKt;
import androidx.compose.ui.Alignment;
import androidx.compose.ui.ComposedModifierKt;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.graphics.Shape;
import androidx.compose.ui.layout.MeasurePolicy;
import androidx.compose.ui.node.ComposeUiNode;
import androidx.compose.ui.window.AndroidDialog_androidKt;
import androidx.compose.ui.window.DialogProperties;
import androidx.profileinstaller.ProfileVerifier;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: TimePickerDialog.kt */
/* JADX INFO: loaded from: classes12.dex */
@Metadata(d1 = {"\u0000@\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\t\u001a·\u0001\u0010\u0000\u001a\u00020\u00012\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00010\u00032\u0011\u0010\u0004\u001a\r\u0012\u0004\u0012\u00020\u00010\u0003¢\u0006\u0002\b\u00052\u0011\u0010\u0006\u001a\r\u0012\u0004\u0012\u00020\u00010\u0003¢\u0006\u0002\b\u00052\b\b\u0002\u0010\u0007\u001a\u00020\b2\b\b\u0002\u0010\t\u001a\u00020\n2\u0015\b\u0002\u0010\u000b\u001a\u000f\u0012\u0004\u0012\u00020\u0001\u0018\u00010\u0003¢\u0006\u0002\b\u00052\u0015\b\u0002\u0010\f\u001a\u000f\u0012\u0004\u0012\u00020\u0001\u0018\u00010\u0003¢\u0006\u0002\b\u00052\b\b\u0002\u0010\r\u001a\u00020\u000e2\b\b\u0002\u0010\u000f\u001a\u00020\u00102\u001c\u0010\u0011\u001a\u0018\u0012\u0004\u0012\u00020\u0013\u0012\u0004\u0012\u00020\u00010\u0012¢\u0006\u0002\b\u0005¢\u0006\u0002\b\u0014H\u0007¢\u0006\u0004\b\u0015\u0010\u0016\u001a\u009f\u0001\u0010\u0017\u001a\u00020\u00012\u0011\u0010\u0004\u001a\r\u0012\u0004\u0012\u00020\u00010\u0003¢\u0006\u0002\b\u00052\u0011\u0010\u0006\u001a\r\u0012\u0004\u0012\u00020\u00010\u0003¢\u0006\u0002\b\u00052\b\b\u0002\u0010\u0007\u001a\u00020\b2\u0015\b\u0002\u0010\u000b\u001a\u000f\u0012\u0004\u0012\u00020\u0001\u0018\u00010\u0003¢\u0006\u0002\b\u00052\u0015\b\u0002\u0010\f\u001a\u000f\u0012\u0004\u0012\u00020\u0001\u0018\u00010\u0003¢\u0006\u0002\b\u00052\b\b\u0002\u0010\r\u001a\u00020\u000e2\b\b\u0002\u0010\u000f\u001a\u00020\u00102\u001c\u0010\u0011\u001a\u0018\u0012\u0004\u0012\u00020\u0013\u0012\u0004\u0012\u00020\u00010\u0012¢\u0006\u0002\b\u0005¢\u0006\u0002\b\u0014H\u0001¢\u0006\u0004\b\u0018\u0010\u0019\u001aQ\u0010\u001a\u001a\u00020\u00012\u0011\u0010\u0006\u001a\r\u0012\u0004\u0012\u00020\u00010\u0003¢\u0006\u0002\b\u00052\u0011\u0010\u001b\u001a\r\u0012\u0004\u0012\u00020\u00010\u0003¢\u0006\u0002\b\u00052\u001c\u0010\u0011\u001a\u0018\u0012\u0004\u0012\u00020\u0013\u0012\u0004\u0012\u00020\u00010\u0012¢\u0006\u0002\b\u0005¢\u0006\u0002\b\u0014H\u0001¢\u0006\u0002\u0010\u001c¨\u0006\u001d"}, d2 = {"TimePickerDialog", "", "onDismissRequest", "Lkotlin/Function0;", "confirmButton", "Landroidx/compose/runtime/Composable;", "title", "modifier", "Landroidx/compose/ui/Modifier;", "properties", "Landroidx/compose/ui/window/DialogProperties;", "modeToggleButton", "dismissButton", "shape", "Landroidx/compose/ui/graphics/Shape;", "containerColor", "Landroidx/compose/ui/graphics/Color;", "content", "Lkotlin/Function1;", "Landroidx/compose/foundation/layout/ColumnScope;", "Lkotlin/ExtensionFunctionType;", "TimePickerDialog-FItCLgY", "(Lkotlin/jvm/functions/Function0;Lkotlin/jvm/functions/Function2;Lkotlin/jvm/functions/Function2;Landroidx/compose/ui/Modifier;Landroidx/compose/ui/window/DialogProperties;Lkotlin/jvm/functions/Function2;Lkotlin/jvm/functions/Function2;Landroidx/compose/ui/graphics/Shape;JLkotlin/jvm/functions/Function3;Landroidx/compose/runtime/Composer;II)V", "TimePickerDialogLayout", "TimePickerDialogLayout-3csKH6Y", "(Lkotlin/jvm/functions/Function2;Lkotlin/jvm/functions/Function2;Landroidx/compose/ui/Modifier;Lkotlin/jvm/functions/Function2;Lkotlin/jvm/functions/Function2;Landroidx/compose/ui/graphics/Shape;JLkotlin/jvm/functions/Function3;Landroidx/compose/runtime/Composer;II)V", "TimePickerCustomLayout", "actions", "(Lkotlin/jvm/functions/Function2;Lkotlin/jvm/functions/Function2;Lkotlin/jvm/functions/Function3;Landroidx/compose/runtime/Composer;I)V", "material3"}, k = 2, mv = {2, 0, 0}, xi = 48)
public final class TimePickerDialogKt {
    static final Unit TimePickerCustomLayout$lambda$3(Function2 function2, Function2 function22, Function3 function3, int i, Composer composer, int i2) {
        TimePickerCustomLayout(function2, function22, function3, composer, RecomposeScopeImplKt.updateChangedFlags(i | 1));
        return Unit.INSTANCE;
    }

    static final Unit TimePickerDialogLayout_3csKH6Y$lambda$1(Function2 function2, Function2 function22, Modifier modifier, Function2 function23, Function2 function24, Shape shape, long j, Function3 function3, int i, int i2, Composer composer, int i3) {
        m3187TimePickerDialogLayout3csKH6Y(function2, function22, modifier, function23, function24, shape, j, function3, composer, RecomposeScopeImplKt.updateChangedFlags(i | 1), i2);
        return Unit.INSTANCE;
    }

    static final Unit TimePickerDialog_FItCLgY$lambda$0(Function0 function0, Function2 function2, Function2 function22, Modifier modifier, DialogProperties dialogProperties, Function2 function23, Function2 function24, Shape shape, long j, Function3 function3, int i, int i2, Composer composer, int i3) {
        m3186TimePickerDialogFItCLgY(function0, function2, function22, modifier, dialogProperties, function23, function24, shape, j, function3, composer, RecomposeScopeImplKt.updateChangedFlags(i | 1), i2);
        return Unit.INSTANCE;
    }

    /* JADX INFO: renamed from: TimePickerDialog-FItCLgY, reason: not valid java name */
    public static final void m3186TimePickerDialogFItCLgY(final Function0<Unit> function0, final Function2<? super Composer, ? super Integer, Unit> function2, final Function2<? super Composer, ? super Integer, Unit> function22, Modifier modifier, DialogProperties properties, Function2<? super Composer, ? super Integer, Unit> function23, Function2<? super Composer, ? super Integer, Unit> function24, Shape shape, long containerColor, final Function3<? super ColumnScope, ? super Composer, ? super Integer, Unit> function3, Composer $composer, final int $changed, final int i) {
        final Function2<? super Composer, ? super Integer, Unit> function25;
        Function2<? super Composer, ? super Integer, Unit> function26;
        Modifier modifier2;
        DialogProperties properties2;
        Function2<? super Composer, ? super Integer, Unit> function27;
        Function2<? super Composer, ? super Integer, Unit> function28;
        int $dirty;
        int $dirty2;
        Composer $composer2;
        final Modifier modifier3;
        final DialogProperties properties3;
        final Function2<? super Composer, ? super Integer, Unit> function29;
        final Function2<? super Composer, ? super Integer, Unit> function210;
        final Shape shape2;
        final long containerColor2;
        DialogProperties properties4;
        Shape shape3;
        final long containerColor3;
        final Shape shape4;
        final Function2<? super Composer, ? super Integer, Unit> function211;
        int $dirty3;
        final Modifier modifier4;
        final Function2<? super Composer, ? super Integer, Unit> function212;
        int $dirty4;
        int i2;
        Composer $composer3 = $composer.startRestartGroup(951250327);
        ComposerKt.sourceInformation($composer3, "C(TimePickerDialog)N(onDismissRequest,confirmButton,title,modifier,properties,modeToggleButton,dismissButton,shape,containerColor:c#ui.graphics.Color,content)80@3861L347,80@3792L416:TimePickerDialog.kt#uh7d8r");
        int $dirty5 = $changed;
        if ((i & 1) != 0) {
            $dirty5 |= 6;
        } else if (($changed & 6) == 0) {
            $dirty5 |= $composer3.changedInstance(function0) ? 4 : 2;
        }
        if ((i & 2) != 0) {
            $dirty5 |= 48;
            function25 = function2;
        } else if (($changed & 48) == 0) {
            function25 = function2;
            $dirty5 |= $composer3.changedInstance(function25) ? 32 : 16;
        } else {
            function25 = function2;
        }
        if ((i & 4) != 0) {
            $dirty5 |= 384;
            function26 = function22;
        } else if (($changed & 384) == 0) {
            function26 = function22;
            $dirty5 |= $composer3.changedInstance(function26) ? 256 : 128;
        } else {
            function26 = function22;
        }
        int i3 = i & 8;
        if (i3 != 0) {
            $dirty5 |= 3072;
            modifier2 = modifier;
        } else if (($changed & 3072) == 0) {
            modifier2 = modifier;
            $dirty5 |= $composer3.changed(modifier2) ? 2048 : 1024;
        } else {
            modifier2 = modifier;
        }
        int i4 = i & 16;
        if (i4 != 0) {
            $dirty5 |= 24576;
            properties2 = properties;
        } else if (($changed & 24576) == 0) {
            properties2 = properties;
            $dirty5 |= $composer3.changed(properties2) ? 16384 : 8192;
        } else {
            properties2 = properties;
        }
        int i5 = i & 32;
        if (i5 != 0) {
            $dirty5 |= ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE;
            function27 = function23;
        } else if ((196608 & $changed) == 0) {
            function27 = function23;
            $dirty5 |= $composer3.changedInstance(function27) ? 131072 : 65536;
        } else {
            function27 = function23;
        }
        int i6 = i & 64;
        if (i6 != 0) {
            $dirty5 |= 1572864;
            function28 = function24;
        } else if ((1572864 & $changed) == 0) {
            function28 = function24;
            $dirty5 |= $composer3.changedInstance(function28) ? 1048576 : 524288;
        } else {
            function28 = function24;
        }
        if (($changed & 12582912) == 0) {
            $dirty5 |= ((i & 128) == 0 && $composer3.changed(shape)) ? 8388608 : 4194304;
        }
        if (($changed & 100663296) == 0) {
            if ((i & 256) == 0) {
                $dirty4 = $dirty5;
                if ($composer3.changed(containerColor)) {
                    i2 = 67108864;
                }
                $dirty = $dirty4 | i2;
            } else {
                $dirty4 = $dirty5;
            }
            i2 = GroupFlagsKt.HasAuxSlotFlag;
            $dirty = $dirty4 | i2;
        } else {
            $dirty = $dirty5;
        }
        int $dirty6 = $dirty;
        if ((i & 512) != 0) {
            $dirty2 = $dirty6 | 805306368;
        } else if (($changed & 805306368) == 0) {
            $dirty2 = $dirty6 | ($composer3.changedInstance(function3) ? GroupFlagsKt.HasMovableContentFlag : GroupFlagsKt.IsMovableContentFlag);
        } else {
            $dirty2 = $dirty6;
        }
        if (!$composer3.shouldExecute(($dirty2 & 306783379) != 306783378, $dirty2 & 1)) {
            $composer2 = $composer3;
            $composer2.skipToGroupEnd();
            modifier3 = modifier2;
            properties3 = properties2;
            function29 = function27;
            function210 = function28;
            shape2 = shape;
            containerColor2 = containerColor;
        } else {
            $composer3.startDefaults();
            ComposerKt.sourceInformation($composer3, "76@3659L5,77@3719L14");
            if (($changed & 1) != 0 && !$composer3.getDefaultsInvalid()) {
                $composer3.skipToGroupEnd();
                if ((i & 128) != 0) {
                    $dirty2 &= -29360129;
                }
                if ((i & 256) != 0) {
                    shape4 = shape;
                    containerColor3 = containerColor;
                    $dirty3 = $dirty2 & (-234881025);
                    modifier4 = modifier2;
                    function211 = function27;
                    function212 = function28;
                } else {
                    shape4 = shape;
                    containerColor3 = containerColor;
                    function211 = function27;
                    function212 = function28;
                    $dirty3 = $dirty2;
                    modifier4 = modifier2;
                }
            } else {
                if (i3 != 0) {
                    modifier2 = Modifier.INSTANCE;
                }
                if (i4 == 0) {
                    properties4 = properties2;
                } else {
                    properties4 = new DialogProperties(false, false, false, 3, (DefaultConstructorMarker) null);
                }
                if (i5 != 0) {
                    function27 = null;
                }
                if (i6 != 0) {
                    function28 = null;
                }
                if ((i & 128) == 0) {
                    shape3 = shape;
                } else {
                    shape3 = TimePickerDialogDefaults.INSTANCE.getShape($composer3, 6);
                    $dirty2 &= -29360129;
                }
                if ((i & 256) == 0) {
                    containerColor3 = containerColor;
                    shape4 = shape3;
                    function211 = function27;
                    $dirty3 = $dirty2;
                    properties2 = properties4;
                    modifier4 = modifier2;
                    function212 = function28;
                } else {
                    $dirty3 = $dirty2 & (-234881025);
                    shape4 = shape3;
                    modifier4 = modifier2;
                    containerColor3 = TimePickerDialogDefaults.INSTANCE.getContainerColor($composer3, 6);
                    function211 = function27;
                    properties2 = properties4;
                    function212 = function28;
                }
            }
            $composer3.endDefaults();
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(951250327, $dirty3, -1, "androidx.compose.material3.TimePickerDialog (TimePickerDialog.kt:79)");
            }
            final Function2<? super Composer, ? super Integer, Unit> function213 = function26;
            AndroidDialog_androidKt.Dialog(function0, properties2, ComposableLambdaKt.rememberComposableLambda(296331566, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.TimePickerDialogKt$TimePickerDialog$1
                @Override // kotlin.jvm.functions.Function2
                public /* bridge */ /* synthetic */ Unit invoke(Composer composer, Integer num) {
                    invoke(composer, num.intValue());
                    return Unit.INSTANCE;
                }

                public final void invoke(Composer $composer4, int $changed2) {
                    ComposerKt.sourceInformation($composer4, "C81@3871L331:TimePickerDialog.kt#uh7d8r");
                    if (!$composer4.shouldExecute(($changed2 & 3) != 2, $changed2 & 1)) {
                        $composer4.skipToGroupEnd();
                        return;
                    }
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventStart(296331566, $changed2, -1, "androidx.compose.material3.TimePickerDialog.<anonymous> (TimePickerDialog.kt:81)");
                    }
                    TimePickerDialogKt.m3187TimePickerDialogLayout3csKH6Y(function25, function213, modifier4, function211, function212, shape4, containerColor3, function3, $composer4, 0, 0);
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventEnd();
                    }
                }
            }, $composer3, 54), $composer3, ($dirty3 & 14) | 384 | (($dirty3 >> 9) & 112), 0);
            $composer2 = $composer3;
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
            properties3 = properties2;
            modifier3 = modifier4;
            function29 = function211;
            function210 = function212;
            shape2 = shape4;
            containerColor2 = containerColor3;
        }
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup = $composer2.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: androidx.compose.material3.TimePickerDialogKt$$ExternalSyntheticLambda2
                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj, Object obj2) {
                    return TimePickerDialogKt.TimePickerDialog_FItCLgY$lambda$0(function0, function2, function22, modifier3, properties3, function29, function210, shape2, containerColor2, function3, $changed, i, (Composer) obj, ((Integer) obj2).intValue());
                }
            });
        }
    }

    /* JADX INFO: renamed from: TimePickerDialogLayout-3csKH6Y, reason: not valid java name */
    public static final void m3187TimePickerDialogLayout3csKH6Y(final Function2<? super Composer, ? super Integer, Unit> function2, final Function2<? super Composer, ? super Integer, Unit> function22, Modifier modifier, Function2<? super Composer, ? super Integer, Unit> function23, Function2<? super Composer, ? super Integer, Unit> function24, Shape shape, long containerColor, final Function3<? super ColumnScope, ? super Composer, ? super Integer, Unit> function3, Composer $composer, final int $changed, final int i) {
        Function2<? super Composer, ? super Integer, Unit> function25;
        Modifier modifier2;
        Function2<? super Composer, ? super Integer, Unit> function26;
        Function2<? super Composer, ? super Integer, Unit> function27;
        Shape shape2;
        long containerColor2;
        Composer $composer2;
        final Function2<? super Composer, ? super Integer, Unit> function28;
        final Function2<? super Composer, ? super Integer, Unit> function29;
        final long containerColor3;
        final Modifier modifier3;
        final Shape shape3;
        int $dirty;
        int $dirty2;
        Composer $composer3 = $composer.startRestartGroup(-401873644);
        ComposerKt.sourceInformation($composer3, "C(TimePickerDialogLayout)N(confirmButton,title,modifier,modeToggleButton,dismissButton,shape,containerColor:c#ui.graphics.Color,content)109@4835L408,105@4660L583:TimePickerDialog.kt#uh7d8r");
        int $dirty3 = $changed;
        if ((i & 1) != 0) {
            $dirty3 |= 6;
        } else if (($changed & 6) == 0) {
            $dirty3 |= $composer3.changedInstance(function2) ? 4 : 2;
        }
        if ((i & 2) != 0) {
            $dirty3 |= 48;
            function25 = function22;
        } else if (($changed & 48) == 0) {
            function25 = function22;
            $dirty3 |= $composer3.changedInstance(function25) ? 32 : 16;
        } else {
            function25 = function22;
        }
        int i2 = i & 4;
        if (i2 != 0) {
            $dirty3 |= 384;
            modifier2 = modifier;
        } else if (($changed & 384) == 0) {
            modifier2 = modifier;
            $dirty3 |= $composer3.changed(modifier2) ? 256 : 128;
        } else {
            modifier2 = modifier;
        }
        int i3 = i & 8;
        if (i3 != 0) {
            $dirty3 |= 3072;
            function26 = function23;
        } else if (($changed & 3072) == 0) {
            function26 = function23;
            $dirty3 |= $composer3.changedInstance(function26) ? 2048 : 1024;
        } else {
            function26 = function23;
        }
        int i4 = i & 16;
        if (i4 != 0) {
            $dirty3 |= 24576;
            function27 = function24;
        } else if (($changed & 24576) == 0) {
            function27 = function24;
            $dirty3 |= $composer3.changedInstance(function27) ? 16384 : 8192;
        } else {
            function27 = function24;
        }
        if ((196608 & $changed) == 0) {
            if ((i & 32) == 0) {
                shape2 = shape;
                int i5 = $composer3.changed(shape2) ? 131072 : 65536;
                $dirty3 |= i5;
            } else {
                shape2 = shape;
            }
            $dirty3 |= i5;
        } else {
            shape2 = shape;
        }
        if ((1572864 & $changed) == 0) {
            if ((i & 64) == 0) {
                containerColor2 = containerColor;
                int i6 = $composer3.changed(containerColor2) ? 1048576 : 524288;
                $dirty3 |= i6;
            } else {
                containerColor2 = containerColor;
            }
            $dirty3 |= i6;
        } else {
            containerColor2 = containerColor;
        }
        if ((i & 128) != 0) {
            $dirty3 |= 12582912;
        } else if (($changed & 12582912) == 0) {
            $dirty3 |= $composer3.changedInstance(function3) ? 8388608 : 4194304;
        }
        int $dirty4 = $dirty3;
        if (!$composer3.shouldExecute(($dirty3 & 4793491) != 4793490, $dirty4 & 1)) {
            $composer2 = $composer3;
            $composer2.skipToGroupEnd();
            function28 = function26;
            function29 = function27;
            containerColor3 = containerColor2;
            modifier3 = modifier2;
            shape3 = shape2;
        } else {
            $composer3.startDefaults();
            ComposerKt.sourceInformation($composer3, "101@4527L5,102@4587L14");
            if (($changed & 1) != 0 && !$composer3.getDefaultsInvalid()) {
                $composer3.skipToGroupEnd();
                int $dirty5 = (i & 32) != 0 ? $dirty4 & (-458753) : $dirty4;
                if ((i & 64) != 0) {
                    $dirty5 &= -3670017;
                }
                $dirty2 = $dirty5;
            } else {
                if (i2 != 0) {
                    modifier2 = Modifier.INSTANCE;
                }
                if (i3 != 0) {
                    function26 = null;
                }
                if (i4 != 0) {
                    function27 = null;
                }
                if ((i & 32) == 0) {
                    $dirty = $dirty4;
                } else {
                    $dirty = $dirty4 & (-458753);
                    shape2 = TimePickerDialogDefaults.INSTANCE.getShape($composer3, 6);
                }
                if ((i & 64) == 0) {
                    $dirty2 = $dirty;
                } else {
                    containerColor2 = TimePickerDialogDefaults.INSTANCE.getContainerColor($composer3, 6);
                    $dirty2 = $dirty & (-3670017);
                }
            }
            $composer3.endDefaults();
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-401873644, $dirty2, -1, "androidx.compose.material3.TimePickerDialogLayout (TimePickerDialog.kt:104)");
            }
            final Function2<? super Composer, ? super Integer, Unit> function210 = function27;
            final Function2<? super Composer, ? super Integer, Unit> function211 = function25;
            final Function2<? super Composer, ? super Integer, Unit> function212 = function26;
            long containerColor4 = containerColor2;
            $composer2 = $composer3;
            SurfaceKt.m3014SurfaceT9BRK9s(BackgroundKt.m285backgroundbw27NRU(modifier2, containerColor2, shape2), shape2, 0L, 0L, DialogTokens.INSTANCE.m3774getContainerElevationD9Ej5fM(), 0.0f, null, ComposableLambdaKt.rememberComposableLambda(1522143641, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.TimePickerDialogKt$TimePickerDialogLayout$1
                @Override // kotlin.jvm.functions.Function2
                public /* bridge */ /* synthetic */ Unit invoke(Composer composer, Integer num) {
                    invoke(composer, num.intValue());
                    return Unit.INSTANCE;
                }

                public final void invoke(Composer $composer4, int $changed2) {
                    ComposerKt.sourceInformation($composer4, "C112@4918L277,110@4845L392:TimePickerDialog.kt#uh7d8r");
                    if (!$composer4.shouldExecute(($changed2 & 3) != 2, $changed2 & 1)) {
                        $composer4.skipToGroupEnd();
                        return;
                    }
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventStart(1522143641, $changed2, -1, "androidx.compose.material3.TimePickerDialogLayout.<anonymous> (TimePickerDialog.kt:110)");
                    }
                    Function2<Composer, Integer, Unit> function213 = function211;
                    final Function2<Composer, Integer, Unit> function214 = function212;
                    final Function2<Composer, Integer, Unit> function215 = function210;
                    final Function2<Composer, Integer, Unit> function216 = function2;
                    TimePickerDialogKt.TimePickerCustomLayout(function213, ComposableLambdaKt.rememberComposableLambda(2122920701, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.TimePickerDialogKt$TimePickerDialogLayout$1.1
                        @Override // kotlin.jvm.functions.Function2
                        public /* bridge */ /* synthetic */ Unit invoke(Composer composer, Integer num) {
                            invoke(composer, num.intValue());
                            return Unit.INSTANCE;
                        }

                        public final void invoke(Composer $composer5, int $changed3) {
                            Function0<ComposeUiNode> function0;
                            ComposerKt.sourceInformation($composer5, "C113@4936L245:TimePickerDialog.kt#uh7d8r");
                            if (!$composer5.shouldExecute(($changed3 & 3) != 2, $changed3 & 1)) {
                                $composer5.skipToGroupEnd();
                                return;
                            }
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventStart(2122920701, $changed3, -1, "androidx.compose.material3.TimePickerDialogLayout.<anonymous>.<anonymous> (TimePickerDialog.kt:113)");
                            }
                            Modifier modifier$iv = SizeKt.fillMaxWidth$default(Modifier.INSTANCE, 0.0f, 1, null);
                            Function2<Composer, Integer, Unit> function217 = function214;
                            Function2<Composer, Integer, Unit> function218 = function215;
                            Function2<Composer, Integer, Unit> function219 = function216;
                            ComposerKt.sourceInformationMarkerStart($composer5, 693286680, "CC(Row)P(2,1,3)99@5124L58,100@5187L130:Row.kt#2w3rfo");
                            Arrangement.Horizontal horizontalArrangement$iv = Arrangement.INSTANCE.getStart();
                            Alignment.Vertical verticalAlignment$iv = Alignment.INSTANCE.getTop();
                            MeasurePolicy measurePolicy$iv = RowKt.rowMeasurePolicy(horizontalArrangement$iv, verticalAlignment$iv, $composer5, ((6 >> 3) & 14) | ((6 >> 3) & 112));
                            int $changed$iv$iv = (6 << 3) & 112;
                            ComposerKt.sourceInformationMarkerStart($composer5, -1323940314, "CC(Layout)P(!1,2)79@3206L23,82@3357L359:Layout.kt#80mrfh");
                            int compositeKeyHash$iv$iv = ComposablesKt.getCurrentCompositeKeyHash($composer5, 0);
                            CompositionLocalMap localMap$iv$iv = $composer5.getCurrentCompositionLocalMap();
                            Modifier materialized$iv$iv = ComposedModifierKt.materializeModifier($composer5, modifier$iv);
                            Function0<ComposeUiNode> constructor = ComposeUiNode.INSTANCE.getConstructor();
                            int $changed$iv$iv$iv = (($changed$iv$iv << 6) & 896) | 6;
                            ComposerKt.sourceInformationMarkerStart($composer5, -692256719, "CC(ReusableComposeNode)P(1,2)355@14017L9:Composables.kt#9igjgp");
                            if (!($composer5.getApplier() instanceof Applier)) {
                                ComposablesKt.invalidApplier();
                            }
                            $composer5.startReusableNode();
                            if ($composer5.getInserting()) {
                                function0 = constructor;
                                $composer5.createNode(function0);
                            } else {
                                function0 = constructor;
                                $composer5.useNode();
                            }
                            Composer $this$Layout_u24lambda_u240$iv$iv = Updater.m4433constructorimpl($composer5);
                            Updater.m4441setimpl($this$Layout_u24lambda_u240$iv$iv, measurePolicy$iv, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
                            Updater.m4441setimpl($this$Layout_u24lambda_u240$iv$iv, localMap$iv$iv, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
                            Function2<ComposeUiNode, Integer, Unit> setCompositeKeyHash = ComposeUiNode.INSTANCE.getSetCompositeKeyHash();
                            if ($this$Layout_u24lambda_u240$iv$iv.getInserting() || !Intrinsics.areEqual($this$Layout_u24lambda_u240$iv$iv.rememberedValue(), Integer.valueOf(compositeKeyHash$iv$iv))) {
                                $this$Layout_u24lambda_u240$iv$iv.updateRememberedValue(Integer.valueOf(compositeKeyHash$iv$iv));
                                $this$Layout_u24lambda_u240$iv$iv.apply(Integer.valueOf(compositeKeyHash$iv$iv), setCompositeKeyHash);
                            }
                            Updater.m4441setimpl($this$Layout_u24lambda_u240$iv$iv, materialized$iv$iv, ComposeUiNode.INSTANCE.getSetModifier());
                            int i7 = ($changed$iv$iv$iv >> 6) & 14;
                            ComposerKt.sourceInformationMarkerStart($composer5, -407735110, "C101@5232L9:Row.kt#2w3rfo");
                            int i8 = ((6 >> 6) & 112) | 6;
                            RowScope $this$invoke_u24lambda_u240 = RowScopeInstance.INSTANCE;
                            ComposerKt.sourceInformationMarkerStart($composer5, 675820093, "C115@5045L38,117@5148L15:TimePickerDialog.kt#uh7d8r");
                            if (function217 == null) {
                                $composer5.startReplaceGroup(675833080);
                            } else {
                                $composer5.startReplaceGroup(2100011049);
                                ComposerKt.sourceInformation($composer5, "114@5016L8");
                                function217.invoke($composer5, 0);
                            }
                            $composer5.endReplaceGroup();
                            SpacerKt.Spacer(RowScope.weight$default($this$invoke_u24lambda_u240, Modifier.INSTANCE, 1.0f, false, 2, null), $composer5, 0);
                            if (function218 == null) {
                                $composer5.startReplaceGroup(675935256);
                            } else {
                                $composer5.startReplaceGroup(2100014345);
                                ComposerKt.sourceInformation($composer5, "116@5119L8");
                                function218.invoke($composer5, 0);
                            }
                            $composer5.endReplaceGroup();
                            function219.invoke($composer5, 0);
                            ComposerKt.sourceInformationMarkerEnd($composer5);
                            ComposerKt.sourceInformationMarkerEnd($composer5);
                            $composer5.endNode();
                            ComposerKt.sourceInformationMarkerEnd($composer5);
                            ComposerKt.sourceInformationMarkerEnd($composer5);
                            ComposerKt.sourceInformationMarkerEnd($composer5);
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventEnd();
                            }
                        }
                    }, $composer4, 54), function3, $composer4, 48);
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventEnd();
                    }
                }
            }, $composer3, 54), $composer2, (($dirty2 >> 12) & 112) | 12607488, AppCompatDelegate.FEATURE_SUPPORT_ACTION_BAR);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
            function29 = function210;
            function28 = function26;
            containerColor3 = containerColor4;
            modifier3 = modifier2;
            shape3 = shape2;
        }
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup = $composer2.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: androidx.compose.material3.TimePickerDialogKt$$ExternalSyntheticLambda1
                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj, Object obj2) {
                    return TimePickerDialogKt.TimePickerDialogLayout_3csKH6Y$lambda$1(function2, function22, modifier3, function28, function29, shape3, containerColor3, function3, $changed, i, (Composer) obj, ((Integer) obj2).intValue());
                }
            });
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:53:0x0171  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static final void TimePickerCustomLayout(final kotlin.jvm.functions.Function2<? super androidx.compose.runtime.Composer, ? super java.lang.Integer, kotlin.Unit> r24, final kotlin.jvm.functions.Function2<? super androidx.compose.runtime.Composer, ? super java.lang.Integer, kotlin.Unit> r25, final kotlin.jvm.functions.Function3<? super androidx.compose.foundation.layout.ColumnScope, ? super androidx.compose.runtime.Composer, ? super java.lang.Integer, kotlin.Unit> r26, androidx.compose.runtime.Composer r27, final int r28) {
        /*
            Method dump skipped, instruction units count: 393
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.material3.TimePickerDialogKt.TimePickerCustomLayout(kotlin.jvm.functions.Function2, kotlin.jvm.functions.Function2, kotlin.jvm.functions.Function3, androidx.compose.runtime.Composer, int):void");
    }
}
