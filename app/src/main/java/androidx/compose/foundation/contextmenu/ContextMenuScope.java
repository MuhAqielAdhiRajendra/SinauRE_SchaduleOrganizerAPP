package androidx.compose.foundation.contextmenu;

import androidx.autofill.HintConstants;
import androidx.compose.foundation.internal.InlineClassHelperKt;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.RecomposeScopeImplKt;
import androidx.compose.runtime.ScopeUpdateScope;
import androidx.compose.runtime.SnapshotStateKt;
import androidx.compose.runtime.internal.ComposableLambdaKt;
import androidx.compose.runtime.snapshots.SnapshotStateList;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.graphics.Color;
import java.util.List;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.functions.Function8;
import kotlin.text.StringsKt;

/* JADX INFO: compiled from: ContextMenuUi.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000P\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\t\b\u0001\u0018\u00002\u00020\u0001BÄ\u0001\b\u0000\u0012¸\u0001\u0010\u0002\u001a³\u0001\u0012\u0013\u0012\u00110\u0004¢\u0006\f\b\u0005\u0012\b\b\u0006\u0012\u0004\b\b(\u0007\u0012\u0013\u0012\u00110\b¢\u0006\f\b\u0005\u0012\b\b\u0006\u0012\u0004\b\b(\t\u0012\u0013\u0012\u00110\n¢\u0006\f\b\u0005\u0012\b\b\u0006\u0012\u0004\b\b(\u000b\u0012\u0013\u0012\u00110\f¢\u0006\f\b\u0005\u0012\b\b\u0006\u0012\u0004\b\b(\r\u00125\u00123\u0012\u0013\u0012\u00110\u000f¢\u0006\f\b\u0005\u0012\b\b\u0006\u0012\u0004\b\b(\u0010\u0012\u0004\u0012\u00020\u0011\u0018\u00010\u000e¢\u0006\u0002\b\u0012¢\u0006\f\b\u0005\u0012\b\b\u0006\u0012\u0004\b\b(\u0013\u0012\u0019\u0012\u0017\u0012\u0004\u0012\u00020\u00110\u0014¢\u0006\f\b\u0005\u0012\b\b\u0006\u0012\u0004\b\b(\u0015\u0012\u0004\u0012\u00020\u00110\u0003¢\u0006\u0002\b\u0012¢\u0006\u0004\b\u0016\u0010\u0017J\u0017\u0010\u001b\u001a\u00020\u00112\u0006\u0010\r\u001a\u00020\fH\u0001¢\u0006\u0004\b\u001c\u0010\u001dJ\r\u0010\u001e\u001a\u00020\u0011H\u0000¢\u0006\u0002\b\u001fJn\u0010 \u001a\u00020\u00112\u0011\u0010\t\u001a\r\u0012\u0004\u0012\u00020\b0\u0014¢\u0006\u0002\b\u00122\b\b\u0002\u0010\u0007\u001a\u00020\u00042\b\b\u0002\u0010\u000b\u001a\u00020\n2*\b\u0002\u0010\u0013\u001a$\u0012\u0013\u0012\u00110\u000f¢\u0006\f\b\u0005\u0012\b\b\u0006\u0012\u0004\b\b(\u0010\u0012\u0004\u0012\u00020\u0011\u0018\u00010\u000e¢\u0006\u0002\b\u00122\f\u0010\u0015\u001a\b\u0012\u0004\u0012\u00020\u00110\u0014H\u0007¢\u0006\u0002\u0010!J\u0006\u0010\"\u001a\u00020\u0011RÂ\u0001\u0010\u0002\u001a³\u0001\u0012\u0013\u0012\u00110\u0004¢\u0006\f\b\u0005\u0012\b\b\u0006\u0012\u0004\b\b(\u0007\u0012\u0013\u0012\u00110\b¢\u0006\f\b\u0005\u0012\b\b\u0006\u0012\u0004\b\b(\t\u0012\u0013\u0012\u00110\n¢\u0006\f\b\u0005\u0012\b\b\u0006\u0012\u0004\b\b(\u000b\u0012\u0013\u0012\u00110\f¢\u0006\f\b\u0005\u0012\b\b\u0006\u0012\u0004\b\b(\r\u00125\u00123\u0012\u0013\u0012\u00110\u000f¢\u0006\f\b\u0005\u0012\b\b\u0006\u0012\u0004\b\b(\u0010\u0012\u0004\u0012\u00020\u0011\u0018\u00010\u000e¢\u0006\u0002\b\u0012¢\u0006\f\b\u0005\u0012\b\b\u0006\u0012\u0004\b\b(\u0013\u0012\u0019\u0012\u0017\u0012\u0004\u0012\u00020\u00110\u0014¢\u0006\f\b\u0005\u0012\b\b\u0006\u0012\u0004\b\b(\u0015\u0012\u0004\u0012\u00020\u00110\u0003¢\u0006\u0002\b\u0012X\u0082\u0004¢\u0006\u0004\n\u0002\u0010\u0018R4\u0010\u0019\u001a(\u0012$\u0012\"\u0012\u0013\u0012\u00110\f¢\u0006\f\b\u0005\u0012\b\b\u0006\u0012\u0004\b\b(\r\u0012\u0004\u0012\u00020\u00110\u000e¢\u0006\u0002\b\u00120\u001aX\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006#"}, d2 = {"Landroidx/compose/foundation/contextmenu/ContextMenuScope;", "", "itemUi", "Lkotlin/Function6;", "Landroidx/compose/ui/Modifier;", "Lkotlin/ParameterName;", HintConstants.AUTOFILL_HINT_NAME, "modifier", "", "label", "", "enabled", "Landroidx/compose/foundation/contextmenu/ContextMenuColors;", "colors", "Lkotlin/Function1;", "Landroidx/compose/ui/graphics/Color;", "iconColor", "", "Landroidx/compose/runtime/Composable;", "leadingIcon", "Lkotlin/Function0;", "onClick", "<init>", "(Lkotlin/jvm/functions/Function8;)V", "Lkotlin/jvm/functions/Function8;", "composables", "Landroidx/compose/runtime/snapshots/SnapshotStateList;", "Content", "Content$foundation", "(Landroidx/compose/foundation/contextmenu/ContextMenuColors;Landroidx/compose/runtime/Composer;I)V", "clear", "clear$foundation", "item", "(Lkotlin/jvm/functions/Function2;Landroidx/compose/ui/Modifier;ZLkotlin/jvm/functions/Function3;Lkotlin/jvm/functions/Function0;)V", "separator", "foundation"}, k = 1, mv = {2, 1, 0}, xi = 48)
public final class ContextMenuScope {
    public static final int $stable = 0;
    private final SnapshotStateList<Function3<ContextMenuColors, Composer, Integer, Unit>> composables = SnapshotStateKt.mutableStateListOf();
    private final Function8<Modifier, String, Boolean, ContextMenuColors, Function3<? super Color, ? super Composer, ? super Integer, Unit>, Function0<Unit>, Composer, Integer, Unit> itemUi;

    static final Unit Content$lambda$1(ContextMenuScope contextMenuScope, ContextMenuColors contextMenuColors, int i, Composer composer, int i2) {
        contextMenuScope.Content$foundation(contextMenuColors, composer, RecomposeScopeImplKt.updateChangedFlags(i | 1));
        return Unit.INSTANCE;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public ContextMenuScope(Function8<? super Modifier, ? super String, ? super Boolean, ? super ContextMenuColors, ? super Function3<? super Color, ? super Composer, ? super Integer, Unit>, ? super Function0<Unit>, ? super Composer, ? super Integer, Unit> function8) {
        this.itemUi = function8;
    }

    public final void Content$foundation(final ContextMenuColors colors, Composer $composer, final int $changed) {
        Composer $composer2 = $composer.startRestartGroup(-798501095);
        ComposerKt.sourceInformation($composer2, "C(Content)N(colors)*256@9261L18:ContextMenuUi.kt#3xeu6s");
        int $dirty = $changed;
        if (($changed & 6) == 0) {
            $dirty |= $composer2.changed(colors) ? 4 : 2;
        }
        if (($changed & 48) == 0) {
            $dirty |= $composer2.changed(this) ? 32 : 16;
        }
        if (!$composer2.shouldExecute(($dirty & 19) != 18, $dirty & 1)) {
            $composer2.skipToGroupEnd();
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-798501095, $dirty, -1, "androidx.compose.foundation.contextmenu.ContextMenuScope.Content (ContextMenuUi.kt:255)");
            }
            List $this$fastForEach$iv = this.composables;
            int size = $this$fastForEach$iv.size();
            for (int index$iv = 0; index$iv < size; index$iv++) {
                Object item$iv = $this$fastForEach$iv.get(index$iv);
                ((Function3) item$iv).invoke(colors, $composer2, Integer.valueOf($dirty & 14));
            }
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup = $composer2.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: androidx.compose.foundation.contextmenu.ContextMenuScope$$ExternalSyntheticLambda1
                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj, Object obj2) {
                    return ContextMenuScope.Content$lambda$1(this.f$0, colors, $changed, (Composer) obj, ((Integer) obj2).intValue());
                }
            });
        }
    }

    public final void clear$foundation() {
        this.composables.clear();
    }

    public final void item(final Function2<? super Composer, ? super Integer, String> label, final Modifier modifier, final boolean enabled, final Function3<? super Color, ? super Composer, ? super Integer, Unit> leadingIcon, final Function0<Unit> onClick) {
        this.composables.add(ComposableLambdaKt.composableLambdaInstance(-1789283891, true, new Function3() { // from class: androidx.compose.foundation.contextmenu.ContextMenuScope$$ExternalSyntheticLambda0
            @Override // kotlin.jvm.functions.Function3
            public final Object invoke(Object obj, Object obj2, Object obj3) {
                return ContextMenuScope.item$lambda$0(label, this, modifier, enabled, leadingIcon, onClick, (ContextMenuColors) obj, (Composer) obj2, ((Integer) obj3).intValue());
            }
        }));
    }

    static final Unit item$lambda$0(Function2 $label, ContextMenuScope this$0, Modifier $modifier, boolean $enabled, Function3 $leadingIcon, Function0 $onClick, ContextMenuColors colors, Composer $composer, int $changed) {
        ComposerKt.sourceInformation($composer, "CN(colors)297@11061L7,299@11169L70:ContextMenuUi.kt#3xeu6s");
        int $dirty = $changed;
        if (($changed & 6) == 0) {
            $dirty |= $composer.changed(colors) ? 4 : 2;
        }
        int $dirty2 = $dirty;
        if (!$composer.shouldExecute(($dirty2 & 19) != 18, $dirty2 & 1)) {
            $composer.skipToGroupEnd();
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-1789283891, $dirty2, -1, "androidx.compose.foundation.contextmenu.ContextMenuScope.item.<anonymous> (ContextMenuUi.kt:297)");
            }
            String resolvedLabel = (String) $label.invoke($composer, 0);
            boolean value$iv = !StringsKt.isBlank(resolvedLabel);
            if (!value$iv) {
                InlineClassHelperKt.throwIllegalStateException("Label must not be blank");
            }
            this$0.itemUi.invoke($modifier, resolvedLabel, Boolean.valueOf($enabled), colors, $leadingIcon, $onClick, $composer, Integer.valueOf(($dirty2 << 9) & 7168));
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        return Unit.INSTANCE;
    }

    public final void separator() {
        this.composables.add(ComposableSingletons$ContextMenuUiKt.INSTANCE.m392getLambda$1455401925$foundation());
    }
}
