package androidx.compose.foundation;

import android.view.KeyEvent;
import androidx.compose.foundation.gestures.ScrollableContainerNode;
import androidx.compose.foundation.interaction.InteractionSourceKt;
import androidx.compose.foundation.interaction.MutableInteractionSource;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.ProvidableCompositionLocal;
import androidx.compose.ui.ComposedModifierKt;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.input.indirect.IndirectPointerInputChange;
import androidx.compose.ui.input.key.Key;
import androidx.compose.ui.input.key.KeyEventType;
import androidx.compose.ui.input.key.KeyEvent_androidKt;
import androidx.compose.ui.input.pointer.PointerInputChange;
import androidx.compose.ui.node.DelegatingNode;
import androidx.compose.ui.node.TraversableNode;
import androidx.compose.ui.node.TraversableNodeKt;
import androidx.compose.ui.platform.InspectableValueKt;
import androidx.compose.ui.platform.InspectorInfo;
import androidx.compose.ui.semantics.Role;
import androidx.core.app.NotificationCompat;
import kotlin.Deprecated;
import kotlin.DeprecationLevel;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.Ref;

/* JADX INFO: compiled from: Clickable.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000`\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\r\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\u001aA\u0010\u0000\u001a\u00020\u0001*\u00020\u00012\b\b\u0002\u0010\u0002\u001a\u00020\u00032\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u00052\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u00072\f\u0010\b\u001a\b\u0012\u0004\u0012\u00020\n0\tH\u0007¢\u0006\u0002\b\u000b\u001aK\u0010\u0000\u001a\u00020\u0001*\u00020\u00012\b\b\u0002\u0010\u0002\u001a\u00020\u00032\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u00052\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u00072\n\b\u0002\u0010\f\u001a\u0004\u0018\u00010\r2\f\u0010\b\u001a\b\u0012\u0004\u0012\u00020\n0\t¢\u0006\u0002\b\u000e\u001aS\u0010\u0000\u001a\u00020\u0001*\u00020\u00012\b\u0010\f\u001a\u0004\u0018\u00010\r2\b\u0010\u000f\u001a\u0004\u0018\u00010\u00102\b\b\u0002\u0010\u0002\u001a\u00020\u00032\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u00052\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u00072\f\u0010\b\u001a\b\u0012\u0004\u0012\u00020\n0\t¢\u0006\u0002\b\u0011\u001a{\u0010\u0012\u001a\u00020\u0001*\u00020\u00012\b\b\u0002\u0010\u0002\u001a\u00020\u00032\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u00052\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u00072\n\b\u0002\u0010\u0013\u001a\u0004\u0018\u00010\u00052\u0010\b\u0002\u0010\u0014\u001a\n\u0012\u0004\u0012\u00020\n\u0018\u00010\t2\u0010\b\u0002\u0010\u0015\u001a\n\u0012\u0004\u0012\u00020\n\u0018\u00010\t2\b\b\u0002\u0010\u0016\u001a\u00020\u00032\f\u0010\b\u001a\b\u0012\u0004\u0012\u00020\n0\tH\u0007¢\u0006\u0002\b\u0017\u001a\u0085\u0001\u0010\u0012\u001a\u00020\u0001*\u00020\u00012\b\b\u0002\u0010\u0002\u001a\u00020\u00032\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u00052\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u00072\n\b\u0002\u0010\u0013\u001a\u0004\u0018\u00010\u00052\u0010\b\u0002\u0010\u0014\u001a\n\u0012\u0004\u0012\u00020\n\u0018\u00010\t2\u0010\b\u0002\u0010\u0015\u001a\n\u0012\u0004\u0012\u00020\n\u0018\u00010\t2\b\b\u0002\u0010\u0016\u001a\u00020\u00032\n\b\u0002\u0010\f\u001a\u0004\u0018\u00010\r2\f\u0010\b\u001a\b\u0012\u0004\u0012\u00020\n0\t¢\u0006\u0002\b\u0018\u001aq\u0010\u0012\u001a\u00020\u0001*\u00020\u00012\b\b\u0002\u0010\u0002\u001a\u00020\u00032\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u00052\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u00072\n\b\u0002\u0010\u0013\u001a\u0004\u0018\u00010\u00052\u0010\b\u0002\u0010\u0014\u001a\n\u0012\u0004\u0012\u00020\n\u0018\u00010\t2\u0010\b\u0002\u0010\u0015\u001a\n\u0012\u0004\u0012\u00020\n\u0018\u00010\t2\f\u0010\b\u001a\b\u0012\u0004\u0012\u00020\n0\tH\u0007¢\u0006\u0002\b\u0019\u001a\u008d\u0001\u0010\u0012\u001a\u00020\u0001*\u00020\u00012\b\u0010\f\u001a\u0004\u0018\u00010\r2\b\u0010\u000f\u001a\u0004\u0018\u00010\u00102\b\b\u0002\u0010\u0002\u001a\u00020\u00032\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u00052\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u00072\n\b\u0002\u0010\u0013\u001a\u0004\u0018\u00010\u00052\u0010\b\u0002\u0010\u0014\u001a\n\u0012\u0004\u0012\u00020\n\u0018\u00010\t2\u0010\b\u0002\u0010\u0015\u001a\n\u0012\u0004\u0012\u00020\n\u0018\u00010\t2\b\b\u0002\u0010\u0016\u001a\u00020\u00032\f\u0010\b\u001a\b\u0012\u0004\u0012\u00020\n0\t¢\u0006\u0002\b\u001a\u001a\u0085\u0001\u0010\u0012\u001a\u00020\u0001*\u00020\u00012\b\u0010\f\u001a\u0004\u0018\u00010\r2\b\u0010\u000f\u001a\u0004\u0018\u00010\u00102\b\b\u0002\u0010\u0002\u001a\u00020\u00032\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u00052\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u00072\n\b\u0002\u0010\u0013\u001a\u0004\u0018\u00010\u00052\u0010\b\u0002\u0010\u0014\u001a\n\u0012\u0004\u0012\u00020\n\u0018\u00010\t2\u0010\b\u0002\u0010\u0015\u001a\n\u0012\u0004\u0012\u00020\n\u0018\u00010\t2\f\u0010\b\u001a\b\u0012\u0004\u0012\u00020\n0\tH\u0007¢\u0006\u0002\b\u001b\u001aA\u0010\u001c\u001a\u00020\u0001*\u00020\u00012\b\u0010\f\u001a\u0004\u0018\u00010\r2\b\u0010\u000f\u001a\u0004\u0018\u00010\u00102\u001e\b\u0004\u0010\u001d\u001a\u0018\u0012\u0006\u0012\u0004\u0018\u00010\r\u0012\u0006\u0012\u0004\u0018\u00010\u001f\u0012\u0004\u0012\u00020\u00010\u001eH\u0080\b\u001a\u0014\u0010(\u001a\u00020\u0003*\u00020)2\u0006\u0010*\u001a\u00020+H\u0000\u001a\u0014\u0010(\u001a\u00020\u0003*\u00020)2\u0006\u0010*\u001a\u00020,H\u0000\u001a\f\u0010-\u001a\u00020\u0003*\u00020.H\u0000\u001a\u0010\u0010/\u001a\u00020\u00052\u0006\u0010\u000f\u001a\u00020\u0010H\u0002\u001a\f\u00100\u001a\u00020\u0003*\u00020+H\u0002\u001a\f\u00101\u001a\u00020\u0003*\u00020+H\u0002\"\u0018\u0010 \u001a\u00020\u0003*\u00020!8BX\u0082\u0004¢\u0006\u0006\u001a\u0004\b\"\u0010#\"\u0018\u0010$\u001a\u00020\u0003*\u00020!8BX\u0082\u0004¢\u0006\u0006\u001a\u0004\b%\u0010#\"\u0018\u0010&\u001a\u00020\u0003*\u00020!8BX\u0082\u0004¢\u0006\u0006\u001a\u0004\b'\u0010#¨\u00062"}, d2 = {"clickable", "Landroidx/compose/ui/Modifier;", "enabled", "", "onClickLabel", "", "role", "Landroidx/compose/ui/semantics/Role;", "onClick", "Lkotlin/Function0;", "", "clickable-XHw0xAI", "interactionSource", "Landroidx/compose/foundation/interaction/MutableInteractionSource;", "clickable-oSLSa3U", "indication", "Landroidx/compose/foundation/Indication;", "clickable-O2vRcR0", "combinedClickable", "onLongClickLabel", "onLongClick", "onDoubleClick", "hapticFeedbackEnabled", "combinedClickable-f5TDLPQ", "combinedClickable-hoGz1lA", "combinedClickable-cJG_KMw", "combinedClickable-auXiCPI", "combinedClickable-XVZzFYc", "clickableWithIndicationIfNeeded", "createClickable", "Lkotlin/Function2;", "Landroidx/compose/foundation/IndicationNodeFactory;", "isPress", "Landroidx/compose/ui/input/key/KeyEvent;", "isPress-ZmokQxo", "(Landroid/view/KeyEvent;)Z", "isClick", "isClick-ZmokQxo", "isEnter", "isEnter-ZmokQxo", "hasInterestedParent", "Landroidx/compose/ui/node/DelegatingNode;", NotificationCompat.CATEGORY_EVENT, "Landroidx/compose/ui/input/indirect/IndirectPointerInputChange;", "Landroidx/compose/ui/input/pointer/PointerInputChange;", "hasScrollableContainer", "Landroidx/compose/ui/node/TraversableNode;", "unsupportedIndicationExceptionMessage", "changedToUp", "changedToUpIgnoreConsumed", "foundation"}, k = 2, mv = {2, 1, 0}, xi = 48)
public final class ClickableKt {
    /* JADX INFO: renamed from: clickable-XHw0xAI$default */
    public static /* synthetic */ Modifier m319clickableXHw0xAI$default(Modifier modifier, boolean z, String str, Role role, Function0 function0, int i, Object obj) {
        if ((i & 1) != 0) {
            z = true;
        }
        if ((i & 2) != 0) {
            str = null;
        }
        if ((i & 4) != 0) {
            role = null;
        }
        return m318clickableXHw0xAI(modifier, z, str, role, function0);
    }

    @Deprecated(level = DeprecationLevel.HIDDEN, message = "Replaced with new overload that only supports IndicationNodeFactory instances inside LocalIndication, and does not use composed")
    /* JADX INFO: renamed from: clickable-XHw0xAI */
    public static final /* synthetic */ Modifier m318clickableXHw0xAI(Modifier $this$clickable_u2dXHw0xAI, final boolean enabled, final String onClickLabel, final Role role, final Function0 onClick) {
        return ComposedModifierKt.composed($this$clickable_u2dXHw0xAI, InspectableValueKt.isDebugInspectorInfoEnabled() ? new Function1<InspectorInfo, Unit>() { // from class: androidx.compose.foundation.ClickableKt$clickable-XHw0xAI$$inlined$debugInspectorInfo$1
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(InspectorInfo inspectorInfo) {
                invoke2(inspectorInfo);
                return Unit.INSTANCE;
            }

            /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(InspectorInfo inspectorInfo) {
                inspectorInfo.setName("clickable");
                inspectorInfo.getProperties().set("enabled", Boolean.valueOf(enabled));
                inspectorInfo.getProperties().set("onClickLabel", onClickLabel);
                inspectorInfo.getProperties().set("role", role);
                inspectorInfo.getProperties().set("onClick", onClick);
            }
        } : InspectableValueKt.getNoInspectorInfo(), new Function3() { // from class: androidx.compose.foundation.ClickableKt$$ExternalSyntheticLambda5
            @Override // kotlin.jvm.functions.Function3
            public final Object invoke(Object obj, Object obj2, Object obj3) {
                return ClickableKt.clickable_XHw0xAI$lambda$1(enabled, onClickLabel, role, onClick, (Modifier) obj, (Composer) obj2, ((Integer) obj3).intValue());
            }
        });
    }

    static final Modifier clickable_XHw0xAI$lambda$1(boolean $enabled, String $onClickLabel, Role $role, Function0 $onClick, Modifier $this$composed, Composer $composer, int $changed) {
        MutableInteractionSource interactionSource;
        $composer.startReplaceGroup(-756081143);
        ComposerKt.sourceInformation($composer, "C144@6863L7:Clickable.kt#71ulvw");
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(-756081143, $changed, -1, "androidx.compose.foundation.clickable.<anonymous> (Clickable.kt:144)");
        }
        ProvidableCompositionLocal<Indication> localIndication = IndicationKt.getLocalIndication();
        ComposerKt.sourceInformationMarkerStart($composer, 2023513938, "CC(<get-current>):CompositionLocal.kt#9igjgp");
        Object objConsume = $composer.consume(localIndication);
        ComposerKt.sourceInformationMarkerEnd($composer);
        Indication localIndication2 = (Indication) objConsume;
        if (localIndication2 instanceof IndicationNodeFactory) {
            $composer.startReplaceGroup(-1604682242);
            $composer.endReplaceGroup();
            interactionSource = null;
        } else {
            $composer.startReplaceGroup(-1604549624);
            ComposerKt.sourceInformation($composer, "153@7310L39");
            ComposerKt.sourceInformationMarkerStart($composer, -744489520, "CC(remember):Clickable.kt#9igjgp");
            Object it$iv = $composer.rememberedValue();
            if (it$iv == Composer.INSTANCE.getEmpty()) {
                Object value$iv = InteractionSourceKt.MutableInteractionSource();
                $composer.updateRememberedValue(value$iv);
                it$iv = value$iv;
            }
            ComposerKt.sourceInformationMarkerEnd($composer);
            $composer.endReplaceGroup();
            interactionSource = (MutableInteractionSource) it$iv;
        }
        Modifier modifierM316clickableO2vRcR0 = m316clickableO2vRcR0(Modifier.INSTANCE, interactionSource, localIndication2, $enabled, $onClickLabel, $role, $onClick);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        $composer.endReplaceGroup();
        return modifierM316clickableO2vRcR0;
    }

    /* JADX INFO: renamed from: clickable-oSLSa3U$default */
    public static /* synthetic */ Modifier m321clickableoSLSa3U$default(Modifier modifier, boolean z, String str, Role role, MutableInteractionSource mutableInteractionSource, Function0 function0, int i, Object obj) {
        if ((i & 1) != 0) {
            z = true;
        }
        if ((i & 2) != 0) {
            str = null;
        }
        if ((i & 4) != 0) {
            role = null;
        }
        return m320clickableoSLSa3U(modifier, z, str, role, (i & 8) != 0 ? null : mutableInteractionSource, function0);
    }

    /* JADX INFO: renamed from: clickable-oSLSa3U */
    public static final Modifier m320clickableoSLSa3U(Modifier $this$clickable_u2doSLSa3U, boolean enabled, String onClickLabel, Role role, MutableInteractionSource interactionSource, Function0<Unit> function0) {
        return $this$clickable_u2doSLSa3U.then(new ClickableElement(interactionSource, null, true, enabled, onClickLabel, role, function0, null));
    }

    /* JADX INFO: renamed from: clickable-O2vRcR0 */
    public static final Modifier m316clickableO2vRcR0(Modifier $this$clickable_u2dO2vRcR0, MutableInteractionSource interactionSource, final Indication indication, final boolean enabled, final String onClickLabel, final Role role, final Function0<Unit> function0) {
        ClickableElement clickableElement;
        if (indication instanceof IndicationNodeFactory) {
            IndicationNodeFactory indicationNodeFactory = (IndicationNodeFactory) indication;
            clickableElement = new ClickableElement(interactionSource, indicationNodeFactory, false, enabled, onClickLabel, role, function0, null);
        } else {
            clickableElement = indication == null ? new ClickableElement(interactionSource, null, false, enabled, onClickLabel, role, function0, null) : interactionSource != null ? IndicationKt.indication(Modifier.INSTANCE, interactionSource, indication).then(new ClickableElement(interactionSource, null, false, enabled, onClickLabel, role, function0, null)) : ComposedModifierKt.composed$default(Modifier.INSTANCE, null, new Function3<Modifier, Composer, Integer, Modifier>() { // from class: androidx.compose.foundation.ClickableKt$clickable-O2vRcR0$$inlined$clickableWithIndicationIfNeeded$1
                @Override // kotlin.jvm.functions.Function3
                public /* bridge */ /* synthetic */ Modifier invoke(Modifier modifier, Composer composer, Integer num) {
                    return invoke(modifier, composer, num.intValue());
                }

                public final Modifier invoke(Modifier $this$composed, Composer $composer, int $changed) {
                    $composer.startReplaceGroup(-1525724089);
                    ComposerKt.sourceInformation($composer, "C637@30530L39:Clickable.kt#71ulvw");
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventStart(-1525724089, $changed, -1, "androidx.compose.foundation.clickableWithIndicationIfNeeded.<anonymous> (Clickable.kt:637)");
                    }
                    ComposerKt.sourceInformationMarkerStart($composer, -1636999986, "CC(remember):Clickable.kt#9igjgp");
                    Object it$iv = $composer.rememberedValue();
                    if (it$iv == Composer.INSTANCE.getEmpty()) {
                        Object value$iv = InteractionSourceKt.MutableInteractionSource();
                        $composer.updateRememberedValue(value$iv);
                        it$iv = value$iv;
                    }
                    MutableInteractionSource newInteractionSource = (MutableInteractionSource) it$iv;
                    ComposerKt.sourceInformationMarkerEnd($composer);
                    Modifier modifierThen = IndicationKt.indication(Modifier.INSTANCE, newInteractionSource, indication).then(new ClickableElement(newInteractionSource, null, false, enabled, onClickLabel, role, function0, null));
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventEnd();
                    }
                    $composer.endReplaceGroup();
                    return modifierThen;
                }
            }, 1, null);
        }
        return $this$clickable_u2dO2vRcR0.then(clickableElement);
    }

    /* JADX INFO: renamed from: combinedClickable-f5TDLPQ$default */
    public static /* synthetic */ Modifier m329combinedClickablef5TDLPQ$default(Modifier modifier, boolean z, String str, Role role, String str2, Function0 function0, Function0 function02, boolean z2, Function0 function03, int i, Object obj) {
        if ((i & 1) != 0) {
            z = true;
        }
        if ((i & 2) != 0) {
            str = null;
        }
        if ((i & 4) != 0) {
            role = null;
        }
        if ((i & 8) != 0) {
            str2 = null;
        }
        if ((i & 16) != 0) {
            function0 = null;
        }
        if ((i & 32) != 0) {
            function02 = null;
        }
        if ((i & 64) != 0) {
            z2 = true;
        }
        return m328combinedClickablef5TDLPQ(modifier, z, str, role, str2, function0, function02, z2, function03);
    }

    @Deprecated(level = DeprecationLevel.HIDDEN, message = "Replaced with new overload that only supports IndicationNodeFactory instances inside LocalIndication, and does not use composed")
    /* JADX INFO: renamed from: combinedClickable-f5TDLPQ */
    public static final /* synthetic */ Modifier m328combinedClickablef5TDLPQ(Modifier $this$combinedClickable_u2df5TDLPQ, final boolean enabled, final String onClickLabel, final Role role, final String onLongClickLabel, final Function0 onLongClick, final Function0 onDoubleClick, final boolean hapticFeedbackEnabled, final Function0 onClick) {
        return ComposedModifierKt.composed($this$combinedClickable_u2df5TDLPQ, InspectableValueKt.isDebugInspectorInfoEnabled() ? new Function1<InspectorInfo, Unit>() { // from class: androidx.compose.foundation.ClickableKt$combinedClickable-f5TDLPQ$$inlined$debugInspectorInfo$1
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(InspectorInfo inspectorInfo) {
                invoke2(inspectorInfo);
                return Unit.INSTANCE;
            }

            /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(InspectorInfo inspectorInfo) {
                inspectorInfo.setName("combinedClickable");
                inspectorInfo.getProperties().set("enabled", Boolean.valueOf(enabled));
                inspectorInfo.getProperties().set("onClickLabel", onClickLabel);
                inspectorInfo.getProperties().set("role", role);
                inspectorInfo.getProperties().set("onClick", onClick);
                inspectorInfo.getProperties().set("onDoubleClick", onDoubleClick);
                inspectorInfo.getProperties().set("onLongClick", onLongClick);
                inspectorInfo.getProperties().set("onLongClickLabel", onLongClickLabel);
                inspectorInfo.getProperties().set("hapticFeedbackEnabled", Boolean.valueOf(hapticFeedbackEnabled));
            }
        } : InspectableValueKt.getNoInspectorInfo(), new Function3() { // from class: androidx.compose.foundation.ClickableKt$$ExternalSyntheticLambda0
            @Override // kotlin.jvm.functions.Function3
            public final Object invoke(Object obj, Object obj2, Object obj3) {
                return ClickableKt.combinedClickable_f5TDLPQ$lambda$1(enabled, onClickLabel, role, onLongClickLabel, onLongClick, onDoubleClick, hapticFeedbackEnabled, onClick, (Modifier) obj, (Composer) obj2, ((Integer) obj3).intValue());
            }
        });
    }

    static final Modifier combinedClickable_f5TDLPQ$lambda$1(boolean $enabled, String $onClickLabel, Role $role, String $onLongClickLabel, Function0 $onLongClick, Function0 $onDoubleClick, boolean $hapticFeedbackEnabled, Function0 $onClick, Modifier $this$composed, Composer $composer, int $changed) {
        MutableInteractionSource interactionSource;
        $composer.startReplaceGroup(-1534186401);
        ComposerKt.sourceInformation($composer, "C353@17168L7:Clickable.kt#71ulvw");
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(-1534186401, $changed, -1, "androidx.compose.foundation.combinedClickable.<anonymous> (Clickable.kt:353)");
        }
        ProvidableCompositionLocal<Indication> localIndication = IndicationKt.getLocalIndication();
        ComposerKt.sourceInformationMarkerStart($composer, 2023513938, "CC(<get-current>):CompositionLocal.kt#9igjgp");
        Object objConsume = $composer.consume(localIndication);
        ComposerKt.sourceInformationMarkerEnd($composer);
        Indication localIndication2 = (Indication) objConsume;
        if (localIndication2 instanceof IndicationNodeFactory) {
            $composer.startReplaceGroup(2095040488);
            $composer.endReplaceGroup();
            interactionSource = null;
        } else {
            $composer.startReplaceGroup(2095173106);
            ComposerKt.sourceInformation($composer, "362@17615L39");
            ComposerKt.sourceInformationMarkerStart($composer, -1179332954, "CC(remember):Clickable.kt#9igjgp");
            Object it$iv = $composer.rememberedValue();
            if (it$iv == Composer.INSTANCE.getEmpty()) {
                Object value$iv = InteractionSourceKt.MutableInteractionSource();
                $composer.updateRememberedValue(value$iv);
                it$iv = value$iv;
            }
            ComposerKt.sourceInformationMarkerEnd($composer);
            $composer.endReplaceGroup();
            interactionSource = (MutableInteractionSource) it$iv;
        }
        Modifier modifierM324combinedClickableauXiCPI = m324combinedClickableauXiCPI(Modifier.INSTANCE, interactionSource, localIndication2, $enabled, $onClickLabel, $role, $onLongClickLabel, $onLongClick, $onDoubleClick, $hapticFeedbackEnabled, $onClick);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        $composer.endReplaceGroup();
        return modifierM324combinedClickableauXiCPI;
    }

    /* JADX INFO: renamed from: combinedClickable-hoGz1lA$default */
    public static /* synthetic */ Modifier m331combinedClickablehoGz1lA$default(Modifier modifier, boolean z, String str, Role role, String str2, Function0 function0, Function0 function02, boolean z2, MutableInteractionSource mutableInteractionSource, Function0 function03, int i, Object obj) {
        if ((i & 1) != 0) {
            z = true;
        }
        if ((i & 2) != 0) {
            str = null;
        }
        if ((i & 4) != 0) {
            role = null;
        }
        if ((i & 8) != 0) {
            str2 = null;
        }
        if ((i & 16) != 0) {
            function0 = null;
        }
        if ((i & 32) != 0) {
            function02 = null;
        }
        if ((i & 64) != 0) {
            z2 = true;
        }
        if ((i & 128) != 0) {
            mutableInteractionSource = null;
        }
        return m330combinedClickablehoGz1lA(modifier, z, str, role, str2, function0, function02, z2, mutableInteractionSource, function03);
    }

    /* JADX INFO: renamed from: combinedClickable-hoGz1lA */
    public static final Modifier m330combinedClickablehoGz1lA(Modifier $this$combinedClickable_u2dhoGz1lA, boolean enabled, String onClickLabel, Role role, String onLongClickLabel, Function0<Unit> function0, Function0<Unit> function02, boolean hapticFeedbackEnabled, MutableInteractionSource interactionSource, Function0<Unit> function03) {
        return $this$combinedClickable_u2dhoGz1lA.then(new CombinedClickableElement(interactionSource, null, true, enabled, onClickLabel, role, function03, onLongClickLabel, function0, function02, hapticFeedbackEnabled, null));
    }

    /* JADX INFO: renamed from: combinedClickable-cJG_KMw$default */
    public static /* synthetic */ Modifier m327combinedClickablecJG_KMw$default(Modifier modifier, boolean z, String str, Role role, String str2, Function0 function0, Function0 function02, Function0 function03, int i, Object obj) {
        if ((i & 1) != 0) {
            z = true;
        }
        if ((i & 2) != 0) {
            str = null;
        }
        if ((i & 4) != 0) {
            role = null;
        }
        if ((i & 8) != 0) {
            str2 = null;
        }
        if ((i & 16) != 0) {
            function0 = null;
        }
        return m326combinedClickablecJG_KMw(modifier, z, str, role, str2, function0, (i & 32) != 0 ? null : function02, function03);
    }

    @Deprecated(level = DeprecationLevel.HIDDEN, message = "Maintained for binary compatibility")
    /* JADX INFO: renamed from: combinedClickable-cJG_KMw */
    public static final /* synthetic */ Modifier m326combinedClickablecJG_KMw(Modifier $this$combinedClickable_u2dcJG_KMw, final boolean enabled, final String onClickLabel, final Role role, final String onLongClickLabel, final Function0 onLongClick, final Function0 onDoubleClick, final Function0 onClick) {
        return ComposedModifierKt.composed($this$combinedClickable_u2dcJG_KMw, InspectableValueKt.isDebugInspectorInfoEnabled() ? new Function1<InspectorInfo, Unit>() { // from class: androidx.compose.foundation.ClickableKt$combinedClickable-cJG_KMw$$inlined$debugInspectorInfo$1
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(InspectorInfo inspectorInfo) {
                invoke2(inspectorInfo);
                return Unit.INSTANCE;
            }

            /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(InspectorInfo inspectorInfo) {
                inspectorInfo.setName("combinedClickable");
                inspectorInfo.getProperties().set("enabled", Boolean.valueOf(enabled));
                inspectorInfo.getProperties().set("onClickLabel", onClickLabel);
                inspectorInfo.getProperties().set("role", role);
                inspectorInfo.getProperties().set("onClick", onClick);
                inspectorInfo.getProperties().set("onDoubleClick", onDoubleClick);
                inspectorInfo.getProperties().set("onLongClick", onLongClick);
                inspectorInfo.getProperties().set("onLongClickLabel", onLongClickLabel);
            }
        } : InspectableValueKt.getNoInspectorInfo(), new Function3() { // from class: androidx.compose.foundation.ClickableKt$$ExternalSyntheticLambda2
            @Override // kotlin.jvm.functions.Function3
            public final Object invoke(Object obj, Object obj2, Object obj3) {
                return ClickableKt.combinedClickable_cJG_KMw$lambda$1(enabled, onClickLabel, role, onLongClickLabel, onLongClick, onDoubleClick, onClick, (Modifier) obj, (Composer) obj2, ((Integer) obj3).intValue());
            }
        });
    }

    static final Modifier combinedClickable_cJG_KMw$lambda$1(boolean $enabled, String $onClickLabel, Role $role, String $onLongClickLabel, Function0 $onLongClick, Function0 $onDoubleClick, Function0 $onClick, Modifier $this$composed, Composer $composer, int $changed) {
        MutableInteractionSource interactionSource;
        $composer.startReplaceGroup(1969174843);
        ComposerKt.sourceInformation($composer, "C474@22818L7:Clickable.kt#71ulvw");
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(1969174843, $changed, -1, "androidx.compose.foundation.combinedClickable.<anonymous> (Clickable.kt:474)");
        }
        ProvidableCompositionLocal<Indication> localIndication = IndicationKt.getLocalIndication();
        ComposerKt.sourceInformationMarkerStart($composer, 2023513938, "CC(<get-current>):CompositionLocal.kt#9igjgp");
        Object objConsume = $composer.consume(localIndication);
        ComposerKt.sourceInformationMarkerEnd($composer);
        Indication localIndication2 = (Indication) objConsume;
        if (localIndication2 instanceof IndicationNodeFactory) {
            $composer.startReplaceGroup(-1270399604);
            $composer.endReplaceGroup();
            interactionSource = null;
        } else {
            $composer.startReplaceGroup(-1270266986);
            ComposerKt.sourceInformation($composer, "483@23265L39");
            ComposerKt.sourceInformationMarkerStart($composer, -1703537534, "CC(remember):Clickable.kt#9igjgp");
            Object it$iv = $composer.rememberedValue();
            if (it$iv == Composer.INSTANCE.getEmpty()) {
                Object value$iv = InteractionSourceKt.MutableInteractionSource();
                $composer.updateRememberedValue(value$iv);
                it$iv = value$iv;
            }
            ComposerKt.sourceInformationMarkerEnd($composer);
            $composer.endReplaceGroup();
            interactionSource = (MutableInteractionSource) it$iv;
        }
        Modifier modifierM324combinedClickableauXiCPI = m324combinedClickableauXiCPI(Modifier.INSTANCE, interactionSource, localIndication2, $enabled, $onClickLabel, $role, $onLongClickLabel, $onLongClick, $onDoubleClick, true, $onClick);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        $composer.endReplaceGroup();
        return modifierM324combinedClickableauXiCPI;
    }

    /* JADX INFO: renamed from: combinedClickable-auXiCPI$default */
    public static /* synthetic */ Modifier m325combinedClickableauXiCPI$default(Modifier modifier, MutableInteractionSource mutableInteractionSource, Indication indication, boolean z, String str, Role role, String str2, Function0 function0, Function0 function02, boolean z2, Function0 function03, int i, Object obj) {
        if ((i & 4) != 0) {
            z = true;
        }
        if ((i & 8) != 0) {
            str = null;
        }
        if ((i & 16) != 0) {
            role = null;
        }
        if ((i & 32) != 0) {
            str2 = null;
        }
        if ((i & 64) != 0) {
            function0 = null;
        }
        if ((i & 128) != 0) {
            function02 = null;
        }
        if ((i & 256) != 0) {
            z2 = true;
        }
        return m324combinedClickableauXiCPI(modifier, mutableInteractionSource, indication, z, str, role, str2, function0, function02, z2, function03);
    }

    /* JADX INFO: renamed from: combinedClickable-auXiCPI */
    public static final Modifier m324combinedClickableauXiCPI(Modifier $this$combinedClickable_u2dauXiCPI, MutableInteractionSource interactionSource, final Indication indication, final boolean enabled, final String onClickLabel, final Role role, final String onLongClickLabel, final Function0<Unit> function0, final Function0<Unit> function02, final boolean hapticFeedbackEnabled, final Function0<Unit> function03) {
        CombinedClickableElement combinedClickableElement;
        if (indication instanceof IndicationNodeFactory) {
            IndicationNodeFactory indicationNodeFactory = (IndicationNodeFactory) indication;
            combinedClickableElement = new CombinedClickableElement(interactionSource, indicationNodeFactory, false, enabled, onClickLabel, role, function03, onLongClickLabel, function0, function02, hapticFeedbackEnabled, null);
        } else {
            combinedClickableElement = indication == null ? new CombinedClickableElement(interactionSource, null, false, enabled, onClickLabel, role, function03, onLongClickLabel, function0, function02, hapticFeedbackEnabled, null) : interactionSource != null ? IndicationKt.indication(Modifier.INSTANCE, interactionSource, indication).then(new CombinedClickableElement(interactionSource, null, false, enabled, onClickLabel, role, function03, onLongClickLabel, function0, function02, hapticFeedbackEnabled, null)) : ComposedModifierKt.composed$default(Modifier.INSTANCE, null, new Function3<Modifier, Composer, Integer, Modifier>() { // from class: androidx.compose.foundation.ClickableKt$combinedClickable-auXiCPI$$inlined$clickableWithIndicationIfNeeded$1
                @Override // kotlin.jvm.functions.Function3
                public /* bridge */ /* synthetic */ Modifier invoke(Modifier modifier, Composer composer, Integer num) {
                    return invoke(modifier, composer, num.intValue());
                }

                public final Modifier invoke(Modifier $this$composed, Composer $composer, int $changed) {
                    $composer.startReplaceGroup(-1525724089);
                    ComposerKt.sourceInformation($composer, "C637@30530L39:Clickable.kt#71ulvw");
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventStart(-1525724089, $changed, -1, "androidx.compose.foundation.clickableWithIndicationIfNeeded.<anonymous> (Clickable.kt:637)");
                    }
                    ComposerKt.sourceInformationMarkerStart($composer, -1636999986, "CC(remember):Clickable.kt#9igjgp");
                    Object it$iv = $composer.rememberedValue();
                    if (it$iv == Composer.INSTANCE.getEmpty()) {
                        Object value$iv = InteractionSourceKt.MutableInteractionSource();
                        $composer.updateRememberedValue(value$iv);
                        it$iv = value$iv;
                    }
                    MutableInteractionSource newInteractionSource = (MutableInteractionSource) it$iv;
                    ComposerKt.sourceInformationMarkerEnd($composer);
                    Modifier modifierThen = IndicationKt.indication(Modifier.INSTANCE, newInteractionSource, indication).then(new CombinedClickableElement(newInteractionSource, null, false, enabled, onClickLabel, role, function03, onLongClickLabel, function0, function02, hapticFeedbackEnabled, null));
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventEnd();
                    }
                    $composer.endReplaceGroup();
                    return modifierThen;
                }
            }, 1, null);
        }
        return $this$combinedClickable_u2dauXiCPI.then(combinedClickableElement);
    }

    @Deprecated(level = DeprecationLevel.HIDDEN, message = "Maintained for binary compatibility")
    /* JADX INFO: renamed from: combinedClickable-XVZzFYc */
    public static final /* synthetic */ Modifier m322combinedClickableXVZzFYc(Modifier $this$combinedClickable_u2dXVZzFYc, MutableInteractionSource interactionSource, final Indication indication, final boolean enabled, final String onClickLabel, final Role role, final String onLongClickLabel, final Function0 onLongClick, final Function0 onDoubleClick, final Function0 onClick) {
        CombinedClickableElement combinedClickableElement;
        if (indication instanceof IndicationNodeFactory) {
            IndicationNodeFactory indicationNodeFactory = (IndicationNodeFactory) indication;
            combinedClickableElement = new CombinedClickableElement(interactionSource, indicationNodeFactory, false, enabled, onClickLabel, role, onClick, onLongClickLabel, onLongClick, onDoubleClick, true, null);
        } else {
            combinedClickableElement = indication == null ? new CombinedClickableElement(interactionSource, null, false, enabled, onClickLabel, role, onClick, onLongClickLabel, onLongClick, onDoubleClick, true, null) : interactionSource != null ? IndicationKt.indication(Modifier.INSTANCE, interactionSource, indication).then(new CombinedClickableElement(interactionSource, null, false, enabled, onClickLabel, role, onClick, onLongClickLabel, onLongClick, onDoubleClick, true, null)) : ComposedModifierKt.composed$default(Modifier.INSTANCE, null, new Function3<Modifier, Composer, Integer, Modifier>() { // from class: androidx.compose.foundation.ClickableKt$combinedClickable-XVZzFYc$$inlined$clickableWithIndicationIfNeeded$1
                @Override // kotlin.jvm.functions.Function3
                public /* bridge */ /* synthetic */ Modifier invoke(Modifier modifier, Composer composer, Integer num) {
                    return invoke(modifier, composer, num.intValue());
                }

                public final Modifier invoke(Modifier $this$composed, Composer $composer, int $changed) {
                    $composer.startReplaceGroup(-1525724089);
                    ComposerKt.sourceInformation($composer, "C637@30530L39:Clickable.kt#71ulvw");
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventStart(-1525724089, $changed, -1, "androidx.compose.foundation.clickableWithIndicationIfNeeded.<anonymous> (Clickable.kt:637)");
                    }
                    ComposerKt.sourceInformationMarkerStart($composer, -1636999986, "CC(remember):Clickable.kt#9igjgp");
                    Object it$iv = $composer.rememberedValue();
                    if (it$iv == Composer.INSTANCE.getEmpty()) {
                        Object value$iv = InteractionSourceKt.MutableInteractionSource();
                        $composer.updateRememberedValue(value$iv);
                        it$iv = value$iv;
                    }
                    MutableInteractionSource newInteractionSource = (MutableInteractionSource) it$iv;
                    ComposerKt.sourceInformationMarkerEnd($composer);
                    Modifier modifierThen = IndicationKt.indication(Modifier.INSTANCE, newInteractionSource, indication).then(new CombinedClickableElement(newInteractionSource, null, false, enabled, onClickLabel, role, onClick, onLongClickLabel, onLongClick, onDoubleClick, true, null));
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventEnd();
                    }
                    $composer.endReplaceGroup();
                    return modifierThen;
                }
            }, 1, null);
        }
        return $this$combinedClickable_u2dXVZzFYc.then(combinedClickableElement);
    }

    public static final Modifier clickableWithIndicationIfNeeded(Modifier $this$clickableWithIndicationIfNeeded, MutableInteractionSource interactionSource, Indication indication, Function2<? super MutableInteractionSource, ? super IndicationNodeFactory, ? extends Modifier> function2) {
        Modifier modifierThen;
        if (indication instanceof IndicationNodeFactory) {
            modifierThen = function2.invoke(interactionSource, indication);
        } else if (indication == null) {
            modifierThen = function2.invoke(interactionSource, null);
        } else {
            modifierThen = interactionSource != null ? IndicationKt.indication(Modifier.INSTANCE, interactionSource, indication).then(function2.invoke(interactionSource, null)) : ComposedModifierKt.composed$default(Modifier.INSTANCE, null, new Function3<Modifier, Composer, Integer, Modifier>() { // from class: androidx.compose.foundation.ClickableKt.clickableWithIndicationIfNeeded.1
                final /* synthetic */ Function2<MutableInteractionSource, IndicationNodeFactory, Modifier> $createClickable;

                /* JADX WARN: Multi-variable type inference failed */
                public AnonymousClass1(Function2<? super MutableInteractionSource, ? super IndicationNodeFactory, ? extends Modifier> function22) {
                    function2 = function22;
                }

                @Override // kotlin.jvm.functions.Function3
                public /* bridge */ /* synthetic */ Modifier invoke(Modifier modifier, Composer composer, Integer num) {
                    return invoke(modifier, composer, num.intValue());
                }

                public final Modifier invoke(Modifier $this$composed, Composer $composer, int $changed) {
                    $composer.startReplaceGroup(-1525724089);
                    ComposerKt.sourceInformation($composer, "C637@30530L39:Clickable.kt#71ulvw");
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventStart(-1525724089, $changed, -1, "androidx.compose.foundation.clickableWithIndicationIfNeeded.<anonymous> (Clickable.kt:637)");
                    }
                    ComposerKt.sourceInformationMarkerStart($composer, -1636999986, "CC(remember):Clickable.kt#9igjgp");
                    Object it$iv = $composer.rememberedValue();
                    if (it$iv == Composer.INSTANCE.getEmpty()) {
                        Object value$iv = InteractionSourceKt.MutableInteractionSource();
                        $composer.updateRememberedValue(value$iv);
                        it$iv = value$iv;
                    }
                    MutableInteractionSource newInteractionSource = (MutableInteractionSource) it$iv;
                    ComposerKt.sourceInformationMarkerEnd($composer);
                    Modifier modifierThen2 = IndicationKt.indication(Modifier.INSTANCE, newInteractionSource, indication).then(function2.invoke(newInteractionSource, null));
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventEnd();
                    }
                    $composer.endReplaceGroup();
                    return modifierThen2;
                }
            }, 1, null);
        }
        return $this$clickableWithIndicationIfNeeded.then(modifierThen);
    }

    /* JADX INFO: renamed from: androidx.compose.foundation.ClickableKt$clickableWithIndicationIfNeeded$1 */
    /* JADX INFO: compiled from: Clickable.kt */
    @Metadata(k = 3, mv = {2, 1, 0}, xi = 176)
    public static final class AnonymousClass1 implements Function3<Modifier, Composer, Integer, Modifier> {
        final /* synthetic */ Function2<MutableInteractionSource, IndicationNodeFactory, Modifier> $createClickable;

        /* JADX WARN: Multi-variable type inference failed */
        public AnonymousClass1(Function2<? super MutableInteractionSource, ? super IndicationNodeFactory, ? extends Modifier> function22) {
            function2 = function22;
        }

        @Override // kotlin.jvm.functions.Function3
        public /* bridge */ /* synthetic */ Modifier invoke(Modifier modifier, Composer composer, Integer num) {
            return invoke(modifier, composer, num.intValue());
        }

        public final Modifier invoke(Modifier $this$composed, Composer $composer, int $changed) {
            $composer.startReplaceGroup(-1525724089);
            ComposerKt.sourceInformation($composer, "C637@30530L39:Clickable.kt#71ulvw");
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-1525724089, $changed, -1, "androidx.compose.foundation.clickableWithIndicationIfNeeded.<anonymous> (Clickable.kt:637)");
            }
            ComposerKt.sourceInformationMarkerStart($composer, -1636999986, "CC(remember):Clickable.kt#9igjgp");
            Object it$iv = $composer.rememberedValue();
            if (it$iv == Composer.INSTANCE.getEmpty()) {
                Object value$iv = InteractionSourceKt.MutableInteractionSource();
                $composer.updateRememberedValue(value$iv);
                it$iv = value$iv;
            }
            MutableInteractionSource newInteractionSource = (MutableInteractionSource) it$iv;
            ComposerKt.sourceInformationMarkerEnd($composer);
            Modifier modifierThen2 = IndicationKt.indication(Modifier.INSTANCE, newInteractionSource, indication).then(function2.invoke(newInteractionSource, null));
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
            $composer.endReplaceGroup();
            return modifierThen2;
        }
    }

    /* JADX INFO: renamed from: isPress-ZmokQxo */
    public static final boolean m334isPressZmokQxo(KeyEvent $this$isPress) {
        return KeyEventType.m6475equalsimpl0(KeyEvent_androidKt.m6483getTypeZmokQxo($this$isPress), KeyEventType.INSTANCE.m6479getKeyDownCS__XNY()) && m333isEnterZmokQxo($this$isPress);
    }

    /* JADX INFO: renamed from: isClick-ZmokQxo */
    public static final boolean m332isClickZmokQxo(KeyEvent $this$isClick) {
        return KeyEventType.m6475equalsimpl0(KeyEvent_androidKt.m6483getTypeZmokQxo($this$isClick), KeyEventType.INSTANCE.m6480getKeyUpCS__XNY()) && m333isEnterZmokQxo($this$isClick);
    }

    /* JADX INFO: renamed from: isEnter-ZmokQxo */
    private static final boolean m333isEnterZmokQxo(KeyEvent $this$isEnter) {
        long jM6482getKeyZmokQxo = KeyEvent_androidKt.m6482getKeyZmokQxo($this$isEnter);
        if (Key.m6162equalsimpl0(jM6482getKeyZmokQxo, Key.INSTANCE.m6235getDirectionCenterEK5gGoQ()) || Key.m6162equalsimpl0(jM6482getKeyZmokQxo, Key.INSTANCE.m6249getEnterEK5gGoQ()) || Key.m6162equalsimpl0(jM6482getKeyZmokQxo, Key.INSTANCE.m6346getNumPadEnterEK5gGoQ()) || Key.m6162equalsimpl0(jM6482getKeyZmokQxo, Key.INSTANCE.m6397getSpacebarEK5gGoQ())) {
            return true;
        }
        return false;
    }

    public static final boolean hasInterestedParent(DelegatingNode $this$hasInterestedParent, final IndirectPointerInputChange event) {
        final Ref.BooleanRef hasInterestedParent = new Ref.BooleanRef();
        GestureNodeKt.traverseAncestorGestureConnections($this$hasInterestedParent, new Function1() { // from class: androidx.compose.foundation.ClickableKt$$ExternalSyntheticLambda1
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return Boolean.valueOf(ClickableKt.hasInterestedParent$lambda$0(event, hasInterestedParent, (GestureConnection) obj));
            }
        });
        return hasInterestedParent.element;
    }

    static final boolean hasInterestedParent$lambda$0(IndirectPointerInputChange $event, Ref.BooleanRef $hasInterestedParent, GestureConnection coordinator) {
        boolean isCoordinatorInterested = coordinator.isInterested($event);
        $hasInterestedParent.element = $hasInterestedParent.element || isCoordinatorInterested;
        return !$hasInterestedParent.element;
    }

    public static final boolean hasInterestedParent(DelegatingNode $this$hasInterestedParent, final PointerInputChange event) {
        final Ref.BooleanRef hasInterestedParent = new Ref.BooleanRef();
        GestureNodeKt.traverseAncestorGestureConnections($this$hasInterestedParent, new Function1() { // from class: androidx.compose.foundation.ClickableKt$$ExternalSyntheticLambda3
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return Boolean.valueOf(ClickableKt.hasInterestedParent$lambda$1(event, hasInterestedParent, (GestureConnection) obj));
            }
        });
        return hasInterestedParent.element;
    }

    static final boolean hasInterestedParent$lambda$1(PointerInputChange $event, Ref.BooleanRef $hasInterestedParent, GestureConnection coordinator) {
        boolean isCoordinatorInterested = coordinator.isInterested($event);
        $hasInterestedParent.element = $hasInterestedParent.element || isCoordinatorInterested;
        return !$hasInterestedParent.element;
    }

    public static final boolean hasScrollableContainer(TraversableNode $this$hasScrollableContainer) {
        final Ref.BooleanRef hasScrollable = new Ref.BooleanRef();
        TraversableNodeKt.traverseAncestors($this$hasScrollableContainer, ScrollableContainerNode.INSTANCE, new Function1() { // from class: androidx.compose.foundation.ClickableKt$$ExternalSyntheticLambda4
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return Boolean.valueOf(ClickableKt.hasScrollableContainer$lambda$0(hasScrollable, (TraversableNode) obj));
            }
        });
        return hasScrollable.element;
    }

    /* JADX WARN: Removed duplicated region for block: B:19:0x0017  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    static final boolean hasScrollableContainer$lambda$0(kotlin.jvm.internal.Ref.BooleanRef r2, androidx.compose.ui.node.TraversableNode r3) {
        /*
            boolean r0 = r2.element
            r1 = 1
            if (r0 != 0) goto L17
            java.lang.String r0 = "null cannot be cast to non-null type androidx.compose.foundation.gestures.ScrollableContainerNode"
            kotlin.jvm.internal.Intrinsics.checkNotNull(r3, r0)
            r0 = r3
            androidx.compose.foundation.gestures.ScrollableContainerNode r0 = (androidx.compose.foundation.gestures.ScrollableContainerNode) r0
            boolean r0 = r0.getEnabled()
            if (r0 == 0) goto L15
            goto L17
        L15:
            r0 = 0
            goto L18
        L17:
            r0 = r1
        L18:
            r2.element = r0
            boolean r0 = r2.element
            r0 = r0 ^ r1
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.foundation.ClickableKt.hasScrollableContainer$lambda$0(kotlin.jvm.internal.Ref$BooleanRef, androidx.compose.ui.node.TraversableNode):boolean");
    }

    public static final String unsupportedIndicationExceptionMessage(Indication indication) {
        return "clickable only supports IndicationNodeFactory instances provided to LocalIndication, but Indication was provided instead. Either migrate the Indication implementation to implement IndicationNodeFactory, or use the other clickable overload that takes an Indication parameter, and explicitly pass LocalIndication.current there. The Indication instance provided here was: " + indication;
    }

    public static final boolean changedToUp(IndirectPointerInputChange $this$changedToUp) {
        return ($this$changedToUp.getIsConsumed() || !$this$changedToUp.getPreviousPressed() || $this$changedToUp.getPressed()) ? false : true;
    }

    public static final boolean changedToUpIgnoreConsumed(IndirectPointerInputChange $this$changedToUpIgnoreConsumed) {
        return $this$changedToUpIgnoreConsumed.getPreviousPressed() && !$this$changedToUpIgnoreConsumed.getPressed();
    }
}
