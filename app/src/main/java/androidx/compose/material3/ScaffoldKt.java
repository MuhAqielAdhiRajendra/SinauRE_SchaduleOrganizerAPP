package androidx.compose.material3;

import androidx.compose.foundation.layout.PaddingKt;
import androidx.compose.foundation.layout.PaddingValues;
import androidx.compose.foundation.layout.WindowInsets;
import androidx.compose.foundation.layout.WindowInsetsKt;
import androidx.compose.material3.internal.MutableWindowInsets;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.RecomposeScopeImplKt;
import androidx.compose.runtime.ScopeUpdateScope;
import androidx.compose.runtime.internal.ComposableLambdaKt;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.layout.Measurable;
import androidx.compose.ui.layout.MeasureResult;
import androidx.compose.ui.layout.MeasureScope;
import androidx.compose.ui.layout.Placeable;
import androidx.compose.ui.layout.SubcomposeLayoutKt;
import androidx.compose.ui.layout.SubcomposeMeasureScope;
import androidx.compose.ui.unit.Constraints;
import androidx.compose.ui.unit.ConstraintsKt;
import androidx.compose.ui.unit.Dp;
import androidx.compose.ui.unit.LayoutDirection;
import java.util.List;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: Scaffold.kt */
/* JADX INFO: loaded from: classes12.dex */
@Metadata(d1 = {"\u0000B\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\b\u0002\u001a®\u0001\u0010\u0000\u001a\u00020\u00012\b\b\u0002\u0010\u0002\u001a\u00020\u00032\u0013\b\u0002\u0010\u0004\u001a\r\u0012\u0004\u0012\u00020\u00010\u0005¢\u0006\u0002\b\u00062\u0013\b\u0002\u0010\u0007\u001a\r\u0012\u0004\u0012\u00020\u00010\u0005¢\u0006\u0002\b\u00062\u0013\b\u0002\u0010\b\u001a\r\u0012\u0004\u0012\u00020\u00010\u0005¢\u0006\u0002\b\u00062\u0013\b\u0002\u0010\t\u001a\r\u0012\u0004\u0012\u00020\u00010\u0005¢\u0006\u0002\b\u00062\b\b\u0002\u0010\n\u001a\u00020\u000b2\b\b\u0002\u0010\f\u001a\u00020\r2\b\b\u0002\u0010\u000e\u001a\u00020\r2\b\b\u0002\u0010\u000f\u001a\u00020\u00102\u0017\u0010\u0011\u001a\u0013\u0012\u0004\u0012\u00020\u0013\u0012\u0004\u0012\u00020\u00010\u0012¢\u0006\u0002\b\u0006H\u0007¢\u0006\u0004\b\u0014\u0010\u0015\u001a\u0084\u0001\u0010\u0016\u001a\u00020\u00012\u0006\u0010\u0017\u001a\u00020\u000b2\u0011\u0010\u0004\u001a\r\u0012\u0004\u0012\u00020\u00010\u0005¢\u0006\u0002\b\u00062\u0017\u0010\u0011\u001a\u0013\u0012\u0004\u0012\u00020\u0013\u0012\u0004\u0012\u00020\u00010\u0012¢\u0006\u0002\b\u00062\u0011\u0010\u0018\u001a\r\u0012\u0004\u0012\u00020\u00010\u0005¢\u0006\u0002\b\u00062\u0011\u0010\u0019\u001a\r\u0012\u0004\u0012\u00020\u00010\u0005¢\u0006\u0002\b\u00062\u0006\u0010\u000f\u001a\u00020\u00102\u0011\u0010\u0007\u001a\r\u0012\u0004\u0012\u00020\u00010\u0005¢\u0006\u0002\b\u0006H\u0003¢\u0006\u0004\b\u001a\u0010\u001b\"\u0010\u0010\u001c\u001a\u00020\u001dX\u0082\u0004¢\u0006\u0004\n\u0002\u0010\u001e¨\u0006\u001f"}, d2 = {"Scaffold", "", "modifier", "Landroidx/compose/ui/Modifier;", "topBar", "Lkotlin/Function0;", "Landroidx/compose/runtime/Composable;", "bottomBar", "snackbarHost", "floatingActionButton", "floatingActionButtonPosition", "Landroidx/compose/material3/FabPosition;", "containerColor", "Landroidx/compose/ui/graphics/Color;", "contentColor", "contentWindowInsets", "Landroidx/compose/foundation/layout/WindowInsets;", "content", "Lkotlin/Function1;", "Landroidx/compose/foundation/layout/PaddingValues;", "Scaffold-TvnljyQ", "(Landroidx/compose/ui/Modifier;Lkotlin/jvm/functions/Function2;Lkotlin/jvm/functions/Function2;Lkotlin/jvm/functions/Function2;Lkotlin/jvm/functions/Function2;IJJLandroidx/compose/foundation/layout/WindowInsets;Lkotlin/jvm/functions/Function3;Landroidx/compose/runtime/Composer;II)V", "ScaffoldLayout", "fabPosition", "snackbar", "fab", "ScaffoldLayout-FMILGgc", "(ILkotlin/jvm/functions/Function2;Lkotlin/jvm/functions/Function3;Lkotlin/jvm/functions/Function2;Lkotlin/jvm/functions/Function2;Landroidx/compose/foundation/layout/WindowInsets;Lkotlin/jvm/functions/Function2;Landroidx/compose/runtime/Composer;I)V", "FabSpacing", "Landroidx/compose/ui/unit/Dp;", "F", "material3"}, k = 2, mv = {2, 0, 0}, xi = 48)
public final class ScaffoldKt {
    private static final float FabSpacing = Dp.m8150constructorimpl(16);

    static final Unit ScaffoldLayout_FMILGgc$lambda$16(int i, Function2 function2, Function3 function3, Function2 function22, Function2 function23, WindowInsets windowInsets, Function2 function24, int i2, Composer composer, int i3) {
        m2851ScaffoldLayoutFMILGgc(i, function2, function3, function22, function23, windowInsets, function24, composer, RecomposeScopeImplKt.updateChangedFlags(i2 | 1));
        return Unit.INSTANCE;
    }

    static final Unit Scaffold_TvnljyQ$lambda$3(Modifier modifier, Function2 function2, Function2 function22, Function2 function23, Function2 function24, int i, long j, long j2, WindowInsets windowInsets, Function3 function3, int i2, int i3, Composer composer, int i4) {
        m2850ScaffoldTvnljyQ(modifier, function2, function22, function23, function24, i, j, j2, windowInsets, function3, composer, RecomposeScopeImplKt.updateChangedFlags(i2 | 1), i3);
        return Unit.INSTANCE;
    }

    /* JADX WARN: Removed duplicated region for block: B:187:0x02c0  */
    /* JADX WARN: Removed duplicated region for block: B:191:0x02cc A[ADDED_TO_REGION] */
    /* JADX WARN: Removed duplicated region for block: B:195:0x032d  */
    /* JADX INFO: renamed from: Scaffold-TvnljyQ, reason: not valid java name */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static final void m2850ScaffoldTvnljyQ(androidx.compose.ui.Modifier r30, kotlin.jvm.functions.Function2<? super androidx.compose.runtime.Composer, ? super java.lang.Integer, kotlin.Unit> r31, kotlin.jvm.functions.Function2<? super androidx.compose.runtime.Composer, ? super java.lang.Integer, kotlin.Unit> r32, kotlin.jvm.functions.Function2<? super androidx.compose.runtime.Composer, ? super java.lang.Integer, kotlin.Unit> r33, kotlin.jvm.functions.Function2<? super androidx.compose.runtime.Composer, ? super java.lang.Integer, kotlin.Unit> r34, int r35, long r36, long r38, androidx.compose.foundation.layout.WindowInsets r40, final kotlin.jvm.functions.Function3<? super androidx.compose.foundation.layout.PaddingValues, ? super androidx.compose.runtime.Composer, ? super java.lang.Integer, kotlin.Unit> r41, androidx.compose.runtime.Composer r42, final int r43, final int r44) {
        /*
            Method dump skipped, instruction units count: 867
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.material3.ScaffoldKt.m2850ScaffoldTvnljyQ(androidx.compose.ui.Modifier, kotlin.jvm.functions.Function2, kotlin.jvm.functions.Function2, kotlin.jvm.functions.Function2, kotlin.jvm.functions.Function2, int, long, long, androidx.compose.foundation.layout.WindowInsets, kotlin.jvm.functions.Function3, androidx.compose.runtime.Composer, int, int):void");
    }

    static final Unit Scaffold_TvnljyQ$lambda$2$lambda$1(MutableWindowInsets $safeInsets, WindowInsets $contentWindowInsets, WindowInsets consumedWindowInsets) {
        $safeInsets.setInsets(WindowInsetsKt.exclude($contentWindowInsets, consumedWindowInsets));
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: ScaffoldLayout-FMILGgc, reason: not valid java name */
    public static final void m2851ScaffoldLayoutFMILGgc(final int i, final Function2<? super Composer, ? super Integer, Unit> function2, final Function3<? super PaddingValues, ? super Composer, ? super Integer, Unit> function3, final Function2<? super Composer, ? super Integer, Unit> function22, final Function2<? super Composer, ? super Integer, Unit> function23, final WindowInsets windowInsets, final Function2<? super Composer, ? super Integer, Unit> function24, Composer composer, final int i2) {
        int i3;
        ScaffoldKt$ScaffoldLayout$contentPadding$1$1 scaffoldKt$ScaffoldLayout$contentPadding$1$1;
        int i4;
        int i5;
        Composer composerStartRestartGroup = composer.startRestartGroup(-280287501);
        ComposerKt.sourceInformation(composerStartRestartGroup, "C(ScaffoldLayout)N(fabPosition:c#material3.FabPosition,topBar,content,snackbar,fab,contentWindowInsets,bottomBar)142@6839L626,158@7515L41,159@7607L45,160@7698L35,162@7788L73,163@7913L47,164@7982L5885,164@7965L5902:Scaffold.kt#uh7d8r");
        int i6 = i2;
        if ((i2 & 6) == 0) {
            i6 |= composerStartRestartGroup.changed(i) ? 4 : 2;
        }
        if ((i2 & 48) == 0) {
            i6 |= composerStartRestartGroup.changedInstance(function2) ? 32 : 16;
        }
        if ((i2 & 384) == 0) {
            i6 |= composerStartRestartGroup.changedInstance(function3) ? 256 : 128;
        }
        if ((i2 & 3072) == 0) {
            i6 |= composerStartRestartGroup.changedInstance(function22) ? 2048 : 1024;
        }
        if ((i2 & 24576) == 0) {
            i6 |= composerStartRestartGroup.changedInstance(function23) ? 16384 : 8192;
        }
        if ((196608 & i2) == 0) {
            i6 |= composerStartRestartGroup.changed(windowInsets) ? 131072 : 65536;
        }
        if ((i2 & 1572864) == 0) {
            i6 |= composerStartRestartGroup.changedInstance(function24) ? 1048576 : 524288;
        }
        if (composerStartRestartGroup.shouldExecute((i6 & 599187) != 599186, i6 & 1)) {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-280287501, i6, -1, "androidx.compose.material3.ScaffoldLayout (Scaffold.kt:137)");
            }
            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 1226671013, "CC(remember):Scaffold.kt#9igjgp");
            Object objRememberedValue = composerStartRestartGroup.rememberedValue();
            if (objRememberedValue == Composer.INSTANCE.getEmpty()) {
                Object scaffoldKt$ScaffoldLayout$contentPadding$1$12 = new ScaffoldKt$ScaffoldLayout$contentPadding$1$1();
                composerStartRestartGroup.updateRememberedValue(scaffoldKt$ScaffoldLayout$contentPadding$1$12);
                objRememberedValue = scaffoldKt$ScaffoldLayout$contentPadding$1$12;
            }
            final ScaffoldKt$ScaffoldLayout$contentPadding$1$1 scaffoldKt$ScaffoldLayout$contentPadding$1$13 = (ScaffoldKt$ScaffoldLayout$contentPadding$1$1) objRememberedValue;
            ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 1226692060, "CC(remember):Scaffold.kt#9igjgp");
            boolean z = (i6 & 112) == 32;
            Object objRememberedValue2 = composerStartRestartGroup.rememberedValue();
            if (z || objRememberedValue2 == Composer.INSTANCE.getEmpty()) {
                Object objComposableLambdaInstance = ComposableLambdaKt.composableLambdaInstance(605195056, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.ScaffoldKt$ScaffoldLayout$topBarContent$1$1
                    @Override // kotlin.jvm.functions.Function2
                    public /* bridge */ /* synthetic */ Unit invoke(Composer composer2, Integer num) {
                        invoke(composer2, num.intValue());
                        return Unit.INSTANCE;
                    }

                    /* JADX WARN: Removed duplicated region for block: B:28:0x0150  */
                    /* JADX WARN: Removed duplicated region for block: B:31:? A[RETURN, SYNTHETIC] */
                    /*
                        Code decompiled incorrectly, please refer to instructions dump.
                        To view partially-correct code enable 'Show inconsistent code' option in preferences
                    */
                    public final void invoke(androidx.compose.runtime.Composer r28, int r29) {
                        /*
                            Method dump skipped, instruction units count: 344
                            To view this dump change 'Code comments level' option to 'DEBUG'
                        */
                        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.material3.ScaffoldKt$ScaffoldLayout$topBarContent$1$1.invoke(androidx.compose.runtime.Composer, int):void");
                    }
                });
                composerStartRestartGroup.updateRememberedValue(objComposableLambdaInstance);
                objRememberedValue2 = objComposableLambdaInstance;
            }
            final Function2 function25 = (Function2) objRememberedValue2;
            ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 1226695008, "CC(remember):Scaffold.kt#9igjgp");
            boolean z2 = (i6 & 7168) == 2048;
            Object objRememberedValue3 = composerStartRestartGroup.rememberedValue();
            if (z2 || objRememberedValue3 == Composer.INSTANCE.getEmpty()) {
                Object objComposableLambdaInstance2 = ComposableLambdaKt.composableLambdaInstance(418899191, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.ScaffoldKt$ScaffoldLayout$snackbarContent$1$1
                    @Override // kotlin.jvm.functions.Function2
                    public /* bridge */ /* synthetic */ Unit invoke(Composer composer2, Integer num) {
                        invoke(composer2, num.intValue());
                        return Unit.INSTANCE;
                    }

                    /* JADX WARN: Removed duplicated region for block: B:28:0x0150  */
                    /* JADX WARN: Removed duplicated region for block: B:31:? A[RETURN, SYNTHETIC] */
                    /*
                        Code decompiled incorrectly, please refer to instructions dump.
                        To view partially-correct code enable 'Show inconsistent code' option in preferences
                    */
                    public final void invoke(androidx.compose.runtime.Composer r28, int r29) {
                        /*
                            Method dump skipped, instruction units count: 344
                            To view this dump change 'Code comments level' option to 'DEBUG'
                        */
                        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.material3.ScaffoldKt$ScaffoldLayout$snackbarContent$1$1.invoke(androidx.compose.runtime.Composer, int):void");
                    }
                });
                composerStartRestartGroup.updateRememberedValue(objComposableLambdaInstance2);
                objRememberedValue3 = objComposableLambdaInstance2;
            }
            final Function2 function26 = (Function2) objRememberedValue3;
            ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 1226697910, "CC(remember):Scaffold.kt#9igjgp");
            boolean z3 = (57344 & i6) == 16384;
            Object objRememberedValue4 = composerStartRestartGroup.rememberedValue();
            if (z3 || objRememberedValue4 == Composer.INSTANCE.getEmpty()) {
                Object objComposableLambdaInstance3 = ComposableLambdaKt.composableLambdaInstance(338600263, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.ScaffoldKt$ScaffoldLayout$fabContent$1$1
                    @Override // kotlin.jvm.functions.Function2
                    public /* bridge */ /* synthetic */ Unit invoke(Composer composer2, Integer num) {
                        invoke(composer2, num.intValue());
                        return Unit.INSTANCE;
                    }

                    /* JADX WARN: Removed duplicated region for block: B:28:0x0150  */
                    /* JADX WARN: Removed duplicated region for block: B:31:? A[RETURN, SYNTHETIC] */
                    /*
                        Code decompiled incorrectly, please refer to instructions dump.
                        To view partially-correct code enable 'Show inconsistent code' option in preferences
                    */
                    public final void invoke(androidx.compose.runtime.Composer r28, int r29) {
                        /*
                            Method dump skipped, instruction units count: 344
                            To view this dump change 'Code comments level' option to 'DEBUG'
                        */
                        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.material3.ScaffoldKt$ScaffoldLayout$fabContent$1$1.invoke(androidx.compose.runtime.Composer, int):void");
                    }
                });
                composerStartRestartGroup.updateRememberedValue(objComposableLambdaInstance3);
                objRememberedValue4 = objComposableLambdaInstance3;
            }
            final Function2 function27 = (Function2) objRememberedValue4;
            ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 1226700828, "CC(remember):Scaffold.kt#9igjgp");
            boolean z4 = (i6 & 896) == 256;
            Object objRememberedValue5 = composerStartRestartGroup.rememberedValue();
            if (z4 || objRememberedValue5 == Composer.INSTANCE.getEmpty()) {
                i3 = i6;
                scaffoldKt$ScaffoldLayout$contentPadding$1$1 = scaffoldKt$ScaffoldLayout$contentPadding$1$13;
                Object objComposableLambdaInstance4 = ComposableLambdaKt.composableLambdaInstance(-1776388365, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.ScaffoldKt$ScaffoldLayout$bodyContent$1$1
                    @Override // kotlin.jvm.functions.Function2
                    public /* bridge */ /* synthetic */ Unit invoke(Composer composer2, Integer num) {
                        invoke(composer2, num.intValue());
                        return Unit.INSTANCE;
                    }

                    /* JADX WARN: Removed duplicated region for block: B:28:0x0152  */
                    /* JADX WARN: Removed duplicated region for block: B:31:? A[RETURN, SYNTHETIC] */
                    /*
                        Code decompiled incorrectly, please refer to instructions dump.
                        To view partially-correct code enable 'Show inconsistent code' option in preferences
                    */
                    public final void invoke(androidx.compose.runtime.Composer r29, int r30) {
                        /*
                            Method dump skipped, instruction units count: 346
                            To view this dump change 'Code comments level' option to 'DEBUG'
                        */
                        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.material3.ScaffoldKt$ScaffoldLayout$bodyContent$1$1.invoke(androidx.compose.runtime.Composer, int):void");
                    }
                });
                composerStartRestartGroup.updateRememberedValue(objComposableLambdaInstance4);
                objRememberedValue5 = objComposableLambdaInstance4;
            } else {
                scaffoldKt$ScaffoldLayout$contentPadding$1$1 = scaffoldKt$ScaffoldLayout$contentPadding$1$13;
                i3 = i6;
            }
            final Function2 function28 = (Function2) objRememberedValue5;
            ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 1226704802, "CC(remember):Scaffold.kt#9igjgp");
            boolean z5 = (i3 & 3670016) == 1048576;
            Object objRememberedValue6 = composerStartRestartGroup.rememberedValue();
            if (z5 || objRememberedValue6 == Composer.INSTANCE.getEmpty()) {
                i4 = 1;
                Object objComposableLambdaInstance5 = ComposableLambdaKt.composableLambdaInstance(-1731662488, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.ScaffoldKt$ScaffoldLayout$bottomBarContent$1$1
                    @Override // kotlin.jvm.functions.Function2
                    public /* bridge */ /* synthetic */ Unit invoke(Composer composer2, Integer num) {
                        invoke(composer2, num.intValue());
                        return Unit.INSTANCE;
                    }

                    /* JADX WARN: Removed duplicated region for block: B:28:0x0150  */
                    /* JADX WARN: Removed duplicated region for block: B:31:? A[RETURN, SYNTHETIC] */
                    /*
                        Code decompiled incorrectly, please refer to instructions dump.
                        To view partially-correct code enable 'Show inconsistent code' option in preferences
                    */
                    public final void invoke(androidx.compose.runtime.Composer r28, int r29) {
                        /*
                            Method dump skipped, instruction units count: 344
                            To view this dump change 'Code comments level' option to 'DEBUG'
                        */
                        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.material3.ScaffoldKt$ScaffoldLayout$bottomBarContent$1$1.invoke(androidx.compose.runtime.Composer, int):void");
                    }
                });
                composerStartRestartGroup.updateRememberedValue(objComposableLambdaInstance5);
                objRememberedValue6 = objComposableLambdaInstance5;
            } else {
                i4 = 1;
            }
            final Function2 function29 = (Function2) objRememberedValue6;
            ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 1226712848, "CC(remember):Scaffold.kt#9igjgp");
            int i7 = (composerStartRestartGroup.changed(function25) ? 1 : 0) | ((i3 & 458752) == 131072 ? i4 : 0) | (composerStartRestartGroup.changed(function26) ? 1 : 0) | (composerStartRestartGroup.changed(function27) ? 1 : 0) | ((i3 & 14) == 4 ? i4 : 0) | (composerStartRestartGroup.changed(function29) ? 1 : 0) | (composerStartRestartGroup.changed(function28) ? 1 : 0);
            Object objRememberedValue7 = composerStartRestartGroup.rememberedValue();
            if (i7 != 0 || objRememberedValue7 == Composer.INSTANCE.getEmpty()) {
                final ScaffoldKt$ScaffoldLayout$contentPadding$1$1 scaffoldKt$ScaffoldLayout$contentPadding$1$14 = scaffoldKt$ScaffoldLayout$contentPadding$1$1;
                i5 = 0;
                Object obj = new Function2() { // from class: androidx.compose.material3.ScaffoldKt$$ExternalSyntheticLambda2
                    @Override // kotlin.jvm.functions.Function2
                    public final Object invoke(Object obj2, Object obj3) {
                        return ScaffoldKt.ScaffoldLayout_FMILGgc$lambda$15$lambda$14(windowInsets, function25, function26, function27, i, function29, scaffoldKt$ScaffoldLayout$contentPadding$1$14, function28, (SubcomposeMeasureScope) obj2, (Constraints) obj3);
                    }
                };
                composerStartRestartGroup.updateRememberedValue(obj);
                objRememberedValue7 = obj;
            } else {
                i5 = 0;
            }
            ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
            SubcomposeLayoutKt.SubcomposeLayout(null, (Function2) objRememberedValue7, composerStartRestartGroup, i5, i4);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        } else {
            composerStartRestartGroup.skipToGroupEnd();
        }
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup = composerStartRestartGroup.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: androidx.compose.material3.ScaffoldKt$$ExternalSyntheticLambda3
                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj2, Object obj3) {
                    return ScaffoldKt.ScaffoldLayout_FMILGgc$lambda$16(i, function2, function3, function22, function23, windowInsets, function24, i2, (Composer) obj2, ((Integer) obj3).intValue());
                }
            });
        }
    }

    static final MeasureResult ScaffoldLayout_FMILGgc$lambda$15$lambda$14(final WindowInsets $contentWindowInsets, Function2 $topBarContent, Function2 $snackbarContent, Function2 $fabContent, int $fabPosition, Function2 $bottomBarContent, ScaffoldKt$ScaffoldLayout$contentPadding$1$1 $contentPadding, Function2 $bodyContent, final SubcomposeMeasureScope $this$SubcomposeLayout, Constraints constraints) {
        Placeable fabPlaceable;
        final FabPlacement fabPlacement;
        Placeable bottomBarPlaceable;
        final Integer fabOffsetFromBottom;
        int snackbarOffsetFromBottom;
        float top;
        Placeable topBarPlaceable;
        float bottom;
        int iIntValue;
        int height;
        int fabLeftOffset;
        final int layoutWidth = Constraints.m8103getMaxWidthimpl(constraints.getValue());
        final int layoutHeight = Constraints.m8102getMaxHeightimpl(constraints.getValue());
        long value = constraints.getValue();
        long looseConstraints = Constraints.m8092copyZbe2FdA(value, (11 & 1) != 0 ? Constraints.m8105getMinWidthimpl(value) : 0, (11 & 2) != 0 ? Constraints.m8103getMaxWidthimpl(value) : 0, (11 & 4) != 0 ? Constraints.m8104getMinHeightimpl(value) : 0, (11 & 8) != 0 ? Constraints.m8102getMaxHeightimpl(value) : 0);
        int leftInset = $contentWindowInsets.getLeft($this$SubcomposeLayout, $this$SubcomposeLayout.getLayoutDirection());
        int rightInset = $contentWindowInsets.getRight($this$SubcomposeLayout, $this$SubcomposeLayout.getLayoutDirection());
        int bottomInset = $contentWindowInsets.getBottom($this$SubcomposeLayout);
        Placeable topBarPlaceable2 = ((Measurable) CollectionsKt.first((List) $this$SubcomposeLayout.subcompose(ScaffoldLayoutContent.TopBar, $topBarContent))).mo6783measureBRTryo0(looseConstraints);
        final Placeable snackbarPlaceable = ((Measurable) CollectionsKt.first((List) $this$SubcomposeLayout.subcompose(ScaffoldLayoutContent.Snackbar, $snackbarContent))).mo6783measureBRTryo0(ConstraintsKt.m8122offsetNN6EwU(looseConstraints, (-leftInset) - rightInset, -bottomInset));
        Placeable fabPlaceable2 = ((Measurable) CollectionsKt.first((List) $this$SubcomposeLayout.subcompose(ScaffoldLayoutContent.Fab, $fabContent))).mo6783measureBRTryo0(ConstraintsKt.m8122offsetNN6EwU(looseConstraints, (-leftInset) - rightInset, -bottomInset));
        boolean isFabEmpty = fabPlaceable2.getWidth() == 0 && fabPlaceable2.getHeight() == 0;
        if (!isFabEmpty) {
            int fabWidth = fabPlaceable2.getWidth();
            int fabHeight = fabPlaceable2.getHeight();
            if (FabPosition.m2533equalsimpl0($fabPosition, FabPosition.INSTANCE.m2540getStartERTFSPs())) {
                fabPlaceable = fabPlaceable2;
                if ($this$SubcomposeLayout.getLayoutDirection() == LayoutDirection.Ltr) {
                    fabLeftOffset = $this$SubcomposeLayout.mo426roundToPx0680j_4(FabSpacing) + leftInset;
                } else {
                    fabLeftOffset = ((layoutWidth - $this$SubcomposeLayout.mo426roundToPx0680j_4(FabSpacing)) - fabWidth) - rightInset;
                }
            } else {
                fabPlaceable = fabPlaceable2;
                if (FabPosition.m2533equalsimpl0($fabPosition, FabPosition.INSTANCE.m2538getEndERTFSPs()) || FabPosition.m2533equalsimpl0($fabPosition, FabPosition.INSTANCE.m2539getEndOverlayERTFSPs())) {
                    if ($this$SubcomposeLayout.getLayoutDirection() == LayoutDirection.Ltr) {
                        fabLeftOffset = ((layoutWidth - $this$SubcomposeLayout.mo426roundToPx0680j_4(FabSpacing)) - fabWidth) - rightInset;
                    } else {
                        fabLeftOffset = $this$SubcomposeLayout.mo426roundToPx0680j_4(FabSpacing) + leftInset;
                    }
                } else {
                    fabLeftOffset = (((layoutWidth - fabWidth) + leftInset) - rightInset) / 2;
                }
            }
            fabPlacement = new FabPlacement(fabLeftOffset, fabWidth, fabHeight);
        } else {
            fabPlaceable = fabPlaceable2;
            fabPlacement = null;
        }
        Placeable bottomBarPlaceable2 = ((Measurable) CollectionsKt.first((List) $this$SubcomposeLayout.subcompose(ScaffoldLayoutContent.BottomBar, $bottomBarContent))).mo6783measureBRTryo0(looseConstraints);
        boolean isBottomBarEmpty = bottomBarPlaceable2.getWidth() == 0 && bottomBarPlaceable2.getHeight() == 0;
        if (fabPlacement != null) {
            FabPlacement it = fabPlacement;
            if (!isBottomBarEmpty) {
                bottomBarPlaceable = bottomBarPlaceable2;
                if (!FabPosition.m2533equalsimpl0($fabPosition, FabPosition.INSTANCE.m2539getEndOverlayERTFSPs())) {
                    height = bottomBarPlaceable.getHeight() + it.getHeight() + $this$SubcomposeLayout.mo426roundToPx0680j_4(FabSpacing);
                }
                fabOffsetFromBottom = Integer.valueOf(height);
            } else {
                bottomBarPlaceable = bottomBarPlaceable2;
            }
            height = it.getHeight() + $this$SubcomposeLayout.mo426roundToPx0680j_4(FabSpacing) + $contentWindowInsets.getBottom($this$SubcomposeLayout);
            fabOffsetFromBottom = Integer.valueOf(height);
        } else {
            bottomBarPlaceable = bottomBarPlaceable2;
            fabOffsetFromBottom = null;
        }
        int snackbarHeight = snackbarPlaceable.getHeight();
        if (snackbarHeight != 0) {
            if (fabOffsetFromBottom != null) {
                iIntValue = fabOffsetFromBottom.intValue();
            } else {
                Integer numValueOf = Integer.valueOf(bottomBarPlaceable.getHeight());
                numValueOf.intValue();
                if (!(!isBottomBarEmpty)) {
                    numValueOf = null;
                }
                iIntValue = numValueOf != null ? numValueOf.intValue() : $contentWindowInsets.getBottom($this$SubcomposeLayout);
            }
            snackbarOffsetFromBottom = snackbarHeight + iIntValue;
        } else {
            snackbarOffsetFromBottom = 0;
        }
        PaddingValues insets = WindowInsetsKt.asPaddingValues($contentWindowInsets, $this$SubcomposeLayout);
        if (topBarPlaceable2.getWidth() == 0 && topBarPlaceable2.getHeight() == 0) {
            top = insets.getTop();
        } else {
            top = $this$SubcomposeLayout.mo429toDpu2uoSUM(topBarPlaceable2.getHeight());
        }
        if (isBottomBarEmpty) {
            topBarPlaceable = topBarPlaceable2;
            bottom = insets.getBottom();
        } else {
            topBarPlaceable = topBarPlaceable2;
            bottom = $this$SubcomposeLayout.mo429toDpu2uoSUM(bottomBarPlaceable.getHeight());
        }
        $contentPadding.setPaddingHolder(PaddingKt.m1044PaddingValuesa9UjIt4(PaddingKt.calculateStartPadding(insets, $this$SubcomposeLayout.getLayoutDirection()), top, PaddingKt.calculateEndPadding(insets, $this$SubcomposeLayout.getLayoutDirection()), bottom));
        final Placeable bodyContentPlaceable = ((Measurable) CollectionsKt.first((List) $this$SubcomposeLayout.subcompose(ScaffoldLayoutContent.MainContent, $bodyContent))).mo6783measureBRTryo0(looseConstraints);
        final Placeable topBarPlaceable3 = topBarPlaceable;
        final Placeable fabPlaceable3 = fabPlaceable;
        final int snackbarOffsetFromBottom2 = snackbarOffsetFromBottom;
        final Placeable bottomBarPlaceable3 = bottomBarPlaceable;
        return MeasureScope.layout$default($this$SubcomposeLayout, layoutWidth, layoutHeight, null, new Function1() { // from class: androidx.compose.material3.ScaffoldKt$$ExternalSyntheticLambda4
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return ScaffoldKt.ScaffoldLayout_FMILGgc$lambda$15$lambda$14$lambda$13(bodyContentPlaceable, topBarPlaceable3, snackbarPlaceable, layoutWidth, $contentWindowInsets, $this$SubcomposeLayout, layoutHeight, snackbarOffsetFromBottom2, bottomBarPlaceable3, fabPlacement, fabPlaceable3, fabOffsetFromBottom, (Placeable.PlacementScope) obj);
            }
        }, 4, null);
    }

    static final Unit ScaffoldLayout_FMILGgc$lambda$15$lambda$14$lambda$13(Placeable $bodyContentPlaceable, Placeable $topBarPlaceable, Placeable $snackbarPlaceable, int $layoutWidth, WindowInsets $contentWindowInsets, SubcomposeMeasureScope $this_SubcomposeLayout, int $layoutHeight, int $snackbarOffsetFromBottom, Placeable $bottomBarPlaceable, FabPlacement $fabPlacement, Placeable $fabPlaceable, Integer $fabOffsetFromBottom, Placeable.PlacementScope $this$layout) {
        Placeable.PlacementScope.place$default($this$layout, $bodyContentPlaceable, 0, 0, 0.0f, 4, null);
        Placeable.PlacementScope.place$default($this$layout, $topBarPlaceable, 0, 0, 0.0f, 4, null);
        Placeable.PlacementScope.place$default($this$layout, $snackbarPlaceable, ((($layoutWidth - $snackbarPlaceable.getWidth()) + $contentWindowInsets.getLeft($this_SubcomposeLayout, $this_SubcomposeLayout.getLayoutDirection())) - $contentWindowInsets.getRight($this_SubcomposeLayout, $this_SubcomposeLayout.getLayoutDirection())) / 2, $layoutHeight - $snackbarOffsetFromBottom, 0.0f, 4, null);
        Placeable.PlacementScope.place$default($this$layout, $bottomBarPlaceable, 0, $layoutHeight - $bottomBarPlaceable.getHeight(), 0.0f, 4, null);
        if ($fabPlacement != null) {
            int left = $fabPlacement.getLeft();
            Intrinsics.checkNotNull($fabOffsetFromBottom);
            Placeable.PlacementScope.place$default($this$layout, $fabPlaceable, left, $layoutHeight - $fabOffsetFromBottom.intValue(), 0.0f, 4, null);
        }
        return Unit.INSTANCE;
    }
}
