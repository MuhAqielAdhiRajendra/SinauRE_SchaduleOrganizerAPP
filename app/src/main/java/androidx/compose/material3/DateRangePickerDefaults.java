package androidx.compose.material3;

import androidx.compose.foundation.layout.Arrangement;
import androidx.compose.foundation.layout.RowKt;
import androidx.compose.foundation.layout.RowScopeInstance;
import androidx.compose.material3.internal.Strings;
import androidx.compose.material3.internal.Strings_androidKt;
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
import androidx.compose.ui.layout.MeasurePolicy;
import androidx.compose.ui.node.ComposeUiNode;
import androidx.compose.ui.semantics.LiveRegionMode;
import androidx.compose.ui.semantics.SemanticsModifierKt;
import androidx.compose.ui.semantics.SemanticsPropertiesKt;
import androidx.compose.ui.semantics.SemanticsPropertyReceiver;
import androidx.compose.ui.unit.Dp;
import java.util.Locale;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: DateRangePicker.kt */
/* JADX INFO: loaded from: classes12.dex */
@Metadata(d1 = {"\u0000V\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\bÇ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J+\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00072\b\b\u0002\u0010\b\u001a\u00020\t2\b\b\u0002\u0010\n\u001a\u00020\u000bH\u0007¢\u0006\u0004\b\f\u0010\rJG\u0010\u000e\u001a\u00020\u00052\b\u0010\u000f\u001a\u0004\u0018\u00010\u00102\b\u0010\u0011\u001a\u0004\u0018\u00010\u00102\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\u0012\u001a\u00020\u00132\b\b\u0002\u0010\b\u001a\u00020\t2\b\b\u0002\u0010\n\u001a\u00020\u000bH\u0007¢\u0006\u0004\b\u0014\u0010\u0015J\u0098\u0001\u0010\u000e\u001a\u00020\u00052\b\u0010\u000f\u001a\u0004\u0018\u00010\u00102\b\u0010\u0011\u001a\u0004\u0018\u00010\u00102\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\u0012\u001a\u00020\u00132\u0006\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000b2\u0006\u0010\u0016\u001a\u00020\u00172\u0006\u0010\u0018\u001a\u00020\u00172\u0011\u0010\u0019\u001a\r\u0012\u0004\u0012\u00020\u00050\u001a¢\u0006\u0002\b\u001b2\u0011\u0010\u001c\u001a\r\u0012\u0004\u0012\u00020\u00050\u001a¢\u0006\u0002\b\u001b2\u0011\u0010\u001d\u001a\r\u0012\u0004\u0012\u00020\u00050\u001a¢\u0006\u0002\b\u001b2\n\u0010\u001e\u001a\u00060\u001fj\u0002` H\u0003¢\u0006\u0004\b!\u0010\"¨\u0006#"}, d2 = {"Landroidx/compose/material3/DateRangePickerDefaults;", "", "<init>", "()V", "DateRangePickerTitle", "", "displayMode", "Landroidx/compose/material3/DisplayMode;", "modifier", "Landroidx/compose/ui/Modifier;", "contentColor", "Landroidx/compose/ui/graphics/Color;", "DateRangePickerTitle-FNtVw6o", "(ILandroidx/compose/ui/Modifier;JLandroidx/compose/runtime/Composer;II)V", "DateRangePickerHeadline", "selectedStartDateMillis", "", "selectedEndDateMillis", "dateFormatter", "Landroidx/compose/material3/DatePickerFormatter;", "DateRangePickerHeadline-qS89cEg", "(Ljava/lang/Long;Ljava/lang/Long;ILandroidx/compose/material3/DatePickerFormatter;Landroidx/compose/ui/Modifier;JLandroidx/compose/runtime/Composer;II)V", "startDateText", "", "endDateText", "startDatePlaceholder", "Lkotlin/Function0;", "Landroidx/compose/runtime/Composable;", "endDatePlaceholder", "datesDelimiter", "locale", "Ljava/util/Locale;", "Landroidx/compose/material3/CalendarLocale;", "DateRangePickerHeadline-nZrIstQ", "(Ljava/lang/Long;Ljava/lang/Long;ILandroidx/compose/material3/DatePickerFormatter;Landroidx/compose/ui/Modifier;JLjava/lang/String;Ljava/lang/String;Lkotlin/jvm/functions/Function2;Lkotlin/jvm/functions/Function2;Lkotlin/jvm/functions/Function2;Ljava/util/Locale;Landroidx/compose/runtime/Composer;II)V", "material3"}, k = 1, mv = {2, 0, 0}, xi = 48)
public final class DateRangePickerDefaults {
    public static final int $stable = 0;
    public static final DateRangePickerDefaults INSTANCE = new DateRangePickerDefaults();

    static final Unit DateRangePickerHeadline_nZrIstQ$lambda$5(DateRangePickerDefaults dateRangePickerDefaults, Long l, Long l2, int i, DatePickerFormatter datePickerFormatter, Modifier modifier, long j, String str, String str2, Function2 function2, Function2 function22, Function2 function23, Locale locale, int i2, int i3, Composer composer, int i4) {
        dateRangePickerDefaults.m2449DateRangePickerHeadlinenZrIstQ(l, l2, i, datePickerFormatter, modifier, j, str, str2, function2, function22, function23, locale, composer, RecomposeScopeImplKt.updateChangedFlags(i2 | 1), RecomposeScopeImplKt.updateChangedFlags(i3));
        return Unit.INSTANCE;
    }

    static final Unit DateRangePickerHeadline_qS89cEg$lambda$1(DateRangePickerDefaults dateRangePickerDefaults, Long l, Long l2, int i, DatePickerFormatter datePickerFormatter, Modifier modifier, long j, int i2, int i3, Composer composer, int i4) {
        dateRangePickerDefaults.m2450DateRangePickerHeadlineqS89cEg(l, l2, i, datePickerFormatter, modifier, j, composer, RecomposeScopeImplKt.updateChangedFlags(i2 | 1), i3);
        return Unit.INSTANCE;
    }

    static final Unit DateRangePickerTitle_FNtVw6o$lambda$0(DateRangePickerDefaults dateRangePickerDefaults, int i, Modifier modifier, long j, int i2, int i3, Composer composer, int i4) {
        dateRangePickerDefaults.m2451DateRangePickerTitleFNtVw6o(i, modifier, j, composer, RecomposeScopeImplKt.updateChangedFlags(i2 | 1), i3);
        return Unit.INSTANCE;
    }

    private DateRangePickerDefaults() {
    }

    /* JADX INFO: renamed from: DateRangePickerTitle-FNtVw6o, reason: not valid java name */
    public final void m2451DateRangePickerTitleFNtVw6o(final int displayMode, Modifier modifier, long contentColor, Composer $composer, final int $changed, final int i) {
        Modifier modifier2;
        long contentColor2;
        Composer $composer2;
        final Modifier modifier3;
        final long contentColor3;
        Modifier.Companion modifier4;
        long contentColor4;
        Modifier modifier5;
        long contentColor5;
        Composer $composer3 = $composer.startRestartGroup(694693107);
        ComposerKt.sourceInformation($composer3, "C(DateRangePickerTitle)N(displayMode:c#material3.DisplayMode,modifier,contentColor:c#ui.graphics.Color):DateRangePicker.kt#uh7d8r");
        int $dirty = $changed;
        if ((i & 1) != 0) {
            $dirty |= 6;
        } else if (($changed & 6) == 0) {
            $dirty |= $composer3.changed(displayMode) ? 4 : 2;
        }
        int i2 = i & 2;
        if (i2 != 0) {
            $dirty |= 48;
            modifier2 = modifier;
        } else if (($changed & 48) == 0) {
            modifier2 = modifier;
            $dirty |= $composer3.changed(modifier2) ? 32 : 16;
        } else {
            modifier2 = modifier;
        }
        if (($changed & 384) == 0) {
            if ((i & 4) == 0) {
                contentColor2 = contentColor;
                int i3 = $composer3.changed(contentColor2) ? 256 : 128;
                $dirty |= i3;
            } else {
                contentColor2 = contentColor;
            }
            $dirty |= i3;
        } else {
            contentColor2 = contentColor;
        }
        if ($composer3.shouldExecute(($dirty & 147) != 146, $dirty & 1)) {
            $composer3.startDefaults();
            ComposerKt.sourceInformation($composer3, "370@17337L8");
            if (($changed & 1) != 0 && !$composer3.getDefaultsInvalid()) {
                $composer3.skipToGroupEnd();
                if ((i & 4) != 0) {
                    $dirty &= -897;
                }
                contentColor4 = contentColor2;
                modifier5 = modifier2;
            } else {
                if (i2 != 0) {
                    modifier4 = Modifier.INSTANCE;
                } else {
                    modifier4 = modifier2;
                }
                if ((i & 4) == 0) {
                    contentColor4 = contentColor2;
                    modifier5 = modifier4;
                } else {
                    $dirty &= -897;
                    contentColor4 = DatePickerDefaults.INSTANCE.colors($composer3, 6).getTitleContentColor();
                    modifier5 = modifier4;
                }
            }
            $composer3.endDefaults();
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(694693107, $dirty, -1, "androidx.compose.material3.DateRangePickerDefaults.DateRangePickerTitle (DateRangePicker.kt:371)");
            }
            if (!DisplayMode.m2476equalsimpl0(displayMode, DisplayMode.INSTANCE.m2481getPickerjFl4v0())) {
                if (DisplayMode.m2476equalsimpl0(displayMode, DisplayMode.INSTANCE.m2480getInputjFl4v0())) {
                    $composer3.startReplaceGroup(1880161282);
                    ComposerKt.sourceInformation($composer3, "381@17704L47,380@17678L175");
                    Strings.Companion companion = Strings.INSTANCE;
                    TextKt.m3157TextNvy7gAk(Strings_androidKt.m3533getString2EP1pXo(Strings.m3454constructorimpl(R.string.m3c_date_range_input_title), $composer3, 0), modifier5, contentColor4, null, 0L, null, null, null, 0L, null, null, 0L, 0, false, 0, 0, null, null, $composer3, ($dirty & 112) | ($dirty & 896), 0, 262136);
                    contentColor5 = contentColor4;
                    $composer2 = $composer3;
                    $composer2.endReplaceGroup();
                } else {
                    long j = contentColor4;
                    $composer2 = $composer3;
                    contentColor5 = j;
                    $composer2.startReplaceGroup(-1844364305);
                    $composer2.endReplaceGroup();
                }
            } else {
                $composer3.startReplaceGroup(1880154051);
                ComposerKt.sourceInformation($composer3, "375@17478L48,374@17452L176");
                Strings.Companion companion2 = Strings.INSTANCE;
                TextKt.m3157TextNvy7gAk(Strings_androidKt.m3533getString2EP1pXo(Strings.m3454constructorimpl(R.string.m3c_date_range_picker_title), $composer3, 0), modifier5, contentColor4, null, 0L, null, null, null, 0L, null, null, 0L, 0, false, 0, 0, null, null, $composer3, ($dirty & 112) | ($dirty & 896), 0, 262136);
                $composer3.endReplaceGroup();
                long j2 = contentColor4;
                $composer2 = $composer3;
                contentColor5 = j2;
            }
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
            modifier3 = modifier5;
            contentColor3 = contentColor5;
        } else {
            $composer2 = $composer3;
            $composer2.skipToGroupEnd();
            modifier3 = modifier2;
            contentColor3 = contentColor2;
        }
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup = $composer2.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: androidx.compose.material3.DateRangePickerDefaults$$ExternalSyntheticLambda2
                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj, Object obj2) {
                    return DateRangePickerDefaults.DateRangePickerTitle_FNtVw6o$lambda$0(this.f$0, displayMode, modifier3, contentColor3, $changed, i, (Composer) obj, ((Integer) obj2).intValue());
                }
            });
        }
    }

    /* JADX INFO: renamed from: DateRangePickerHeadline-qS89cEg, reason: not valid java name */
    public final void m2450DateRangePickerHeadlineqS89cEg(final Long selectedStartDateMillis, final Long selectedEndDateMillis, final int displayMode, final DatePickerFormatter dateFormatter, Modifier modifier, long contentColor, Composer $composer, final int $changed, final int i) {
        Long l;
        Long l2;
        int i2;
        Modifier modifier2;
        final long contentColor2;
        DateRangePickerDefaults dateRangePickerDefaults;
        final Modifier modifier3;
        final long contentColor3;
        Modifier.Companion modifier4;
        Composer $composer2 = $composer.startRestartGroup(1655228151);
        ComposerKt.sourceInformation($composer2, "C(DateRangePickerHeadline)N(selectedStartDateMillis,selectedEndDateMillis,displayMode:c#material3.DisplayMode,dateFormatter,modifier,contentColor:c#ui.graphics.Color)410@19016L47,411@19090L45,421@19564L52,422@19651L50,423@19732L42,424@19797L15,412@19144L679:DateRangePicker.kt#uh7d8r");
        int $dirty = $changed;
        if ((i & 1) != 0) {
            $dirty |= 6;
            l = selectedStartDateMillis;
        } else if (($changed & 6) == 0) {
            l = selectedStartDateMillis;
            $dirty |= $composer2.changed(l) ? 4 : 2;
        } else {
            l = selectedStartDateMillis;
        }
        if ((i & 2) != 0) {
            $dirty |= 48;
            l2 = selectedEndDateMillis;
        } else if (($changed & 48) == 0) {
            l2 = selectedEndDateMillis;
            $dirty |= $composer2.changed(l2) ? 32 : 16;
        } else {
            l2 = selectedEndDateMillis;
        }
        if ((i & 4) != 0) {
            $dirty |= 384;
            i2 = displayMode;
        } else if (($changed & 384) == 0) {
            i2 = displayMode;
            $dirty |= $composer2.changed(i2) ? 256 : 128;
        } else {
            i2 = displayMode;
        }
        if ((i & 8) != 0) {
            $dirty |= 3072;
        } else if (($changed & 3072) == 0) {
            $dirty |= ($changed & 4096) == 0 ? $composer2.changed(dateFormatter) : $composer2.changedInstance(dateFormatter) ? 2048 : 1024;
        }
        int i3 = i & 16;
        if (i3 != 0) {
            $dirty |= 24576;
            modifier2 = modifier;
        } else if (($changed & 24576) == 0) {
            modifier2 = modifier;
            $dirty |= $composer2.changed(modifier2) ? 16384 : 8192;
        } else {
            modifier2 = modifier;
        }
        if ((196608 & $changed) == 0) {
            if ((i & 32) == 0) {
                contentColor2 = contentColor;
                int i4 = $composer2.changed(contentColor2) ? 131072 : 65536;
                $dirty |= i4;
            } else {
                contentColor2 = contentColor;
            }
            $dirty |= i4;
        } else {
            contentColor2 = contentColor;
        }
        if ((i & 64) != 0) {
            $dirty |= 1572864;
            dateRangePickerDefaults = this;
        } else if (($changed & 1572864) == 0) {
            dateRangePickerDefaults = this;
            $dirty |= $composer2.changed(dateRangePickerDefaults) ? 1048576 : 524288;
        } else {
            dateRangePickerDefaults = this;
        }
        if ($composer2.shouldExecute((599187 & $dirty) != 599186, $dirty & 1)) {
            $composer2.startDefaults();
            ComposerKt.sourceInformation($composer2, "408@18949L8");
            if (($changed & 1) != 0 && !$composer2.getDefaultsInvalid()) {
                $composer2.skipToGroupEnd();
                if ((i & 32) != 0) {
                    $dirty &= -458753;
                }
                modifier4 = modifier2;
            } else {
                if (i3 != 0) {
                    modifier4 = Modifier.INSTANCE;
                } else {
                    modifier4 = modifier2;
                }
                if ((i & 32) != 0) {
                    $dirty &= -458753;
                    contentColor2 = DatePickerDefaults.INSTANCE.colors($composer2, 6).getHeadlineContentColor();
                }
            }
            $composer2.endDefaults();
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(1655228151, $dirty, -1, "androidx.compose.material3.DateRangePickerDefaults.DateRangePickerHeadline (DateRangePicker.kt:409)");
            }
            Strings.Companion companion = Strings.INSTANCE;
            final String startDateText = Strings_androidKt.m3533getString2EP1pXo(Strings.m3454constructorimpl(R.string.m3c_date_range_picker_start_headline), $composer2, 0);
            Strings.Companion companion2 = Strings.INSTANCE;
            final String endDateText = Strings_androidKt.m3533getString2EP1pXo(Strings.m3454constructorimpl(R.string.m3c_date_range_picker_end_headline), $composer2, 0);
            int i5 = i2;
            Modifier modifier5 = modifier4;
            long contentColor4 = contentColor2;
            dateRangePickerDefaults.m2449DateRangePickerHeadlinenZrIstQ(l, l2, i5, dateFormatter, modifier5, contentColor4, startDateText, endDateText, ComposableLambdaKt.rememberComposableLambda(850203865, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.DateRangePickerDefaults$DateRangePickerHeadline$1
                @Override // kotlin.jvm.functions.Function2
                public /* bridge */ /* synthetic */ Unit invoke(Composer composer, Integer num) {
                    invoke(composer, num.intValue());
                    return Unit.INSTANCE;
                }

                public final void invoke(Composer $composer3, int $changed2) {
                    ComposerKt.sourceInformation($composer3, "C421@19566L48:DateRangePicker.kt#uh7d8r");
                    if (!$composer3.shouldExecute(($changed2 & 3) != 2, $changed2 & 1)) {
                        $composer3.skipToGroupEnd();
                        return;
                    }
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventStart(850203865, $changed2, -1, "androidx.compose.material3.DateRangePickerDefaults.DateRangePickerHeadline.<anonymous> (DateRangePicker.kt:421)");
                    }
                    TextKt.m3157TextNvy7gAk(startDateText, null, contentColor2, null, 0L, null, null, null, 0L, null, null, 0L, 0, false, 0, 0, null, null, $composer3, 0, 0, 262138);
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventEnd();
                    }
                }
            }, $composer2, 54), ComposableLambdaKt.rememberComposableLambda(282231642, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.DateRangePickerDefaults$DateRangePickerHeadline$2
                @Override // kotlin.jvm.functions.Function2
                public /* bridge */ /* synthetic */ Unit invoke(Composer composer, Integer num) {
                    invoke(composer, num.intValue());
                    return Unit.INSTANCE;
                }

                public final void invoke(Composer $composer3, int $changed2) {
                    ComposerKt.sourceInformation($composer3, "C422@19653L46:DateRangePicker.kt#uh7d8r");
                    if (!$composer3.shouldExecute(($changed2 & 3) != 2, $changed2 & 1)) {
                        $composer3.skipToGroupEnd();
                        return;
                    }
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventStart(282231642, $changed2, -1, "androidx.compose.material3.DateRangePickerDefaults.DateRangePickerHeadline.<anonymous> (DateRangePicker.kt:422)");
                    }
                    TextKt.m3157TextNvy7gAk(endDateText, null, contentColor2, null, 0L, null, null, null, 0L, null, null, 0L, 0, false, 0, 0, null, null, $composer3, 0, 0, 262138);
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventEnd();
                    }
                }
            }, $composer2, 54), ComposableLambdaKt.rememberComposableLambda(-320655704, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.DateRangePickerDefaults$DateRangePickerHeadline$3
                @Override // kotlin.jvm.functions.Function2
                public /* bridge */ /* synthetic */ Unit invoke(Composer composer, Integer num) {
                    invoke(composer, num.intValue());
                    return Unit.INSTANCE;
                }

                public final void invoke(Composer $composer3, int $changed2) {
                    ComposerKt.sourceInformation($composer3, "C423@19734L38:DateRangePicker.kt#uh7d8r");
                    if (!$composer3.shouldExecute(($changed2 & 3) != 2, $changed2 & 1)) {
                        $composer3.skipToGroupEnd();
                        return;
                    }
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventStart(-320655704, $changed2, -1, "androidx.compose.material3.DateRangePickerDefaults.DateRangePickerHeadline.<anonymous> (DateRangePicker.kt:423)");
                    }
                    TextKt.m3157TextNvy7gAk("-", null, contentColor2, null, 0L, null, null, null, 0L, null, null, 0L, 0, false, 0, 0, null, null, $composer3, 6, 0, 262138);
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventEnd();
                    }
                }
            }, $composer2, 54), CalendarLocale_androidKt.defaultLocale($composer2, 0), $composer2, (458752 & $dirty) | ($dirty & 14) | 905969664 | ($dirty & 112) | ($dirty & 896) | ($dirty & 7168) | (57344 & $dirty), (($dirty >> 12) & 896) | 6);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
            contentColor3 = contentColor4;
            modifier3 = modifier5;
        } else {
            $composer2.skipToGroupEnd();
            modifier3 = modifier2;
            contentColor3 = contentColor2;
        }
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup = $composer2.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: androidx.compose.material3.DateRangePickerDefaults$$ExternalSyntheticLambda3
                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj, Object obj2) {
                    return DateRangePickerDefaults.DateRangePickerHeadline_qS89cEg$lambda$1(this.f$0, selectedStartDateMillis, selectedEndDateMillis, displayMode, dateFormatter, modifier3, contentColor3, $changed, i, (Composer) obj, ((Integer) obj2).intValue());
                }
            });
        }
    }

    /* JADX INFO: renamed from: DateRangePickerHeadline-nZrIstQ, reason: not valid java name */
    private final void m2449DateRangePickerHeadlinenZrIstQ(Long selectedStartDateMillis, final Long selectedEndDateMillis, final int displayMode, final DatePickerFormatter dateFormatter, final Modifier modifier, final long contentColor, final String startDateText, final String endDateText, final Function2<? super Composer, ? super Integer, Unit> function2, final Function2<? super Composer, ? super Integer, Unit> function22, final Function2<? super Composer, ? super Integer, Unit> function23, final Locale locale, Composer $composer, final int $changed, final int $changed1) {
        int $dirty;
        Long l;
        String formatterEndDate;
        Function0<ComposeUiNode> function0;
        Composer $composer2 = $composer.startRestartGroup(1381313200);
        ComposerKt.sourceInformation($composer2, "C(DateRangePickerHeadline)N(selectedStartDateMillis,selectedEndDateMillis,displayMode:c#material3.DisplayMode,dateFormatter,modifier,contentColor:c#ui.graphics.Color,startDateText,endDateText,startDatePlaceholder,endDatePlaceholder,datesDelimiter,locale)504@23527L168,502@23453L792:DateRangePicker.kt#uh7d8r");
        int $dirty2 = $changed;
        int $dirty1 = $changed1;
        if (($changed & 6) == 0) {
            $dirty2 |= $composer2.changed(selectedStartDateMillis) ? 4 : 2;
        }
        if (($changed & 48) == 0) {
            $dirty2 |= $composer2.changed(selectedEndDateMillis) ? 32 : 16;
        }
        if (($changed & 384) == 0) {
            $dirty2 |= $composer2.changed(displayMode) ? 256 : 128;
        }
        if (($changed & 3072) == 0) {
            $dirty2 |= ($changed & 4096) == 0 ? $composer2.changed(dateFormatter) : $composer2.changedInstance(dateFormatter) ? 2048 : 1024;
        }
        if (($changed & 24576) == 0) {
            $dirty2 |= $composer2.changed(modifier) ? 16384 : 8192;
        }
        if ((196608 & $changed) == 0) {
            $dirty = $dirty2 | ($composer2.changed(contentColor) ? 131072 : 65536);
        } else {
            $dirty = $dirty2;
        }
        if (($changed & 1572864) == 0) {
            $dirty |= $composer2.changed(startDateText) ? 1048576 : 524288;
        }
        if (($changed & 12582912) == 0) {
            $dirty |= $composer2.changed(endDateText) ? 8388608 : 4194304;
        }
        if (($changed & 100663296) == 0) {
            $dirty |= $composer2.changedInstance(function2) ? 67108864 : GroupFlagsKt.HasAuxSlotFlag;
        }
        if (($changed & 805306368) == 0) {
            $dirty |= $composer2.changedInstance(function22) ? GroupFlagsKt.HasMovableContentFlag : GroupFlagsKt.IsMovableContentFlag;
        }
        int $dirty3 = $dirty;
        if (($changed1 & 6) == 0) {
            $dirty1 |= $composer2.changedInstance(function23) ? 4 : 2;
        }
        if (($changed1 & 48) == 0) {
            $dirty1 |= $composer2.changedInstance(locale) ? 32 : 16;
        }
        int $dirty12 = $dirty1;
        if (!$composer2.shouldExecute(((306783379 & $dirty3) == 306783378 && ($dirty12 & 19) == 18) ? false : true, $dirty3 & 1)) {
            l = selectedStartDateMillis;
            $composer2.skipToGroupEnd();
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(1381313200, $dirty3, $dirty12, "androidx.compose.material3.DateRangePickerDefaults.DateRangePickerHeadline (DateRangePicker.kt:468)");
            }
            String formatterStartDate = DatePickerFormatter.formatDate$default(dateFormatter, selectedStartDateMillis, locale, false, 4, null);
            l = selectedStartDateMillis;
            String formatterEndDate2 = DatePickerFormatter.formatDate$default(dateFormatter, selectedEndDateMillis, locale, false, 4, null);
            String verboseStartDateDescription = dateFormatter.formatDate(l, locale, true);
            String str = "";
            if (verboseStartDateDescription != null) {
                formatterEndDate = formatterEndDate2;
                $composer2.startReplaceGroup(297117483);
                $composer2.endReplaceGroup();
            } else {
                $composer2.startReplaceGroup(620891895);
                ComposerKt.sourceInformation($composer2, "");
                if (DisplayMode.m2476equalsimpl0(displayMode, DisplayMode.INSTANCE.m2481getPickerjFl4v0())) {
                    $composer2.startReplaceGroup(297125251);
                    ComposerKt.sourceInformation($composer2, "482@22593L51");
                    Strings.Companion companion = Strings.INSTANCE;
                    formatterEndDate = formatterEndDate2;
                    verboseStartDateDescription = Strings_androidKt.m3533getString2EP1pXo(Strings.m3454constructorimpl(R.string.m3c_date_picker_no_selection_description), $composer2, 0);
                    $composer2.endReplaceGroup();
                } else {
                    formatterEndDate = formatterEndDate2;
                    if (DisplayMode.m2476equalsimpl0(displayMode, DisplayMode.INSTANCE.m2480getInputjFl4v0())) {
                        $composer2.startReplaceGroup(297128222);
                        ComposerKt.sourceInformation($composer2, "483@22686L46");
                        Strings.Companion companion2 = Strings.INSTANCE;
                        String strM3533getString2EP1pXo = Strings_androidKt.m3533getString2EP1pXo(Strings.m3454constructorimpl(R.string.m3c_date_input_no_input_description), $composer2, 0);
                        $composer2.endReplaceGroup();
                        verboseStartDateDescription = strM3533getString2EP1pXo;
                    } else {
                        $composer2.startReplaceGroup(621113326);
                        $composer2.endReplaceGroup();
                        verboseStartDateDescription = "";
                    }
                }
                $composer2.endReplaceGroup();
            }
            String verboseEndDateDescription = dateFormatter.formatDate(selectedEndDateMillis, locale, true);
            if (verboseEndDateDescription != null) {
                $composer2.startReplaceGroup(297133385);
                $composer2.endReplaceGroup();
            } else {
                $composer2.startReplaceGroup(621382935);
                ComposerKt.sourceInformation($composer2, "");
                if (DisplayMode.m2476equalsimpl0(displayMode, DisplayMode.INSTANCE.m2481getPickerjFl4v0())) {
                    $composer2.startReplaceGroup(297141091);
                    ComposerKt.sourceInformation($composer2, "494@23088L51");
                    Strings.Companion companion3 = Strings.INSTANCE;
                    String strM3533getString2EP1pXo2 = Strings_androidKt.m3533getString2EP1pXo(Strings.m3454constructorimpl(R.string.m3c_date_picker_no_selection_description), $composer2, 0);
                    $composer2.endReplaceGroup();
                    str = strM3533getString2EP1pXo2;
                } else if (DisplayMode.m2476equalsimpl0(displayMode, DisplayMode.INSTANCE.m2480getInputjFl4v0())) {
                    $composer2.startReplaceGroup(297144062);
                    ComposerKt.sourceInformation($composer2, "495@23181L46");
                    Strings.Companion companion4 = Strings.INSTANCE;
                    String strM3533getString2EP1pXo3 = Strings_androidKt.m3533getString2EP1pXo(Strings.m3454constructorimpl(R.string.m3c_date_input_no_input_description), $composer2, 0);
                    $composer2.endReplaceGroup();
                    str = strM3533getString2EP1pXo3;
                } else {
                    $composer2.startReplaceGroup(621604366);
                    $composer2.endReplaceGroup();
                }
                $composer2.endReplaceGroup();
                verboseEndDateDescription = str;
            }
            final String startHeadlineDescription = startDateText + ": " + verboseStartDateDescription;
            final String endHeadlineDescription = endDateText + ": " + verboseEndDateDescription;
            ComposerKt.sourceInformationMarkerStart($composer2, 297155256, "CC(remember):DateRangePicker.kt#9igjgp");
            boolean invalid$iv = $composer2.changed(startHeadlineDescription) | $composer2.changed(endHeadlineDescription);
            Object value$iv = $composer2.rememberedValue();
            if (invalid$iv || value$iv == Composer.INSTANCE.getEmpty()) {
                value$iv = new Function1() { // from class: androidx.compose.material3.DateRangePickerDefaults$$ExternalSyntheticLambda0
                    @Override // kotlin.jvm.functions.Function1
                    public final Object invoke(Object obj) {
                        return DateRangePickerDefaults.DateRangePickerHeadline_nZrIstQ$lambda$3$lambda$2(startHeadlineDescription, endHeadlineDescription, (SemanticsPropertyReceiver) obj);
                    }
                };
                $composer2.updateRememberedValue(value$iv);
            }
            ComposerKt.sourceInformationMarkerEnd($composer2);
            Modifier modifier$iv$iv = SemanticsModifierKt.clearAndSetSemantics(modifier, (Function1) value$iv);
            Alignment.Vertical centerVertically = Alignment.INSTANCE.getCenterVertically();
            Arrangement.HorizontalOrVertical horizontalOrVerticalM740spacedBy0680j_4 = Arrangement.INSTANCE.m740spacedBy0680j_4(Dp.m8150constructorimpl(4));
            ComposerKt.sourceInformationMarkerStart($composer2, 693286680, "CC(Row)P(2,1,3)99@5124L58,100@5187L130:Row.kt#2w3rfo");
            MeasurePolicy measurePolicy$iv = RowKt.rowMeasurePolicy(horizontalOrVerticalM740spacedBy0680j_4, centerVertically, $composer2, ((432 >> 3) & 14) | ((432 >> 3) & 112));
            int $changed$iv$iv = (432 << 3) & 112;
            ComposerKt.sourceInformationMarkerStart($composer2, -1323940314, "CC(Layout)P(!1,2)79@3206L23,82@3357L359:Layout.kt#80mrfh");
            int compositeKeyHash$iv$iv = ComposablesKt.getCurrentCompositeKeyHash($composer2, 0);
            CompositionLocalMap localMap$iv$iv = $composer2.getCurrentCompositionLocalMap();
            Modifier materialized$iv$iv = ComposedModifierKt.materializeModifier($composer2, modifier$iv$iv);
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
            Updater.m4441setimpl($this$Layout_u24lambda_u240$iv$iv, measurePolicy$iv, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
            Updater.m4441setimpl($this$Layout_u24lambda_u240$iv$iv, localMap$iv$iv, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
            Function2<ComposeUiNode, Integer, Unit> setCompositeKeyHash = ComposeUiNode.INSTANCE.getSetCompositeKeyHash();
            if ($this$Layout_u24lambda_u240$iv$iv.getInserting() || !Intrinsics.areEqual($this$Layout_u24lambda_u240$iv$iv.rememberedValue(), Integer.valueOf(compositeKeyHash$iv$iv))) {
                $this$Layout_u24lambda_u240$iv$iv.updateRememberedValue(Integer.valueOf(compositeKeyHash$iv$iv));
                $this$Layout_u24lambda_u240$iv$iv.apply(Integer.valueOf(compositeKeyHash$iv$iv), setCompositeKeyHash);
            }
            Updater.m4441setimpl($this$Layout_u24lambda_u240$iv$iv, materialized$iv$iv, ComposeUiNode.INSTANCE.getSetModifier());
            int i = ($changed$iv$iv$iv >> 6) & 14;
            ComposerKt.sourceInformationMarkerStart($composer2, -407735110, "C101@5232L9:Row.kt#2w3rfo");
            RowScopeInstance rowScopeInstance = RowScopeInstance.INSTANCE;
            int i2 = ((432 >> 6) & 112) | 6;
            ComposerKt.sourceInformationMarkerStart($composer2, -177408791, "C516@24035L16:DateRangePicker.kt#uh7d8r");
            if (formatterStartDate != null) {
                $composer2.startReplaceGroup(-177386503);
                ComposerKt.sourceInformation($composer2, "512@23895L53");
                TextKt.m3157TextNvy7gAk(formatterStartDate, null, contentColor, null, 0L, null, null, null, 0L, null, null, 0L, 0, false, 0, 0, null, null, $composer2, ($dirty3 >> 9) & 896, 0, 262138);
                $composer2.endReplaceGroup();
            } else {
                $composer2.startReplaceGroup(-177297192);
                ComposerKt.sourceInformation($composer2, "514@23986L22");
                function2.invoke($composer2, Integer.valueOf(($dirty3 >> 24) & 14));
                $composer2.endReplaceGroup();
            }
            function23.invoke($composer2, Integer.valueOf($dirty12 & 14));
            if (formatterEndDate != null) {
                $composer2.startReplaceGroup(-177171301);
                ComposerKt.sourceInformation($composer2, "518@24112L51");
                TextKt.m3157TextNvy7gAk(formatterEndDate, null, contentColor, null, 0L, null, null, null, 0L, null, null, 0L, 0, false, 0, 0, null, null, $composer2, ($dirty3 >> 9) & 896, 0, 262138);
                $composer2.endReplaceGroup();
            } else {
                $composer2.startReplaceGroup(-177083974);
                ComposerKt.sourceInformation($composer2, "520@24201L20");
                function22.invoke($composer2, Integer.valueOf(($dirty3 >> 27) & 14));
                $composer2.endReplaceGroup();
            }
            ComposerKt.sourceInformationMarkerEnd($composer2);
            ComposerKt.sourceInformationMarkerEnd($composer2);
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
            final Long l2 = l;
            scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: androidx.compose.material3.DateRangePickerDefaults$$ExternalSyntheticLambda1
                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj, Object obj2) {
                    return DateRangePickerDefaults.DateRangePickerHeadline_nZrIstQ$lambda$5(this.f$0, l2, selectedEndDateMillis, displayMode, dateFormatter, modifier, contentColor, startDateText, endDateText, function2, function22, function23, locale, $changed, $changed1, (Composer) obj, ((Integer) obj2).intValue());
                }
            });
        }
    }

    static final Unit DateRangePickerHeadline_nZrIstQ$lambda$3$lambda$2(String $startHeadlineDescription, String $endHeadlineDescription, SemanticsPropertyReceiver $this$clearAndSetSemantics) {
        SemanticsPropertiesKt.m7361setLiveRegionhR3wRGc($this$clearAndSetSemantics, LiveRegionMode.INSTANCE.m7335getPolite0phEisY());
        SemanticsPropertiesKt.setContentDescription($this$clearAndSetSemantics, $startHeadlineDescription + ", " + $endHeadlineDescription);
        return Unit.INSTANCE;
    }
}
