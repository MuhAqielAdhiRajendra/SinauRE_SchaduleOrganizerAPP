package androidx.compose.foundation.text.contextmenu.internal;

import android.app.PendingIntent;
import android.app.RemoteAction;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.Icon;
import android.view.textclassifier.TextClassification;
import androidx.compose.foundation.contextmenu.ContextMenuScope;
import androidx.compose.foundation.contextmenu.ContextMenuSpec;
import androidx.compose.foundation.layout.BoxKt;
import androidx.compose.foundation.layout.SizeKt;
import androidx.compose.foundation.text.contextmenu.data.TextContextMenuRemoteActionItem;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.ProvidableCompositionLocal;
import androidx.compose.runtime.RecomposeScopeImplKt;
import androidx.compose.runtime.ScopeUpdateScope;
import androidx.compose.runtime.internal.ComposableLambda;
import androidx.compose.runtime.internal.ComposableLambdaKt;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.draw.DrawModifierKt;
import androidx.compose.ui.graphics.AndroidCanvas_androidKt;
import androidx.compose.ui.graphics.Canvas;
import androidx.compose.ui.graphics.Color;
import androidx.compose.ui.graphics.drawscope.DrawScope;
import androidx.compose.ui.platform.AndroidCompositionLocals_androidKt;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function3;

/* JADX INFO: compiled from: DefaultTextContextMenuDropdownProvider.android.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\bÃ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u001c\u0010\u0004\u001a\u00020\u0005*\u00020\u00062\b\u0010\u0007\u001a\u0004\u0018\u00010\b2\u0006\u0010\t\u001a\u00020\nJ\u0015\u0010\u000b\u001a\u00020\u00052\u0006\u0010\f\u001a\u00020\rH\u0003¢\u0006\u0002\u0010\u000eJ\u0015\u0010\u000b\u001a\u00020\u00052\u0006\u0010\u000f\u001a\u00020\u0010H\u0003¢\u0006\u0002\u0010\u0011¨\u0006\u0012"}, d2 = {"Landroidx/compose/foundation/text/contextmenu/internal/TextContextMenuHelperApi28;", "", "<init>", "()V", "textClassificationItem", "", "Landroidx/compose/foundation/contextmenu/ContextMenuScope;", "context", "Landroid/content/Context;", "component", "Landroidx/compose/foundation/text/contextmenu/data/TextContextMenuTextClassificationItem;", "IconBox", "icon", "Landroid/graphics/drawable/Icon;", "(Landroid/graphics/drawable/Icon;Landroidx/compose/runtime/Composer;I)V", "drawable", "Landroid/graphics/drawable/Drawable;", "(Landroid/graphics/drawable/Drawable;Landroidx/compose/runtime/Composer;I)V", "foundation"}, k = 1, mv = {2, 1, 0}, xi = 48)
final class TextContextMenuHelperApi28 {
    public static final TextContextMenuHelperApi28 INSTANCE = new TextContextMenuHelperApi28();

    static final Unit IconBox$lambda$1(TextContextMenuHelperApi28 textContextMenuHelperApi28, Icon icon, int i, Composer composer, int i2) {
        textContextMenuHelperApi28.IconBox(icon, composer, RecomposeScopeImplKt.updateChangedFlags(i | 1));
        return Unit.INSTANCE;
    }

    static final Unit IconBox$lambda$2(TextContextMenuHelperApi28 textContextMenuHelperApi28, Icon icon, int i, Composer composer, int i2) {
        textContextMenuHelperApi28.IconBox(icon, composer, RecomposeScopeImplKt.updateChangedFlags(i | 1));
        return Unit.INSTANCE;
    }

    static final Unit IconBox$lambda$4(TextContextMenuHelperApi28 textContextMenuHelperApi28, Drawable drawable, int i, Composer composer, int i2) {
        textContextMenuHelperApi28.IconBox(drawable, composer, RecomposeScopeImplKt.updateChangedFlags(i | 1));
        return Unit.INSTANCE;
    }

    private TextContextMenuHelperApi28() {
    }

    public final void textClassificationItem(ContextMenuScope $this$textClassificationItem, final Context context, TextContextMenuRemoteActionItem component) {
        ComposableLambda composableLambdaComposableLambdaInstance;
        ComposableLambda composableLambdaComposableLambdaInstance2;
        if (context == null) {
            return;
        }
        int index = component.getIndex();
        final TextClassification textClassification = component.getTextClassification();
        if (index < 0) {
            Function2 function2 = new Function2() { // from class: androidx.compose.foundation.text.contextmenu.internal.TextContextMenuHelperApi28$$ExternalSyntheticLambda2
                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj, Object obj2) {
                    return TextContextMenuHelperApi28.textClassificationItem$lambda$0(textClassification, (Composer) obj, ((Integer) obj2).intValue());
                }
            };
            final Drawable icon = textClassification.getIcon();
            if (icon == null) {
                composableLambdaComposableLambdaInstance2 = null;
            } else {
                composableLambdaComposableLambdaInstance2 = ComposableLambdaKt.composableLambdaInstance(-1123224187, true, new Function3<Color, Composer, Integer, Unit>() { // from class: androidx.compose.foundation.text.contextmenu.internal.TextContextMenuHelperApi28$textClassificationItem$2$1
                    @Override // kotlin.jvm.functions.Function3
                    public /* bridge */ /* synthetic */ Unit invoke(Color color, Composer composer, Integer num) {
                        m1700invokeek8zF_U(color.m5323unboximpl(), composer, num.intValue());
                        return Unit.INSTANCE;
                    }

                    /* JADX INFO: renamed from: invoke-ek8zF_U, reason: not valid java name */
                    public final void m1700invokeek8zF_U(long color, Composer $composer, int $changed) {
                        ComposerKt.sourceInformation($composer, "CN(color:c#ui.graphics.Color)247@9913L13:DefaultTextContextMenuDropdownProvider.android.kt#18dpbw");
                        if (!$composer.shouldExecute(($changed & 17) != 16, $changed & 1)) {
                            $composer.skipToGroupEnd();
                            return;
                        }
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventStart(-1123224187, $changed, -1, "androidx.compose.foundation.text.contextmenu.internal.TextContextMenuHelperApi28.textClassificationItem.<anonymous>.<anonymous> (DefaultTextContextMenuDropdownProvider.android.kt:247)");
                        }
                        TextContextMenuHelperApi28.INSTANCE.IconBox(icon, $composer, 48);
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventEnd();
                        }
                    }
                });
            }
            $this$textClassificationItem.item(function2, (14 & 2) != 0 ? Modifier.INSTANCE : null, (14 & 4) != 0, (14 & 8) != 0 ? null : composableLambdaComposableLambdaInstance2, new Function0() { // from class: androidx.compose.foundation.text.contextmenu.internal.TextContextMenuHelperApi28$$ExternalSyntheticLambda3
                @Override // kotlin.jvm.functions.Function0
                public final Object invoke() {
                    return TextContextMenuHelperApi28.textClassificationItem$lambda$2(context, textClassification);
                }
            });
            return;
        }
        final RemoteAction action = textClassification.getActions().get(index);
        boolean isPrimary = index == 0;
        Function2 function22 = new Function2() { // from class: androidx.compose.foundation.text.contextmenu.internal.TextContextMenuHelperApi28$$ExternalSyntheticLambda4
            @Override // kotlin.jvm.functions.Function2
            public final Object invoke(Object obj, Object obj2) {
                return TextContextMenuHelperApi28.textClassificationItem$lambda$3(action, (Composer) obj, ((Integer) obj2).intValue());
            }
        };
        if (isPrimary || action.shouldShowIcon()) {
            composableLambdaComposableLambdaInstance = ComposableLambdaKt.composableLambdaInstance(-1261173016, true, new Function3<Color, Composer, Integer, Unit>() { // from class: androidx.compose.foundation.text.contextmenu.internal.TextContextMenuHelperApi28.textClassificationItem.5
                final /* synthetic */ RemoteAction $action;

                AnonymousClass5(final RemoteAction action2) {
                    remoteAction = action2;
                }

                @Override // kotlin.jvm.functions.Function3
                public /* bridge */ /* synthetic */ Unit invoke(Color color, Composer composer, Integer num) {
                    m1701invokeek8zF_U(color.m5323unboximpl(), composer, num.intValue());
                    return Unit.INSTANCE;
                }

                /* JADX INFO: renamed from: invoke-ek8zF_U */
                public final void m1701invokeek8zF_U(long it, Composer $composer, int $changed) {
                    ComposerKt.sourceInformation($composer, "CN(it:c#ui.graphics.Color)257@10329L20:DefaultTextContextMenuDropdownProvider.android.kt#18dpbw");
                    if (!$composer.shouldExecute(($changed & 17) != 16, $changed & 1)) {
                        $composer.skipToGroupEnd();
                        return;
                    }
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventStart(-1261173016, $changed, -1, "androidx.compose.foundation.text.contextmenu.internal.TextContextMenuHelperApi28.textClassificationItem.<anonymous> (DefaultTextContextMenuDropdownProvider.android.kt:257)");
                    }
                    TextContextMenuHelperApi28.INSTANCE.IconBox(remoteAction.getIcon(), $composer, 48);
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventEnd();
                    }
                }
            });
        } else {
            composableLambdaComposableLambdaInstance = null;
        }
        $this$textClassificationItem.item(function22, (14 & 2) != 0 ? Modifier.INSTANCE : null, (14 & 4) != 0, (14 & 8) != 0 ? null : composableLambdaComposableLambdaInstance, new Function0() { // from class: androidx.compose.foundation.text.contextmenu.internal.TextContextMenuHelperApi28$$ExternalSyntheticLambda5
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                return TextContextMenuHelperApi28.textClassificationItem$lambda$4(action2);
            }
        });
    }

    static final String textClassificationItem$lambda$0(TextClassification $textClassification, Composer $composer, int $changed) {
        $composer.startReplaceGroup(950061013);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(950061013, $changed, -1, "androidx.compose.foundation.text.contextmenu.internal.TextContextMenuHelperApi28.textClassificationItem.<anonymous> (DefaultTextContextMenuDropdownProvider.android.kt:246)");
        }
        String strValueOf = String.valueOf($textClassification.getLabel());
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        $composer.endReplaceGroup();
        return strValueOf;
    }

    static final Unit textClassificationItem$lambda$2(Context $context, TextClassification $textClassification) throws PendingIntent.CanceledException {
        TextClassificationHelperApi28.INSTANCE.sendLegacyIntent($context, $textClassification);
        return Unit.INSTANCE;
    }

    static final String textClassificationItem$lambda$3(RemoteAction $action, Composer $composer, int $changed) {
        $composer.startReplaceGroup(-1376593684);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(-1376593684, $changed, -1, "androidx.compose.foundation.text.contextmenu.internal.TextContextMenuHelperApi28.textClassificationItem.<anonymous> (DefaultTextContextMenuDropdownProvider.android.kt:254)");
        }
        String string = $action.getTitle().toString();
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        $composer.endReplaceGroup();
        return string;
    }

    /* JADX INFO: renamed from: androidx.compose.foundation.text.contextmenu.internal.TextContextMenuHelperApi28$textClassificationItem$5 */
    /* JADX INFO: compiled from: DefaultTextContextMenuDropdownProvider.android.kt */
    @Metadata(k = 3, mv = {2, 1, 0}, xi = 48)
    static final class AnonymousClass5 implements Function3<Color, Composer, Integer, Unit> {
        final /* synthetic */ RemoteAction $action;

        AnonymousClass5(final RemoteAction action2) {
            remoteAction = action2;
        }

        @Override // kotlin.jvm.functions.Function3
        public /* bridge */ /* synthetic */ Unit invoke(Color color, Composer composer, Integer num) {
            m1701invokeek8zF_U(color.m5323unboximpl(), composer, num.intValue());
            return Unit.INSTANCE;
        }

        /* JADX INFO: renamed from: invoke-ek8zF_U */
        public final void m1701invokeek8zF_U(long it, Composer $composer, int $changed) {
            ComposerKt.sourceInformation($composer, "CN(it:c#ui.graphics.Color)257@10329L20:DefaultTextContextMenuDropdownProvider.android.kt#18dpbw");
            if (!$composer.shouldExecute(($changed & 17) != 16, $changed & 1)) {
                $composer.skipToGroupEnd();
                return;
            }
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-1261173016, $changed, -1, "androidx.compose.foundation.text.contextmenu.internal.TextContextMenuHelperApi28.textClassificationItem.<anonymous> (DefaultTextContextMenuDropdownProvider.android.kt:257)");
            }
            TextContextMenuHelperApi28.INSTANCE.IconBox(remoteAction.getIcon(), $composer, 48);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
    }

    static final Unit textClassificationItem$lambda$4(RemoteAction $action) throws PendingIntent.CanceledException {
        TextClassificationHelperApi28.INSTANCE.sendPendingIntent($action.getActionIntent());
        return Unit.INSTANCE;
    }

    public final void IconBox(final Icon icon, Composer $composer, final int $changed) {
        Composer $composer2 = $composer.startRestartGroup(2116504409);
        ComposerKt.sourceInformation($composer2, "C(IconBox)N(icon)268@10623L7,269@10654L54,270@10727L17:DefaultTextContextMenuDropdownProvider.android.kt#18dpbw");
        int $dirty = $changed;
        if (($changed & 6) == 0) {
            $dirty |= $composer2.changedInstance(icon) ? 4 : 2;
        }
        if (($changed & 48) == 0) {
            $dirty |= $composer2.changed(this) ? 32 : 16;
        }
        if ($composer2.shouldExecute(($dirty & 19) != 18, $dirty & 1)) {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(2116504409, $dirty, -1, "androidx.compose.foundation.text.contextmenu.internal.TextContextMenuHelperApi28.IconBox (DefaultTextContextMenuDropdownProvider.android.kt:267)");
            }
            ProvidableCompositionLocal<Context> localContext = AndroidCompositionLocals_androidKt.getLocalContext();
            ComposerKt.sourceInformationMarkerStart($composer2, 2023513938, "CC(<get-current>):CompositionLocal.kt#9igjgp");
            Object objConsume = $composer2.consume(localContext);
            ComposerKt.sourceInformationMarkerEnd($composer2);
            Context context = (Context) objConsume;
            ComposerKt.sourceInformationMarkerStart($composer2, -1853758257, "CC(remember):DefaultTextContextMenuDropdownProvider.android.kt#9igjgp");
            boolean invalid$iv = $composer2.changed(icon) | $composer2.changed(context);
            Object it$iv = $composer2.rememberedValue();
            if (invalid$iv || it$iv == Composer.INSTANCE.getEmpty()) {
                Object value$iv = icon.loadDrawable(context);
                $composer2.updateRememberedValue(value$iv);
                it$iv = value$iv;
            }
            Drawable drawable = (Drawable) it$iv;
            ComposerKt.sourceInformationMarkerEnd($composer2);
            if (drawable == null) {
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventEnd();
                }
                ScopeUpdateScope scopeUpdateScopeEndRestartGroup = $composer2.endRestartGroup();
                if (scopeUpdateScopeEndRestartGroup != null) {
                    scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: androidx.compose.foundation.text.contextmenu.internal.TextContextMenuHelperApi28$$ExternalSyntheticLambda0
                        @Override // kotlin.jvm.functions.Function2
                        public final Object invoke(Object obj, Object obj2) {
                            return TextContextMenuHelperApi28.IconBox$lambda$1(this.f$0, icon, $changed, (Composer) obj, ((Integer) obj2).intValue());
                        }
                    });
                    return;
                }
                return;
            }
            IconBox(drawable, $composer2, $dirty & 112);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        } else {
            $composer2.skipToGroupEnd();
        }
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup2 = $composer2.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup2 != null) {
            scopeUpdateScopeEndRestartGroup2.updateScope(new Function2() { // from class: androidx.compose.foundation.text.contextmenu.internal.TextContextMenuHelperApi28$$ExternalSyntheticLambda1
                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj, Object obj2) {
                    return TextContextMenuHelperApi28.IconBox$lambda$2(this.f$0, icon, $changed, (Composer) obj, ((Integer) obj2).intValue());
                }
            });
        }
    }

    public final void IconBox(final Drawable drawable, Composer $composer, final int $changed) {
        Composer $composer2 = $composer.startRestartGroup(257732500);
        ComposerKt.sourceInformation($composer2, "C(IconBox)N(drawable)276@10890L217,275@10822L295:DefaultTextContextMenuDropdownProvider.android.kt#18dpbw");
        int $dirty = $changed;
        if (($changed & 6) == 0) {
            $dirty |= $composer2.changedInstance(drawable) ? 4 : 2;
        }
        if (!$composer2.shouldExecute(($dirty & 3) != 2, $dirty & 1)) {
            $composer2.skipToGroupEnd();
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(257732500, $dirty, -1, "androidx.compose.foundation.text.contextmenu.internal.TextContextMenuHelperApi28.IconBox (DefaultTextContextMenuDropdownProvider.android.kt:274)");
            }
            Modifier modifierM1115size3ABfNKs = SizeKt.m1115size3ABfNKs(Modifier.INSTANCE, ContextMenuSpec.INSTANCE.m407getIconSizeD9Ej5fM());
            ComposerKt.sourceInformationMarkerStart($composer2, -1427166931, "CC(remember):DefaultTextContextMenuDropdownProvider.android.kt#9igjgp");
            boolean invalid$iv = $composer2.changedInstance(drawable);
            Object it$iv = $composer2.rememberedValue();
            if (invalid$iv || it$iv == Composer.INSTANCE.getEmpty()) {
                Object value$iv = new Function1() { // from class: androidx.compose.foundation.text.contextmenu.internal.TextContextMenuHelperApi28$$ExternalSyntheticLambda6
                    @Override // kotlin.jvm.functions.Function1
                    public final Object invoke(Object obj) {
                        return TextContextMenuHelperApi28.IconBox$lambda$3$0(drawable, (DrawScope) obj);
                    }
                };
                $composer2.updateRememberedValue(value$iv);
                it$iv = value$iv;
            }
            ComposerKt.sourceInformationMarkerEnd($composer2);
            BoxKt.Box(DrawModifierKt.drawBehind(modifierM1115size3ABfNKs, (Function1) it$iv), $composer2, 0);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup = $composer2.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: androidx.compose.foundation.text.contextmenu.internal.TextContextMenuHelperApi28$$ExternalSyntheticLambda7
                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj, Object obj2) {
                    return TextContextMenuHelperApi28.IconBox$lambda$4(this.f$0, drawable, $changed, (Composer) obj, ((Integer) obj2).intValue());
                }
            });
        }
    }

    public static final Unit IconBox$lambda$3$0(Drawable $drawable, DrawScope $this$drawBehind) {
        Canvas canvas = $this$drawBehind.getDrawContext().getCanvas();
        long arg0$iv = $this$drawBehind.mo5887getSizeNHjbRc();
        int bits$iv$iv$iv = (int) (arg0$iv >> 32);
        int iIntBitsToFloat = (int) Float.intBitsToFloat(bits$iv$iv$iv);
        long arg0$iv2 = $this$drawBehind.mo5887getSizeNHjbRc();
        int bits$iv$iv$iv2 = (int) (4294967295L & arg0$iv2);
        $drawable.setBounds(0, 0, iIntBitsToFloat, (int) Float.intBitsToFloat(bits$iv$iv$iv2));
        $drawable.draw(AndroidCanvas_androidKt.getNativeCanvas(canvas));
        return Unit.INSTANCE;
    }
}
