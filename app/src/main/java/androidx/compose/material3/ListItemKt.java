package androidx.compose.material3;

import androidx.compose.foundation.layout.BoxKt;
import androidx.compose.foundation.layout.BoxScopeInstance;
import androidx.compose.foundation.layout.PaddingKt;
import androidx.compose.material3.internal.LayoutUtilKt;
import androidx.compose.material3.internal.ProvideContentColorTextStyleKt;
import androidx.compose.material3.tokens.ListTokens;
import androidx.compose.material3.tokens.TypographyKeyTokens;
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
import androidx.compose.ui.layout.IntrinsicMeasureScope;
import androidx.compose.ui.layout.LayoutKt;
import androidx.compose.ui.layout.MeasurePolicy;
import androidx.compose.ui.layout.MeasureResult;
import androidx.compose.ui.layout.MeasureScope;
import androidx.compose.ui.layout.MultiContentMeasurePolicyKt;
import androidx.compose.ui.layout.Placeable;
import androidx.compose.ui.node.ComposeUiNode;
import androidx.compose.ui.semantics.SemanticsModifierKt;
import androidx.compose.ui.unit.Constraints;
import androidx.compose.ui.unit.Density;
import androidx.compose.ui.unit.Dp;
import androidx.compose.ui.unit.TextUnitKt;
import androidx.constraintlayout.core.motion.utils.TypedValues;
import androidx.profileinstaller.ProfileVerifier;
import java.util.List;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.ranges.RangesKt;

/* JADX INFO: compiled from: ListItem.kt */
/* JADX INFO: loaded from: classes12.dex */
@Metadata(d1 = {"\u0000v\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u000b\n\u0002\u0010\b\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0019\n\u0002\u0018\u0002\n\u0002\b\u0004\u001a¦\u0001\u0010\u0000\u001a\u00020\u00012\u0011\u0010\u0002\u001a\r\u0012\u0004\u0012\u00020\u00010\u0003¢\u0006\u0002\b\u00042\b\b\u0002\u0010\u0005\u001a\u00020\u00062\u0015\b\u0002\u0010\u0007\u001a\u000f\u0012\u0004\u0012\u00020\u0001\u0018\u00010\u0003¢\u0006\u0002\b\u00042\u0015\b\u0002\u0010\b\u001a\u000f\u0012\u0004\u0012\u00020\u0001\u0018\u00010\u0003¢\u0006\u0002\b\u00042\u0015\b\u0002\u0010\t\u001a\u000f\u0012\u0004\u0012\u00020\u0001\u0018\u00010\u0003¢\u0006\u0002\b\u00042\u0015\b\u0002\u0010\n\u001a\u000f\u0012\u0004\u0012\u00020\u0001\u0018\u00010\u0003¢\u0006\u0002\b\u00042\b\b\u0002\u0010\u000b\u001a\u00020\f2\b\b\u0002\u0010\r\u001a\u00020\u000e2\b\b\u0002\u0010\u000f\u001a\u00020\u000eH\u0007¢\u0006\u0004\b\u0010\u0010\u0011\u001at\u0010\u0012\u001a\u00020\u00012\u0013\u0010\u0013\u001a\u000f\u0012\u0004\u0012\u00020\u0001\u0018\u00010\u0003¢\u0006\u0002\b\u00042\u0013\u0010\u0014\u001a\u000f\u0012\u0004\u0012\u00020\u0001\u0018\u00010\u0003¢\u0006\u0002\b\u00042\u0011\u0010\u0015\u001a\r\u0012\u0004\u0012\u00020\u00010\u0003¢\u0006\u0002\b\u00042\u0013\u0010\u0016\u001a\u000f\u0012\u0004\u0012\u00020\u0001\u0018\u00010\u0003¢\u0006\u0002\b\u00042\u0013\u0010\u0017\u001a\u000f\u0012\u0004\u0012\u00020\u0001\u0018\u00010\u0003¢\u0006\u0002\b\u0004H\u0003¢\u0006\u0002\u0010\u0018\u001aK\u0010\u0019\u001a\u00020\u001a*\u00020\u001b2\u0006\u0010\u001c\u001a\u00020\u001a2\u0006\u0010\u001d\u001a\u00020\u001a2\u0006\u0010\u001e\u001a\u00020\u001a2\u0006\u0010\u001f\u001a\u00020\u001a2\u0006\u0010 \u001a\u00020\u001a2\u0006\u0010!\u001a\u00020\u001a2\u0006\u0010\"\u001a\u00020#H\u0002¢\u0006\u0004\b$\u0010%\u001aS\u0010&\u001a\u00020\u001a*\u00020\u001b2\u0006\u0010'\u001a\u00020\u001a2\u0006\u0010(\u001a\u00020\u001a2\u0006\u0010)\u001a\u00020\u001a2\u0006\u0010*\u001a\u00020\u001a2\u0006\u0010+\u001a\u00020\u001a2\u0006\u0010,\u001a\u00020-2\u0006\u0010.\u001a\u00020\u001a2\u0006\u0010\"\u001a\u00020#H\u0002¢\u0006\u0004\b/\u00100\u001an\u00101\u001a\u000202*\u0002032\u0006\u00104\u001a\u00020\u001a2\u0006\u00105\u001a\u00020\u001a2\b\u00106\u001a\u0004\u0018\u0001072\b\u00108\u001a\u0004\u0018\u0001072\b\u00109\u001a\u0004\u0018\u0001072\b\u0010:\u001a\u0004\u0018\u0001072\b\u0010;\u001a\u0004\u0018\u0001072\u0006\u0010<\u001a\u00020=2\u0006\u0010>\u001a\u00020\u001a2\u0006\u0010?\u001a\u00020\u001a2\u0006\u0010@\u001a\u00020\u001aH\u0002\u001a2\u0010A\u001a\u00020\u00012\u0006\u0010B\u001a\u00020C2\u0006\u0010D\u001a\u00020E2\u0011\u0010F\u001a\r\u0012\u0004\u0012\u00020\u00010\u0003¢\u0006\u0002\b\u0004H\u0003¢\u0006\u0004\bG\u0010H\u001a\u0014\u0010^\u001a\u00020=*\u00020_2\u0006\u0010`\u001a\u00020\u001aH\u0002\u001a\u0017\u0010.\u001a\u00020\u000e2\u0006\u0010,\u001a\u00020-H\u0002¢\u0006\u0004\ba\u0010b\"\u001e\u0010I\u001a\u00020\u000e8\u0000X\u0081\u0004¢\u0006\u0010\n\u0002\u0010N\u0012\u0004\bJ\u0010K\u001a\u0004\bL\u0010M\"\u001e\u0010O\u001a\u00020\u000e8\u0000X\u0081\u0004¢\u0006\u0010\n\u0002\u0010N\u0012\u0004\bP\u0010K\u001a\u0004\bQ\u0010M\"\u001e\u0010R\u001a\u00020\u000e8\u0000X\u0081\u0004¢\u0006\u0010\n\u0002\u0010N\u0012\u0004\bS\u0010K\u001a\u0004\bT\u0010M\"\u001e\u0010U\u001a\u00020\u000e8\u0000X\u0081\u0004¢\u0006\u0010\n\u0002\u0010N\u0012\u0004\bV\u0010K\u001a\u0004\bW\u0010M\"\u001e\u0010X\u001a\u00020\u000e8\u0000X\u0081\u0004¢\u0006\u0010\n\u0002\u0010N\u0012\u0004\bY\u0010K\u001a\u0004\bZ\u0010M\"\u001e\u0010[\u001a\u00020\u000e8\u0000X\u0081\u0004¢\u0006\u0010\n\u0002\u0010N\u0012\u0004\b\\\u0010K\u001a\u0004\b]\u0010M¨\u0006c"}, d2 = {"ListItem", "", "headlineContent", "Lkotlin/Function0;", "Landroidx/compose/runtime/Composable;", "modifier", "Landroidx/compose/ui/Modifier;", "overlineContent", "supportingContent", "leadingContent", "trailingContent", "colors", "Landroidx/compose/material3/ListItemColors;", "tonalElevation", "Landroidx/compose/ui/unit/Dp;", "shadowElevation", "ListItem-HXNGIdc", "(Lkotlin/jvm/functions/Function2;Landroidx/compose/ui/Modifier;Lkotlin/jvm/functions/Function2;Lkotlin/jvm/functions/Function2;Lkotlin/jvm/functions/Function2;Lkotlin/jvm/functions/Function2;Landroidx/compose/material3/ListItemColors;FFLandroidx/compose/runtime/Composer;II)V", "ListItemLayout", "leading", "trailing", "headline", "overline", "supporting", "(Lkotlin/jvm/functions/Function2;Lkotlin/jvm/functions/Function2;Lkotlin/jvm/functions/Function2;Lkotlin/jvm/functions/Function2;Lkotlin/jvm/functions/Function2;Landroidx/compose/runtime/Composer;I)V", "calculateWidth", "", "Landroidx/compose/ui/layout/IntrinsicMeasureScope;", "leadingWidth", "trailingWidth", "headlineWidth", "overlineWidth", "supportingWidth", "horizontalPadding", "constraints", "Landroidx/compose/ui/unit/Constraints;", "calculateWidth-yeHjK3Y", "(Landroidx/compose/ui/layout/IntrinsicMeasureScope;IIIIIIJ)I", "calculateHeight", "leadingHeight", "trailingHeight", "headlineHeight", "overlineHeight", "supportingHeight", "listItemType", "Landroidx/compose/material3/ListItemType;", "verticalPadding", "calculateHeight-N4Jib3Y", "(Landroidx/compose/ui/layout/IntrinsicMeasureScope;IIIIIIIJ)I", "place", "Landroidx/compose/ui/layout/MeasureResult;", "Landroidx/compose/ui/layout/MeasureScope;", "width", "height", "leadingPlaceable", "Landroidx/compose/ui/layout/Placeable;", "trailingPlaceable", "headlinePlaceable", "overlinePlaceable", "supportingPlaceable", "isThreeLine", "", "startPadding", "endPadding", "topPadding", "ProvideTextStyleFromToken", TypedValues.Custom.S_COLOR, "Landroidx/compose/ui/graphics/Color;", "textToken", "Landroidx/compose/material3/tokens/TypographyKeyTokens;", "content", "ProvideTextStyleFromToken-3J-VO9M", "(JLandroidx/compose/material3/tokens/TypographyKeyTokens;Lkotlin/jvm/functions/Function2;Landroidx/compose/runtime/Composer;I)V", "ListItemVerticalPadding", "getListItemVerticalPadding$annotations", "()V", "getListItemVerticalPadding", "()F", "F", "ListItemThreeLineVerticalPadding", "getListItemThreeLineVerticalPadding$annotations", "getListItemThreeLineVerticalPadding", "ListItemStartPadding", "getListItemStartPadding$annotations", "getListItemStartPadding", "ListItemEndPadding", "getListItemEndPadding$annotations", "getListItemEndPadding", "LeadingContentEndPadding", "getLeadingContentEndPadding$annotations", "getLeadingContentEndPadding", "TrailingContentStartPadding", "getTrailingContentStartPadding$annotations", "getTrailingContentStartPadding", "isSupportingMultilineHeuristic", "Landroidx/compose/ui/unit/Density;", "estimatedSupportingHeight", "verticalPadding-yh95HIg", "(I)F", "material3"}, k = 2, mv = {2, 0, 0}, xi = 48)
public final class ListItemKt {
    private static final float ListItemVerticalPadding = Dp.m8150constructorimpl(8);
    private static final float ListItemThreeLineVerticalPadding = Dp.m8150constructorimpl(12);
    private static final float ListItemStartPadding = Dp.m8150constructorimpl(16);
    private static final float ListItemEndPadding = Dp.m8150constructorimpl(16);
    private static final float LeadingContentEndPadding = Dp.m8150constructorimpl(16);
    private static final float TrailingContentStartPadding = Dp.m8150constructorimpl(16);

    static final Unit ListItemLayout$lambda$8(Function2 function2, Function2 function22, Function2 function23, Function2 function24, Function2 function25, int i, Composer composer, int i2) {
        ListItemLayout(function2, function22, function23, function24, function25, composer, RecomposeScopeImplKt.updateChangedFlags(i | 1));
        return Unit.INSTANCE;
    }

    static final Unit ListItem_HXNGIdc$lambda$6(Function2 function2, Modifier modifier, Function2 function22, Function2 function23, Function2 function24, Function2 function25, ListItemColors listItemColors, float f, float f2, int i, int i2, Composer composer, int i3) {
        m2657ListItemHXNGIdc(function2, modifier, function22, function23, function24, function25, listItemColors, f, f2, composer, RecomposeScopeImplKt.updateChangedFlags(i | 1), i2);
        return Unit.INSTANCE;
    }

    static final Unit ProvideTextStyleFromToken_3J_VO9M$lambda$12(long j, TypographyKeyTokens typographyKeyTokens, Function2 function2, int i, Composer composer, int i2) {
        m2658ProvideTextStyleFromToken3JVO9M(j, typographyKeyTokens, function2, composer, RecomposeScopeImplKt.updateChangedFlags(i | 1));
        return Unit.INSTANCE;
    }

    public static /* synthetic */ void getLeadingContentEndPadding$annotations() {
    }

    public static /* synthetic */ void getListItemEndPadding$annotations() {
    }

    public static /* synthetic */ void getListItemStartPadding$annotations() {
    }

    public static /* synthetic */ void getListItemThreeLineVerticalPadding$annotations() {
    }

    public static /* synthetic */ void getListItemVerticalPadding$annotations() {
    }

    public static /* synthetic */ void getTrailingContentStartPadding$annotations() {
    }

    /* JADX INFO: renamed from: ListItem-HXNGIdc, reason: not valid java name */
    public static final void m2657ListItemHXNGIdc(final Function2<? super Composer, ? super Integer, Unit> function2, Modifier modifier, Function2<? super Composer, ? super Integer, Unit> function22, Function2<? super Composer, ? super Integer, Unit> function23, Function2<? super Composer, ? super Integer, Unit> function24, Function2<? super Composer, ? super Integer, Unit> function25, ListItemColors colors, float tonalElevation, float shadowElevation, Composer $composer, final int $changed, final int i) {
        Modifier modifier2;
        Function2<? super Composer, ? super Integer, Unit> function26;
        Function2<? super Composer, ? super Integer, Unit> function27;
        Function2<? super Composer, ? super Integer, Unit> function28;
        Function2<? super Composer, ? super Integer, Unit> function29;
        int i2;
        int i3;
        Composer $composer2;
        final float tonalElevation2;
        final Modifier modifier3;
        final Function2<? super Composer, ? super Integer, Unit> function210;
        final Function2<? super Composer, ? super Integer, Unit> function211;
        final Function2<? super Composer, ? super Integer, Unit> function212;
        final Function2<? super Composer, ? super Integer, Unit> function213;
        final ListItemColors colors2;
        final float shadowElevation2;
        ListItemColors colors3;
        int $dirty;
        float tonalElevation3;
        float shadowElevation3;
        int $dirty2;
        float tonalElevation4;
        final ListItemColors colors4;
        Function2<? super Composer, ? super Integer, Unit> function214;
        Function2<? super Composer, ? super Integer, Unit> function215;
        Function2 leadingContent;
        final Function2 decoratedSupportingContent;
        Function2 decoratedSupportingContent2;
        final Function2 decoratedOverlineContent;
        Function2 decoratedLeadingContent;
        Function2 decoratedTrailingContent;
        Composer $composer3 = $composer.startRestartGroup(487133126);
        ComposerKt.sourceInformation($composer3, "C(ListItem)N(headlineContent,modifier,overlineContent,supportingContent,leadingContent,trailingContent,colors,tonalElevation:c#ui.unit.Dp,shadowElevation:c#ui.unit.Dp)105@4827L177,157@6607L2,158@6659L5,163@6858L288,156@6535L611:ListItem.kt#uh7d8r");
        int $dirty3 = $changed;
        if ((i & 1) != 0) {
            $dirty3 |= 6;
        } else if (($changed & 6) == 0) {
            $dirty3 |= $composer3.changedInstance(function2) ? 4 : 2;
        }
        int i4 = i & 2;
        if (i4 != 0) {
            $dirty3 |= 48;
            modifier2 = modifier;
        } else if (($changed & 48) == 0) {
            modifier2 = modifier;
            $dirty3 |= $composer3.changed(modifier2) ? 32 : 16;
        } else {
            modifier2 = modifier;
        }
        int i5 = i & 4;
        if (i5 != 0) {
            $dirty3 |= 384;
            function26 = function22;
        } else if (($changed & 384) == 0) {
            function26 = function22;
            $dirty3 |= $composer3.changedInstance(function26) ? 256 : 128;
        } else {
            function26 = function22;
        }
        int i6 = i & 8;
        if (i6 != 0) {
            $dirty3 |= 3072;
            function27 = function23;
        } else if (($changed & 3072) == 0) {
            function27 = function23;
            $dirty3 |= $composer3.changedInstance(function27) ? 2048 : 1024;
        } else {
            function27 = function23;
        }
        int i7 = i & 16;
        if (i7 != 0) {
            $dirty3 |= 24576;
            function28 = function24;
        } else if (($changed & 24576) == 0) {
            function28 = function24;
            $dirty3 |= $composer3.changedInstance(function28) ? 16384 : 8192;
        } else {
            function28 = function24;
        }
        int i8 = i & 32;
        if (i8 != 0) {
            $dirty3 |= ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE;
            function29 = function25;
        } else if ((196608 & $changed) == 0) {
            function29 = function25;
            $dirty3 |= $composer3.changedInstance(function29) ? 131072 : 65536;
        } else {
            function29 = function25;
        }
        if (($changed & 1572864) == 0) {
            $dirty3 |= ((i & 64) == 0 && $composer3.changed(colors)) ? 1048576 : 524288;
        }
        int i9 = i & 128;
        if (i9 != 0) {
            $dirty3 |= 12582912;
            i2 = i9;
        } else if (($changed & 12582912) == 0) {
            i2 = i9;
            $dirty3 |= $composer3.changed(tonalElevation) ? 8388608 : 4194304;
        } else {
            i2 = i9;
        }
        int i10 = i & 256;
        if (i10 != 0) {
            $dirty3 |= 100663296;
            i3 = i10;
        } else if (($changed & 100663296) == 0) {
            i3 = i10;
            $dirty3 |= $composer3.changed(shadowElevation) ? 67108864 : GroupFlagsKt.HasAuxSlotFlag;
        } else {
            i3 = i10;
        }
        int $dirty4 = $dirty3;
        if ($composer3.shouldExecute(($dirty3 & 38347923) != 38347922, $dirty4 & 1)) {
            $composer3.startDefaults();
            ComposerKt.sourceInformation($composer3, "101@4647L8");
            if (($changed & 1) != 0 && !$composer3.getDefaultsInvalid()) {
                $composer3.skipToGroupEnd();
                if ((i & 64) != 0) {
                    colors4 = colors;
                    tonalElevation4 = tonalElevation;
                    shadowElevation3 = shadowElevation;
                    $dirty2 = $dirty4 & (-3670017);
                    function214 = function28;
                    function213 = function29;
                } else {
                    colors4 = colors;
                    tonalElevation4 = tonalElevation;
                    shadowElevation3 = shadowElevation;
                    $dirty2 = $dirty4;
                    function214 = function28;
                    function213 = function29;
                }
            } else {
                if (i4 != 0) {
                    modifier2 = Modifier.INSTANCE;
                }
                if (i5 != 0) {
                    function26 = null;
                }
                if (i6 != 0) {
                    function27 = null;
                }
                if (i7 != 0) {
                    function28 = null;
                }
                if (i8 != 0) {
                    function29 = null;
                }
                if ((i & 64) == 0) {
                    colors3 = colors;
                    $dirty = $dirty4;
                } else {
                    colors3 = ListItemDefaults.INSTANCE.colors($composer3, 6);
                    $dirty = $dirty4 & (-3670017);
                }
                if (i2 == 0) {
                    tonalElevation3 = tonalElevation;
                } else {
                    tonalElevation3 = ListItemDefaults.INSTANCE.m2656getElevationD9Ej5fM();
                }
                if (i3 == 0) {
                    shadowElevation3 = shadowElevation;
                    $dirty2 = $dirty;
                    tonalElevation4 = tonalElevation3;
                    function213 = function29;
                    colors4 = colors3;
                    function214 = function28;
                } else {
                    tonalElevation4 = tonalElevation3;
                    shadowElevation3 = ListItemDefaults.INSTANCE.m2656getElevationD9Ej5fM();
                    colors4 = colors3;
                    $dirty2 = $dirty;
                    function214 = function28;
                    function213 = function29;
                }
            }
            $composer3.endDefaults();
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(487133126, $dirty2, -1, "androidx.compose.material3.ListItem (ListItem.kt:104)");
            }
            final Function2 decoratedHeadlineContent = ComposableLambdaKt.rememberComposableLambda(629852750, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.ListItemKt$ListItem$decoratedHeadlineContent$1
                @Override // kotlin.jvm.functions.Function2
                public /* bridge */ /* synthetic */ Unit invoke(Composer composer, Integer num) {
                    invoke(composer, num.intValue());
                    return Unit.INSTANCE;
                }

                public final void invoke(Composer $composer4, int $changed2) {
                    ComposerKt.sourceInformation($composer4, "C106@4837L161:ListItem.kt#uh7d8r");
                    if ($composer4.shouldExecute(($changed2 & 3) != 2, $changed2 & 1)) {
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventStart(629852750, $changed2, -1, "androidx.compose.material3.ListItem.<anonymous> (ListItem.kt:106)");
                        }
                        ListItemKt.m2658ProvideTextStyleFromToken3JVO9M(colors4.m2650headlineColorvNxB06k$material3(true), ListTokens.INSTANCE.getListItemLabelTextFont(), function2, $composer4, 48);
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventEnd();
                            return;
                        }
                        return;
                    }
                    $composer4.skipToGroupEnd();
                }
            }, $composer3, 54);
            if (function27 == null) {
                $composer3.startReplaceGroup(-510713870);
                $composer3.endReplaceGroup();
                function215 = function214;
                leadingContent = null;
            } else {
                $composer3.startReplaceGroup(-510713869);
                ComposerKt.sourceInformation($composer3, "*114@5114L205");
                final Function2<? super Composer, ? super Integer, Unit> function216 = function27;
                function215 = function214;
                leadingContent = ComposableLambdaKt.rememberComposableLambda(-1291211644, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.ListItemKt$ListItem$decoratedSupportingContent$1$1
                    @Override // kotlin.jvm.functions.Function2
                    public /* bridge */ /* synthetic */ Unit invoke(Composer composer, Integer num) {
                        invoke(composer, num.intValue());
                        return Unit.INSTANCE;
                    }

                    public final void invoke(Composer $composer4, int $changed2) {
                        ComposerKt.sourceInformation($composer4, "C115@5132L173:ListItem.kt#uh7d8r");
                        if ($composer4.shouldExecute(($changed2 & 3) != 2, $changed2 & 1)) {
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventStart(-1291211644, $changed2, -1, "androidx.compose.material3.ListItem.<anonymous>.<anonymous> (ListItem.kt:115)");
                            }
                            ListItemKt.m2658ProvideTextStyleFromToken3JVO9M(colors4.m2653supportingColor0d7_KjU$material3(), ListTokens.INSTANCE.getListItemSupportingTextFont(), function216, $composer4, 48);
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventEnd();
                                return;
                            }
                            return;
                        }
                        $composer4.skipToGroupEnd();
                    }
                }, $composer3, 54);
                $composer3.endReplaceGroup();
            }
            if (function26 == null) {
                $composer3.startReplaceGroup(-510395686);
                $composer3.endReplaceGroup();
                decoratedSupportingContent = leadingContent;
                decoratedSupportingContent2 = null;
            } else {
                $composer3.startReplaceGroup(-510395685);
                ComposerKt.sourceInformation($composer3, "*124@5435L197");
                final Function2<? super Composer, ? super Integer, Unit> function217 = function26;
                decoratedSupportingContent = leadingContent;
                decoratedSupportingContent2 = ComposableLambdaKt.rememberComposableLambda(372414991, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.ListItemKt$ListItem$decoratedOverlineContent$1$1
                    @Override // kotlin.jvm.functions.Function2
                    public /* bridge */ /* synthetic */ Unit invoke(Composer composer, Integer num) {
                        invoke(composer, num.intValue());
                        return Unit.INSTANCE;
                    }

                    public final void invoke(Composer $composer4, int $changed2) {
                        ComposerKt.sourceInformation($composer4, "C125@5453L165:ListItem.kt#uh7d8r");
                        if ($composer4.shouldExecute(($changed2 & 3) != 2, $changed2 & 1)) {
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventStart(372414991, $changed2, -1, "androidx.compose.material3.ListItem.<anonymous>.<anonymous> (ListItem.kt:125)");
                            }
                            ListItemKt.m2658ProvideTextStyleFromToken3JVO9M(colors4.m2652overlineColor0d7_KjU$material3(), ListTokens.INSTANCE.getListItemOverlineFont(), function217, $composer4, 48);
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventEnd();
                                return;
                            }
                            return;
                        }
                        $composer4.skipToGroupEnd();
                    }
                }, $composer3, 54);
                $composer3.endReplaceGroup();
            }
            if (function215 == null) {
                $composer3.startReplaceGroup(-510083888);
                $composer3.endReplaceGroup();
                decoratedOverlineContent = decoratedSupportingContent2;
                decoratedLeadingContent = null;
            } else {
                $composer3.startReplaceGroup(-510083887);
                ComposerKt.sourceInformation($composer3, "*134@5746L303");
                final Function2<? super Composer, ? super Integer, Unit> function218 = function215;
                decoratedOverlineContent = decoratedSupportingContent2;
                decoratedLeadingContent = ComposableLambdaKt.rememberComposableLambda(449548451, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.ListItemKt$ListItem$decoratedLeadingContent$1$1
                    @Override // kotlin.jvm.functions.Function2
                    public /* bridge */ /* synthetic */ Unit invoke(Composer composer, Integer num) {
                        invoke(composer, num.intValue());
                        return Unit.INSTANCE;
                    }

                    /* JADX WARN: Removed duplicated region for block: B:28:0x016f  */
                    /* JADX WARN: Removed duplicated region for block: B:31:? A[RETURN, SYNTHETIC] */
                    /*
                        Code decompiled incorrectly, please refer to instructions dump.
                        To view partially-correct code enable 'Show inconsistent code' option in preferences
                    */
                    public final void invoke(androidx.compose.runtime.Composer r30, int r31) {
                        /*
                            Method dump skipped, instruction units count: 375
                            To view this dump change 'Code comments level' option to 'DEBUG'
                        */
                        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.material3.ListItemKt$ListItem$decoratedLeadingContent$1$1.invoke(androidx.compose.runtime.Composer, int):void");
                    }
                }, $composer3, 54);
                $composer3.endReplaceGroup();
            }
            if (function213 == null) {
                $composer3.startReplaceGroup(-509666659);
                $composer3.endReplaceGroup();
                decoratedTrailingContent = null;
            } else {
                $composer3.startReplaceGroup(-509666658);
                ComposerKt.sourceInformation($composer3, "*145@6165L354");
                final Function2<? super Composer, ? super Integer, Unit> function219 = function213;
                decoratedTrailingContent = ComposableLambdaKt.rememberComposableLambda(1946411067, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.ListItemKt$ListItem$decoratedTrailingContent$1$1
                    @Override // kotlin.jvm.functions.Function2
                    public /* bridge */ /* synthetic */ Unit invoke(Composer composer, Integer num) {
                        invoke(composer, num.intValue());
                        return Unit.INSTANCE;
                    }

                    public final void invoke(Composer $composer4, int $changed2) {
                        Function0<ComposeUiNode> function0;
                        ComposerKt.sourceInformation($composer4, "C146@6183L322:ListItem.kt#uh7d8r");
                        if (!$composer4.shouldExecute(($changed2 & 3) != 2, $changed2 & 1)) {
                            $composer4.skipToGroupEnd();
                            return;
                        }
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventStart(1946411067, $changed2, -1, "androidx.compose.material3.ListItem.<anonymous>.<anonymous> (ListItem.kt:146)");
                        }
                        Modifier modifier$iv = PaddingKt.m1052paddingqDBjuR0$default(Modifier.INSTANCE, ListItemKt.getTrailingContentStartPadding(), 0.0f, 0.0f, 0.0f, 14, null);
                        ListItemColors listItemColors = colors4;
                        Function2<Composer, Integer, Unit> function220 = function219;
                        ComposerKt.sourceInformationMarkerStart($composer4, 733328855, "CC(Box)P(2,1,3)71@3423L130:Box.kt#2w3rfo");
                        Alignment contentAlignment$iv = Alignment.INSTANCE.getTopStart();
                        MeasurePolicy measurePolicy$iv = BoxKt.maybeCachedBoxMeasurePolicy(contentAlignment$iv, false);
                        int $changed$iv$iv = (6 << 3) & 112;
                        ComposerKt.sourceInformationMarkerStart($composer4, -1323940314, "CC(Layout)P(!1,2)79@3206L23,82@3357L359:Layout.kt#80mrfh");
                        int compositeKeyHash$iv$iv = ComposablesKt.getCurrentCompositeKeyHash($composer4, 0);
                        CompositionLocalMap localMap$iv$iv = $composer4.getCurrentCompositionLocalMap();
                        Modifier materialized$iv$iv = ComposedModifierKt.materializeModifier($composer4, modifier$iv);
                        Function0<ComposeUiNode> constructor = ComposeUiNode.INSTANCE.getConstructor();
                        int $changed$iv$iv$iv = (($changed$iv$iv << 6) & 896) | 6;
                        ComposerKt.sourceInformationMarkerStart($composer4, -692256719, "CC(ReusableComposeNode)P(1,2)355@14017L9:Composables.kt#9igjgp");
                        if (!($composer4.getApplier() instanceof Applier)) {
                            ComposablesKt.invalidApplier();
                        }
                        $composer4.startReusableNode();
                        if ($composer4.getInserting()) {
                            function0 = constructor;
                            $composer4.createNode(function0);
                        } else {
                            function0 = constructor;
                            $composer4.useNode();
                        }
                        Composer $this$Layout_u24lambda_u240$iv$iv = Updater.m4433constructorimpl($composer4);
                        Updater.m4441setimpl($this$Layout_u24lambda_u240$iv$iv, measurePolicy$iv, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
                        Updater.m4441setimpl($this$Layout_u24lambda_u240$iv$iv, localMap$iv$iv, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
                        Function2<ComposeUiNode, Integer, Unit> setCompositeKeyHash = ComposeUiNode.INSTANCE.getSetCompositeKeyHash();
                        if ($this$Layout_u24lambda_u240$iv$iv.getInserting() || !Intrinsics.areEqual($this$Layout_u24lambda_u240$iv$iv.rememberedValue(), Integer.valueOf(compositeKeyHash$iv$iv))) {
                            $this$Layout_u24lambda_u240$iv$iv.updateRememberedValue(Integer.valueOf(compositeKeyHash$iv$iv));
                            $this$Layout_u24lambda_u240$iv$iv.apply(Integer.valueOf(compositeKeyHash$iv$iv), setCompositeKeyHash);
                        }
                        Updater.m4441setimpl($this$Layout_u24lambda_u240$iv$iv, materialized$iv$iv, ComposeUiNode.INSTANCE.getSetModifier());
                        int i11 = ($changed$iv$iv$iv >> 6) & 14;
                        ComposerKt.sourceInformationMarkerStart($composer4, -2146730711, "C72@3468L9:Box.kt#2w3rfo");
                        BoxScopeInstance boxScopeInstance = BoxScopeInstance.INSTANCE;
                        int i12 = ((6 >> 6) & 112) | 6;
                        ComposerKt.sourceInformationMarkerStart($composer4, -869008925, "C147@6264L223:ListItem.kt#uh7d8r");
                        ListItemKt.m2658ProvideTextStyleFromToken3JVO9M(listItemColors.m2654trailingIconColorvNxB06k$material3(true), ListTokens.INSTANCE.getListItemTrailingSupportingTextFont(), function220, $composer4, 48);
                        ComposerKt.sourceInformationMarkerEnd($composer4);
                        ComposerKt.sourceInformationMarkerEnd($composer4);
                        $composer4.endNode();
                        ComposerKt.sourceInformationMarkerEnd($composer4);
                        ComposerKt.sourceInformationMarkerEnd($composer4);
                        ComposerKt.sourceInformationMarkerEnd($composer4);
                        boolean propagateMinConstraints$iv = ComposerKt.isTraceInProgress();
                        if (propagateMinConstraints$iv) {
                            ComposerKt.traceEventEnd();
                        }
                    }
                }, $composer3, 54);
                $composer3.endReplaceGroup();
            }
            Modifier.Companion companion = Modifier.INSTANCE;
            ComposerKt.sourceInformationMarkerStart($composer3, -16426520, "CC(remember):ListItem.kt#9igjgp");
            Object it$iv = $composer3.rememberedValue();
            if (it$iv == Composer.INSTANCE.getEmpty()) {
                Object value$iv = new Function1() { // from class: androidx.compose.material3.ListItemKt$$ExternalSyntheticLambda2
                    @Override // kotlin.jvm.functions.Function1
                    public final Object invoke(Object obj) {
                        return Unit.INSTANCE;
                    }
                };
                $composer3.updateRememberedValue(value$iv);
                it$iv = value$iv;
            }
            ComposerKt.sourceInformationMarkerEnd($composer3);
            final Function2 decoratedTrailingContent2 = decoratedTrailingContent;
            final Function2 decoratedLeadingContent2 = decoratedLeadingContent;
            $composer2 = $composer3;
            SurfaceKt.m3014SurfaceT9BRK9s(SemanticsModifierKt.semantics(companion, true, (Function1) it$iv).then(modifier2), ListItemDefaults.INSTANCE.getShape($composer3, 6), colors4.getContainerColor(), colors4.m2650headlineColorvNxB06k$material3(true), tonalElevation4, shadowElevation3, null, ComposableLambdaKt.rememberComposableLambda(1192488737, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.ListItemKt$ListItem$2
                @Override // kotlin.jvm.functions.Function2
                public /* bridge */ /* synthetic */ Unit invoke(Composer composer, Integer num) {
                    invoke(composer, num.intValue());
                    return Unit.INSTANCE;
                }

                public final void invoke(Composer $composer4, int $changed2) {
                    ComposerKt.sourceInformation($composer4, "C164@6868L272:ListItem.kt#uh7d8r");
                    if ($composer4.shouldExecute(($changed2 & 3) != 2, $changed2 & 1)) {
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventStart(1192488737, $changed2, -1, "androidx.compose.material3.ListItem.<anonymous> (ListItem.kt:164)");
                        }
                        ListItemKt.ListItemLayout(decoratedLeadingContent2, decoratedTrailingContent2, decoratedHeadlineContent, decoratedOverlineContent, decoratedSupportingContent, $composer4, 384);
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventEnd();
                            return;
                        }
                        return;
                    }
                    $composer4.skipToGroupEnd();
                }
            }, $composer3, 54), $composer2, (($dirty2 >> 9) & 57344) | 12582912 | (458752 & ($dirty2 >> 9)), 64);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
            modifier3 = modifier2;
            function210 = function26;
            colors2 = colors4;
            function211 = function27;
            tonalElevation2 = tonalElevation4;
            shadowElevation2 = shadowElevation3;
            function212 = function215;
        } else {
            $composer2 = $composer3;
            $composer2.skipToGroupEnd();
            tonalElevation2 = tonalElevation;
            modifier3 = modifier2;
            function210 = function26;
            function211 = function27;
            function212 = function28;
            function213 = function29;
            colors2 = colors;
            shadowElevation2 = shadowElevation;
        }
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup = $composer2.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: androidx.compose.material3.ListItemKt$$ExternalSyntheticLambda3
                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj, Object obj2) {
                    return ListItemKt.ListItem_HXNGIdc$lambda$6(function2, modifier3, function210, function211, function212, function213, colors2, tonalElevation2, shadowElevation2, $changed, i, (Composer) obj, ((Integer) obj2).intValue());
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void ListItemLayout(final Function2<? super Composer, ? super Integer, Unit> function2, final Function2<? super Composer, ? super Integer, Unit> function22, final Function2<? super Composer, ? super Integer, Unit> function23, final Function2<? super Composer, ? super Integer, Unit> function24, final Function2<? super Composer, ? super Integer, Unit> function25, Composer $composer, final int $changed) {
        Function0<ComposeUiNode> function0;
        Composer $composer2 = $composer.startRestartGroup(-61277522);
        ComposerKt.sourceInformation($composer2, "C(ListItemLayout)N(leading,trailing,headline,overline,supporting)182@7421L36,183@7462L166:ListItem.kt#uh7d8r");
        int $dirty = $changed;
        if (($changed & 6) == 0) {
            $dirty |= $composer2.changedInstance(function2) ? 4 : 2;
        }
        if (($changed & 48) == 0) {
            $dirty |= $composer2.changedInstance(function22) ? 32 : 16;
        }
        if (($changed & 384) == 0) {
            $dirty |= $composer2.changedInstance(function23) ? 256 : 128;
        }
        if (($changed & 3072) == 0) {
            $dirty |= $composer2.changedInstance(function24) ? 2048 : 1024;
        }
        if (($changed & 24576) == 0) {
            $dirty |= $composer2.changedInstance(function25) ? 16384 : 8192;
        }
        if (!$composer2.shouldExecute(($dirty & 9363) != 9362, $dirty & 1)) {
            $composer2.skipToGroupEnd();
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-61277522, $dirty, -1, "androidx.compose.material3.ListItemLayout (ListItem.kt:181)");
            }
            ComposerKt.sourceInformationMarkerStart($composer2, 1241852210, "CC(remember):ListItem.kt#9igjgp");
            Object it$iv = $composer2.rememberedValue();
            if (it$iv == Composer.INSTANCE.getEmpty()) {
                Object value$iv = new ListItemMeasurePolicy();
                $composer2.updateRememberedValue(value$iv);
                it$iv = value$iv;
            }
            ListItemMeasurePolicy measurePolicy = (ListItemMeasurePolicy) it$iv;
            ComposerKt.sourceInformationMarkerEnd($composer2);
            Function2[] function2Arr = new Function2[5];
            function2Arr[0] = function23;
            function2Arr[1] = function24 == null ? ComposableSingletons$ListItemKt.INSTANCE.m2382getLambda$489887388$material3() : function24;
            function2Arr[2] = function25 == null ? ComposableSingletons$ListItemKt.INSTANCE.getLambda$1629163587$material3() : function25;
            function2Arr[3] = function2 == null ? ComposableSingletons$ListItemKt.INSTANCE.m2383getLambda$546752734$material3() : function2;
            function2Arr[4] = function22 == null ? ComposableSingletons$ListItemKt.INSTANCE.getLambda$1572298241$material3() : function22;
            List contents$iv = CollectionsKt.listOf((Object[]) function2Arr);
            ComposerKt.sourceInformationMarkerStart($composer2, 1399185516, "CC(Layout)P(!1,2)168@6883L62,165@6769L182:Layout.kt#80mrfh");
            Modifier modifier$iv = Modifier.INSTANCE;
            Function2<Composer, Integer, Unit> function2CombineAsVirtualLayouts = LayoutKt.combineAsVirtualLayouts(contents$iv);
            ComposerKt.sourceInformationMarkerStart($composer2, -290764973, "CC(remember):Layout.kt#9igjgp");
            boolean invalid$iv$iv = (((384 & 896) ^ 384) > 256 && $composer2.changed(measurePolicy)) || (384 & 384) == 256;
            Object it$iv$iv = $composer2.rememberedValue();
            if (invalid$iv$iv || it$iv$iv == Composer.INSTANCE.getEmpty()) {
                Object value$iv$iv = MultiContentMeasurePolicyKt.createMeasurePolicy(measurePolicy);
                $composer2.updateRememberedValue(value$iv$iv);
                it$iv$iv = value$iv$iv;
            }
            MeasurePolicy measurePolicy$iv$iv = (MeasurePolicy) it$iv$iv;
            ComposerKt.sourceInformationMarkerEnd($composer2);
            int $changed$iv$iv = 384 & 112;
            ComposerKt.sourceInformationMarkerStart($composer2, -1323940314, "CC(Layout)P(!1,2)79@3206L23,82@3357L359:Layout.kt#80mrfh");
            int compositeKeyHash$iv$iv = ComposablesKt.getCurrentCompositeKeyHash($composer2, 0);
            CompositionLocalMap localMap$iv$iv = $composer2.getCurrentCompositionLocalMap();
            Modifier materialized$iv$iv = ComposedModifierKt.materializeModifier($composer2, modifier$iv);
            Function0<ComposeUiNode> constructor = ComposeUiNode.INSTANCE.getConstructor();
            int $changed$iv$iv$iv = (($changed$iv$iv << 6) & 896) | 6;
            ComposerKt.sourceInformationMarkerStart($composer2, -692256719, "CC(ReusableComposeNode)P(1,2)355@14017L9:Composables.kt#9igjgp");
            if (!($composer2.getApplier() instanceof Applier)) {
                ComposablesKt.invalidApplier();
            }
            $composer2.startReusableNode();
            if ($composer2.getInserting()) {
                function0 = constructor;
                $composer2.createNode(function0);
            } else {
                function0 = constructor;
                $composer2.useNode();
            }
            Composer $this$Layout_u24lambda_u240$iv$iv = Updater.m4433constructorimpl($composer2);
            Updater.m4441setimpl($this$Layout_u24lambda_u240$iv$iv, measurePolicy$iv$iv, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
            Updater.m4441setimpl($this$Layout_u24lambda_u240$iv$iv, localMap$iv$iv, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
            Function2<ComposeUiNode, Integer, Unit> setCompositeKeyHash = ComposeUiNode.INSTANCE.getSetCompositeKeyHash();
            if ($this$Layout_u24lambda_u240$iv$iv.getInserting() || !Intrinsics.areEqual($this$Layout_u24lambda_u240$iv$iv.rememberedValue(), Integer.valueOf(compositeKeyHash$iv$iv))) {
                $this$Layout_u24lambda_u240$iv$iv.updateRememberedValue(Integer.valueOf(compositeKeyHash$iv$iv));
                $this$Layout_u24lambda_u240$iv$iv.apply(Integer.valueOf(compositeKeyHash$iv$iv), setCompositeKeyHash);
            }
            Updater.m4441setimpl($this$Layout_u24lambda_u240$iv$iv, materialized$iv$iv, ComposeUiNode.INSTANCE.getSetModifier());
            function2CombineAsVirtualLayouts.invoke($composer2, Integer.valueOf(($changed$iv$iv$iv >> 6) & 14));
            $composer2.endNode();
            ComposerKt.sourceInformationMarkerEnd($composer2);
            ComposerKt.sourceInformationMarkerEnd($composer2);
            ComposerKt.sourceInformationMarkerEnd($composer2);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup = $composer2.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: androidx.compose.material3.ListItemKt$$ExternalSyntheticLambda1
                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj, Object obj2) {
                    return ListItemKt.ListItemLayout$lambda$8(function2, function22, function23, function24, function25, $changed, (Composer) obj, ((Integer) obj2).intValue());
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: calculateWidth-yeHjK3Y, reason: not valid java name */
    public static final int m2664calculateWidthyeHjK3Y(IntrinsicMeasureScope $this$calculateWidth_u2dyeHjK3Y, int leadingWidth, int trailingWidth, int headlineWidth, int overlineWidth, int supportingWidth, int horizontalPadding, long constraints) {
        if (Constraints.m8099getHasBoundedWidthimpl(constraints)) {
            return Constraints.m8103getMaxWidthimpl(constraints);
        }
        int mainContentWidth = Math.max(headlineWidth, Math.max(overlineWidth, supportingWidth));
        return horizontalPadding + leadingWidth + mainContentWidth + trailingWidth;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: calculateHeight-N4Jib3Y, reason: not valid java name */
    public static final int m2663calculateHeightN4Jib3Y(IntrinsicMeasureScope $this$calculateHeight_u2dN4Jib3Y, int leadingHeight, int trailingHeight, int headlineHeight, int overlineHeight, int supportingHeight, int listItemType, int verticalPadding, long constraints) {
        float defaultMinHeight;
        if (ListItemType.m2670equalsimpl0(listItemType, ListItemType.INSTANCE.m2675getOneLineAlXitO8())) {
            defaultMinHeight = ListTokens.INSTANCE.m3959getListItemOneLineContainerHeightD9Ej5fM();
        } else {
            defaultMinHeight = ListItemType.m2670equalsimpl0(listItemType, ListItemType.INSTANCE.m2677getTwoLineAlXitO8()) ? ListTokens.INSTANCE.m3964getListItemTwoLineContainerHeightD9Ej5fM() : ListTokens.INSTANCE.m3961getListItemThreeLineContainerHeightD9Ej5fM();
        }
        int minHeight = Math.max(Constraints.m8104getMinHeightimpl(constraints), $this$calculateHeight_u2dN4Jib3Y.mo426roundToPx0680j_4(defaultMinHeight));
        int mainContentHeight = headlineHeight + overlineHeight + supportingHeight;
        return RangesKt.coerceAtMost(Math.max(minHeight, Math.max(leadingHeight, Math.max(mainContentHeight, trailingHeight)) + verticalPadding), Constraints.m8102getMaxHeightimpl(constraints));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final MeasureResult place(MeasureScope $this$place, final int width, final int height, final Placeable leadingPlaceable, final Placeable trailingPlaceable, final Placeable headlinePlaceable, final Placeable overlinePlaceable, final Placeable supportingPlaceable, final boolean isThreeLine, final int startPadding, final int endPadding, final int topPadding) {
        return MeasureScope.layout$default($this$place, width, height, null, new Function1() { // from class: androidx.compose.material3.ListItemKt$$ExternalSyntheticLambda0
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return ListItemKt.place$lambda$11(leadingPlaceable, startPadding, isThreeLine, topPadding, headlinePlaceable, overlinePlaceable, supportingPlaceable, height, trailingPlaceable, width, endPadding, (Placeable.PlacementScope) obj);
            }
        }, 4, null);
    }

    static final Unit place$lambda$11(Placeable $leadingPlaceable, int $startPadding, boolean $isThreeLine, int $topPadding, Placeable $headlinePlaceable, Placeable $overlinePlaceable, Placeable $supportingPlaceable, int $height, Placeable $trailingPlaceable, int $width, int $endPadding, Placeable.PlacementScope $this$layout) {
        int iAlign;
        if ($leadingPlaceable != null) {
            Placeable.PlacementScope.placeRelative$default($this$layout, $leadingPlaceable, $startPadding, $isThreeLine ? $topPadding : Alignment.INSTANCE.getCenterVertically().align($leadingPlaceable.getHeight(), $height), 0.0f, 4, null);
        }
        int mainContentX = $startPadding + LayoutUtilKt.getWidthOrZero($leadingPlaceable);
        if ($isThreeLine) {
            iAlign = $topPadding;
        } else {
            int totalHeight = LayoutUtilKt.getHeightOrZero($headlinePlaceable) + LayoutUtilKt.getHeightOrZero($overlinePlaceable) + LayoutUtilKt.getHeightOrZero($supportingPlaceable);
            iAlign = Alignment.INSTANCE.getCenterVertically().align(totalHeight, $height);
        }
        int mainContentY = iAlign;
        if ($overlinePlaceable != null) {
            Placeable.PlacementScope.placeRelative$default($this$layout, $overlinePlaceable, mainContentX, mainContentY, 0.0f, 4, null);
        }
        int currentY = mainContentY + LayoutUtilKt.getHeightOrZero($overlinePlaceable);
        if ($headlinePlaceable != null) {
            Placeable.PlacementScope.placeRelative$default($this$layout, $headlinePlaceable, mainContentX, currentY, 0.0f, 4, null);
        }
        int currentY2 = currentY + LayoutUtilKt.getHeightOrZero($headlinePlaceable);
        if ($supportingPlaceable != null) {
            Placeable.PlacementScope.placeRelative$default($this$layout, $supportingPlaceable, mainContentX, currentY2, 0.0f, 4, null);
        }
        if ($trailingPlaceable != null) {
            Placeable.PlacementScope.placeRelative$default($this$layout, $trailingPlaceable, ($width - $endPadding) - $trailingPlaceable.getWidth(), $isThreeLine ? $topPadding : Alignment.INSTANCE.getCenterVertically().align($trailingPlaceable.getHeight(), $height), 0.0f, 4, null);
        }
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: ProvideTextStyleFromToken-3J-VO9M, reason: not valid java name */
    public static final void m2658ProvideTextStyleFromToken3JVO9M(final long color, final TypographyKeyTokens textToken, final Function2<? super Composer, ? super Integer, Unit> function2, Composer $composer, final int $changed) {
        Function2<? super Composer, ? super Integer, Unit> function22;
        Composer $composer2 = $composer.startRestartGroup(-285397024);
        ComposerKt.sourceInformation($composer2, "C(ProvideTextStyleFromToken)N(color:c#ui.graphics.Color,textToken,content)705@29794L5,703@29704L129:ListItem.kt#uh7d8r");
        int $dirty = $changed;
        if (($changed & 6) == 0) {
            $dirty |= $composer2.changed(color) ? 4 : 2;
        }
        if (($changed & 48) == 0) {
            $dirty |= $composer2.changed(textToken.ordinal()) ? 32 : 16;
        }
        if (($changed & 384) == 0) {
            function22 = function2;
            $dirty |= $composer2.changedInstance(function22) ? 256 : 128;
        } else {
            function22 = function2;
        }
        int $dirty2 = $dirty;
        if (!$composer2.shouldExecute(($dirty2 & 147) != 146, $dirty2 & 1)) {
            $composer2.skipToGroupEnd();
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-285397024, $dirty2, -1, "androidx.compose.material3.ProvideTextStyleFromToken (ListItem.kt:703)");
            }
            ProvideContentColorTextStyleKt.m3452ProvideContentColorTextStyle3JVO9M(color, TypographyKt.getValue(textToken, $composer2, ($dirty2 >> 3) & 14), function22, $composer2, ($dirty2 & 14) | ($dirty2 & 896));
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup = $composer2.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: androidx.compose.material3.ListItemKt$$ExternalSyntheticLambda4
                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj, Object obj2) {
                    return ListItemKt.ProvideTextStyleFromToken_3J_VO9M$lambda$12(color, textToken, function2, $changed, (Composer) obj, ((Integer) obj2).intValue());
                }
            });
        }
    }

    public static final float getListItemVerticalPadding() {
        return ListItemVerticalPadding;
    }

    public static final float getListItemThreeLineVerticalPadding() {
        return ListItemThreeLineVerticalPadding;
    }

    public static final float getListItemStartPadding() {
        return ListItemStartPadding;
    }

    public static final float getListItemEndPadding() {
        return ListItemEndPadding;
    }

    public static final float getLeadingContentEndPadding() {
        return LeadingContentEndPadding;
    }

    public static final float getTrailingContentStartPadding() {
        return TrailingContentStartPadding;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final boolean isSupportingMultilineHeuristic(Density $this$isSupportingMultilineHeuristic, int estimatedSupportingHeight) {
        return estimatedSupportingHeight > $this$isSupportingMultilineHeuristic.mo425roundToPxR2X_6o(TextUnitKt.getSp(30));
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: verticalPadding-yh95HIg, reason: not valid java name */
    public static final float m2665verticalPaddingyh95HIg(int listItemType) {
        return ListItemType.m2670equalsimpl0(listItemType, ListItemType.INSTANCE.m2676getThreeLineAlXitO8()) ? ListItemThreeLineVerticalPadding : ListItemVerticalPadding;
    }
}
