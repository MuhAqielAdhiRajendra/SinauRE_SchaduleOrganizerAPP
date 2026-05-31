package androidx.compose.material3;

import androidx.compose.animation.core.FiniteAnimationSpec;
import androidx.compose.foundation.BackgroundKt;
import androidx.compose.foundation.layout.BoxKt;
import androidx.compose.foundation.layout.BoxScopeInstance;
import androidx.compose.foundation.layout.ColumnScope;
import androidx.compose.foundation.layout.PaddingValues;
import androidx.compose.foundation.layout.SizeKt;
import androidx.compose.material3.internal.AnchoredDraggableKt;
import androidx.compose.material3.internal.DraggableAnchors;
import androidx.compose.material3.internal.DraggableAnchorsConfig;
import androidx.compose.runtime.Applier;
import androidx.compose.runtime.ComposablesKt;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.CompositionLocalKt;
import androidx.compose.runtime.CompositionLocalMap;
import androidx.compose.runtime.ProvidedValue;
import androidx.compose.runtime.RecomposeScopeImplKt;
import androidx.compose.runtime.ScopeUpdateScope;
import androidx.compose.runtime.Updater;
import androidx.compose.runtime.composer.linkbuffer.GroupFlagsKt;
import androidx.compose.runtime.internal.ComposableLambdaKt;
import androidx.compose.ui.Alignment;
import androidx.compose.ui.ComposedModifierKt;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.graphics.Color;
import androidx.compose.ui.graphics.GraphicsLayerModifierKt;
import androidx.compose.ui.graphics.GraphicsLayerScope;
import androidx.compose.ui.graphics.Shape;
import androidx.compose.ui.graphics.TransformOriginKt;
import androidx.compose.ui.layout.MeasurePolicy;
import androidx.compose.ui.node.ComposeUiNode;
import androidx.compose.ui.unit.Constraints;
import androidx.compose.ui.unit.Dp;
import androidx.compose.ui.unit.IntSize;
import androidx.profileinstaller.ProfileVerifier;
import kotlin.Metadata;
import kotlin.NoWhenBranchMatchedException;
import kotlin.Pair;
import kotlin.TuplesKt;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.CoroutineScope;

/* JADX INFO: compiled from: BottomSheetScaffold.kt */
/* JADX INFO: loaded from: classes12.dex */
@Metadata(d1 = {"\u0000r\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0010\n\u0002\u0010\u0007\n\u0002\b\u0005\u001a\u0087\u0002\u0010\u0000\u001a\u00020\u00012\u001c\u0010\u0002\u001a\u0018\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00010\u0003¢\u0006\u0002\b\u0005¢\u0006\u0002\b\u00062\b\b\u0002\u0010\u0007\u001a\u00020\b2\b\b\u0002\u0010\t\u001a\u00020\n2\b\b\u0002\u0010\u000b\u001a\u00020\f2\b\b\u0002\u0010\r\u001a\u00020\f2\b\b\u0002\u0010\u000e\u001a\u00020\u000f2\b\b\u0002\u0010\u0010\u001a\u00020\u00112\b\b\u0002\u0010\u0012\u001a\u00020\u00112\b\b\u0002\u0010\u0013\u001a\u00020\f2\b\b\u0002\u0010\u0014\u001a\u00020\f2\u0015\b\u0002\u0010\u0015\u001a\u000f\u0012\u0004\u0012\u00020\u0001\u0018\u00010\u0016¢\u0006\u0002\b\u00052\b\b\u0002\u0010\u0017\u001a\u00020\u00182\u0015\b\u0002\u0010\u0019\u001a\u000f\u0012\u0004\u0012\u00020\u0001\u0018\u00010\u0016¢\u0006\u0002\b\u00052\u0019\b\u0002\u0010\u001a\u001a\u0013\u0012\u0004\u0012\u00020\u001b\u0012\u0004\u0012\u00020\u00010\u0003¢\u0006\u0002\b\u00052\b\b\u0002\u0010\u001c\u001a\u00020\u00112\b\b\u0002\u0010\u001d\u001a\u00020\u00112\u0017\u0010\u001e\u001a\u0013\u0012\u0004\u0012\u00020\u001f\u0012\u0004\u0012\u00020\u00010\u0003¢\u0006\u0002\b\u0005H\u0007¢\u0006\u0004\b \u0010!\u001a!\u0010\"\u001a\u00020\n2\b\b\u0002\u0010#\u001a\u00020$2\b\b\u0002\u0010%\u001a\u00020\u001bH\u0007¢\u0006\u0002\u0010&\u001a7\u0010'\u001a\u00020$2\b\b\u0002\u0010(\u001a\u00020)2\u0014\b\u0002\u0010*\u001a\u000e\u0012\u0004\u0012\u00020)\u0012\u0004\u0012\u00020\u00180\u00032\b\b\u0002\u0010+\u001a\u00020\u0018H\u0007¢\u0006\u0002\u0010,\u001a\u008a\u0001\u0010-\u001a\u00020\u00012\u0006\u0010.\u001a\u00020$2\u0006\u0010/\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\f2\u0006\u0010\u0017\u001a\u00020\u00182\u0006\u00100\u001a\u00020\u000f2\u0006\u0010\u001c\u001a\u00020\u00112\u0006\u0010\u001d\u001a\u00020\u00112\u0006\u00101\u001a\u00020\f2\u0006\u00102\u001a\u00020\f2\u0013\u00103\u001a\u000f\u0012\u0004\u0012\u00020\u0001\u0018\u00010\u0016¢\u0006\u0002\b\u00052\u001c\u0010\u001e\u001a\u0018\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00010\u0003¢\u0006\u0002\b\u0005¢\u0006\u0002\b\u0006H\u0003¢\u0006\u0004\b4\u00105\u001aq\u00106\u001a\u00020\u00012\u0013\u0010\u0019\u001a\u000f\u0012\u0004\u0012\u00020\u0001\u0018\u00010\u0016¢\u0006\u0002\b\u00052\u0011\u00107\u001a\r\u0012\u0004\u0012\u00020\u00010\u0016¢\u0006\u0002\b\u00052\u0011\u00108\u001a\r\u0012\u0004\u0012\u00020\u00010\u0016¢\u0006\u0002\b\u00052\u0011\u0010\u001a\u001a\r\u0012\u0004\u0012\u00020\u00010\u0016¢\u0006\u0002\b\u00052\f\u00109\u001a\b\u0012\u0004\u0012\u00020:0\u00162\u0006\u0010;\u001a\u00020$H\u0003¢\u0006\u0002\u0010<\u001a\u0014\u0010=\u001a\u00020\b*\u00020\b2\u0006\u0010.\u001a\u00020$H\u0000\u001a\u0014\u0010>\u001a\u00020\b*\u00020\b2\u0006\u0010.\u001a\u00020$H\u0000¨\u0006?"}, d2 = {"BottomSheetScaffold", "", "sheetContent", "Lkotlin/Function1;", "Landroidx/compose/foundation/layout/ColumnScope;", "Landroidx/compose/runtime/Composable;", "Lkotlin/ExtensionFunctionType;", "modifier", "Landroidx/compose/ui/Modifier;", "scaffoldState", "Landroidx/compose/material3/BottomSheetScaffoldState;", "sheetPeekHeight", "Landroidx/compose/ui/unit/Dp;", "sheetMaxWidth", "sheetShape", "Landroidx/compose/ui/graphics/Shape;", "sheetContainerColor", "Landroidx/compose/ui/graphics/Color;", "sheetContentColor", "sheetTonalElevation", "sheetShadowElevation", "sheetDragHandle", "Lkotlin/Function0;", "sheetSwipeEnabled", "", "topBar", "snackbarHost", "Landroidx/compose/material3/SnackbarHostState;", "containerColor", "contentColor", "content", "Landroidx/compose/foundation/layout/PaddingValues;", "BottomSheetScaffold-sdMYb0k", "(Lkotlin/jvm/functions/Function3;Landroidx/compose/ui/Modifier;Landroidx/compose/material3/BottomSheetScaffoldState;FFLandroidx/compose/ui/graphics/Shape;JJFFLkotlin/jvm/functions/Function2;ZLkotlin/jvm/functions/Function2;Lkotlin/jvm/functions/Function3;JJLkotlin/jvm/functions/Function3;Landroidx/compose/runtime/Composer;III)V", "rememberBottomSheetScaffoldState", "bottomSheetState", "Landroidx/compose/material3/SheetState;", "snackbarHostState", "(Landroidx/compose/material3/SheetState;Landroidx/compose/material3/SnackbarHostState;Landroidx/compose/runtime/Composer;II)Landroidx/compose/material3/BottomSheetScaffoldState;", "rememberStandardBottomSheetState", "initialValue", "Landroidx/compose/material3/SheetValue;", "confirmValueChange", "skipHiddenState", "(Landroidx/compose/material3/SheetValue;Lkotlin/jvm/functions/Function1;ZLandroidx/compose/runtime/Composer;II)Landroidx/compose/material3/SheetState;", "StandardBottomSheet", "state", "peekHeight", "shape", "tonalElevation", "shadowElevation", "dragHandle", "StandardBottomSheet-w7I5h1o", "(Landroidx/compose/material3/SheetState;FFZLandroidx/compose/ui/graphics/Shape;JJFFLkotlin/jvm/functions/Function2;Lkotlin/jvm/functions/Function3;Landroidx/compose/runtime/Composer;II)V", "BottomSheetScaffoldLayout", "body", "bottomSheet", "sheetOffset", "", "sheetState", "(Lkotlin/jvm/functions/Function2;Lkotlin/jvm/functions/Function2;Lkotlin/jvm/functions/Function2;Lkotlin/jvm/functions/Function2;Lkotlin/jvm/functions/Function0;Landroidx/compose/material3/SheetState;Landroidx/compose/runtime/Composer;I)V", "verticalScaleUp", "verticalScaleDown", "material3"}, k = 2, mv = {2, 0, 0}, xi = 48)
public final class BottomSheetScaffoldKt {

    /* JADX INFO: compiled from: BottomSheetScaffold.kt */
    @Metadata(k = 3, mv = {2, 0, 0}, xi = 48)
    public /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        static {
            int[] iArr = new int[SheetValue.values().length];
            try {
                iArr[SheetValue.Hidden.ordinal()] = 1;
            } catch (NoSuchFieldError e) {
            }
            try {
                iArr[SheetValue.PartiallyExpanded.ordinal()] = 2;
            } catch (NoSuchFieldError e2) {
            }
            try {
                iArr[SheetValue.Expanded.ordinal()] = 3;
            } catch (NoSuchFieldError e3) {
            }
            $EnumSwitchMapping$0 = iArr;
        }
    }

    static final Unit BottomSheetScaffoldLayout$lambda$16(Function2 function2, Function2 function22, Function2 function23, Function2 function24, Function0 function0, SheetState sheetState, int i, Composer composer, int i2) {
        BottomSheetScaffoldLayout(function2, function22, function23, function24, function0, sheetState, composer, RecomposeScopeImplKt.updateChangedFlags(i | 1));
        return Unit.INSTANCE;
    }

    static final Unit BottomSheetScaffold_sdMYb0k$lambda$1(Function3 function3, Modifier modifier, BottomSheetScaffoldState bottomSheetScaffoldState, float f, float f2, Shape shape, long j, long j2, float f3, float f4, Function2 function2, boolean z, Function2 function22, Function3 function32, long j3, long j4, Function3 function33, int i, int i2, int i3, Composer composer, int i4) {
        m2197BottomSheetScaffoldsdMYb0k(function3, modifier, bottomSheetScaffoldState, f, f2, shape, j, j2, f3, f4, function2, z, function22, function32, j3, j4, function33, composer, RecomposeScopeImplKt.updateChangedFlags(i | 1), RecomposeScopeImplKt.updateChangedFlags(i2), i3);
        return Unit.INSTANCE;
    }

    static final Unit StandardBottomSheet_w7I5h1o$lambda$14(SheetState sheetState, float f, float f2, boolean z, Shape shape, long j, long j2, float f3, float f4, Function2 function2, Function3 function3, int i, int i2, Composer composer, int i3) {
        m2198StandardBottomSheetw7I5h1o(sheetState, f, f2, z, shape, j, j2, f3, f4, function2, function3, composer, RecomposeScopeImplKt.updateChangedFlags(i | 1), RecomposeScopeImplKt.updateChangedFlags(i2));
        return Unit.INSTANCE;
    }

    /* JADX INFO: renamed from: BottomSheetScaffold-sdMYb0k, reason: not valid java name */
    public static final void m2197BottomSheetScaffoldsdMYb0k(final Function3<? super ColumnScope, ? super Composer, ? super Integer, Unit> function3, Modifier modifier, BottomSheetScaffoldState scaffoldState, float sheetPeekHeight, float sheetMaxWidth, Shape sheetShape, long sheetContainerColor, long sheetContentColor, float sheetTonalElevation, float sheetShadowElevation, Function2<? super Composer, ? super Integer, Unit> function2, boolean sheetSwipeEnabled, Function2<? super Composer, ? super Integer, Unit> function22, Function3<? super SnackbarHostState, ? super Composer, ? super Integer, Unit> function32, long containerColor, long contentColor, final Function3<? super PaddingValues, ? super Composer, ? super Integer, Unit> function33, Composer $composer, final int $changed, final int $changed1, final int i) {
        BottomSheetScaffoldState scaffoldState2;
        float sheetPeekHeight2;
        float sheetMaxWidth2;
        Shape sheetShape2;
        long sheetContainerColor2;
        int $dirty;
        int $dirty1;
        int i2;
        int i3;
        int i4;
        int i5;
        Composer $composer2;
        final Modifier modifier2;
        final Function2<? super Composer, ? super Integer, Unit> function23;
        final Function3<? super SnackbarHostState, ? super Composer, ? super Integer, Unit> function34;
        final long containerColor2;
        final long contentColor2;
        final float sheetPeekHeight3;
        final float sheetMaxWidth3;
        final Shape sheetShape3;
        final long sheetContainerColor3;
        final BottomSheetScaffoldState scaffoldState3;
        final long sheetContentColor2;
        final float sheetTonalElevation2;
        final float sheetShadowElevation2;
        final Function2<? super Composer, ? super Integer, Unit> function24;
        final boolean sheetSwipeEnabled2;
        Modifier.Companion modifier3;
        Modifier modifier4;
        int i6;
        long sheetContentColor3;
        float sheetTonalElevation3;
        float sheetShadowElevation3;
        Function2<? super Composer, ? super Integer, Unit> lambda$1392012807$material3;
        boolean sheetSwipeEnabled3;
        Function2<? super Composer, ? super Integer, Unit> function25;
        Function3<? super SnackbarHostState, ? super Composer, ? super Integer, Unit> lambda$1768941633$material3;
        float sheetTonalElevation4;
        long containerColor3;
        int $dirty2;
        long contentColor3;
        long containerColor4;
        Modifier modifier5;
        float sheetTonalElevation5;
        float sheetTonalElevation6;
        long sheetContentColor4;
        int $dirty3;
        int i7;
        Composer $composer3 = $composer.startRestartGroup(920075480);
        ComposerKt.sourceInformation($composer3, "C(BottomSheetScaffold)N(sheetContent,modifier,scaffoldState,sheetPeekHeight:c#ui.unit.Dp,sheetMaxWidth:c#ui.unit.Dp,sheetShape,sheetContainerColor:c#ui.graphics.Color,sheetContentColor:c#ui.graphics.Color,sheetTonalElevation:c#ui.unit.Dp,sheetShadowElevation:c#ui.unit.Dp,sheetDragHandle,sheetSwipeEnabled,topBar,snackbarHost,containerColor:c#ui.graphics.Color,contentColor:c#ui.graphics.Color,content)136@7263L1424:BottomSheetScaffold.kt#uh7d8r");
        int $dirty4 = $changed;
        if ((i & 1) != 0) {
            $dirty4 |= 6;
        } else if (($changed & 6) == 0) {
            $dirty4 |= $composer3.changedInstance(function3) ? 4 : 2;
        }
        int i8 = i & 2;
        if (i8 != 0) {
            $dirty4 |= 48;
        } else if (($changed & 48) == 0) {
            $dirty4 |= $composer3.changed(modifier) ? 32 : 16;
        }
        if (($changed & 384) == 0) {
            if ((i & 4) == 0) {
                scaffoldState2 = scaffoldState;
                int i9 = $composer3.changed(scaffoldState2) ? 256 : 128;
                $dirty4 |= i9;
            } else {
                scaffoldState2 = scaffoldState;
            }
            $dirty4 |= i9;
        } else {
            scaffoldState2 = scaffoldState;
        }
        int i10 = i & 8;
        if (i10 != 0) {
            $dirty4 |= 3072;
            sheetPeekHeight2 = sheetPeekHeight;
        } else if (($changed & 3072) == 0) {
            sheetPeekHeight2 = sheetPeekHeight;
            $dirty4 |= $composer3.changed(sheetPeekHeight2) ? 2048 : 1024;
        } else {
            sheetPeekHeight2 = sheetPeekHeight;
        }
        int i11 = i & 16;
        int i12 = 8192;
        if (i11 != 0) {
            $dirty4 |= 24576;
            sheetMaxWidth2 = sheetMaxWidth;
        } else if (($changed & 24576) == 0) {
            sheetMaxWidth2 = sheetMaxWidth;
            $dirty4 |= $composer3.changed(sheetMaxWidth2) ? 16384 : 8192;
        } else {
            sheetMaxWidth2 = sheetMaxWidth;
        }
        if (($changed & ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE) == 0) {
            if ((i & 32) == 0) {
                sheetShape2 = sheetShape;
                if ($composer3.changed(sheetShape2)) {
                    i7 = 131072;
                }
                $dirty4 |= i7;
            } else {
                sheetShape2 = sheetShape;
            }
            i7 = 65536;
            $dirty4 |= i7;
        } else {
            sheetShape2 = sheetShape;
        }
        if (($changed & 1572864) == 0) {
            if ((i & 64) == 0) {
                sheetContainerColor2 = sheetContainerColor;
                int i13 = $composer3.changed(sheetContainerColor2) ? 1048576 : 524288;
                $dirty4 |= i13;
            } else {
                sheetContainerColor2 = sheetContainerColor;
            }
            $dirty4 |= i13;
        } else {
            sheetContainerColor2 = sheetContainerColor;
        }
        if (($changed & 12582912) == 0) {
            if ((i & 128) == 0) {
                $dirty3 = $dirty4;
                $dirty1 = $changed1;
                int i14 = $composer3.changed(sheetContentColor) ? 8388608 : 4194304;
                $dirty = $dirty3 | i14;
            } else {
                $dirty3 = $dirty4;
                $dirty1 = $changed1;
            }
            $dirty = $dirty3 | i14;
        } else {
            $dirty = $dirty4;
            $dirty1 = $changed1;
        }
        int i15 = i & 256;
        if (i15 != 0) {
            $dirty |= 100663296;
        } else if (($changed & 100663296) == 0) {
            $dirty |= $composer3.changed(sheetTonalElevation) ? 67108864 : GroupFlagsKt.HasAuxSlotFlag;
        }
        int i16 = i & 512;
        if (i16 != 0) {
            $dirty |= 805306368;
            i2 = i16;
        } else if (($changed & 805306368) == 0) {
            i2 = i16;
            $dirty |= $composer3.changed(sheetShadowElevation) ? GroupFlagsKt.HasMovableContentFlag : GroupFlagsKt.IsMovableContentFlag;
        } else {
            i2 = i16;
        }
        int $dirty5 = $dirty;
        int i17 = i & 1024;
        if (i17 != 0) {
            $dirty1 |= 6;
        } else {
            int $dirty12 = $changed1 & 6;
            if ($dirty12 == 0) {
                $dirty1 |= $composer3.changedInstance(function2) ? 4 : 2;
            }
        }
        int i18 = i & 2048;
        if (i18 != 0) {
            $dirty1 |= 48;
            i3 = i18;
        } else if (($changed1 & 48) == 0) {
            i3 = i18;
            $dirty1 |= $composer3.changed(sheetSwipeEnabled) ? 32 : 16;
        } else {
            i3 = i18;
        }
        int i19 = i & 4096;
        if (i19 != 0) {
            $dirty1 |= 384;
            i4 = i19;
        } else {
            i4 = i19;
            if (($changed1 & 384) == 0) {
                $dirty1 |= $composer3.changedInstance(function22) ? 256 : 128;
            }
        }
        int i20 = i & 8192;
        if (i20 != 0) {
            $dirty1 |= 3072;
            i5 = i20;
        } else {
            i5 = i20;
            if (($changed1 & 3072) == 0) {
                $dirty1 |= $composer3.changedInstance(function32) ? 2048 : 1024;
            }
        }
        if (($changed1 & 24576) == 0) {
            if ((i & 16384) == 0 && $composer3.changed(containerColor)) {
                i12 = 16384;
            }
            $dirty1 |= i12;
        }
        if (($changed1 & ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE) == 0) {
            $dirty1 |= ((i & 32768) == 0 && $composer3.changed(contentColor)) ? 131072 : 65536;
        }
        if ((i & 65536) != 0) {
            $dirty1 |= 1572864;
        } else if (($changed1 & 1572864) == 0) {
            $dirty1 |= $composer3.changedInstance(function33) ? 1048576 : 524288;
        }
        if ($composer3.shouldExecute((($dirty5 & 306783379) == 306783378 && (599187 & $dirty1) == 599186) ? false : true, $dirty5 & 1)) {
            $composer3.startDefaults();
            ComposerKt.sourceInformation($composer3, "120@6376L34,123@6578L13,124@6646L14,125@6693L36,132@7125L11,133@7172L31");
            if (($changed & 1) != 0 && !$composer3.getDefaultsInvalid()) {
                $composer3.skipToGroupEnd();
                if ((i & 4) != 0) {
                    $dirty5 &= -897;
                }
                if ((i & 32) != 0) {
                    $dirty5 &= -458753;
                }
                if ((i & 64) != 0) {
                    $dirty5 &= -3670017;
                }
                if ((i & 128) != 0) {
                    $dirty5 &= -29360129;
                }
                if ((i & 16384) != 0) {
                    $dirty1 &= -57345;
                }
                if ((i & 32768) != 0) {
                    sheetContentColor3 = sheetContentColor;
                    sheetShadowElevation3 = sheetShadowElevation;
                    lambda$1392012807$material3 = function2;
                    sheetSwipeEnabled3 = sheetSwipeEnabled;
                    function25 = function22;
                    lambda$1768941633$material3 = function32;
                    containerColor4 = containerColor;
                    contentColor3 = contentColor;
                    $dirty2 = $dirty5;
                    $dirty1 &= -458753;
                    modifier5 = modifier;
                    sheetTonalElevation5 = sheetTonalElevation;
                } else {
                    sheetContentColor3 = sheetContentColor;
                    sheetTonalElevation5 = sheetTonalElevation;
                    sheetShadowElevation3 = sheetShadowElevation;
                    lambda$1392012807$material3 = function2;
                    sheetSwipeEnabled3 = sheetSwipeEnabled;
                    function25 = function22;
                    lambda$1768941633$material3 = function32;
                    containerColor4 = containerColor;
                    contentColor3 = contentColor;
                    $dirty2 = $dirty5;
                    modifier5 = modifier;
                }
            } else {
                if (i8 != 0) {
                    modifier3 = Modifier.INSTANCE;
                } else {
                    modifier3 = modifier;
                }
                if ((i & 4) == 0) {
                    modifier4 = modifier3;
                } else {
                    modifier4 = modifier3;
                    $dirty5 &= -897;
                    scaffoldState2 = rememberBottomSheetScaffoldState(null, null, $composer3, 0, 3);
                }
                if (i10 != 0) {
                    sheetPeekHeight2 = BottomSheetDefaults.INSTANCE.m2195getSheetPeekHeightD9Ej5fM();
                }
                if (i11 != 0) {
                    sheetMaxWidth2 = BottomSheetDefaults.INSTANCE.m2194getSheetMaxWidthD9Ej5fM();
                }
                if ((i & 32) == 0) {
                    i6 = 6;
                } else {
                    i6 = 6;
                    $dirty5 &= -458753;
                    sheetShape2 = BottomSheetDefaults.INSTANCE.getExpandedShape($composer3, 6);
                }
                if ((i & 64) != 0) {
                    sheetContainerColor2 = BottomSheetDefaults.INSTANCE.getContainerColor($composer3, i6);
                    $dirty5 &= -3670017;
                }
                if ((i & 128) == 0) {
                    sheetContentColor3 = sheetContentColor;
                } else {
                    sheetContentColor3 = ColorSchemeKt.m2347contentColorForek8zF_U(sheetContainerColor2, $composer3, ($dirty5 >> 18) & 14);
                    $dirty5 &= -29360129;
                }
                if (i15 == 0) {
                    sheetTonalElevation3 = sheetTonalElevation;
                } else {
                    sheetTonalElevation3 = Dp.m8150constructorimpl(0);
                }
                if (i2 != 0) {
                    sheetShadowElevation3 = BottomSheetDefaults.INSTANCE.m2192getElevationD9Ej5fM();
                } else {
                    sheetShadowElevation3 = sheetShadowElevation;
                }
                lambda$1392012807$material3 = i17 != 0 ? ComposableSingletons$BottomSheetScaffoldKt.INSTANCE.getLambda$1392012807$material3() : function2;
                if (i3 == 0) {
                    sheetSwipeEnabled3 = sheetSwipeEnabled;
                } else {
                    sheetSwipeEnabled3 = true;
                }
                if (i4 == 0) {
                    function25 = function22;
                } else {
                    function25 = null;
                }
                lambda$1768941633$material3 = i5 != 0 ? ComposableSingletons$BottomSheetScaffoldKt.INSTANCE.getLambda$1768941633$material3() : function32;
                int $dirty6 = $dirty5;
                if ((i & 16384) == 0) {
                    sheetTonalElevation4 = sheetTonalElevation3;
                    containerColor3 = containerColor;
                } else {
                    sheetTonalElevation4 = sheetTonalElevation3;
                    containerColor3 = MaterialTheme.INSTANCE.getColorScheme($composer3, 6).getSurface();
                    $dirty1 &= -57345;
                }
                if ((i & 32768) == 0) {
                    $dirty2 = $dirty6;
                    contentColor3 = contentColor;
                    containerColor4 = containerColor3;
                    modifier5 = modifier4;
                    sheetTonalElevation5 = sheetTonalElevation4;
                } else {
                    contentColor3 = ColorSchemeKt.m2347contentColorForek8zF_U(containerColor3, $composer3, ($dirty1 >> 12) & 14);
                    containerColor4 = containerColor3;
                    $dirty1 &= -458753;
                    modifier5 = modifier4;
                    $dirty2 = $dirty6;
                    sheetTonalElevation5 = sheetTonalElevation4;
                }
            }
            $composer3.endDefaults();
            if (ComposerKt.isTraceInProgress()) {
                sheetTonalElevation6 = sheetTonalElevation5;
                sheetContentColor4 = sheetContentColor3;
                ComposerKt.traceEventStart(920075480, $dirty2, $dirty1, "androidx.compose.material3.BottomSheetScaffold (BottomSheetScaffold.kt:135)");
            } else {
                sheetTonalElevation6 = sheetTonalElevation5;
                sheetContentColor4 = sheetContentColor3;
            }
            long containerColor5 = containerColor4;
            Modifier modifier$iv = BackgroundKt.m286backgroundbw27NRU$default(SizeKt.fillMaxSize$default(modifier5, 0.0f, 1, null), containerColor5, null, 2, null);
            Modifier modifier6 = modifier5;
            ComposerKt.sourceInformationMarkerStart($composer3, 733328855, "CC(Box)P(2,1,3)71@3423L130:Box.kt#2w3rfo");
            Alignment contentAlignment$iv = Alignment.INSTANCE.getTopStart();
            MeasurePolicy measurePolicy$iv = BoxKt.maybeCachedBoxMeasurePolicy(contentAlignment$iv, false);
            int $changed$iv$iv = (0 << 3) & 112;
            ComposerKt.sourceInformationMarkerStart($composer3, -1323940314, "CC(Layout)P(!1,2)79@3206L23,82@3357L359:Layout.kt#80mrfh");
            int compositeKeyHash$iv$iv = ComposablesKt.getCurrentCompositeKeyHash($composer3, 0);
            CompositionLocalMap localMap$iv$iv = $composer3.getCurrentCompositionLocalMap();
            Modifier materialized$iv$iv = ComposedModifierKt.materializeModifier($composer3, modifier$iv);
            Function0<ComposeUiNode> constructor = ComposeUiNode.INSTANCE.getConstructor();
            int $changed$iv$iv$iv = (($changed$iv$iv << 6) & 896) | 6;
            ComposerKt.sourceInformationMarkerStart($composer3, -692256719, "CC(ReusableComposeNode)P(1,2)355@14017L9:Composables.kt#9igjgp");
            if (!($composer3.getApplier() instanceof Applier)) {
                ComposablesKt.invalidApplier();
            }
            $composer3.startReusableNode();
            if ($composer3.getInserting()) {
                $composer3.createNode(constructor);
            } else {
                $composer3.useNode();
            }
            Composer $this$Layout_u24lambda_u240$iv$iv = Updater.m4433constructorimpl($composer3);
            $composer2 = $composer3;
            Updater.m4441setimpl($this$Layout_u24lambda_u240$iv$iv, measurePolicy$iv, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
            Updater.m4441setimpl($this$Layout_u24lambda_u240$iv$iv, localMap$iv$iv, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
            Function2<ComposeUiNode, Integer, Unit> setCompositeKeyHash = ComposeUiNode.INSTANCE.getSetCompositeKeyHash();
            if ($this$Layout_u24lambda_u240$iv$iv.getInserting() || !Intrinsics.areEqual($this$Layout_u24lambda_u240$iv$iv.rememberedValue(), Integer.valueOf(compositeKeyHash$iv$iv))) {
                $this$Layout_u24lambda_u240$iv$iv.updateRememberedValue(Integer.valueOf(compositeKeyHash$iv$iv));
                $this$Layout_u24lambda_u240$iv$iv.apply(Integer.valueOf(compositeKeyHash$iv$iv), setCompositeKeyHash);
            }
            Updater.m4441setimpl($this$Layout_u24lambda_u240$iv$iv, materialized$iv$iv, ComposeUiNode.INSTANCE.getSetModifier());
            int i21 = ($changed$iv$iv$iv >> 6) & 14;
            ComposerKt.sourceInformationMarkerStart($composer2, -2146730711, "C72@3468L9:Box.kt#2w3rfo");
            BoxScopeInstance boxScopeInstance = BoxScopeInstance.INSTANCE;
            int i22 = ((0 >> 6) & 112) | 6;
            ComposerKt.sourceInformationMarkerStart($composer2, -1080463218, "C139@7540L1141,139@7474L1207:BottomSheetScaffold.kt#uh7d8r");
            float sheetShadowElevation4 = sheetShadowElevation3;
            Function2<? super Composer, ? super Integer, Unit> function26 = lambda$1392012807$material3;
            boolean sheetSwipeEnabled4 = sheetSwipeEnabled3;
            Function2<? super Composer, ? super Integer, Unit> function27 = function25;
            Function3<? super SnackbarHostState, ? super Composer, ? super Integer, Unit> function35 = lambda$1768941633$material3;
            long sheetContentColor5 = sheetContentColor4;
            float sheetTonalElevation7 = sheetTonalElevation6;
            CompositionLocalKt.CompositionLocalProvider(ContentColorKt.getLocalContentColor().provides(Color.m5303boximpl(contentColor3)), ComposableLambdaKt.rememberComposableLambda(999829022, true, new BottomSheetScaffoldKt$BottomSheetScaffold$1$1(scaffoldState2, function27, function33, sheetPeekHeight2, sheetMaxWidth2, sheetSwipeEnabled4, sheetShape2, sheetContainerColor2, sheetContentColor4, sheetTonalElevation6, sheetShadowElevation4, function26, function3, function35), $composer2, 54), $composer2, ProvidedValue.$stable | 48);
            ComposerKt.sourceInformationMarkerEnd($composer2);
            ComposerKt.sourceInformationMarkerEnd($composer2);
            $composer2.endNode();
            ComposerKt.sourceInformationMarkerEnd($composer2);
            ComposerKt.sourceInformationMarkerEnd($composer2);
            ComposerKt.sourceInformationMarkerEnd($composer2);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
            sheetPeekHeight3 = sheetPeekHeight2;
            sheetMaxWidth3 = sheetMaxWidth2;
            sheetShape3 = sheetShape2;
            sheetContainerColor3 = sheetContainerColor2;
            scaffoldState3 = scaffoldState2;
            sheetShadowElevation2 = sheetShadowElevation4;
            function24 = function26;
            contentColor2 = contentColor3;
            modifier2 = modifier6;
            containerColor2 = containerColor5;
            sheetTonalElevation2 = sheetTonalElevation7;
            sheetContentColor2 = sheetContentColor5;
            sheetSwipeEnabled2 = sheetSwipeEnabled4;
            function23 = function27;
            function34 = function35;
        } else {
            $composer2 = $composer3;
            $composer2.skipToGroupEnd();
            modifier2 = modifier;
            function23 = function22;
            function34 = function32;
            containerColor2 = containerColor;
            contentColor2 = contentColor;
            sheetPeekHeight3 = sheetPeekHeight2;
            sheetMaxWidth3 = sheetMaxWidth2;
            sheetShape3 = sheetShape2;
            sheetContainerColor3 = sheetContainerColor2;
            scaffoldState3 = scaffoldState2;
            sheetContentColor2 = sheetContentColor;
            sheetTonalElevation2 = sheetTonalElevation;
            sheetShadowElevation2 = sheetShadowElevation;
            function24 = function2;
            sheetSwipeEnabled2 = sheetSwipeEnabled;
        }
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup = $composer2.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: androidx.compose.material3.BottomSheetScaffoldKt$$ExternalSyntheticLambda7
                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj, Object obj2) {
                    return BottomSheetScaffoldKt.BottomSheetScaffold_sdMYb0k$lambda$1(function3, modifier2, scaffoldState3, sheetPeekHeight3, sheetMaxWidth3, sheetShape3, sheetContainerColor3, sheetContentColor2, sheetTonalElevation2, sheetShadowElevation2, function24, sheetSwipeEnabled2, function23, function34, containerColor2, contentColor2, function33, $changed, $changed1, i, (Composer) obj, ((Integer) obj2).intValue());
                }
            });
        }
    }

    public static final BottomSheetScaffoldState rememberBottomSheetScaffoldState(SheetState bottomSheetState, SnackbarHostState snackbarHostState, Composer $composer, int $changed, int i) {
        Composer $composer2;
        ComposerKt.sourceInformationMarkerStart($composer, -1474606134, "C(rememberBottomSheetScaffoldState)N(bottomSheetState,snackbarHostState)189@9451L34,190@9530L32,192@9605L197:BottomSheetScaffold.kt#uh7d8r");
        if ((i & 1) != 0) {
            $composer2 = $composer;
            bottomSheetState = rememberStandardBottomSheetState(null, null, false, $composer2, 0, 7);
        } else {
            $composer2 = $composer;
        }
        if ((i & 2) != 0) {
            ComposerKt.sourceInformationMarkerStart($composer2, 242719018, "CC(remember):BottomSheetScaffold.kt#9igjgp");
            Composer $this$cache$iv = $composer2;
            Object it$iv = $this$cache$iv.rememberedValue();
            if (it$iv == Composer.INSTANCE.getEmpty()) {
                Object value$iv = new SnackbarHostState();
                $this$cache$iv.updateRememberedValue(value$iv);
                it$iv = value$iv;
            }
            snackbarHostState = (SnackbarHostState) it$iv;
            ComposerKt.sourceInformationMarkerEnd($composer2);
        }
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(-1474606134, $changed, -1, "androidx.compose.material3.rememberBottomSheetScaffoldState (BottomSheetScaffold.kt:191)");
        }
        ComposerKt.sourceInformationMarkerStart($composer2, 242721583, "CC(remember):BottomSheetScaffold.kt#9igjgp");
        boolean invalid$iv = (((($changed & 14) ^ 6) > 4 && $composer2.changed(bottomSheetState)) || ($changed & 6) == 4) | (((($changed & 112) ^ 48) > 32 && $composer2.changed(snackbarHostState)) || ($changed & 48) == 32);
        Composer $this$cache$iv2 = $composer2;
        Object it$iv2 = $this$cache$iv2.rememberedValue();
        if (invalid$iv || it$iv2 == Composer.INSTANCE.getEmpty()) {
            Object value$iv2 = new BottomSheetScaffoldState(bottomSheetState, snackbarHostState);
            $this$cache$iv2.updateRememberedValue(value$iv2);
            it$iv2 = value$iv2;
        }
        BottomSheetScaffoldState bottomSheetScaffoldState = (BottomSheetScaffoldState) it$iv2;
        ComposerKt.sourceInformationMarkerEnd($composer2);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        ComposerKt.sourceInformationMarkerEnd($composer2);
        return bottomSheetScaffoldState;
    }

    static final boolean rememberStandardBottomSheetState$lambda$5$lambda$4(SheetValue it) {
        return true;
    }

    public static final SheetState rememberStandardBottomSheetState(SheetValue initialValue, Function1<? super SheetValue, Boolean> function1, boolean skipHiddenState, Composer $composer, int $changed, int i) {
        SheetValue initialValue2;
        Function1<? super SheetValue, Boolean> function12;
        ComposerKt.sourceInformationMarkerStart($composer, 678511581, "C(rememberStandardBottomSheetState)N(initialValue,confirmValueChange,skipHiddenState)212@10383L8,215@10438L154:BottomSheetScaffold.kt#uh7d8r");
        if ((i & 1) != 0) {
            SheetValue initialValue3 = SheetValue.PartiallyExpanded;
            initialValue2 = initialValue3;
        } else {
            initialValue2 = initialValue;
        }
        if ((i & 2) != 0) {
            ComposerKt.sourceInformationMarkerStart($composer, -785394011, "CC(remember):BottomSheetScaffold.kt#9igjgp");
            Object it$iv = $composer.rememberedValue();
            if (it$iv == Composer.INSTANCE.getEmpty()) {
                Object value$iv = new Function1() { // from class: androidx.compose.material3.BottomSheetScaffoldKt$$ExternalSyntheticLambda6
                    @Override // kotlin.jvm.functions.Function1
                    public final Object invoke(Object obj) {
                        return Boolean.valueOf(BottomSheetScaffoldKt.rememberStandardBottomSheetState$lambda$5$lambda$4((SheetValue) obj));
                    }
                };
                $composer.updateRememberedValue(value$iv);
                it$iv = value$iv;
            }
            ComposerKt.sourceInformationMarkerEnd($composer);
            function12 = (Function1) it$iv;
        } else {
            function12 = function1;
        }
        boolean skipHiddenState2 = (i & 4) != 0 ? true : skipHiddenState;
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(678511581, $changed, -1, "androidx.compose.material3.rememberStandardBottomSheetState (BottomSheetScaffold.kt:215)");
        }
        SheetState sheetStateM2923rememberSheetStateAGcomas = SheetDefaultsKt.m2923rememberSheetStateAGcomas(false, function12, initialValue2, skipHiddenState2, 0.0f, 0.0f, $composer, ($changed & 112) | (($changed << 6) & 896) | (($changed << 3) & 7168), 49);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        ComposerKt.sourceInformationMarkerEnd($composer);
        return sheetStateM2923rememberSheetStateAGcomas;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Removed duplicated region for block: B:112:0x01b4  */
    /* JADX WARN: Removed duplicated region for block: B:113:0x01c9  */
    /* JADX WARN: Removed duplicated region for block: B:116:0x01fd  */
    /* JADX WARN: Removed duplicated region for block: B:124:0x0255  */
    /* JADX WARN: Removed duplicated region for block: B:127:0x028d  */
    /* JADX WARN: Removed duplicated region for block: B:128:0x028f  */
    /* JADX WARN: Removed duplicated region for block: B:131:0x02a1  */
    /* JADX WARN: Removed duplicated region for block: B:135:0x02ad  */
    /* JADX WARN: Removed duplicated region for block: B:139:0x0342  */
    /* JADX INFO: renamed from: StandardBottomSheet-w7I5h1o, reason: not valid java name */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static final void m2198StandardBottomSheetw7I5h1o(final androidx.compose.material3.SheetState r30, final float r31, final float r32, final boolean r33, final androidx.compose.ui.graphics.Shape r34, final long r35, final long r37, final float r39, final float r40, final kotlin.jvm.functions.Function2<? super androidx.compose.runtime.Composer, ? super java.lang.Integer, kotlin.Unit> r41, final kotlin.jvm.functions.Function3<? super androidx.compose.foundation.layout.ColumnScope, ? super androidx.compose.runtime.Composer, ? super java.lang.Integer, kotlin.Unit> r42, androidx.compose.runtime.Composer r43, final int r44, final int r45) {
        /*
            Method dump skipped, instruction units count: 891
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.material3.BottomSheetScaffoldKt.m2198StandardBottomSheetw7I5h1o(androidx.compose.material3.SheetState, float, float, boolean, androidx.compose.ui.graphics.Shape, long, long, float, float, kotlin.jvm.functions.Function2, kotlin.jvm.functions.Function3, androidx.compose.runtime.Composer, int, int):void");
    }

    static final Unit StandardBottomSheet_w7I5h1o$lambda$7$lambda$6(SheetState $state, FiniteAnimationSpec $showMotion, FiniteAnimationSpec $hideMotion, FiniteAnimationSpec $anchoredDraggableMotion) {
        $state.setShowMotionSpec$material3($showMotion);
        $state.setHideMotionSpec$material3($hideMotion);
        $state.setAnchoredDraggableMotionSpec$material3($anchoredDraggableMotion);
        return Unit.INSTANCE;
    }

    static final Unit StandardBottomSheet_w7I5h1o$lambda$10$lambda$9(CoroutineScope $scope, SheetState $state, float it) {
        BuildersKt__Builders_commonKt.launch$default($scope, null, null, new BottomSheetScaffoldKt$StandardBottomSheet$nestedScroll$1$1$1($state, it, null), 3, null);
        return Unit.INSTANCE;
    }

    static final Pair StandardBottomSheet_w7I5h1o$lambda$13$lambda$12(final SheetState $state, final float $peekHeightPx, IntSize sheetSize, Constraints constraints) {
        final float layoutHeight = Constraints.m8102getMaxHeightimpl(constraints.getValue());
        long arg0$iv = sheetSize.m8325unboximpl();
        final float sheetHeight = (int) (4294967295L & arg0$iv);
        DraggableAnchors newAnchors = AnchoredDraggableKt.DraggableAnchors(new Function1() { // from class: androidx.compose.material3.BottomSheetScaffoldKt$$ExternalSyntheticLambda8
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return BottomSheetScaffoldKt.StandardBottomSheet_w7I5h1o$lambda$13$lambda$12$lambda$11($state, layoutHeight, $peekHeightPx, sheetHeight, (DraggableAnchorsConfig) obj);
            }
        });
        SheetValue oldTarget = $state.getAnchoredDraggableState$material3().getTargetValue();
        switch (WhenMappings.$EnumSwitchMapping$0[oldTarget.ordinal()]) {
            case 1:
                if (newAnchors.hasAnchorFor(SheetValue.Hidden)) {
                    oldTarget = SheetValue.Hidden;
                }
                break;
            case 2:
                if (newAnchors.hasAnchorFor(SheetValue.PartiallyExpanded)) {
                    oldTarget = SheetValue.PartiallyExpanded;
                } else if (newAnchors.hasAnchorFor(SheetValue.Expanded)) {
                    oldTarget = SheetValue.Expanded;
                } else if (newAnchors.hasAnchorFor(SheetValue.Hidden)) {
                    oldTarget = SheetValue.Hidden;
                }
                break;
            case 3:
                if (newAnchors.hasAnchorFor(SheetValue.Expanded)) {
                    oldTarget = SheetValue.Expanded;
                } else if (newAnchors.hasAnchorFor(SheetValue.PartiallyExpanded)) {
                    oldTarget = SheetValue.PartiallyExpanded;
                } else if (newAnchors.hasAnchorFor(SheetValue.Hidden)) {
                    oldTarget = SheetValue.Hidden;
                }
                break;
            default:
                throw new NoWhenBranchMatchedException();
        }
        return TuplesKt.to(newAnchors, oldTarget);
    }

    static final Unit StandardBottomSheet_w7I5h1o$lambda$13$lambda$12$lambda$11(SheetState $state, float $layoutHeight, float $peekHeightPx, float $sheetHeight, DraggableAnchorsConfig $this$DraggableAnchors) {
        if (!$state.getSkipPartiallyExpanded()) {
            $this$DraggableAnchors.at(SheetValue.PartiallyExpanded, $layoutHeight - $peekHeightPx);
        }
        if (!($sheetHeight == $peekHeightPx)) {
            $this$DraggableAnchors.at(SheetValue.Expanded, Math.max($layoutHeight - $sheetHeight, 0.0f));
        }
        if (!$state.getSkipHiddenState()) {
            $this$DraggableAnchors.at(SheetValue.Hidden, $layoutHeight);
        }
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Removed duplicated region for block: B:105:0x023e  */
    /* JADX WARN: Removed duplicated region for block: B:83:0x0148  */
    /* JADX WARN: Removed duplicated region for block: B:87:0x0152  */
    /* JADX WARN: Removed duplicated region for block: B:90:0x01a3  */
    /* JADX WARN: Removed duplicated region for block: B:93:0x01af  */
    /* JADX WARN: Removed duplicated region for block: B:94:0x01b5  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static final void BottomSheetScaffoldLayout(final kotlin.jvm.functions.Function2<? super androidx.compose.runtime.Composer, ? super java.lang.Integer, kotlin.Unit> r28, final kotlin.jvm.functions.Function2<? super androidx.compose.runtime.Composer, ? super java.lang.Integer, kotlin.Unit> r29, final kotlin.jvm.functions.Function2<? super androidx.compose.runtime.Composer, ? super java.lang.Integer, kotlin.Unit> r30, final kotlin.jvm.functions.Function2<? super androidx.compose.runtime.Composer, ? super java.lang.Integer, kotlin.Unit> r31, final kotlin.jvm.functions.Function0<java.lang.Float> r32, final androidx.compose.material3.SheetState r33, androidx.compose.runtime.Composer r34, final int r35) {
        /*
            Method dump skipped, instruction units count: 604
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.material3.BottomSheetScaffoldKt.BottomSheetScaffoldLayout(kotlin.jvm.functions.Function2, kotlin.jvm.functions.Function2, kotlin.jvm.functions.Function2, kotlin.jvm.functions.Function2, kotlin.jvm.functions.Function0, androidx.compose.material3.SheetState, androidx.compose.runtime.Composer, int):void");
    }

    public static final Modifier verticalScaleUp(Modifier $this$verticalScaleUp, final SheetState state) {
        return GraphicsLayerModifierKt.graphicsLayer($this$verticalScaleUp, new Function1() { // from class: androidx.compose.material3.BottomSheetScaffoldKt$$ExternalSyntheticLambda5
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return BottomSheetScaffoldKt.verticalScaleUp$lambda$17(state, (GraphicsLayerScope) obj);
            }
        });
    }

    static final Unit verticalScaleUp$lambda$17(SheetState $state, GraphicsLayerScope $this$graphicsLayer) {
        float fIntBitsToFloat;
        float offset = $state.getAnchoredDraggableState$material3().getOffset();
        float anchor = $state.getAnchoredDraggableState$material3().getAnchors().minAnchor();
        float overflow = offset < anchor ? anchor - offset : 0.0f;
        if (overflow > 0.0f) {
            long arg0$iv = $this$graphicsLayer.getSize();
            int bits$iv$iv$iv = (int) (arg0$iv & 4294967295L);
            float fIntBitsToFloat2 = Float.intBitsToFloat(bits$iv$iv$iv) + overflow;
            long arg0$iv2 = $this$graphicsLayer.getSize();
            int bits$iv$iv$iv2 = (int) (4294967295L & arg0$iv2);
            fIntBitsToFloat = fIntBitsToFloat2 / Float.intBitsToFloat(bits$iv$iv$iv2);
        } else {
            fIntBitsToFloat = 1.0f;
        }
        $this$graphicsLayer.setScaleY(fIntBitsToFloat);
        $this$graphicsLayer.mo5514setTransformOrigin__ExYCQ(TransformOriginKt.TransformOrigin(0.5f, 0.0f));
        return Unit.INSTANCE;
    }

    public static final Modifier verticalScaleDown(Modifier $this$verticalScaleDown, final SheetState state) {
        return GraphicsLayerModifierKt.graphicsLayer($this$verticalScaleDown, new Function1() { // from class: androidx.compose.material3.BottomSheetScaffoldKt$$ExternalSyntheticLambda0
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return BottomSheetScaffoldKt.verticalScaleDown$lambda$18(state, (GraphicsLayerScope) obj);
            }
        });
    }

    static final Unit verticalScaleDown$lambda$18(SheetState $state, GraphicsLayerScope $this$graphicsLayer) {
        float offset = $state.getAnchoredDraggableState$material3().getOffset();
        float anchor = $state.getAnchoredDraggableState$material3().getAnchors().minAnchor();
        float overflow = offset < anchor ? anchor - offset : 0.0f;
        float fIntBitsToFloat = 1.0f;
        if (overflow > 0.0f) {
            long arg0$iv = $this$graphicsLayer.getSize();
            int bits$iv$iv$iv = (int) (arg0$iv & 4294967295L);
            float fIntBitsToFloat2 = Float.intBitsToFloat(bits$iv$iv$iv) + overflow;
            long arg0$iv2 = $this$graphicsLayer.getSize();
            int bits$iv$iv$iv2 = (int) (4294967295L & arg0$iv2);
            fIntBitsToFloat = 1.0f / (fIntBitsToFloat2 / Float.intBitsToFloat(bits$iv$iv$iv2));
        }
        $this$graphicsLayer.setScaleY(fIntBitsToFloat);
        $this$graphicsLayer.mo5514setTransformOrigin__ExYCQ(TransformOriginKt.TransformOrigin(0.5f, 0.0f));
        return Unit.INSTANCE;
    }
}
