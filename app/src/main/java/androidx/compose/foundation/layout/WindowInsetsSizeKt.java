package androidx.compose.foundation.layout;

import androidx.compose.ui.Modifier;
import androidx.compose.ui.platform.InspectableValueKt;
import androidx.compose.ui.platform.InspectorInfo;
import androidx.compose.ui.unit.Density;
import androidx.compose.ui.unit.LayoutDirection;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;

/* JADX INFO: compiled from: WindowInsetsSize.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u001e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\u001a\u0014\u0010\u0000\u001a\u00020\u0001*\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0003H\u0007\u001a\u0014\u0010\u0006\u001a\u00020\u0001*\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0003H\u0007\u001a\u0014\u0010\b\u001a\u00020\u0001*\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0003H\u0007\u001a\u0014\u0010\u000b\u001a\u00020\u0001*\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0003H\u0007\"\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000\"\u000e\u0010\u0007\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000\"\u000e\u0010\t\u001a\u00020\nX\u0082\u0004¢\u0006\u0002\n\u0000\"\u000e\u0010\f\u001a\u00020\nX\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\r"}, d2 = {"windowInsetsStartWidth", "Landroidx/compose/ui/Modifier;", "insets", "Landroidx/compose/foundation/layout/WindowInsets;", "startCalc", "Landroidx/compose/foundation/layout/WindowInsetsWidthCalculator;", "windowInsetsEndWidth", "endCalc", "windowInsetsTopHeight", "topCalc", "Landroidx/compose/foundation/layout/WindowInsetsHeightCalculator;", "windowInsetsBottomHeight", "bottomCalc", "foundation-layout"}, k = 2, mv = {2, 1, 0}, xi = 48)
public final class WindowInsetsSizeKt {
    private static final WindowInsetsWidthCalculator startCalc = new WindowInsetsWidthCalculator() { // from class: androidx.compose.foundation.layout.WindowInsetsSizeKt$$ExternalSyntheticLambda0
        @Override // androidx.compose.foundation.layout.WindowInsetsWidthCalculator
        public final int calculate(WindowInsets windowInsets, LayoutDirection layoutDirection, Density density) {
            return WindowInsetsSizeKt.startCalc$lambda$0(windowInsets, layoutDirection, density);
        }
    };
    private static final WindowInsetsWidthCalculator endCalc = new WindowInsetsWidthCalculator() { // from class: androidx.compose.foundation.layout.WindowInsetsSizeKt$$ExternalSyntheticLambda1
        @Override // androidx.compose.foundation.layout.WindowInsetsWidthCalculator
        public final int calculate(WindowInsets windowInsets, LayoutDirection layoutDirection, Density density) {
            return WindowInsetsSizeKt.endCalc$lambda$0(windowInsets, layoutDirection, density);
        }
    };
    private static final WindowInsetsHeightCalculator topCalc = new WindowInsetsHeightCalculator() { // from class: androidx.compose.foundation.layout.WindowInsetsSizeKt$$ExternalSyntheticLambda2
        @Override // androidx.compose.foundation.layout.WindowInsetsHeightCalculator
        public final int calculate(WindowInsets windowInsets, Density density) {
            return windowInsets.getTop(density);
        }
    };
    private static final WindowInsetsHeightCalculator bottomCalc = new WindowInsetsHeightCalculator() { // from class: androidx.compose.foundation.layout.WindowInsetsSizeKt$$ExternalSyntheticLambda3
        @Override // androidx.compose.foundation.layout.WindowInsetsHeightCalculator
        public final int calculate(WindowInsets windowInsets, Density density) {
            return windowInsets.getBottom(density);
        }
    };

    public static final Modifier windowInsetsStartWidth(Modifier $this$windowInsetsStartWidth, final WindowInsets insets) {
        return $this$windowInsetsStartWidth.then(new DerivedWidthModifierElement(insets, InspectableValueKt.isDebugInspectorInfoEnabled() ? new Function1<InspectorInfo, Unit>() { // from class: androidx.compose.foundation.layout.WindowInsetsSizeKt$windowInsetsStartWidth$$inlined$debugInspectorInfo$1
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
                inspectorInfo.setName("insetsStartWidth");
                inspectorInfo.getProperties().set("insets", insets);
            }
        } : InspectableValueKt.getNoInspectorInfo(), startCalc));
    }

    static final int startCalc$lambda$0(WindowInsets insets, LayoutDirection layoutDirection, Density density) {
        if (layoutDirection == LayoutDirection.Ltr) {
            return insets.getLeft(density, layoutDirection);
        }
        return insets.getRight(density, layoutDirection);
    }

    public static final Modifier windowInsetsEndWidth(Modifier $this$windowInsetsEndWidth, final WindowInsets insets) {
        return $this$windowInsetsEndWidth.then(new DerivedWidthModifierElement(insets, InspectableValueKt.isDebugInspectorInfoEnabled() ? new Function1<InspectorInfo, Unit>() { // from class: androidx.compose.foundation.layout.WindowInsetsSizeKt$windowInsetsEndWidth$$inlined$debugInspectorInfo$1
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
                inspectorInfo.setName("insetsEndWidth");
                inspectorInfo.getProperties().set("insets", insets);
            }
        } : InspectableValueKt.getNoInspectorInfo(), endCalc));
    }

    static final int endCalc$lambda$0(WindowInsets insets, LayoutDirection layoutDirection, Density density) {
        if (layoutDirection == LayoutDirection.Rtl) {
            return insets.getLeft(density, layoutDirection);
        }
        return insets.getRight(density, layoutDirection);
    }

    public static final Modifier windowInsetsTopHeight(Modifier $this$windowInsetsTopHeight, final WindowInsets insets) {
        return $this$windowInsetsTopHeight.then(new DerivedHeightModifierElement(insets, InspectableValueKt.isDebugInspectorInfoEnabled() ? new Function1<InspectorInfo, Unit>() { // from class: androidx.compose.foundation.layout.WindowInsetsSizeKt$windowInsetsTopHeight$$inlined$debugInspectorInfo$1
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
                inspectorInfo.setName("insetsTopHeight");
                inspectorInfo.getProperties().set("insets", insets);
            }
        } : InspectableValueKt.getNoInspectorInfo(), topCalc));
    }

    public static final Modifier windowInsetsBottomHeight(Modifier $this$windowInsetsBottomHeight, final WindowInsets insets) {
        return $this$windowInsetsBottomHeight.then(new DerivedHeightModifierElement(insets, InspectableValueKt.isDebugInspectorInfoEnabled() ? new Function1<InspectorInfo, Unit>() { // from class: androidx.compose.foundation.layout.WindowInsetsSizeKt$windowInsetsBottomHeight$$inlined$debugInspectorInfo$1
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
                inspectorInfo.setName("insetsBottomHeight");
                inspectorInfo.getProperties().set("insets", insets);
            }
        } : InspectableValueKt.getNoInspectorInfo(), bottomCalc));
    }
}
