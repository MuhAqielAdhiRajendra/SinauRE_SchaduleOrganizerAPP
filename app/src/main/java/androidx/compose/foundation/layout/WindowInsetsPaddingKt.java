package androidx.compose.foundation.layout;

import androidx.autofill.HintConstants;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.platform.InspectableValueKt;
import androidx.compose.ui.platform.InspectorInfo;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;

/* JADX INFO: compiled from: WindowInsetsPadding.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u00002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\u001a\u0014\u0010\u0000\u001a\u00020\u0001*\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0003H\u0007\u001a\u0014\u0010\u0004\u001a\u00020\u0001*\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0003H\u0007\u001a\u0014\u0010\u0004\u001a\u00020\u0001*\u00020\u00012\u0006\u0010\u0005\u001a\u00020\u0006H\u0007\u001a/\u0010\u0007\u001a\u00020\u0001*\u00020\u00012!\u0010\b\u001a\u001d\u0012\u0013\u0012\u00110\u0003¢\u0006\f\b\n\u0012\b\b\u000b\u0012\u0004\b\b(\f\u0012\u0004\u0012\u00020\r0\tH\u0007\u001a\n\u0010\u000e\u001a\u00020\u0001*\u00020\u0001\"\u000e\u0010\u000f\u001a\u00020\u0010X\u0080T¢\u0006\u0002\n\u0000¨\u0006\u0011"}, d2 = {"windowInsetsPadding", "Landroidx/compose/ui/Modifier;", "insets", "Landroidx/compose/foundation/layout/WindowInsets;", "consumeWindowInsets", "paddingValues", "Landroidx/compose/foundation/layout/PaddingValues;", "onConsumedWindowInsetsChanged", "block", "Lkotlin/Function1;", "Lkotlin/ParameterName;", HintConstants.AUTOFILL_HINT_NAME, "consumedWindowInsets", "", "recalculateWindowInsets", "InsetsConsumingModifierNodeKey", "", "foundation-layout"}, k = 2, mv = {2, 1, 0}, xi = 48)
public final class WindowInsetsPaddingKt {
    public static final String InsetsConsumingModifierNodeKey = "androidx.compose.foundation.layout.ConsumedInsetsProvider";

    public static final Modifier windowInsetsPadding(Modifier $this$windowInsetsPadding, final WindowInsets insets) {
        return $this$windowInsetsPadding.then(new InsetsPaddingModifierElement(insets, InspectableValueKt.isDebugInspectorInfoEnabled() ? new Function1<InspectorInfo, Unit>() { // from class: androidx.compose.foundation.layout.WindowInsetsPaddingKt$windowInsetsPadding$$inlined$debugInspectorInfo$1
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
                inspectorInfo.setName("windowInsetsPadding");
                inspectorInfo.getProperties().set("insets", insets);
            }
        } : InspectableValueKt.getNoInspectorInfo()));
    }

    public static final Modifier consumeWindowInsets(Modifier $this$consumeWindowInsets, final WindowInsets insets) {
        return $this$consumeWindowInsets.then(new UnionInsetsConsumingModifierElement(insets, InspectableValueKt.isDebugInspectorInfoEnabled() ? new Function1<InspectorInfo, Unit>() { // from class: androidx.compose.foundation.layout.WindowInsetsPaddingKt$consumeWindowInsets$$inlined$debugInspectorInfo$1
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
                inspectorInfo.setName("consumeWindowInsets");
                inspectorInfo.getProperties().set("insets", insets);
            }
        } : InspectableValueKt.getNoInspectorInfo()));
    }

    public static final Modifier consumeWindowInsets(Modifier $this$consumeWindowInsets, final PaddingValues paddingValues) {
        return $this$consumeWindowInsets.then(new PaddingValuesConsumingModifierElement(paddingValues, InspectableValueKt.isDebugInspectorInfoEnabled() ? new Function1<InspectorInfo, Unit>() { // from class: androidx.compose.foundation.layout.WindowInsetsPaddingKt$consumeWindowInsets$$inlined$debugInspectorInfo$2
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
                inspectorInfo.setName("consumeWindowInsets");
                inspectorInfo.getProperties().set("paddingValues", paddingValues);
            }
        } : InspectableValueKt.getNoInspectorInfo()));
    }

    public static final Modifier onConsumedWindowInsetsChanged(Modifier $this$onConsumedWindowInsetsChanged, final Function1<? super WindowInsets, Unit> function1) {
        return $this$onConsumedWindowInsetsChanged.then(new ConsumedInsetsModifierElement(function1, InspectableValueKt.isDebugInspectorInfoEnabled() ? new Function1<InspectorInfo, Unit>() { // from class: androidx.compose.foundation.layout.WindowInsetsPaddingKt$onConsumedWindowInsetsChanged$$inlined$debugInspectorInfo$1
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
                inspectorInfo.setName("onConsumedWindowInsetsChanged");
                inspectorInfo.getProperties().set("block", function1);
            }
        } : InspectableValueKt.getNoInspectorInfo()));
    }

    public static final Modifier recalculateWindowInsets(Modifier $this$recalculateWindowInsets) {
        return $this$recalculateWindowInsets.then(RecalculateWindowInsetsModifierElement.INSTANCE);
    }
}
